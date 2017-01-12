function nb = nbprof(x)
    nb = 0;
    if not(isempty(find(x)))
        nb = find(x);
    end
end