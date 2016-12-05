debut = 1;
fin = lth;
for i=1:nombre_objets
    x1 = xunit(debut:fin);
    y1 = yunit(debut:fin);
    robot=plot(x1,y1,couleur(i,:));
    drawnow;
    pause(pasTemps);
    delete(robot);
    debut = debut + lth;
    fin = fin + lth;   
end