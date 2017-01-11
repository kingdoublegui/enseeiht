function [ x, lambda, mu ] = lagrangien( f, x0, lambda0, c, mu0, tau, etac0, alpha, beta )
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here

var = sym('x', [length(x0) 1]);

x = x0;
lambda = lambda0;
epsilon0 = 1/mu0;
epsilon = epsilon0;
mu = mu0;
eta = etac0/mu0^alpha;

nIter = 10;

k = 1;
while k <= nIter
    L = f(var(1), var(2), var(3)) + lambda'*c(var(1), var(2), var(3)) + mu/2*norm(c(var(1), var(2), var(3)))^2;
    [ t, x ] = newton(L, x);
    
    % Critere de convergence non satisfait
    if k <= nIter
       k = k+1;
       v = num2cell(x);
       c_x = c(v{:});
       if c_x <= eta
           lambda = lambda + mu*c_x;
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

