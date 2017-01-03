%function [sms,dms,lstar,flag] = etalonms(g,H,delta,told)
%
% Resolution du sous-probleme de regions de confiance
%	min g'*s + 0.5*s'*H*s s.t. ||s|| <= delta
% par l'algorithme de More-Sorensen.
%
% SMS = etalonms(G,H,DELTA,TOLD) renvoie le pas obtenu par l'algorithme 
% de More-Sorensen pour un modele quadratique de partie lineaire G'*s et de 
% partie quadratique 1/2*s'*H*s, dans une region de confiance de rayon 
% DELTA. Il utilise un algorithme de resolution d'equations non lineaires
% appele avec une precision TOLD.
%
% Entrees :
%	g : vecteur de taille n x 1
%	H : matrice de taille n x n
%	delta : reel positif
%	told : precision requise sur la resolution de l'equation ||s||=delta
%
% Sorties :
%	sms : pas de More-Sorensen - vecteur n x 1
%	dms : decroissance du modele obtenue par ce pas
%	lstar : valeur du multiplicateur de Lagrange obtenu
%	flag : indicateur du bon deroulement de l'algorithme
%		0 : l'algorithme s'est bien passe
%		1 : Entree dans un des trois cas particuliers
%			A) le probleme a un minimum pour s=0
%			B) le gradient est nul (modele sans partie lineaire)
%			C) la matrice Hessienne est nulle (modele lineaire)
%		2 : La precision demandee pour l'equation non lineaire n'a
%		pu etre atteinte
%		-1 : une erreur s'est produite
%
% C. Royer - Janvier 2016
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
