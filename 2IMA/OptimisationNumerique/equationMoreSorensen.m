function [ s ] = equationMoreSorensen(q, g, qg, lambda, delta)
%% Calcul de xmin et xmax
ymin = abs(qg(1));
ymax = abs(qg(1)) * 2;
fxmin = normS(qg, lambda, -lambda(1) + ymin) - delta^2;
fxmax = normS(qg, lambda, -lambda(1) + ymax) - delta^2;

% Tant que les bornes ne sont pas de part et d'autre de 0 == pas de 0 entre
while fxmin * fxmax >= 0
    ymin = ymin / 2;
    ymax = ymax * 2;
    fxmin = normS(qg, lambda, -lambda(1) + ymin) - delta^2;
    fxmax = normS(qg, lambda, -lambda(1) + ymax) - delta^2;
end
lambda_min = -lambda(1) + ymin;
lambda_max = -lambda(1) + ymax;

%% Résolution de l'équation non lineaire
syms l;
phi = phi1(l, @(l)normS(qg, lambda, l), delta);
% Recherche du 0
[~, x] = newtonPlus(phi, lambda_min, lambda_max);

%% Calcul de s
s = 0;
for i=1:size(q ,2)
    s = s - (q(:,i)'*g) / (lambda(i) + x) * q(:,i);
end
end