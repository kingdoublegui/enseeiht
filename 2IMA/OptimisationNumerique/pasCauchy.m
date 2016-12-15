function s = pasCauchy(g, H, delta)
    c = g' * H * g;
    ng = norm(g);
    
    % Calcul de s
    if c > 0
        t = ng^2 / c;
        if t > delta / ng
            t = delta / ng;
        end
    else
        if ng == 0
            t = 1;
        else
            t = delta / ng;
        end
    end
    
    s = - t * g;
end