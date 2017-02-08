function s = moreSorensen(g, H, delta)
% MORESORENSEN Determine min g's+1/2*s'Hs pour s dans R^n
% S = MORESORENSEN(g, H, delta)
% PARAMETRES
%   g: gradient de la fonction a minimiser
%   H: hessienne de la fonction a minimiser
%   delta: region de confiance
% SORTIE
%   s: solution du probleme

sn = H \ (-g);
[vecteursPropres, valeursPropres] = eig(H);
lambda = diag(valeursPropres);
[lambda, indices] =  sort(lambda);

%% Solution interieure
if (norm(sn) < delta) && (all(lambda > 0))
    s = sn;
%% Solution a la frontiere
else
    q = vecteursPropres(:,indices);
    qg = q' * g;
    n = size(q, 2);
    
    if qg(1) ~= 0
        % Cas simple
        s = equationMoreSorensen(q, g, qg, lambda, delta);
    else
        s1 = - sum((q(:,2:n)'*g) ./ (lambda(2:n) - lambda(1)) * q(:,2:n), 2);
        if norm(s1) > delta
            % Cas intermédiaire
            s = equationMoreSorensen(q(2:n), g, qg(2:n), lambda(2:n), delta);
        else
            % Cas difficile
            s = s1 + sqrt(delta^2 - norm(s1)^2)*q(:,1);
        end
    end
end

end

