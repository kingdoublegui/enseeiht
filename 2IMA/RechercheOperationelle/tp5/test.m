%--------------------------------------------------------------------------
%------------------------Planification offline-----------------------------
%--------------------------------------------------------------------------
%%
% Recherche des trajectoires initiales du robot et des obstacles mobiles
% sous forme de courbes NURBS parametrees par le temps
% 
% Le robot est un objet ACTIF
% L'obstacle est un objet PASSIF
%
close all;
clear all;
clc;

%% Creation de la carte numerique

CreateScene; % creation de la scene 'im'
imscene = im; % sauvegarde de l'image de la scene
%
squel; % premiere squelettisation
figure('Name','Squelette initial'); % Affichage du squelette;
colormap(gray);
imagesc(im);
%
nbre_obst = input('nombre d''obstacles mobiles: ','s');
nbre_obst = str2double(nbre_obst);
ntotobj = nbre_obst + 1; %ntotobj = nombre total d'objets en mouvement :robot + obstacles mobiles)
%
idep = zeros(ntotobj,1);
jdep = zeros(ntotobj,1);
iarr = zeros(ntotobj,1);
jarr = zeros(ntotobj,1);
%
figure(hscene);
for ind_obst = 0:nbre_obst
    if ind_obst == 0
        nom_objet = 'robot'
    else
        nom_objet = ['obstacle ',num2str(ind_obst)]
    end
%% Lecture des positions de depart et d'arrivee de nom_objet

disp (['*** Pointez le point de depart de ',nom_objet,'! ***']);% Lecture point de depart de nom_objet
p = ginput(1);
idep = round(p(2));
jdep = round(p(1));

disp (['*** Pointez le point d''arrivee de ',nom_objet, '! ***']);% Lecture point d'arrivee de nom_objet
p = ginput(1);
iarr = round(p(2));
jarr = round(p(1)); 
end    