function y = q( f, g, H, s)
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here
y = f + g.'*s + 1/2*s.'*H*s;

end

