function [y] = normS(qg, lambda, l)
%%
%   normS = norm(s(lambda))^2 et s(x) = -sum(qg_i / (lambda_i + l))

denominateur = lambda + l;
frac = qg ./ denominateur;
y = sum(frac.^2);

end