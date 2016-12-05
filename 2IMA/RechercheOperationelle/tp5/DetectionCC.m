function  [vect_CC] = DetectionCC(ti,courbure,cseuil,nbPrbCur)  

vect_CC=zeros(1,nbPrbCur);
i=1;
[pks,locs] = findpeaks(abs(courbure));
nb_extremum=size(pks,2);
for j=1:nb_extremum
    if (abs(courbure(1,locs(j)))> cseuil)
       vect_CC(1,i)=locs(j);i=i+1;
       if courbure(locs(j))<0 
           plot(ti(locs(j)),-pks(j),'.k');hold on;
       else
           plot(ti(locs(j)),pks(j),'.k');hold on;
       end
    end
end

end

