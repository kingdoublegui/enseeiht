%--------------------------------------------------------------------------
%------------------------Planification offline-----------------------------
%--------------------------------------------------------------------------
% Recherche du plus court chemin valide (verifiant les contraintes du probleme)
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
rho_min = Inf; % Rayon de courbure infini