function s = pasCauchy(g, H, delta)
% PASCAUCHY Determine le pas de Cauchy
% S = PASCAUCHY(g, H, delta)
% PARAMETRES
%   g: gradient de la fonction a minimiser
%   H: hessienne de la fonction a minimiser
%   delta: region de confiance
% SORTIE
%   s: pas de Cauchy

    c = g' * H * g;
    ng = norm(g);
    
    %% Calcul de s
    % Cas convexe
    if c > 0
        t = ng^2 / c;
        if t > delta / ng
            t = delta / ng;
        end
    % Cas concave
    else
        if ng == 0
            t = 1;
        else
            t = delta / ng;
        end
    end
    
    s = - t * g;
end