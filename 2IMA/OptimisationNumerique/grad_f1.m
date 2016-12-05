function y = grad_f1( x )
%UNTITLED6 Summary of this function goes here
%   Detailed explanation goes here
x1 = x(1); x2 = x(2); x3 = x(3);
y = [6*x1+2*x2+4*x3 - 12;
       2*x1+4*x2+6*x3 - 12;
       4*x1+6*x2+2*x3 - 12];

end

