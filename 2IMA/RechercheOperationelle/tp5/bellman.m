%*********************************************************************
% Détermination du plus court chemin dans un graphe du noeud ini à tous
% les autres noeuds
%**********************************************************************
%
% Algorithme de Bellman
%
% En ENTREE:
% ntotsom : nombre total de sommets du graphe
% pred{i}(j) : donne le numéro du jème sommet prédécesseur de i
% (pred{i}(j),i) est donc un arc du graphe
% distance(i,j) : donne la distance du sommet i au sommet j
%
% EN SORTIE:
% POPT(j) prédécesseur de j dans le chemin optimel allant de ini à j
% POPT(j) vaut 0 si j est inatteignable à partir de ini
% V(1,j) = distance minimale de ini à j
% V(1,j) = Inf si j est inatteignable à partit de ini
%
function [POPT,V] = bellman (ntotsom,ini,pred,distance)
%
% INITIALISATION
V = zeros(2,ntotsom);
POPT = zeros(1,ntotsom);
for i = 1:ntotsom
    V(1,i) = inf; % V(1,i) vaut +infini
    V(2,i) = inf; % V(2,i) vaut +infini
end
V(1,ini) = 0;
V(2,ini) = 0;
stationnarite = false;
while ~stationnarite
    for j = 1:ntotsom
        if size(pred{j},2) > 0
            i = pred{j}(1); % i : prédécesseur de j
            valmin = V(1,i) + distance(i,j);
            predopt = i;
            for k = 2:size(pred{j},2)
                i = pred{j}(k); % i : prédécesseur de j
                val =  V(1,i) + distance(i,j);
                if val < valmin
                    valmin = val;
                    predopt = i;
                end
            end
            if valmin < V(1,j)
                POPT(j) = predopt;
                V(2,j) = valmin;
            else
                V(2,j) = V(1,j);
            end
        end
    end
    % Comparaison V(1,:) et V(2,:)
    if isequal(V(1,:),V(2,:))
        stationnarite = true;
    else
        V(1,:)=V(2,:);
    end
end