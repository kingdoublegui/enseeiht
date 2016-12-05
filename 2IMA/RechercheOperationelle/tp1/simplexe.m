%% TRAVAIL A FAIRE LORS DU 1er TP de RO
% Compléter le code MATLAB là où il y a ??
% Tester sur des exemples variés (utiliser notamment le script test)
%
function [x,F] = simplexe(OPT,c,A,b)
%
%% Fonction simplexe
%
% Le pb : Opt F(x) = c'*x
%         Ax = b
%         x >= 0
% 
%% EN ENTREE
%  OPT = +1 si pb de maximisation
%  OPT = -1 si pb de minimisation
%  c : vecteur de poids des variables dans le critère à maximiser.
%  A : matrice décrivant les contraintes égalité.
%  b : vecteur second membre des contraintes egalité.
%
%% EN SORTIE
%  x : vecteur des valeurs des variables maximisant la valeur du critère.
%  F : valeur optimale du critere.
%
%% EN VARIABLES INTERNES
%  JB: le vecteur des indices de la base courante (ou du support).
%  JN: l'ensemble des indices hors-base courants.
%  GAMMA est le tableau simplexe courant
%  GAMMA(:,1:n) contient les décompositions des vecteurs colonnes de A sur
% la base courante JB
%  GAMMA(:,n+1) contient le sommet courant
%  alpha indice du vecteur colonne de A qui entre dans la base
%  beta indice du vecteur colonne de A qui sort de la base
%  indbeta indice tel que beta = JB(indbeta)
%% REMARQUES
% La méthode de la base artificielle n'a pas été implantée
% Aucune procédure anti-cyclage n'a été implantée
% Il peut y avoir cyclage si l'on rencontre un sommet dégénéré
%
%% DEBUT DU PROGRAMME
m = size(A,1);
n = size(A,2);
J = 1:n;
JN = 1:n-m;
JB = setdiff(J,JN);
%
%% Prétraitement
% Changer les signes des équations si b(i) < 0
% On rend ainsi b >=0
s = sign(b); % s(i) == 0 si b(i) == 0 !!
s(s == 0) = 1; % s(i)== 1 si b(i) >=0 , s(i)== -1 si b(i) < 0
A = A.*repmat(s,1,n);
b = b.*s;
%
%% PHASE I
% Trouver un sommet initial
if A(:,n-m+1:n) == eye(m)
    % la base canonique A(:,n-m+1) ... A(:,n) est une base réalisable
    % puisque b>=0
    % x=(0,b) est donc un sommet initial
    GAMMA = [A b];
else
    disp('Appliquer la méthode de la base artificielle')
    return
end

%
%% PHASE II
% Trouver un sommet optimal à partir du sommet initial
%
z = c(JB)' * GAMMA(:,JN);
pentes = c(JN)'-z; % pentes contient les mesures des pentes des chemins partant du sommet courant
%% Step 1. Test d'optimalité
while any(OPT*pentes > 0) % Y-a-t-il un chemin améliorant ?
    %
    %% Step 2. Test d'existence de solution optimale
    indJNP = OPT*pentes > 0; %indJNP : indices dans le vecteur des pentes des chemins améliorant
    JNP =  JN(indJNP);  %JNP : indices j des vecteurs Aj définissant des chemins améliorant (xj > 0)
    % Existe-t-il un vecteur Aj, j dans JNP, dont toutes les composantes sur la
    % base courante soient négatives ou nulles ?
    % Il définit alors un chemin infini (xj > 0) admissible (inclus dans l'ensemble des contraintes)
    % sur lequel le critère croit (OPT = 1) ou décroit (OPT=-1) infiniment
    if any(all(OPT*GAMMA(:, JNP)<=0,1))
        disp('pas de solution optimale')
        x=Inf; 
        F = OPT*Inf;
        return
    end
    %
    %% Step 3. Détermination du vecteur A(:,alpha) entrant dans la base
    % Variante du compromis (choix du chemin améliorant possédant la plus forte pente)
    [~,indalpha] = max(OPT*pentes); %indalpha : indice dans pente du chemin améliorant de plus forte pente
    alpha = JN(indalpha); %alpha : le chemin x(alpha) > 0 va être suivi par le randonneur
    %
    %% Step 4. Détermination du vecteur A(:,beta) sortant de la base
    VEC = abs(double(GAMMA(:,alpha)>0) .* GAMMA(:,alpha));
    % VEC(i) = GAMMA(i,alpha) si GAMMA(i,alpha) > 0
    % VEC(i) = +0             si GAMMA(i,alpha) <= 0
    % Se rappeler aussi que 1/+0 = Inf !!
    [~,indbeta] = min(GAMMA(:, n+1)./VEC);
    % donc beta = JB(indbeta);
    %
    %% Step 5. Changement de base et calcul du nouveau tableau simplexe
    %
    % Changement de base
    JB(indbeta) = alpha; 
    JN = setdiff(J,JB);
    %
    % Calcul du nouveau tableau simplexe GAMMA
    % Regle de pivotage du SIMPLEXE
    % GAMMA(indbeta,alpha) est le pivot de l'iteration
    %
    GAMMA(indbeta,:) = GAMMA(indbeta,:) / GAMMA(indbeta,alpha);
    GAMMAMOINS = [GAMMA(1:indbeta-1,:) ; GAMMA(indbeta+1:m,:)];
    GAMMAMOINS = GAMMAMOINS - GAMMAMOINS(:, alpha) * GAMMA(indbeta, :);
    GAMMA = [GAMMAMOINS(1:indbeta-1,:) ; GAMMA(indbeta, :) ; GAMMAMOINS(indbeta:m-1,:)];
    %
    z = c(JB)' * GAMMA(:,JN);
    pentes = c(JN)'-z;
end
%
% Il n'y a plus de chemin améliorant: on a trouvé une solution localement
% optimale et donc globalement optimale puisque (P) est convexe
% Solution optimale
x = zeros(n,1);
x(JB) = GAMMA(:,n+1);
% Valeur optimale du critere
F = c'*x;