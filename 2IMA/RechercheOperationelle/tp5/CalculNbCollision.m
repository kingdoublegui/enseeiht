

function [ensemble_collision,nb_CO]=CalculNbCollision(im,nurbs,subdiv1,r)
% Cette fonction retourne le nombre de points qui ne verifient pas la
% non-collision

nb_CO=0;
imscene=im;
figure;colormap(jet);
imagesc(im);hold on;colormap(jet); 
[p,nurbscor]=nrbplot(nurbs,subdiv1,'m');colormap(jet);


[d1,d2]=size(p);
    nb_col=1;
    for i=1:d2 
        k = round(p(1,i)); % numero de colonne du pixel trajectoire
        l = round(p(2,i));% numero de ligne du pixel trajectoire
        % test de probabilite du blocage dans un voisinage 8 pixels +-r du pixel trajectoire
        blocage1 = ((imscene(l,k)== 7)|(imscene(l,k+r)== 7)|(imscene(l+r,k+r)== 7)|(imscene(l-r,k+r)== 7)|...
        (imscene(l-r,k)== 7)|(imscene(l-r,k-r)== 7)|(imscene(l,k-r)== 7)|(imscene(l+r,k-r)== 7)|(imscene(l+r,k)== 7));
        if blocage1
            nb_CO = nb_CO+1; 
            ensemble_collision(1,nb_col)= p(1,i);
            ensemble_collision(2,nb_col)= p(2,i);
            nb_col=nb_col+1;
        end

    end
if nb_CO==0
    ensemble_collision=0;
end
close;
%delete(nurbscor);

end