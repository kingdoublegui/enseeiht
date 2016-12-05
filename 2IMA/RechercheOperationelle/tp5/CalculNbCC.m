
function [nb_CC]=CalculNbCC(courbure,cseuil)
% Cette fonction retourne le nombre de points qui ne verifient pas la
% contrainte de courbure CC


[pks,locs] = findpeaks(abs(courbure));
nb_extremum=size(pks,2);
nb_CC=0; % nombre de points de contrôle à reajuster (modifier le poids)
for j=1:nb_extremum
    if (abs(courbure(1,locs(j)))> cseuil)
       nb_CC=nb_CC+1;
    end
end

end
