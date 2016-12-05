clear all;
close all;

% Un script matlab pour tester les fonctions

%% Tests de newton
% Sur la fonction f1
x011 = [1; 0; 0];
x012 = [10; 3; -2.2];

[t, resultat_f1] = newton(@(x1, x2, x3) f1(x1, x2, x3), x011)

% Sur la fonction f2
x021 = [-1.2; 1];
x022 = [10; 0];
x023 = [0; 1/200 + 1e-12];
[t, resultat_f2] = newton(@(x1, x2) f2(x1, x2), x022)

%% Tests de pasCauchy
% La quadratique est de la forme q(s) = s.'*grad(x) + 1/2*s.'*hess(x)*s;

delta_max = 10*max(x011);
gamma1 = 1/2;
gamma2 = 2;
eta1 = 1/4;
eta2 = 3/4;

% Quadratique 1
g1 = [0; 0];
H1 = [7 0; 0 2];
pasCauchy(g1, H1, delta);

% Quadratique 2
g2 = [6; 2];
H2 = [7 0; 0 2];
pasCauchy(g2, H2, delta);

% Quadratique 2
g3 = [-2; 1];
H3 = [-2 0; 0 10];
pasCauchy(g3, H3, delta);