%% Echantillonnage des chemins, calcul de leurs longueurs et tracés de ces chemins
couleur = char('red' , 'blue','cyan','magenta', 'yellow', 'black' , 'white', 'green');
%
xy = zeros(3,subd,nombre_objets); % xy(1:2,:,i) : echantillons du chemin i
xy2 = zeros(2,subd);
s1 = zeros(1,nombre_objets); % s1(i) : longueur du chemin i
%
hdepl = figure;
imagesc(im);
hold on
for i = 1:nombre_objets
    if strcmp(T(i).chemin,'DROITE')
        % Le chemin est une droite
        idep = T(i).depart(1);
        jdep = T(i).depart(2);
        iarr = T(i).arrivee(1);
        jarr = T(i).arrivee(2);
        %
        for p=1:subd
            mu = (p-1)/subd;
            xy2(2,p) = idep + mu*(iarr-idep);
            xy2(1,p) = jdep + mu*(jarr-jdep);
        end
        xy(1:2,:,i) = xy2;
        plot(xy2(1,:),xy2(2,:),couleur(i,:));
    else
        if strcmp(T(i).chemin,'NURBS')
            % Le chemin est une NURBS
            nurbsf = T(i).nurbs;
            xy(:,:,i) = nrbeval(nurbsf,linspace(0.0,1.0,subd));
            xy2 = xy(1:2,:,i);
            nrbplot(nurbsf,subd,couleur(i,:));
        else
            disp('ERREUR');
        end 
    end
    % arclength: compute arc length of any curve represented as a sequence of points
    s1(i) =arclength(xy(1,:,i),xy(2,:,i)); % calcul longueur chemin i 
end