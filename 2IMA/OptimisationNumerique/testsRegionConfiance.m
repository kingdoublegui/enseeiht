%% Tests de regions de confiance
disp('### Tests de regionConfiance.m ###');

gamma1 = 1/2;
gamma2 = 2;
eta1 = 1/4;
eta2 = 3/4;
delta = 2;

% Sur la fonction f1
disp('# Test sur f1 #');
syms x1 x2 x3;
fprintf('f1(x1, x2, x3) = '); disp(f1(x1, x2, x3));

x011 = [1; 0; 0];
x012 = [10; 3; -2.2];

fprintf('# Vecteur : '); disp(x011');
[t, resultat] = regionConfiance(@(x1, x2, x3) f1(x1, x2, x3), x011, delta, gamma1, gamma2, eta1, eta2);
fprintf('- Resultat : '); disp(resultat');
fprintf('- Nombre d iterations : '); disp(t);

fprintf('# Vecteur : '); disp(x012');
[t, resultat] = regionConfiance(@(x1, x2, x3) f1(x1, x2, x3), x012, delta, gamma1, gamma2, eta1, eta2);
fprintf('- Resultat : '); disp(resultat');
fprintf('- Nombre d iterations : '); disp(t);

% Sur la fonction f2
disp('# Test sur f2 #');
syms x1 x2;
disp(f2(x1, x2));

x021 = [-1.2; 1];
x022 = [10; 0];
x023 = [0; 1/200 + 1e-12];

% fprintf('# Vecteur : '); disp(x021');
% [t, resultat] = regionConfiance(@(x1, x2) f2(x1, x2), x021, delta, gamma1, gamma2, eta1, eta2);
% fprintf('- Resultat : '); disp(resultat');
% fprintf('- Nombre d iterations : '); disp(t);

fprintf('# Vecteur : '); disp(x022');
[t, resultat] = regionConfiance(@(x1, x2) f2(x1, x2), x022, delta, gamma1, gamma2, eta1, eta2);
fprintf('- Resultat : '); disp(resultat');
fprintf('- Nombre d iterations : '); disp(t);

fprintf('# Vecteur : '); disp(x023');
[t, resultat] = regionConfiance(@(x1, x2) f2(x1, x2), x023, delta, gamma1, gamma2, eta1, eta2);
fprintf('- Resultat : '); disp(resultat');
fprintf('- Nombre d iterations : '); disp(t);