function [ t, x ] = regionConfiance( f, x0, delta0, delta_max, gamma1, gamma2, eta1, eta2 )
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here

t = 0;

x =x0;

delta = delta0;
nbIterations = 10;
k = 0;

var = sym('x', [length(x) 1]);
 
grad = gradient(f, var);
hess = hessian(f, var);

while k < nbIterations
    g = eval(subs(grad, var, x));
    H = eval(subs(hess, var, x));
    s = pasCauchy(g, H, delta);
    
    c = num2cell(x);
    f_x = f(c{:});
    c = num2cell(x+s);
    f_xs = f(c{:});
    m_x = f_x;
    m_xs = q(f_x, g, H, s);
    
    rho = (f_x-f_xs)/(m_x-m_xs);
    
    if rho > eta1
        x = x+s;
    end
    if rho > eta2
        delta = min(gamma2*delta, delta_max);
    else if rho < eta1
         delta = gamma1*delta;
        end
    end
    k = k+1;
    t = k;
end

end

