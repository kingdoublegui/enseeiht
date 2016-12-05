% Creer un chemin segment de droite allant de (idep,jdep) à (iarr,jarr)
%
%%
load imscene % chargement de la scène
imagesc(imscene);
hold on
%% Lecture des positions de depart et d'arrivee

disp ('*** Pointez le point de depart du robot ! ***');% Lecture point de depart du robot
p = ginput(1);
idep = round(p(2));
jdep = round(p(1));

disp ('*** Pointez le point d''arrivee du robot ! ***');% Lecture point d'arrivee du robot
p = ginput(1);
iarr = round(p(2));
jarr = round(p(1));
%%
subd = 1000; % nombre de points de subdivision
xy2 = zeros(2,subd);
for p=1:subd
    mu = (p-1)/subd;
    xy2(2,p) = idep + mu*(iarr-idep);
    xy2(1,p) = jdep + mu*(jarr-jdep);
end
robot = plot(xy2(1,:),xy2(2,:),'r');