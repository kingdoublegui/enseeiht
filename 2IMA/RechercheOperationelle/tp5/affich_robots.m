switch nombre_objets
    case 1
        robot=plot(xunit,yunit,couleur(1,:));
    case 2
        x1 = xunit(1:lth);
        y1 = yunit(1:lth);
        x2 = xunit(lth+1:end);
        y2 = yunit(lth+1:end);
        robot=plot(x1,y1,couleur(1,:),x2,y2,couleur(2,:));
    case 3
        x1 = xunit(1:lth);
        y1 = yunit(1:lth);
        x2 = xunit(lth+1:2*lth);
        y2 = yunit(lth+1:2*lth);
        x3 = xunit(2*lth+1:end);
        y3 = yunit(2*lth+1:end);
        robot=plot(x1,y1,couleur(1,:),x2,y2,couleur(2,:),x3,y3,couleur(3,:));
    case 4
        x1 = xunit(1:lth);
        y1 = yunit(1:lth);
        x2 = xunit(lth+1:2*lth);
        y2 = yunit(lth+1:2*lth);
        x3 = xunit(2*lth+1:3*lth);
        y3 = yunit(2*lth+1:3*lth);
        x4 = xunit(3*lth+1:end);
        y4 = yunit(3*lth+1:end);
        robot=plot(x1,y1,couleur(1,:),x2,y2,couleur(2,:),x3,y3,couleur(3,:),x4,y4,couleur(4,:));
    case 5
        x1 = xunit(1:lth);
        y1 = yunit(1:lth);
        x2 = xunit(lth+1:2*lth);
        y2 = yunit(lth+1:2*lth);
        x3 = xunit(2*lth+1:3*lth);
        y3 = yunit(2*lth+1:3*lth);
        x4 = xunit(3*lth+1:4*lth);
        y4 = yunit(3*lth+1:4*lth);
        x5 = xunit(4*lth+1:end);
        y5 = yunit(4*lth+1:end);
        robot=plot(x1,y1,couleur(1,:),x2,y2,couleur(2,:),x3,y3,couleur(3,:),x4,y4,couleur(4,:),x5,y5,couleur(5,:));
    case 6
        x1 = xunit(1:lth);
        y1 = yunit(1:lth);
        x2 = xunit(lth+1:2*lth);
        y2 = yunit(lth+1:2*lth);
        x3 = xunit(2*lth+1:3*lth);
        y3 = yunit(2*lth+1:3*lth);
        x4 = xunit(3*lth+1:4*lth);
        y4 = yunit(3*lth+1:4*lth);
        x5 = xunit(4*lth+1:5*lth);
        y5 = yunit(4*lth+1:5*lth);
        x6 = xunit(5*lth+1:end);
        y6 = yunit(5*lth+1:end);
        robot=plot(x1,y1,couleur(1,:),x2,y2,couleur(2,:),x3,y3,couleur(3,:),x4,y4,couleur(4,:),x5,y5,couleur(5,:),x6,y6,couleur(6,:));
    otherwise
        error('Trop d''objets a afficher !');
end