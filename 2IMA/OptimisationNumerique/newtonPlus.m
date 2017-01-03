function [ t, lambda ] = newtonPlus( phi, lambda_min, lambda_max )
%Methode de Newton pour les equations non lineaire
epsilon = 10^-3;

lambda = lambda_max;

while ~((abs(phi(lambda_min))  < epsilon) || (abs(phi(lamdaMax))  < epsilon))
    syms l;
    phi_p = gradient(phi, l);
    phi_p_lambda = eval(subs(phi_p, l, lambda));
    
    lambda_n = lambda + phi(lambda)/phi_p_lambda;
    
    if (lambda_min < lambda_n) && (lambda_n < lambda_max) && (abs(phi(lambda_n)) < 1/2*abs(phi(lambda)))
        lambda = lambda_n;
    else
        lambda_d = (lambda_min+lambda_max)/2;
        if phi(lambda_d)*phi(lambda_max) <= 0
            lambda_min = lambda_d;
        else
            lambda_max = lambda_d;
        end;
        lambda = lambda_d;
    end
end

if abs(phi(lambda_min)) < abs(phi(lambda_max))
    lambda = lambda_min;
else
    lambda = lambda_max;
end

end

