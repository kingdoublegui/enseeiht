% Calcul du pixel (ipp,jpp) d'un squelette
% qui est le plus proche d'un pixel donné (iref,jref)
%L'image im est telle que :
% im(i,j) ~= 7 si (i,j) appartient au squelette
% im(i,j) = 7 : si (i,j) appartient au fond
%
dmin = inf;
for i=1:nlig
    for j=1:ncol
        if im(i,j) ~= 7
            % (i,j) est un point du squelette
            dx2 = double(iref - i )^2;
            dy2 = double(jref - j)^2;
            d = sqrt(dx2 + dy2);
            if d < dmin
                dmin = d;
                ipp = i;
                jpp = j;
            end
        end
    end
end
[x y] = bresenham(iref,jref,ipp,jpp);
for i = 1:size(x,1)
        im(x(i),y(i)) = 0;
end
% for i = 1:size(x,1)
%         % Calcul de abs(x) , abs(y) , nvois, im(x(i),y(i)) = abs(xx) + abs(yy)
%             nvois = 0;
%             xx = 0;
%             yy = 0;
%             for k = int16(1):int16(8)
%                 ii = x(i) + itab(k);
%                 jj = y(i) + jtab(k);
%                 if im(ii,jj) ~= 7
%                     nvois = nvois + int16(1);
%                     xx = xx + itab(k);
%                     yy = yy + jtab(k);
%                 end
%             end
%             im(x(i),y(i)) = abs(xx) + abs(yy);
% end
