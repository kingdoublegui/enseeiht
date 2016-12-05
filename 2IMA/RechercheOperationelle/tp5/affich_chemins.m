load imscene
load T
load nombre_objets
imagesc(imscene);
hold on
couleur = char('red' , 'blue','cyan','magenta', 'yellow', 'black' , 'white', 'green');
for i = 1:nombre_objets
    nurbsf = T(i).nurbs;
    [p1f,~] = nrbplot(nurbsf,300,couleur(i,:));
end