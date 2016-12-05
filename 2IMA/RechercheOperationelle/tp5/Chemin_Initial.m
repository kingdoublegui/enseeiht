%--------------------------------------------------------------------------
%------------------------Planification offline-----------------------------
%--------------------------------------------------------------------------
% Recherche du plus court chemin valide (verifiant les contraintes du probleme)

close all;
clear all;
clc;
reprise = true;
%% Creation de la carte numerique
if reprise
    load imscene
    im = imscene;
    nlig = size(imscene,1);
    ncol = size(imscene,2);
    hscene = figure('BackingStore','on');
    colormap(jet);
    imagesc(im);
else
    
    CreateScene; % creation de la scene 'im'
    imscene = im; % sauvegarde de l'image de la scene
end
%% Lecture des positions de depart et d'arrivee

disp ('*** Pointez le point de depart du robot ! ***');% Lecture point de depart du robot
p = ginput(1);
idep = round(p(2));
jdep = round(p(1));

disp ('*** Pointez le point d''arrivee du robot ! ***');% Lecture point d'arrivee du robot
p = ginput(1);
iarr = round(p(2));
jarr = round(p(1));

%% Lecture des parametres du robot

diametre_robot = input('diametre du robot en nombre de pixels:','s'); % Lecture du diametre du robot en pixels
diametre_robot = str2double(diametre_robot);
r = round(diametre_robot/2); %rayon du robot

rho_min = input('Rayon de courbure minimum:','s'); % Lecture du diametre du robot en pixels
rho_min = str2double(rho_min);
global cseuil;
cseuil=1/rho_min; %contrainte de courbure 

%% Squelettisation de l'espace libre des obstacles +  Raccordement des positions de depart et d'arrive 

disp('Squelettisation en cours...');
Squelettisation;

%% Analyse du squelette etendu et conversion du squelette en un graphe

ModelisationGraphe;

%% Recherche du plus court chemin entre A et B dans G'

PlusCourtChemin;
figure('Name','Plus court chemin entre A et B dans le graphe'); %Affichage du chemin trouve
imagesc(im);

%% Lissage du chemin calcule par une courbe NURBS

% Ordre de la courbe ; deg=order-1                    
order=4; 

% Calcul des points de controle (coefs)
coefs=zeros(2,niter); % tableau contenant les coordonnees cartesiennes des sommets
                                   %niter : nombre des sommets du chemin calcule (= size(chemin,2))

for k=1:niter
    numsom = chemin(k);
    iint = double(icar(numsom));
    jint = double(jcar(numsom));
    coefs(:,k)=[jint,iint];
end

% Parameterisation du vecteur nodal
knots=nrbKnots(niter,order); % Calcul du vecteur nodal
% Nombre des points aa evaluer 
subdiv1=100;   
% Les ui
ti = linspace(0.0,1.0,subdiv1);   
%--------------------------------------------------------------------------
% utilisation d'un algorithme genetique pour la parameterisation des poids des points de controle
%---------Parametres de l'AG-----------------------------------------------
global max_weight;  
max_weight=10;% valeur max des poids
PopulationSize=100; % taille de la population
p_mut=1;% Probabilite de mutation
modif_rate = 5;% pourcentage de croisement
nb_iter=7; %nombre d'iterations 
%--------Execution de l'AG----------------------------------------------
tic;
disp('GAforNURBScurveFitting en cours d execution...');
[Eval,W_Elu,indice_elu,sol,weightsInit,weights]= GAforNURBScurveFitting(im,coefs,knots,subdiv1,PopulationSize,max_weight,cseuil,r,modif_rate, nb_iter,p_mut);
toc;
%% Analyse des Resultas

%W_Elu = solution retenue (vecteur poids)
coefs1 = zeros(4,niter); % Passage en coordonnees homogenes
coefs1(1,:) = coefs(1,:) .* W_Elu(1,:);
coefs1(2,:) = coefs(2,:) .* W_Elu(1,:);
coefs1(4,:) = W_Elu(:,:); 

nurbsf = nrbmak2(coefs1,knots);   % Construction de la structure NURBS

nbPrbCur=Eval(indice_elu).nb_CC; % nombre de points de la courbe qui ne respectent pas la contrainte de courbure
nbPrbCol=Eval(indice_elu).nb_CO; % nombre de points de la courbe qui ne respectent pas la contrainte de la non-collision

figure;
colormap(jet);
im = imscene;
imagesc(im);hold on;

colormap(jet);
plot(coefs(1,:),coefs(2,:),'.r');%% les sommets de la courbe aa approximer (formant le polygone de controle)
hold on

colormap(jet);
[p1f,nurbs_corf] = nrbplot(nurbsf,subdiv1,'m');%% Affichage de la courbe NURBS
title('Solution Offline retenue')
hold on;
colormap(jet);
saveas(gcf,'trajectoiref.tif');

if sol  % si la solution est admissible
    [nurbsFinal] = TransformationImageRepere(nurbsf);
    [courburenurbsFinal, courbure_moynurbsFinal]= CalculCourbure(nurbsFinal,subdiv1);
    figure;
    plot(ti,courburenurbsFinal(1,:),'r');xlabel('ui');ylabel('curvature');title('Courbure de la trajectoire');hold on;
else
    % localisation des positions non acceptables si pas de solution
    trackRobot(Eval,nbPrbCur,nbPrbCol,indice_elu,sol,ti,cseuil,subdiv1,im,r);
    
    [nurbsFinal] = TransformationImageRepere(nurbsf);
    [courburenurbsFinal, courbure_moynurbsFinal]= CalculCourbure(nurbsFinal,subdiv1);
    figure;
    plot(ti,courburenurbsFinal(1,:),'r');xlabel('ui');ylabel('curvature');title('Courbure de la trajectoire');hold on;
    % Localisation des points critiques courbure
    [vect_CC]=DetectionCC(ti,courburenurbsFinal,cseuil,nbPrbCur);
end;

 %%
 %%%%%%%%Affichage des parametre input%%%%%%%%%%%%%%%%%%%
 disp('******************Input parameters**********************');
 disp(['Space size : N=',num2str(nlig),' M= ',num2str(ncol)]);          
 disp(['Start position : xi=',num2str(jdep),' yi= ',num2str(idep)]);
 disp(['Target position : xf=',num2str(jarr),' yf= ',num2str(iarr)]);
 disp(['Robot diameter : d=',num2str(diametre_robot)]);
 disp(['Minimum radius of curvature : rho_min=',num2str(rho_min)]);

 disp('********************************************************');

