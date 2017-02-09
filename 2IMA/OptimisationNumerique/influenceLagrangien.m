clear all;
close all;

%% Tests de l'influence de lambda et mu
disp('### Tests de l influence de lambda et mu ###');
w = warning ('off','all');

% tau = 1.5;
etac0 = 0.1258925;
alpha = 0.1;
beta = 0.9;
mu = 2;
lambda = 2;

xc11 = [0; 1; 1];

% mu = linspace(0.1, 2, 10);
% lambda = linspace(0.1, 2, 10);
% tvalue = zeros(length(mu), length(lambda));
% 
% for i = 1:length(mu)
%     for j = 1:length(lambda)
%         [t, x1, lambda1, mu1] = lagrangien(@f1, xc11, lambda(j), @c1, mu(i), tau, etac0, alpha, beta);
%         tvalue(i, j) = t
%     end
% end

%tvalue

tau = linspace(0.1, 2, 10);
tvalue = zeros(1, length(tau));
for i = 1:length(tau)
    [t, x1, lambda1, mu1] = lagrangien(@f1, xc11, lambda, @c1, mu, tau(i), etac0, alpha, beta);
    tvalue(i) = t
end
