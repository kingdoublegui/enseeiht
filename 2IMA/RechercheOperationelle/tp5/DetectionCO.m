
function  [vect_CO] = DetectionCO(im,nurbs,subdiv1,r,nbPrbCol) 


vect_CO = zeros(1,nbPrbCol);
imscene=im;
figure;colormap(jet);
imagesc(im);hold on;colormap(jet); 
[p,nurbscor]=nrbplot(nurbs,subdiv1,'m');colormap(jet);

j=1;
[d1,d2]=size(p);
    for i=1:d2 
        k = round(p(1,i)); % numero de colonne du pixel trajectoire
        l = round(p(2,i));% numero de ligne du pixel trajectoire
        % test de probabilite du blocage dans un voisinage 8 pixels +-r du pixel trajectoire
        blocage1 = ((imscene(l,k)== 7)|(imscene(l,k+r)== 7)|(imscene(l+r,k+r)== 7)|(imscene(l-r,k+r)== 7)|...
        (imscene(l-r,k)== 7)|(imscene(l-r,k-r)== 7)|(imscene(l,k-r)== 7)|(imscene(l+r,k-r)== 7)|(imscene(l+r,k)== 7));
        if blocage1
            vect_CO(1,j)=i;j=j+1;     
        end

    end

close;

end