function [weights] = InitPopulation(PopulationSize,niter,max_weight)
% Cette fonction initialise la population,
% elle retoure une matrice dont chaque ligne represente 
% un indivudu (vecteur poids) genere aleatoirement
% les poids appartiennent à [0.1, max_weight+0.1[
%
for i=1:PopulationSize
    weights(i,:)= max_weight*rand(1,niter)+0.1;
end

end 