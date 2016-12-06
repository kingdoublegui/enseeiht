function pas = pasCauchy(g, H, delta)
    % Calcul du pas de Cauchy
    % q(s) = g'*s + (1/2)s'*H*s
    % p(t) = q(-t*g) -> intervalle non intuitif
    % p(alpha) = q(-alpha*(delta*g/norm(g))) avec alpha entre 0 et 1
    
    c = -delta * (g / norm(g))
    % Courbe convexe, donc admet un minimum
    if (c' * H * c >= 0)
        p0 = 0;
        p1 = g' * c + (1/2) * c' * H * c;
        if (p0 < p1)
            pas = 0;
        else
            pas = -delta*g;
        end
    % Courbe concave
    else
        alpha0 = norm(c' \ (c' * H * c));
        if (alpha0 <= 1)
            pas = alpha0 * delta * norm(g);
        else
            p0 = 0;
            p1 = g' * c + (1/2) * c' * H * c;
            if (p0 < p1)
                pas = 0;
            else
                pas = delta;
            end
        end
    end