function [ t, x ] = regionConfiance( f, x0, delta0, gamma1, gamma2, eta1, eta2 )
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here

delta_max = 10*norm(x0, 1);
t = 0;

x =x0;

delta = delta0;
nbIterations = 1000;
k = 0;

epsilon = 10^-6;

var = sym('x', [length(x) 1]);
 
grad = gradient(f, var);
hess = hessian(f, var);

g = eval(subs(grad, var, x));
H = eval(subs(hess, var, x));
s = moreSorensen(g, H, delta);
c = num2cell(x);

testArret0 = false;
testArret1 = false;
testArret2 = false;
g0 = eval(subs(grad, var, x0));

while (k < nbIterations) && ~testArret0
    x_ancien = x;
    f_x = f(c{:});
    c = num2cell(x+s);
    f_xs = f(c{:});
    m_x = f_x;
    m_xs = q(f_x, g, H, s);
    
    rho = (f_x-f_xs)/(m_x-m_xs);

    if rho >= eta1
        x = x+s;
    end
    if rho >= eta2
        delta = min(gamma2*delta, delta_max);
    else if rho < eta1
         delta = gamma1*delta;
        end
    end
    
    g = eval(subs(grad, var, x));
    H = eval(subs(hess, var, x));
    s = moreSorensen(g, H, delta);
    c = num2cell(x);
    
    k = k+1;
    t = k;
    testArret0 = norm(g) <= epsilon*(norm(g0) + sqrt(eps));
    testArret1 = norm(x - x_ancien) <= epsilon*(norm(x_ancien) + sqrt(eps));
    testArret2 = norm(f(c{:}) - f_x) <= epsilon*(norm(f_x) + sqrt(eps));
end

end

