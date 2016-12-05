function y = q( f, grad, hess, t, x)
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here
s = -t*grad(x);
y = f(x) + grad(x).'*s + 1/2*s.'*hess(x)*s;

end

