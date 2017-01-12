
%% Algorithme de *Ford-Fulkerson* pour les tensions
%
% Application à la recherche du plus court chemin du sommet 1 (b) au sommet 2 (a)
% dans un graphe (X,U) modélisé comme un problème de tension maximale
%
% Representation du graphe
%
% n == nombre de sommets
%
% m == nombre d'arcs
%
% Vecteur du nombre de successeurs de chaque sommet : NSUC
%
% Vecteur des successeurs de chaque sommet : SUC
%
%% Initialisations
NSUC = uint16([3 0 1 3 2 2 2 3]);
n = size(NSUC,2);
X = uint16(1:n); % X contient la liste des sommets de G
m = sum(NSUC);
%
% Vecteur des successeurs de chaque sommet : SUC
SUC = uint16([2 3 8 6 3 5 6 4 7 2 7 2 5 3 4 5]);
%
% Vecteur des longueurs de chaque arc
LONG = [inf 2 1 2 1 7 3 0 3 4 2 1 2 5 3 3];
%
% Vecteur de la tension courante theta
theta = zeros(1,m);
%
MARQUE = false(1,n); % MARQUE est un vecteur logique
MARQUE(2) = true; % on marque le sommet a == 2 ( b == 1 (b,a) == 1)

NONMARQUES = X(~MARQUE); % NONMARQUES contient la liste des sommets non marqués
%
PLUSCOURTCHEMIN = uint16(zeros(1,n)); % Vecteur du plus court chemin de 1 (b) à 2 (a)
PLUSCOURTCHEMIN(1) = 1;
%
%% Algorithme de FF
while ismember(1,NONMARQUES) % Tant que b == 1 non marqué
    CANDIDATS = false(1,n); % CANDIDATS est un vecteur logique contenant les candidats à
    % être marqués
    %
    %% 1.   MAJ tension courante theta
    beta = inf;
    vcocycle = zeros(1,m); % vecteur cocycle des sommets non marqués
    for l=1:size(NONMARQUES,2)
        i = NONMARQUES(l); % i est non marqué
        if NSUC(i) ~= 0 % le nombre de successeurs de i est non nul
            prsuc = sum(NSUC(1:i-1)) + 1; % prsuc contient l'indice du 1er successeur de i dans SUC
            for k = prsuc:prsuc+NSUC(i)-1
                j = SUC(k); % (i,j) est un arc
                if MARQUE(j)
                    % j est un sommet marqué (et i est nonmarqué donc (i,j)
                    % appartient au cocycle
                    % donc i est candidat à être marqué
                    CANDIDATS(i) = true;
                    beta = min(beta,LONG(k)-theta(k)); % beta > 0
                    vcocycle(k) = 1;
                end
            end
        end
    end
    theta = theta + beta*vcocycle; %MAJ theta
    %
    %% 2.   Marquer sommets
    liste_candidats_marquage = X(CANDIDATS);
    for l=1:size(liste_candidats_marquage,2)
        i = liste_candidats_marquage(l); % i est non marqué et candidat à être marqué
        if NSUC(i) ~= 0
            % le nombre de successeurs de i est non nul
            prsuc = sum(NSUC(1:i-1)) + 1; % prsuc contient l'indice du 1er successeur de i dans SUC
            for k = prsuc:prsuc+NSUC(i)-1
                j = SUC(k); % j est successeur du sommet i candidat au marquage
                if MARQUE(j) && theta(k) == LONG(k)
                    % i est non marqué , j est marqué, (i,j) est un arc (de numéro k) , et la
                    % valeur de la tension sur cet arc est égale à la longueur
                    % de l'arc (i,j)
                    % donc on marque i
                    MARQUE(i) = true; % on marque le sommet i
                    NONMARQUES = setdiff(NONMARQUES,i); % on enlève i des sommets non marqués
                end
            end
        end
    end
end
%% 3. Post-traitement : extraction du plus court chemin
i = 1;
prsuc = 1;
nsom = 1;
while i ~= 2
    for k = prsuc:prsuc+NSUC(i)-1
        j = SUC(k);
        if theta(k) == LONG(k)
            nsom = nsom + 1;
            PLUSCOURTCHEMIN(nsom) = j;
            break;
        end
    end
    i=j;
    prsuc = sum(NSUC(1:i-1)) + 1;
end
%
disp (['Plus court chemin : ',num2str(PLUSCOURTCHEMIN(1:nsom))]);
disp (['Longueur du plus court chemin : ', num2str(theta(1))]);