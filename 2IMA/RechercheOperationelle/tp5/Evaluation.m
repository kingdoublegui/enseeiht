
function [Eval,tab_sol,sigmaMoy,sigmaMin,W_Elu,indice_elu,Wfitness]= Evaluation(im,r,niter,coefs,weights,knots,subdiv1,cseuil)

%% Evaluation
sigmaMoy=0;
T=size(weights,1); %T:taille de la population
for i=1:T
 W=weights(i,:);
[ensemble_collision,evaluationIndividu]= Fct_evaluation(im,r,niter,coefs,W,i,knots,subdiv1,cseuil);
 Eval(i)= evaluationIndividu;
 sigmaMoy=sigmaMoy+evaluationIndividu.sigma;
end
sigmaMoy=sigmaMoy/T;
%% l'individu meilleur
%t=size(Eval,2); 
alpha = 0.2;
beta = 0.8;
tab_sol=zeros(2,T);
for i=1:T
    tab_sol(1,i)=Eval(i).Id;
    tab_sol(2,i)=Eval(i).nb_CO + Eval(i).nb_CC + alpha * (1/Eval(i).arclen) + beta * Eval(i).sigma ;
    %Eval(i).fitness = Eval(i).nb_CO + Eval(i).nb_CC + alpha *(1/ Eval(i).arclen) + beta * Eval(i).sigma ;
    %tab_sol(3,i)=Eval(i).sigma;
end

% % % % Tri croissant selon le sigma min
% % % [pasbesoin,idx] = sort(tab_sol(3,:));
% % % tab_sol = tab_sol(:,idx);
% % % % Tri croissant selon le nombre de points critiques
% % % [pasbesoin,idx] = sort(tab_sol(2,:));
% % % tab_sol = tab_sol(:,idx);

% Tri croissant selon la valeur de fitness
[pasbesoin,idx] = sort(tab_sol(2,:));
 tab_sol = tab_sol(:,idx);



% determination indiv meilleur d sa population
indice_elu=tab_sol(1,1);
W_Elu=weights(indice_elu,:);
Wfitness=tab_sol(2,1);
sigmaMin=Eval(indice_elu).sigma; % c est l'ecart type des courbures de l individi elu
Wlength=  Eval(indice_elu).arclen;
nbPrbCur= Eval(indice_elu).nb_CC;
nbPrbCol= Eval(indice_elu).nb_CO;
%disp(['Wfitness:  ' num2str(Wfitness)]);
%disp(['CO:  ' num2str(nbPrbCol)]);
%disp(['CC:  ' num2str(nbPrbCur)]);
%disp(['lgth:  ' num2str(Wlength)]);
%disp(['sig:  ' num2str(sigmaMin)]);





