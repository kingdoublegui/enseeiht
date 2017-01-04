function [ t, lambda ] = newtonPlus( phi, lambda_min, lambda_max )
%Methode de Newton pour les equations non lineaire
epsilon = 10^-3;

t = 0;
lambda = lambda_max;

syms l;
phi_lambda_min = eval(subs(phi, l, lambda_min));
phi_lambda_max = eval(subs(phi, l, lambda_max));

while ~((abs(phi_lambda_min)  < epsilon) || (abs(phi_lambda_max)  < epsilon))
    phi_p = gradient(phi, l);
    phi_p_lambda = eval(subs(phi_p, l, lambda));
    
    
    phi_lambda = eval(subs(phi, l, lambda));
    lambda_n = lambda + phi_lambda/phi_p_lambda;
    
    phi_lambda_n = eval(subs(phi, l, lambda_n));
    if (lambda_min < lambda_n) && (lambda_n < lambda_max) && (abs(phi_lambda_n) < 1/2*abs(phi_lambda))
        lambda = lambda_n;
    else
        lambda_d = (lambda_min+lambda_max)/2;
        phi_lambda_d = eval(subs(phi, l, lambda_d));
        if phi_lambda_d*phi_lambda_max <= 0
            lambda_min = lambda_d;       
            phi_lambda_min = eval(subs(phi, l, lambda_min));
        else
            lambda_max = lambda_d;
            phi_lambda_max = eval(subs(phi, l, lambda_max));
        end;
        lambda = lambda_d;
    end
end

if abs(phi_lambda_min) < abs(phi_lambda_max)
    lambda = lambda_min;
else
    lambda = lambda_max;
end

end

