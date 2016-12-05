%% SIMULATEUR PLATINE
%
% P(i).s : P(i).s(t) est égal à la longueur du chemin parcouru par l'bjet i
% au bout d'une durée t (t est entier positif)
% P(i).pp : P(i).pp(t) est égal à l'index permettant d'accéder à la
% position du centre de l'objet i à l'instant t :
% (x,y) = (xy(1,round(P(i).pp(t)),i),xy(2,round(P(i).pp(t)),i))
%
load scene1
%
xy = zeros(3,subd,nombre_objets); % xy(1:2,:,i) : echantillons du chemin i
xy2 = zeros(2,subd);
s1 = zeros(1,nombre_objets); % s1(i) : longueur du chemin i
%
pasTemps=0.4;
%
hdepl = figure;
hdepl.Name = 'Simulation PLATINE';
hdepl.Units = 'centimeters';
imagesc(imscene);
hold on
%
%% Echantillonnage des chemins, calcul de leurs longueurs et tracés de ces chemins
% attention: on peut parcourir le même chemin plusieurs fois
couleur = char('red' , 'blue','cyan','magenta', 'yellow', 'black' , 'white', 'green');
for i = 1:nombre_objets
    if strcmp(T(i).chemin,'DROITE')
        % Le chemin est une droite
        idep = cell2mat(T(i).depart(1));
        jdep = cell2mat(T(i).depart(2));
        iarr = cell2mat(T(i).arrivee(1));
        jarr = cell2mat(T(i).arrivee(2));
        %
        for p=1:subd
            mu = (p-1)/subd;
            xy2(2,p) = idep + mu*(iarr-idep);
            xy2(1,p) = jdep + mu*(jarr-jdep);
        end
        xy(1:2,:,i) = xy2;
        plot(xy2(1,:),xy2(2,:),couleur(i,:));
    else
        if strcmp(T(i).chemin,'NURBS')
            % Le chemin est une NURBS
            nurbsf = T(i).nurbs;
            xy(:,:,i) = nrbeval(nurbsf,linspace(0.0,1.0,subd));
            xy2 = xy(1:2,:,i);
            nrbplot(nurbsf,subd,couleur(i,:));
        else
            disp('ERREUR');
        end       
    end
    % arclength: compute arc length of any curve represented as a sequence of points
    s1(i) =arclength(xy(1,:,i),xy(2,:,i)); % calcul longueur chemin i
end
%% Tracés des positions de départ et d'arrivée des objets
%
% Visualiser le robot a son point de depart
%
th = 0:3.14/50:2*3.14; % angles pour tracer un cercle
lth = length(th);
figure(hdepl);
%
for i = 1:nombre_objets
    % Visualisation position de depart de l'objet i
    idep = cell2mat(T(i).depart(1));
    jdep = cell2mat(T(i).depart(2));
    diametre_robot = T(i).diametre_robot;
    %
    xunit = double(idep) + diametre_robot * cos(th);
    yunit = double(jdep) + diametre_robot * sin(th);
    hold on;
    depart=plot(yunit, xunit,couleur(i,:)); % image -> figure  (x,y) -> (y,x)
    %
    %  Visualisation position d'arrivee de l'objet i
    iarr = cell2mat(T(i).arrivee(1));
    jarr = cell2mat(T(i).arrivee(2));
    %
    xunit = double(iarr) + diametre_robot * cos(th);
    yunit = double(jarr) + diametre_robot * sin(th);
    hold on;
    arrivee=plot(yunit, xunit, couleur(i,:)); % image -> figure  (x,y) -> (y,x)
end
%
%% Calcul des trajectoires (chemin + cinématique)
%
rrob = zeros(1,nombre_objets);
for k = 1:nombre_objets
    rrob(k) = T(k).diametre_robot/2;
end
% Reindexation des chemins selon les abscisses curvilignes s(t)
p=1:subd;
%
tempsparcours = zeros(1,nombre_objets);
ii = zeros(1,nombre_objets);
%
for i=1:nombre_objets
    v = T(i).vitesse; % vitesse de l'objet i égal à v en unités pixels 
    % La cinématique est donnée par P(i).s et par P(i).pp
    % 1. Calcul de P(i).s
    s=0:v:s1(i); % s = 0 v 2v 3v ... s1(i) donc delta(s) = v donc vitesse constante v
    xy2 = xy(1:2,:,i);
    pp0 = pdearcl(p,xy2,s,0,s1(i)); % reindexation du chemin i selon le vecteur d'abscisse curviligne s
    %
    nbrerepetition = T(i).nbre_repetition;
    nbrefois = nbrerepetition - 1;
    %
    s = 0:v:nbrerepetition*s1(i);
    P(i).s = s;
    tempsparcours(i) = length(s); % temps du parcours de l'objet i (si pas de collision)
    %
    % 2. Calcul de P(i).pp
    pp1 = pp0;
    for k=1:nbrefois
        pp00 = pp1(end:-1:1);
        pp0 = [pp0 pp00];
        pp1 = pp00;
    end
    P(i).pp = pp0; % sauvegarde de pp0 dans structure P
