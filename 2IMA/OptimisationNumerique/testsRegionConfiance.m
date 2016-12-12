%% Tests de regions de confiance

delta_max = 0.3;
gamma1 = 1/2;
gamma2 = 2;
eta1 = 1/4;
eta2 = 3/4;
delta = 0.5;

% Sur la fonction f1
x011 = [1; 0; 0];
x012 = [10; 3; -2.2];

[t, resultat_f1] = regionConfiance(@(x1, x2, x3) f1(x1, x2, x3), x011, delta, delta_max, gamma1, gamma2, eta1, eta2)

% Sur la fonction f2
x021 = [-1.2; 1];
x022 = [10; 0];
x023 = [0; 1/200 + 1e-12];
%[t, resultat_f2] = regionConfiance(@(x1, x2) f2(x1, x2), x022, delta, delta_max, gamma1, gamma2, eta1, eta2)