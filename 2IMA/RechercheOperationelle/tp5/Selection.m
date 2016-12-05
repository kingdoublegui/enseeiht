function [Parents]=Selection(tab_sol,modif_rate)

%Eval1=Eval;
t=size(tab_sol,2); 
%tab_pcCrit=zeros(3,t);
% for i=1:t
%     tab_pcCrit(1,i)=Eval(i).Id;
%     tab_pcCrit(2,i)=Eval(i).nb_CO + Eval(i).nb_CC;
%     tab_pcCrit(3,i)=Eval(i).sigma;
% end
% 
% % Tri croissant selon le sigma min
% [pasbesoin,idx] = sort(tab_pcCrit(3,:));
% tab_pcCrit = tab_pcCrit(:,idx);
% % Tri croissant selon le nombre de points critiques
% [pasbesoin,idx] = sort(tab_pcCrit(2,:));
% tab_pcCrit = tab_pcCrit(:,idx);
% détermination des parents: ceux qui ont un nb min de pts critiques
nb_parents=round((t*modif_rate)/100); % nombre de parents
for i=1:nb_parents
    Parents(i)=tab_sol(1,i);
end

% % % détermination des individus à éliminer
% % j=t;
% % i=1;
% % while i<=nb_parents
% %     Eliminated(i)=tab_pcCrit(1,j);
% %     i=i+1;
% %     j=j-1;
% % end



end