end
%% SIMULATION des deplacements des objets
Temps_reparation = T(1).temps_repar; % Temps de raparation de l'objet 1
portee = T(1).portee; % horizon du robot 1
Nombre_reparations = 0;
arretable = true; % l'objet 1 est arretable
%
maxtempsparcours = max(tempsparcours);
%
t = 1;
while t <= maxtempsparcours % Boucle temporelle avec maxtempsparcours variable à cause des collisions éventuelles
    %
    xunit = [];
    yunit = [];
    if exist('robot','var') == 1
        delete(robot);
    end
    for k = 1:nombre_objets
        if t < tempsparcours(k)
            ii(k) = round(P(k).pp(t)); %% ii(k) est l'index donnant accès à la position du centre de l'objet k à l'instant t
            xcentre = xy(1,ii(k),k);
            ycentre = xy(2,ii(k),k);
        else % t >= tempsparcours(k)
            xcentre = cell2mat(T(k).arrivee(2));
            ycentre = cell2mat(T(k).arrivee(1));
        end
        xunit0 = xcentre + rrob(k) * cos(th);
        yunit0 = ycentre + rrob(k) * sin(th);
        xunit = [xunit xunit0];
        yunit = [yunit yunit0];
    end
    switch nombre_objets
        case 2
            x1 = xunit(1:lth);
            y1 = yunit(1:lth);
            x2 = xunit(lth+1:end);
            y2 = yunit(lth+1:end);
            robot=plot(x1,y1,couleur(1,:),x2,y2,couleur(2,:));
        case 3
            x1 = xunit(1:lth);
            y1 = yunit(1:lth);
            x2 = xunit(lth+1:2*lth);
            y2 = yunit(lth+1:2*lth);
            x3 = xunit(2*lth+1:end);
            y3 = yunit(2*lth+1:end);
            robot=plot(x1,y1,couleur(1,:),x2,y2,couleur(2,:),x3,y3,couleur(3,:));
        otherwise
            disp('ERREUR1');
            return
    end
    drawnow;
    pause(pasTemps);
    %
    % Test de détection d'obstacles de l'objet 1 avec les autres objets
    % kobj
    % On ne considère ici que les collisions de l'objet 1 avec les autres
    % objets
    %
    for kobj = 2:nombre_objets
        % kobj est le numero de l'objet susceptible de rentrer en collision avec l'objet 1
        distance = norm(xy(1:2,ii(kobj),kobj)-xy(1:2,ii(1),1)); % distance du robot (objet 1) à l'objet kobj
        sumray = rrob(1)+rrob(kobj);
        if distance < sumray && arretable
            % collision de l'objet 1 avec l'objet kobj à l'instant t!!
            % arrêtable est vrai si l'objet 1 est arrêtable
            kcol = kobj; % on sauvegarde le numero de l'objet kobj rentré en collision avec l'objet 1
            load gong.mat
            sound(y)
            cprintf('red',['collision avec objet ',num2str(kobj), ' !!']);
            disp(' ');
            % Temps écoulé
            cprintf('blue',['Temps écoulé: ',num2str(t),' secondes']);
            disp(' ');
            % Calcul des longueurs parcourues par chaque objet i au moment
            % de la collision
            for i = 1:nombre_objets
                longueur = P(i).s(t);
                cprintf([0,0,1],['Longueur parcourue par objet ',num2str(i),' : ', num2str(longueur),' pixels']);
                disp(' ');
            end
            pause(1);
            % Il y a eu collision de l'objet 1 avec l'objet kcol,
            % on répare l'objet 1
            Nombre_reparations = Nombre_reparations + 1;
            cprintf([0,1,0],['Temps de reparation: ',num2str(Temps_reparation),' ']);
            disp(' ');
            arretable = false; % l'objet 1 devient inarretable
            %
            % On recalcule les trajectoires des objets 1 et kcol entrés en collision,
            % les chemins restant les mêmes; les autres trajectoires
            % restent inchangées
            %
            num = [1 kcol];
            for i = num(1):num(end)
                % 1. MAJ de P(i).s
                savantcollision = P(i).s(1:t);
                ss = P(i).s(t) * ones(1,Temps_reparation);
                saprescollision = P(i).s(t+1:end);
                s = [savantcollision , ss , saprescollision];
                P(i).s = s;
                % 2. MAJ de P(i).pp
                pavantcollision = P(i).pp(1:t);
                ppp = P(i).pp(t) * ones(1,Temps_reparation);
                paprescollision = P(i).pp(t+1:end);
                P(i).pp= [pavantcollision , ppp , paprescollision];
                % 3. MAJ de tempsparcours(i)
                tempsparcours(i) = tempsparcours(i) + Temps_reparation;
            end
            maxtempsparcours = max(tempsparcours); % reactualisation du temps maximal de parcours
        else %pas de collision
            if distance <= portee
                % l'obstacle kobj est detecte
                cprintf([1,0.5,0],['objet ',num2str(kobj),' détecté']);
                disp(' ');
            end
        end
    end
    %
    t = t + 1;
end
%% Durée écoulée
tf = t;
cprintf('blue',['Durée de la mission: ',num2str(tf),' secondes']);
disp(' ');