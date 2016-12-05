function [weight]=Mutation(weight,ind_mutated,im,r,niter,coefs,knots,subdiv1,cseuil)


%disp('Mutation en cours');
%disp('vecteur poids Before');

 WMutated=weight(ind_mutated,:);
% disp(WMutated);
[ensemble_collision,evalIndivMutated]= Fct_evaluation(im,r,niter,coefs,WMutated,ind_mutated,knots,subdiv1,cseuil);

    dist1 = sparse(subdiv1,subdiv1);
    coefsrep=zeros(2,niter);
    coefsrep(1,:)=coefs(1,:);
    coefsrep(2,:)=100-coefs(2,:);

if (evalIndivMutated.nb_CC==0) && (evalIndivMutated.nb_CC==0)%feasible path
    %Minimising max of curvature
    n1=size(evalIndivMutated.courbure,2);
    ind_max=1;
    max_cur=evalIndivMutated.courbure(1);
    for i=1:n1
        if (max_cur < evalIndivMutated.courbure(i))
            ind_max = i;
            max_cur = evalIndivMutated.courbure(i);
        end
    end
    p = nrbeval (evalIndivMutated.nurbs, ind_max*(1/subdiv1)); %evaluation des coordonnées du path's point
    noeud = NoeudPlusProche(p,coefsrep,niter,dist1); % détermination du control point correspondant
    weight(ind_mutated,noeud) = weight(ind_mutated,noeud)- 0.9;
    if weight(ind_mutated,noeud)<= 0
     weight(ind_mutated,noeud) = 0.07;
    end
    
else
    if evalIndivMutated.nb_CO~=0   %% Faut s eloigner des obstacles
        n2=randi([1, size(ensemble_collision,2)]);
        p(1,1)= ensemble_collision(1,n2);
        p(2,1)= ensemble_collision(2,n2);
        noeud = NoeudPlusProche(p,coefsrep,niter,dist1);
        weight(ind_mutated,noeud)=weight(ind_mutated,noeud)+3;
    else   %% Faut diminuer la courbure (ici on n a pas choisi aléatoirement on a pris le max des cur)
    n1=size(evalIndivMutated.courbure,2);
    ind_max=1;
    max_cur=evalIndivMutated.courbure(1);
    for i=1:n1
        if (max_cur < evalIndivMutated.courbure(i))
            ind_max = i;
            max_cur = evalIndivMutated.courbure(i);
        end
    end
    p = nrbeval (evalIndivMutated.nurbs, ind_max*(1/subdiv1)); %evaluation des coordonnées du path's point
    noeud = NoeudPlusProche(p,coefsrep,niter,dist1); % détermination du control point correspondant
    weight(ind_mutated,noeud) = weight(ind_mutated,noeud)- 0.9;
    if weight(ind_mutated,noeud)<= 0
     weight(ind_mutated,noeud) = 0.07;
    end
        
        
    end
end


%%%disp('vecteur poids After');
%%%disp(weight(ind_mutated,:));
%disp('Indice gene muté');
%disp(noeud);

end

