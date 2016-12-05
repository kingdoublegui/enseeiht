% cette fonction evalue une trajectoire (individu)

function [ensemble_collision,evaluationIndividu]= Fct_evaluation(im,r,niter,coefs,W,i,knots,subdiv1,cseuil)
ti = linspace(0.0,1.0,subdiv1);
%Construction de la trajectoire
coefsH = zeros(4,niter); % Passage en coordonnees homogènes
coefsH(1,:) = coefs(1,:) .* W(1,:);
coefsH(2,:) = coefs(2,:) .* W(1,:);
coefsH(4,:) = W(:,:); 
evaluationIndividu.Id=i;
evaluationIndividu.nurbs = nrbmak2(coefsH,knots);   % Construction de la structure NURBS
%Calcul de la courbure
[evaluationIndividu.courbure, evaluationIndividu.sigma]= CalculCourbure(evaluationIndividu.nurbs,subdiv1);
%Calcul du nombre de points qui ne verifient pas la contrainte de courbure CC
[evaluationIndividu.nb_CC]=CalculNbCC(evaluationIndividu.courbure,cseuil);
%Calcul du nombre de points qui ne verifient pas la contrainte de la non-collision CO
[ensemble_collision,evaluationIndividu.nb_CO]=CalculNbCollision(im,evaluationIndividu.nurbs,subdiv1,r);
%Calcul de la longueur de la courbe NURBS
ptsNurbs=nrbeval(evaluationIndividu.nurbs,ti);
px=ptsNurbs(1,:);
py=ptsNurbs(2,:);
[arclen,seglen] = arclength(px,py,'spline');
%[arclen,seglen] = arclength(coefs(1,:),coefs(2,:),'spline');
evaluationIndividu.arclen=arclen;


end

