function y = grad_f2( x )
%UNTITLED6 Summary of this function goes here
%   Detailed explanation goes here
x1 = x(1); x2 = x(2);
y = [2*x1 - 400*x1*(- x1^2 + x2) - 2;
        - 200*x1^2 + 200*x2];

end

