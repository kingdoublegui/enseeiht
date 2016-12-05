% figure;
% x=2,y=4;r=1;
% theta = 0 : (2 * pi / 10000) : (2 * pi);
% pline_x = r * cos(theta) + x;
% pline_y = r * sin(theta) + y;
% plot(pline_x, pline_y, '-');
% hold off
% 
% %%%%%%%%%%%%%%%%%%%%%%%%
% 
% %e
% %r= desired radius
% %x = x coordinates of the centroid
% %y = y coordinates of the centroid
function []=drawRobot(x,y,r,color)

th = 0:pi/50:2*pi;
xunit = r * cos(th) + x;
yunit = r * sin(th) + y;
plot(xunit, yunit, color);
end