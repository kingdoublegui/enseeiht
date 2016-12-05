function largeur = largmax(x,y,l)
%
% Calcul de la largeur maximale d'un rectangle de longueur l ne contenant
% pas le point (x,y)
%
if 0<=x && x<=l
    largeur = 2 * abs(y);
else
    largeur = 2 * max(abs(x),abs(y));
end