function [gainx, gainy] = gain(x,y)
gainx = -x*(x+y-3);
gainy = -y*(x+y-3);
