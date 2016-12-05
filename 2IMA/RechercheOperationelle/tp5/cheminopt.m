% calcul du plus court chemin pour aller du sommet "ini" au sommet "but"
pred = but;
longueur = 0;
chemin = but;
while pred ~= ini
    mem = PRED(pred);
    chemin = [chemin mem];
    longueur = longueur + dist(pred,mem);
    pred = mem;
end
nsomchemin = size(chemin,2);
chemin = chemin(nsomchemin:-1:1);
dx2 = double(icar(ini) - icar(but))^2;
dy2 = double(jcar(ini) - jcar(but))^2;
longueurmin = sqrt(dx2 + dy2);
rapportlong = longueurmin/longueur;