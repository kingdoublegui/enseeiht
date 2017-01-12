function capacity = fromLineToMatrix(NSUC, SUC, LONG)
    n = size(NSUC, 2);
    capacity = zeros(n);
    iNoeud = 1;
    for i=1:sum(NSUC)
        while (NSUC(iNoeud) == 0)
           iNoeud = iNoeud+1;
        end
        capacity(iNoeud, SUC(i)) = LONG(i);
        
        if i >= sum(NSUC(:, 1:iNoeud))
            iNoeud = iNoeud+1;
        end
    end
end