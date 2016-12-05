function [xnew,ynew] = chgtrepere(xold,yold,x1,y1,x2,y2,l)
% l est la distance euclidienne de (x1,y1) à (x2,y2)
 xnew = double((x2-x1)*(xold-x1) + (y2-y1)*(yold-y1))/double(l);
 ynew = double((y2-y1)*(xold-x1) - (x2-x1)*(yold-y1))/double(l);
 

% xnew=100;
% ynew=100;
end