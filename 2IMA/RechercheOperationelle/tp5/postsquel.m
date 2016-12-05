% Post-traitement du squelette d'une image:
% im(i,j) = 0 ou 1 : pixel "simple" du squelette
% im(i,j) = 2 : pixel "complexe" , voisin d'un carrefour
% im(i,j) = 3 : extrémité
% im(i,j) = 4 : carrefour
% im(i,j) = 7 : fond
%
icar = zeros(1,600,'int16'); % les numeros de ligne des carrefours
jcar = zeros(1,600,'int16'); % les numeros de colonne des carrefours
nlig = size(im,1);
ncol = size(im,2);
nbrecar = 0; % nombre de carrefours
for i = 3:nlig-2
    for j = 3:ncol-2
        if im(i,j) ~= 7
            % Le pixel (i,j) appartient au squelette
            % On calcule le nombre de ses 8-voisins
            nbreq = 0;
            for k = 1:8
                ii = i + itab(k);
                jj = j + jtab(k);
                if im(ii,jj) ~= 7
                    nbreq = nbreq + 1;
                end
            end
            if nbreq == 1
                im(i,j) = 3; % (i,j) est une extremite
            end
            if im(i,j) <= 1
                if (nbreq > 3 || nbreq == 3 && im(i-1,j-1) + im(i+1,j-1)...
                        + im(i-1,j+1) + im(i+1,j+1) == 28) || nbreq == 3 ...
                        && im(i-1,j+1) + im(i+1,j-1) >= 7 && ...
                        im(i-1,j-1) + im(i+1,j+1) >= 7
                    nbrecar = nbrecar + 1;
                    icar(nbrecar) = i;
                    jcar(nbrecar) = j;
                end
            else
                if nbreq == 3 && im(i-1,j) + im(i,j-1) + im(i,j+1) + im(i+1,j) == 28
                    nbrecar = nbrecar + 1;
                    icar(nbrecar) = i;
                    jcar(nbrecar) = j;
                end
            end
        end
    end
end
for k=1:nbrecar
    i = icar(k);
    j = jcar(k);
    im(i,j) = 4;
end
for k=1:nbrecar
    i = icar(k);
    j = jcar(k);
    for k = 1:8
        ii = i + itab(k);
        jj = j + jtab(k);
        if im(ii,jj) <= 1 || im(ii,jj) == 3
            im(ii,jj) = 2;
        end
    end 
end