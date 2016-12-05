% Test de la fonction simplexe sur quelques exemples
% Exemple du brasseur
clear all
%
brasseur;
disp('brasseur');
[x,F] = simplexe(OPT,c,A,b)
%
disp('**********');
%
concurrent;
disp('concurrent');
[x,F] = simplexe(OPT,c,A,b)
%
disp('**********');
%
Gass;
disp('Gass');
[x,F] = simplexe(OPT,c,A,b)
%
disp('**********');
%
exemple1;
disp('exemple 1');
[x,F] = simplexe(OPT,c,A,b)
%
disp('**********');
%
exemple2;
disp('exemple 2');
[x,F] = simplexe(OPT,c,A,b)
%
disp('**********');
%
exemple3;
disp('exemple 3');
[x,F] = simplexe(OPT,c,A,b)
%
disp('**********');
%
exemple4;
disp('exemple 4');
[x,F] = simplexe(OPT,c,A,b)%
disp('**********');
%
exemple5;
disp('exemple 5');
[x,F] = simplexe(OPT,c,A,b)