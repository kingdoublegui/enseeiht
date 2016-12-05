function H = hess_f2( x )
%UNTITLED6 Summary of this function goes here
%   Detailed explanation goes here
x1 = x(1); x2 = x(2);
H = [1200*x1^2 - 400*x2 + 2, -400*x1;
                                   -400*x1,       200];

end