anasquel6; % analyse d'un squelette (*** version 6 ***)

%clearvars  -except graphe im imscene icar jcar idep jdep iarr jarr ...
 %          nlig ncol nsomsimple nbrecar ntotsom diametre_robot rho_min niter;

figure('Name','Squelette analyse');
colormap(jet);
imagesc(im);

distance; % calcul des distances entre les sommets du graphe

[ini,som_trouve] = sommet(idep,jdep,icar,jcar); %ini no sommet de depart
if ~som_trouve
    disp (['Le pixel de depart ( ',num2str(idep),',', num2str(jdep), ') ','n''est pas un sommet du graphe']);
    return;
end % on est sur que le pixel de depart est un sommet du graphe, son numero est ini

% Calcul des largeurs minimales de tous les troncons du graphe.
epaisseur; % epais{i}(k) contient la largeur minimale du troncon (i, graphe{i}(k))

% Reduction du graphe pour obtenir un graphe compatible avec le diametre du robot
graphereduit;