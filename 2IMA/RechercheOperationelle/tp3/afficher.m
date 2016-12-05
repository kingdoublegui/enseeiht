function fig = afficher(tx,ty,gx,gy,nomx,nomy)
% afficher -- Affichage des jeux
%
%  Usage
%    fig = afficher(tx,ty,gx,gy,nomx,nomy)
%
%  Inputs
%    tx     strategies du joueur A
%    ty     strategies du joueur A
%    gx     gains du joueur A
%    gy     gains du joueur B
%    nomx   nom du joueur A
%    nomy   nom du joueur B
%
%  Outputs
%    fig    handlers des figures crees
%
%  Description
%    Affichage des strategies et des resultats des jeux
%
%  See also
%    strategie, jeu
%

NBC= length(tx);
sx = zeros(1,NBC) ;
sy = zeros(1,NBC) ;

sx(1) = gx(1) ;
sy(1) = gy(1) ;

for i = 1:(NBC-1)

    sx(i+1) = sx(i) + gx(i+1) ; 
    sy(i+1) = sy(i) + gy(i+1) ; 

end ;

i = 1:NBC ;

% Affichage des strategies

fig1 = figure ;
set(fig1,'Position',[8 252 495 331]) ;
plot(i,tx(1:NBC),i,ty(1:NBC)) ;
xlabel('Numero du jeu') ;
ylabel('Strategie ') ;
legend(nomx(7:end),nomy(7:end),0) ;

% Affichage des gains

fig2 = figure ;
set(fig2,'Position',[512 252 506 331]) ;
plot(i,sx,i,sy) ;
xlabel('Numero du jeu') ;
ylabel('Total des gains') ;
legend(nomx(7:end),nomy(7:end),0) ;
fig = [ fig1 fig2 ] ;
