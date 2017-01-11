function [ x, lambda, mu ] = lagrangien( f, x0, lambda0, c, u0, tau, etac0, alpha, beta )
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here

var = sym('x', [length(x0) 1]);

x = x0;
lambda = lambda0;
epsilon0 = 1/u0;
epsilon = epsilon0;
eta = etac0/u0^alpha;

nIter = 50;

k = 1;
while k <= nIter
    L = f + lambda'*c + u/2*norm(c)^2;
    [ t, x ] = newton(L, x);
    
    % Critere de convergence non satisfait
    if k <= nIter
       k = k+1;
       if c(x_k1) <= eta_k
           lambda = lambda + mu*c(x_k1);
           mu = mu;
           epsilon = epsilon/mu;
           eta = eta/mu^beta;
       else
           lambda = lambda;
           mu = tau*mu;
           epsilon = epsilon0/mu;
           eta = etac0/mu^beta;
       end
    end
end

end

