% Chargement des paramètres du pb du brasseur modifié
% On ajoute la contrainte x2 - x1 <= 0
%
% Maximisation OPT = +1
OPT = 1;
% Calcul de c
%
c = [65;115;0;0;0;0];
% Calcul de A
%
A = [2.5,7.5,1,0,0,0;
     0.125,0.125,0,1,0,0;
     17.5,10,0,0,1,0;
     -1 1 0 0 0 1];
% Calcul de b
%
b = [240;5;595;0];