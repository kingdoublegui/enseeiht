function s = moreSorensen(g, H, delta)

sn = H \ (-g);
[vecteursPropres, valeursPropres] = eig(H);
lambda = diag(valeursPropres);
[lambda, indices] =  sort(lambda);

%% Solution intérieure
if (norm(sn) < delta) && (all(lambda > 0))
    s = sn;
%% Solution a la frontière
else
    q = vecteursPropres(:,indices);
    qg = q' * g;
    n = size(q, 2);
    
    if qg(1) ~= 0
        % Cas simple
        disp('SIMPLE');
        s = equationMoreSorensen(q, g, qg, lambda, delta);
    else
        s1 = - sum((q(:,2:n)'*g) ./ (lambda(2:n) - lambda(1)) * q(:,2:n), 2);
        if norm(s1) > delta
            % Cas intermédiaire
            disp('INTERMEDIAIRE');
            s = equationMoreSorensen(q(2:n), g, qg(2:n), lambda(2:n), delta);
        else
            % Cas difficile
            disp('DIFFICILE');
            s = s1 + sqrt(delta^2 - norm(s1)^2)*q(:,1);
        end
    end
end

end

