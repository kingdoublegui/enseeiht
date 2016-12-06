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

delta_max = 1e-10;
gamma1 = 1/2;
gamma2 = 2;
eta1 = 1/4;
eta2 = 3/4;
delta = 0.5;

% Quadratique 1
g1 = [0; 0];
H1 = [7 0; 0 2];
% Je ne teste plus e cas car laisse des warnings inutiles dans le terminal
%pasCauchy(g1, H1, delta);

% Quadratique 2
g2 = [6; 2];
H2 = [7 0; 0 2];
pasCauchy(g2, H2, delta)

% Quadratique 2
g3 = [-2; 1];
H3 = [-2 0; 0 10];
pasCauchy(g3, H3, delta)

%% Tests de regions de confiance
% Sur la fonction f1
x011 = [1; 0; 0];
x012 = [10; 3; -2.2];

[t, resultat_f1] = regionConfiance(@(x1, x2, x3) f1(x1, x2, x3), x011, delta, delta_max, gamma1, gamma2, eta1, eta2)

% Sur la fonction f2
x021 = [-1.2; 1];
x022 = [10; 0];
x023 = [0; 1/200 + 1e-12];
%[t, resultat_f2] = regionConfiance(@(x1, x2) f2(x1, x2), x022, delta, delta_max, gamma1, gamma2, eta1, eta2)