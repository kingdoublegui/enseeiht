clear;
close all;

% Constantes
d = 5;  % Nb de jours
t = 4;  % Nb de créneaux par jour
c = 2;  % Nb de promotions
m = 8;  % Nb de professeurs
n = m*c*d*t;    % Nb de cases de l'emploi du temps, taille du vecteur x

% Expression à minimiser
journee = [ones(1, m*c) zerost(1, (t-2)*m*c) ones(1, m*c)];
f = repmat(journee, 1, d);

% Contraintes d'égalité
Aeq = zeros(1, m, c, d*t);    beq = zeros(1, 1);
Aeq(1,1,2,:) = 1;     beq(1) = 0;
Aeq(2,2,1,:) = 1;     beq(2) = 0;
Aeq(3,4,2,:) = 1;     beq(3) = 0;
Aeq(4,5,1,:) = 1;     beq(4) = 0;
Aeq(5,7,2,:) = 1;     beq(5) = 0;
Aeq(6,8,1,:) = 1;     beq(6) = 0;
Aeq(7,1,1,:) = 1;     beq(7) = 5;
Aeq(8,2,2,:) = 1;     beq(8) = 4;
Aeq(9,3,1,:) = 1;     beq(9) = 3;
Aeq(10,3,2,:) = 1;    beq(10) = 3;
Aeq(11,4,1,:) = 1;    beq(11) = 6;
Aeq(12,5,2,:) = 1;    beq(12) = 6;
Aeq(13,6,1,:) = 1;    beq(13) = 3;
Aeq(14,6,2,:) = 1;    beq(14) = 3;
Aeq(15,7,1,15) = 1;   beq(15) = 1;
Aeq(16,8,2,15) = 1;   beq(16) = 1;
Aeq(17,:,:,1) = 1;    beq(17) = 0;
Aeq(18,2,2,2) = 1;    beq(18) = 0;
Aeq(19,3,:,9:12) = 1; beq(19) = 0;

siz = size(Aeq);
Aeq = Aeq(1:siz(1), :);

% Contraintes d'inégalité
A = zeros(1, m, c, d*t);   b = zeros(1, 1);

ind = 1;
for j=1:c
    for k=0:(d-1)
        A(ind,   1, j, k*t+1:(k+1)*t) = 1; b(ind)   = 1;
        A(ind+1, 2, j, k*t+1:(k+1)*t) = 1; b(ind+1) = 1;
        A(ind+2, 3, j, k*t+1:(k+1)*t) = 1; b(ind+2) = 1;
        A(ind+3, 4, j, k*t+1:(k+1)*t) = 1; b(ind+3) = 2;
        A(ind+4, 5, j, k*t+1:(k+1)*t) = 1; b(ind+4) = 2;
        A(ind+5, 6, j, k*t+1:(k+1)*t) = 1; b(ind+5) = 1;
        A(ind+6, 7, j, k*t+1:(k+1)*t) = 1; b(ind+6) = 1;
        A(ind+7, 8, j, k*t+1:(k+1)*t) = 1; b(ind+7) = 1;
        ind = ind + m;
    end
end
for i=1:m
    for k=1:d*t
        A(ind,i,:,k) = 1;  b(ind) = 1;
        ind = ind + 1;
    end
end
for j=1:c
    for k=1:d*t
        A(ind,:,j,k) = 1;  b(ind) = 1;
        ind = ind + 1;
    end
end

siz = size(A);
A = A(1:siz(1), :);

%tests;

% Contrainte x = 0 ou 1
intcon = 1:n;
lb = zeros(n, 1);
ub = ones(n, 1);

% Résolution
x = intlinprog(f, intcon, A, b, Aeq, beq, lb, ub);

% Affichage
x = reshape(x, [m, c, t, d]);
Promo = zeros(2, t, d);
for i=1:t
    for j=1:d
        Promo(1,i,j) = nbprof(x(:,1,i,j));
        Promo(2,i,j) = nbprof(x(:,2,i,j));
    end
end
nbPromo = 1;
affichage_edt;
nbPromo = 2;
figure;
affichage_edt;
