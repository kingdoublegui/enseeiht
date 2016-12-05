function [ r, resultat ] = pasCauchy( grad, hess, delta )
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here

r = -1;

c = delta * grad/norm(grad);

% Si la matrice est semi definie positive
if (c.'*hess*c >= 0)
    % Si le minimum est dans l'intervalle
    m =  zeros(size(grad)) \ hess(x);
    if (true)
        
    else
        r = max(1, delta); % ce serait plus f(delta) d'aillleurs
    end;
else
    r = max(1, delta);
    
end
% Cas concave

end

