% Calcul des distances euclidiennes entre les sommets d'un graphe de sommets
%
dist= sparse(ntotsom,ntotsom);
for i = 1:ntotsom
    for k = 1:size(graphe{i},2)
        j = graphe{i}(k);
        dx2 = double(icar(i) - icar(j))^2;
        dy2 = double(jcar(i) - jcar(j))^2;
        dist(i,j) = sqrt(dx2 + dy2);
    end
end