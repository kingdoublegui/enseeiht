% Cette fonction projette un point de la scène(image) sur un repère 2D
% (x,y)---------------->(x,dim(2)-y)
% nurbs.coefs:  (xw,yw,0,w)----->(xw,(100-y)w,0,w)
function [nurbs] = TransformationImageRepere (nurbs1)

weights = nurbs1.coefs(4,:);
coefs3 = nurbs1.coefs;
coefs3(2,:) = (100-(nurbs1.coefs(2,:)./weights(1,:))) .* weights(1,:);
nurbs = nrbmak2(coefs3,nurbs1.knots); 

end