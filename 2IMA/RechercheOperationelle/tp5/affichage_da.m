%% Tracés des positions de départ et d'arrivée des objets
%
% Visualiser le robot a son point de depart
%
th = 0:3.14/50:2*3.14; % angles pour tracer un cercle
lth = length(th);
figure(hdepl);
%
for i = 1:nombre_objets
    % Visualisation position de depart de l'objet i
    idep = T(i).depart(1);
    jdep = T(i).depart(2);
    diametre_robot = T(i).diametre_robot;
    %
    xunit = double(idep) + diametre_robot * cos(th);
    yunit = double(jdep) + diametre_robot * sin(th);
    hold on;
    depart=plot(yunit, xunit,couleur(i,:)); % image -> figure  (x,y) -> (y,x)
    %
    %  Visualisation position d'arrivee de l'objet i
    iarr = T(i).arrivee(1);
    jarr = T(i).arrivee(2);
    %
    xunit = double(iarr) + diametre_robot * cos(th);
    yunit = double(jarr) + diametre_robot * sin(th);
    hold on;
    arrivee=plot(yunit, xunit, couleur(i,:)); % image -> figure  (x,y) -> (y,x)
end