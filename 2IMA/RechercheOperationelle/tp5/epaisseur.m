% Calcul de l'épaisseur minimale du couloir joignant 2 sommets du squelette
%
epais = graphe;
for i = 1:ntotsom
    for k = 1:size(graphe{i},2)
        j = graphe{i}(k);
        epaisseurmin = inf;
        l = dist(i,j); % distance de i à j
        nlig = size(imscene,1);
        ncol = size(imscene,2);
        for i1 = 1:size(imscene,1)
            for j1 = 1:size(imscene,2)
                if imscene(i1,j1) == 7
                    % (i1,j1) est un pixel appartenant à un obstacle
                    % on calcule ses cooordonnées par rapport au segment
                    % (i,j)
                    [i2,j2] = chgtrepere(i1,j1,icar(i),jcar(i),icar(j),jcar(j),l);
                    largeur = largmax(i2,j2,l);
                    if largeur < epaisseurmin
                        epaisseurmin = largeur;
                    end
                end
            end
        end
        epais{i}(k) = epaisseurmin;
    end
end