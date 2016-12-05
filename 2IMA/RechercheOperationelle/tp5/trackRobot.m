function []= trackRobot(Eval,nbPrbCur,nbPrbCol,indice_elu,sol,ti,cseuil,subdiv1,im,r)


%if sol==0 % Il n existe pas une solution au problème
     % Localisation des points critiques courbure
     [vect_CC]=DetectionCC(ti,Eval(indice_elu).courbure,cseuil,nbPrbCur);   
     % Localisation des points critiques courbure sur la trajectoire générée
     for k=1:nbPrbCur
        pf = nrbeval (Eval(indice_elu).nurbs, vect_CC(k)*(1/subdiv1));
        colormap(jet);
        plot(pf(1,1),pf(2,1),'.k');
        hold on;
        colormap(jet);
        drawRobot(pf(1,1),pf(2,1),r,'r');
        hold on;
     end
     % Localisation des points critiques collision
     [vect_CO] = DetectionCO(im,Eval(indice_elu).nurbs,subdiv1,r,nbPrbCol) ;
     % Localisation des points critiques collision sur la trajectoire générée
     for k=1:nbPrbCol
        pf = nrbeval (Eval(indice_elu).nurbs, vect_CO(k)*(1/subdiv1));
        colormap(jet);
        plot(pf(1,1),pf(2,1),'.k');
        hold on;
        colormap(jet);
        drawRobot(pf(1,1),pf(2,1),r,'g');
        hold on;
     end
%end
 

end

