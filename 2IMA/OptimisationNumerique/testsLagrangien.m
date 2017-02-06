clear all;
close all;

%% Tests du lagrangien
disp('### Tests de lagrangien.m ###');
w = warning ('off','all');
mu0 = 0.6;
tau = 0.6;
etac0 = 0.1258925;
alpha = 0.1;
beta = 0.9;

% Sur la fonction f
disp('# Test sur f1 #');
syms x1 x2 x3;
disp(f1(x1, x2, x3));
xc11 = [0; 1; 1];
lambdac11 = 0.6;
xc12 = [0.5; 1.25; 1];
lambdac12 = 0.6;

[x1, lambda1, mu1] = lagrangien(@(x1, x2, x3) f1(x1, x2, x3), xc11, lambdac11, ...
    @(x1, x2, x3) c1(x1, x2, x3), mu0, tau, etac0, alpha, beta)

% Sur la fonction f2
xc21 = [1; 0];
xc22 = [sqrt(3)/2; sqrt(3)/2];
%[x2, lambda2, mu2] = lagrangien(@(x1, x2, x3) f2(x1, x2, x3), xc21, lambdac11, ...
%    @(x1, x2, x3) c2(x1, x2, x3), mu0, tau, etac0, alpha, beta)