% Calcul du graphe reduit "graphereduit" compatible avec les dimensions
% (i.e. le diametre) du robot
% diametre_robot < epais{i}(k)
graphered = cell(1,600);
for i = 1:ntotsom
    k2 = 0;
    for k = 1:size(graphe{i},2)
        j = graphe{i}(k);
        if (diametre_robot+1)< (epais{i}(k))
           % Le robot peut passer dans le couloir (i,j)
           % L'arc (i,j) est realisable
           k2 = k2 + 1;
           graphered{i}(k2) = j;
        end
    end
end