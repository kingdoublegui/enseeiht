function [Fils,weightMAJ]=Modification(weights,Parents,im,r,niter,coefs,knots,subdiv1,cseuil,p_mut)

sizePop=size(weights,1);

nw=size(weights,2);% taille du vecteur poids

disp('Modification en cours');

%% Croisement
np = size(Parents,2);
%Fils=zeros(np,nw);
i=1; j=2;
if mod(np,2)==0 
   while i <= (np-1)
    [Fils(i,:),Fils(j,:)]= Croisement(weights,nw,Parents(i),Parents(j));
    i=j+1;
    j=j+2;
    end
else
    while i <= (np-2)
    [Fils(i,:),Fils(j,:)]= Croisement(weights,nw,Parents(i),Parents(j));
    i=j+1;
    j=j+2;
    end
    Fils(np,:)= weights(Parents(np),:);
    for i=round(nw/2):nw
    Fils(np,i)= weights(Parents(np-2),i);

    end
end
% disp('modifFils');
% disp(Fils);

%%
%Mise � jour de la population (Insertion des fils)

for j=1:sizePop
    weight(j,:)=weights(j,:);
end
for i=1:np
    weight(j+i-1,:)=Fils(i,:);
end

%% Mutation
sizePop1=size(weight,1);
nb_mut=round((sizePop1*p_mut)/100); % nombre d individus qui subiront la mutation

for i=1:nb_mut
% selection aleatoire d'un individu
ind_mutated = randi([1, sizePop1]);
%WMutated=weight(ind_mutated,:);
[weight]=Mutation(weight,ind_mutated,im,r,niter,coefs,knots,subdiv1,cseuil);
end

%% MAJ de la population Elimination des mauvais elements
% EVALUATION DE LA nouvelle POPULATION
[~,tab_sol,~,~,~,~,~]= Evaluation(im,r,niter,coefs,weight,knots,subdiv1,cseuil);
disp('  Evaluation population modifiee pour la MAJ..');
for i=1:sizePop
    elt=tab_sol(1,i);
    weightMAJ(i,:)=weight(elt,:);
end

%sizePop

end