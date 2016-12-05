% Calcul des chemins les plus courts d'un noeud du graphe reduit a un autre
[PRED,V]=bellman(ntotsom,ini,graphered,dist); % algorihme de Bellman (cf RO)

[but,som_trouve] = sommet(iarr,jarr,icar,jcar); % but no sommet d'arrivee
if ~som_trouve
    disp (['Le pixel d''arrivee ( ',num2str(iarr),',', num2str(jarr), ') ','n''est pas un sommet du graphe']);
    return;
end
% on est s�r que le pixel d'arrivee est un sommet du graphe, son numero est but

if V(1,but) == Inf
    disp('Le point d''arrivee est inatteignable �partir du point de depart');
    return
end

cheminopt; % calcul du plus court chemin de ini � but

chemin % affichage des num�ros des sommets du chemin trouv�

% Affichage du chemin trouv�
im = imscene;
i1 = double(icar(ini));
j1 = double(jcar(ini));
niter = size(chemin,2);
for k=2:niter
    numsom = chemin(k);
    i2 = double(icar(numsom));
    j2 = double(jcar(numsom));
    [x y] = bresenham(i1,j1,i2,j2);
    % on effectue dans im le trace du segment
    for i = 1:size(x,1)
        for j = 1:size(x,1)
            im(x(i),y(i)) = 4;
        end
    end
    i1 = i2;
    j1 = j2;
end
%
im(idep,jdep)=8;
im(iarr,jarr)=8;