function pas = pasCauchy(g, H, delta)
    % Calcul du pas de Cauchy
    % q(s) = g'*s + (1/2)s'*H*s
    % p(t) = q(-t*g) -> intervalle non intuitif
    % p(alpha) = q(-alpha*(delta*g/norm(g))) avec alpha entre 0 et 1

    c = -delta * (g / norm(g));
    % Courbe concave, donc admet un minimum
    if (c' * H * c >= 0)
        p0 = 0;
        p1 = g' * c + (1/2) * c' * H * c;
        if (p0 < p1)
            pas = zeros(size(c));
        else
            pas = c;
        end
    % Courbe convexe
    else
        alpha0 = norm(c' \ (c' * H * c));
        if (alpha0 <= 1)
            pas = -delta * g/ norm(g);
        else
            p0 = 0;
            p1 = g' * c + (1/2) * c' * H * c;
            if (p0 < p1)
                pas = zeros(size(c));
            else
                pas = c;
            end
        end
    end
end