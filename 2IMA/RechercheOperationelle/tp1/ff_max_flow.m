function [FLOT_MAX,COURANT]=ff_max_flow(NSUC, SUC, LONG)
capacite = fromLineToMatrix(NSUC, SUC, LONG);
n = size(capacite, 1);
COURANT=zeros(n,n);
FLOT_MAX=0;
source = 1;
puit = 2;
chemin_ameliorant = bfs_augmentpath(source,puit,COURANT,capacite,n);

while ~isempty(chemin_ameliorant)
    % Si il existe un chemin ameliorant, on augmente le flot d'autant 
    increment = inf;
    % On parcourt le chemin pour connaitre la valeur de l'amelioration
    for i=1:length(chemin_ameliorant)-1
        increment=min(increment, capacite(chemin_ameliorant(i),chemin_ameliorant(i+1))-COURANT(chemin_ameliorant(i),chemin_ameliorant(i+1)));
    end
    % On augmente le flot de l'increment
    for i=1:length(chemin_ameliorant)-1
        COURANT(chemin_ameliorant(i),chemin_ameliorant(i+1))=COURANT(chemin_ameliorant(i),chemin_ameliorant(i+1))+increment;
        COURANT(chemin_ameliorant(i+1),chemin_ameliorant(i))=COURANT(chemin_ameliorant(i+1),chemin_ameliorant(i))-increment;
    end
    FLOT_MAX=FLOT_MAX+increment;
    chemin_ameliorant = bfs_augmentpath(source,puit,COURANT,capacite,n);% On recherche un nouveau chemin ameliorant   

end
