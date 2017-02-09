function [t, x] = newton( f, x0 )
% NEWTON Determine min f(x) pour x dans R^n
% [T, X] = NEWTON( F, X0 )
% PARAMETRES
%   f: fonction a minimiser
%   x0: point de depart de la minimisation
% SORTIE
%   t: nombre d'iterations
%   x: point solution

% t = ordre si solution trouvee et -1 si nbIterations atteints

t = -1;
% nb iterations apres lesqulles on s'arrete
nbIterations = 20;

x = x0;

var = sym('x', [length(x) 1]);

grad = gradient(f, var);
g = eval(subs(grad, var, x));
hess = hessian(f, var);
H = eval(subs(hess, var, x));

k = 0;

epsilon = 1/10^6;

testArret1 = false;
testArret2 = false;
testArret3 = false;
testArret4 = false;

while ~testArret3
    % Calcul de d
    d = - H \ g;
    % Mise a jour des variables
    x_ancien = x;
    x = x+d;
    g = eval(subs(grad, var, x));
    H = eval(subs(hess, var, x));
    k = k+1;
    t = k;
    
    c = num2cell(x);
    c_ancien = num2cell(x_ancien);
    
    testArret1 = norm(g) <= eps(g + epsilon);
    testArret2 = norm(x-x_ancien) <= eps(norm(x_ancien) + epsilon);
    testArret3 = norm(f(c{:})-f(c_ancien{:})) <= eps(abs(f(c_ancien{:})) + epsilon);
    testArret4 = k >= nbIterations;
end;

end

