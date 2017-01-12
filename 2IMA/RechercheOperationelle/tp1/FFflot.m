%% Algorithme de *Ford-Fulkerson* pour les flots
%
% Application à la recherche du plus court chemin du sommet 1 (b) au sommet 2 (a)
% dans un graphe (X,U) modélisé comme un problème de flot maximal
%
% Representation du graphe
%
% n == nombre de sommets
%
% m == nombre d'arcs
%
% Vecteur du nombre de successeurs de chaque sommet : NSUC
%
% Vecteur des successeurs de chaque sommet : SUC
%
%% Initialisations
NSUC = uint16([4 2 5 3 3 2 1 3 1 0]);
%
% Vecteur des successeurs de chaque sommet : SUC
SUC = uint16([1 2 3 4 6 7 2 4 6 7 8 7 8 9 4 8 9 7 10 10 7 9 10 10]);
%
% Vecteur des longueurs de chaque arc
LONG =     [10 20 10 10 8 4 3 5 5 7 3 6 1 7 4 5 2 2 10 10 3 5 20 10];

[flot_max, courant]=ff_max_flow(NSUC, SUC, LONG);
disp(['Flot maximal : ', num2str(flot_max)]);
disp('Flot : ');;
disp(courant);