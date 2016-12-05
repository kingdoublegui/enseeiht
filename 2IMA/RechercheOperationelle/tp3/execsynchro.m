disp('Duopole de Cournot');
disp('Strategies possibles : cooperatif/noncooperatif/stackelberg/myStrat');
NBJ=input('Nombre de parties: ');
strax=input('Strategie du 1er joueur: ','s');
stray=input('Strategie du second joueur: ','s');
[tx, ty, gx, gy] = jeu(NBJ,strax,stray);
fig = afficher(tx,ty,gx,gy,strax,stray);
