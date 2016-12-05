function [Eval,W_Elu,indice_elu,sol,weightsInit,weights]=GAforNURBScurveFitting(im,coefs,knots,subdiv1,PopulationSize,max_weight,cseuil,r,modif_rate, nb_iter,p_mut)
%--------------------------------------------------------------------------
% Cette fonction a pour objectif de re parametriser les poids des points de
% controle d'une courbe NURBS pour chercher une solution qui respecte les
% contraintes du probleme
%--------------------------------------------------------------------------
% INPUTS :
%         - im :  La scene
%         - coefs : les coordonnees des points de controle
%         - knots : le vecteur nodal
%         - subdiv1 : le nombre des points d'evaluation de la courbe
%         - PopulationSize : la taille de la population
%         - max_weight : la valeur maximale des poids
%         - cseuil : la valeur de courbure maximale (= 1/rho_min )
%         - r : rayon du cercle representant le robot
%         - modif_rate : pourcentage de croisement
%         - nb_iter : nombre d'iterations
%         - p_mut : pourcentage de mutation
%
%
% OUTPUTS :
%         - Eval :  Tableau de structures dont chaque structure represente
%         les caracteristiques d'un individu : * Id : indice
%                                              * nurbs : structure
%                                              contenant les parametres de
%                                              la nurbs
%                                              * courbure : vecteur
%                                              courbure en chaque point
%                                              * sigma : ecart type des
%                                              courbures
%                                              * nb_CC : nombre de points
%                                              de la courbe dont la valeur
%                                              de la courbure en valeur
%                                              absolue depasse la valeur
%                                              limite acceptee
%                                              * nb_CO : nombre de points
%                                              de la courbe qui heurtent
%                                              les obstacles
%                                              * arclen : longeur de la
%                                              courbe
%
%         - W_Elu :  meilleur vecteur poids (meilleur individu)
%         - indice_elu :  indice du meilleur individu
%         - sol :  indique si la solution finale est admissible (=1 ) ou
%                  non (= 0 )
%         - weightsInit :  population initiale (matrice dont chaque ligne
%                          represente les poids d'une courbe candidate)
%         - weights :  derniere population
%--------------------------------------------------------------------------

[~,niter]=size(coefs);
T=PopulationSize;

%% fonction INITIALISATION DE LA POPULATION

[weights] = InitPopulation(PopulationSize,niter,max_weight);
%disp('les poids');
weightsInit=weights;% la population initiale
%disp(weightsInit);

%%
it=1;
sol=0;
evolution_rate = 1;% parametre pour savoir si la population evolue ou pas
while (it<=nb_iter && evolution_rate==1)
disp(['***Iteration ' num2str(it) ' en cours...***']);
% fonction EVALUATION DE LA POPULATION
[Eval,tab_sol,sigmaMoy,sigmaMin,W_Elu,indice_elu,Wfitness]= Evaluation(im,r,niter,coefs,weights,knots,subdiv1,cseuil);
disp('  Evaluation reussie..');
% caracteristiques individu meilleur pour chaque population
tab_sig(1,it)=sigmaMoy;
tab_sig(2,it)=sigmaMin; %ecart type
tab_sig(3,it)=Wfitness; % valeur de best fitness
%%disp(tab_sig(2:3,:));
Fitness_value(it)= Wfitness;
% Calcul Mean fitness
MeanFitness=0;
for k=1:T
    MeanFitness=MeanFitness+tab_sol(2,k);
end
MeanFitness=MeanFitness/T;
tab_sig(4,it)=MeanFitness;
% fonction SELECTION DES INDIVIDUS PROMETTEURS
[Parents]=Selection(tab_sol,modif_rate);
disp('  Selection des parents reussie..');
%disp(Parents);
%disp('eliminated');
%disp(Eliminated);

% fonction MODIFICATION DE LA POPULATION
[~, weights]= Modification(weights,Parents,im,r,niter,coefs,knots,subdiv1,cseuil,p_mut);
disp('  MAJ reussie..');
%Test premier critere d'arret: Test si la popoulation n'evolue plus
% if it>=6
%     if (Fitness_value(it)==Fitness_value(it-1))&&(Fitness_value(it-1)==Fitness_value(it-2))&&(Fitness_value(it-2)==Fitness_value(it-3))
%         evolution_rate = 0;
%         [Eval,tab_sol,sigmaMoy,sigmaMin,W_Elu,indice_elu,Wfitness]= Evaluation(im,r,niter,coefs,weights,knots,subdiv1,cseuil);     
%     end
% end
it=it+1;

end

%% results

 if (Eval(indice_elu).nb_CO==0 && Eval(indice_elu).nb_CC==0)
      sol=1;
      disp(['La solution est determinee a l iteration numero:  ' num2str(it-1)]);
      disp(['L individu solution est le  ' num2str(indice_elu) '. La distribution des poids admissible est: ']);
      disp(W_Elu);
      disp(['La Longueur du path:  ' num2str(Eval(indice_elu).arclen)]);
      disp(['L ecart type des courbures du path:  ' num2str(Eval(indice_elu).sigma)]);
 else
      disp(['PAS DE SOLUTION: le nbre de CC est : ' num2str(Eval(indice_elu).nb_CC) '. Le nbre de Collision est : ' num2str(Eval(indice_elu).nb_CO)]);
 end

figure;
plot(tab_sig(1,:),'r');hold on;
plot(tab_sig(2,:),'b');hold on;
legend('sigmaMoy','sigmaMin');
%
figure;
plot(tab_sig(3,:),'.r');xlabel('generations');ylabel('best fitness'); title('Evolution of Best fitness and Mean fitness');
hold on; plot(tab_sig(4,:),'.b');
legend('Best fitness','Mean fitness');

figure;
plot(tab_sig(3,:),'r');xlabel('generations');ylabel('best fitness'); title('Evolution procedure of proposed GA-based path planning method');
FG=tab_sig(3,:);
save('FitnessGenerations','FG');







end
















%W=weights(1,:);
 %disp(W);
%[evaluationIndividu]= Fct_evaluation(im,r,niter,coefs,W,knots,subdiv1,cseuil)


% figure;colormap(jet);imagesc(im);hold on;colormap(jet); [p,nurbscor]=nrbplot(nurbs,subdiv1,'m');colormap(jet);
% 
% pause(1);
% delete(nurbscor);
%%
%figure;
% colormap(jet);
% imagesc(im);
% 
% coefs1 = zeros(4,niter); % Passage en coordonnees homogenes
% coefs1(1,:) = coefs(1,:) .* weights(1,:);
% coefs1(2,:) = coefs(2,:) .* weights(1,:);
% coefs1(4,:) = weights(:,:); 
% 
% 
% nurbs1 = nrbmak2(coefs1,knots);   % Construction de la structure NURBS
% 
% colormap(jet);
% plot(coefs(1,:),coefs(2,:),'*r');%% les sommets de la courbe � approximer (formant le polygone de controle)
% hold on
% 
% colormap(jet);
% [p11,nurbs_cor1] = nrbplot(nurbs1,subdiv1,'m');%% Construction de la courbe NURBS
% hold on;
% colormap(jet);
% saveas(gcf,'trajectoireInitiale.tif');
% end