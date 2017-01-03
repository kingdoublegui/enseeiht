function [ sval, lamda ] = moreSorensen( g, H, delta )
%UNTITLED4 Summary of this function goes here
%   Detailed explanation goes here

% Existence d'une solution interieure
syms s;
phi = q(0, g, H, s);
sval = newtonPlus(phi, 0, delta);

if (H >= 0) && (H*sval == -g)
    lambda = 0;
else
    [Q, L] = eig(H);
    if Q(:, 1)*g ~= 0
        syms l;
        
    else
        
    end
end

end

