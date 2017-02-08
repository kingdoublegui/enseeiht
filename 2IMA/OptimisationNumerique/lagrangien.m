function [ t, x, lambda, mu ] = lagrangien( f, x0, lambda0, c, mu0, tau, etac0, alpha, beta )
% LAGRANGIEN Resolution du probleme min f(x) sous la contrainte c
% [ T, X, LAMBDA, MU ] = LAGRANGIEN( F, X0, LAMBDA0, C, MU0, TAU, ETAC0, ALPHA, BETA )
% PARAMETRES
%   f: fonction a minimiser
%   x0: point de depart de la minimisation
%   lambda0: Importance de la contrainte
%   c: contrainte d'egalite
%   mu0: penalite initiale
%   tau: facteur de penalite
%   etac0: importance initiale de la contrainte
%   alpha: coefficient de penalite
%   beta: coefficient de non penalite
% SORTIE
%   t: nombres d'iterations
%   x: point solution
%   lambda: coefficient solution
%   mu: penalite finale

var = sym('x', [length(x0) 1]);
arrayfun(@(x) assume(x, 'real'), var)

epsilonp = 1/10^6;

x = x0;
lambda = lambda0;
epsilon0 = 1/mu0;
epsilon = epsilon0;
mu = mu0;
eta = etac0/mu0^alpha;

nIter = 50;
k = 0;
converge = false;
% Tant qu'il n'y a pas convergence
while ~converge
    x_ancien = x;
    v = num2cell(var);
    
    % On calcule le lagrangien augmente
    L = f(v{:}) + lambda'*c(v{:}) + mu/2*norm(c(v{:}))^2;
    L = simplify(L);
    [ ~, x ] = newton(matlabFunction(L), x);
    
    % Calcul du gradient du Lagrangien
    gradL = gradient(matlabFunction(L), var);
    gL = eval(subs(gradL, var, x));
    xc = num2cell(x);
    
    % Une fois la precision atteinte ou un avancement nul ou trop
    % d'iterations faites
    converge = (norm(gL) < epsilonp && norm(c(xc{:})) < epsilonp)...
        || norm(x_ancien - x) < epsilonp...
        || k >= nIter;
    % Critere de convergence non satisfait
    if ~converge
       k = k+1;
       v = num2cell(x);
       c_x = c(v{:});
       % Si on est dans le domaine de la contrainte
       if c_x <= eta
           lambda = lambda + mu*c_x;
           mu = mu;
           epsilon = epsilon/mu;
           eta = eta/mu^beta;
       % Sinon on est hors domaine et on penalise
       else
           lambda = lambda;
           mu = tau*mu;
           epsilon = epsilon0/mu;
           eta = etac0/mu^beta;
       end
    end
end

t = k;

end

