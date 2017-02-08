clear all;
close all;

%% Tests de newton
disp('### Tests de newton.m ###');

% Sur la fonction f1
disp('# Test sur f1 #');
syms x1 x2 x3;
disp(f1(x1, x2, x3));
x011 = [1; 0; 0];
x012 = [10; 3; -2.2];

[t, resultat_f1] = newton(@(x1, x2, x3) f1(x1, x2, x3), x012)
f1(resultat_f1(1), resultat_f1(2), resultat_f1(3))


% Sur la fonction f2
disp('# Test sur f2 #');
syms x1 x2;
disp(f2(x1, x2));
x021 = [-1.2; 1];
x022 = [10; 0];
x023 = [0; 1/200 + 1e-12];
[t, resultat_f2] = newton(@(x1, x2) f2(x1, x2), x022)
f2(resultat_f2(1), resultat_f2(2))