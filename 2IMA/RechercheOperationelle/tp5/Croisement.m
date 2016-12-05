function [wfils1,wfils2]= Croisement(weights,nw,parent1,parent2)

nb_croist= randi([1, 3]);

for i=1:nb_croist
%site de croisement    
pt_croist = round((nw-1)*rand()+1); % varie de 1 à nw
% premier fils
for i=1:pt_croist
    wfils1(1,i)= weights(parent1,i);
end
for i=pt_croist+1:nw
    wfils1(1,i)= weights(parent2,i);
end
% deuxieme fils
for i=1:pt_croist
    wfils2(1,i)= weights(parent2,i);

end
for i=pt_croist+1:nw
    wfils2(1,i)= weights(parent1,i);

end
end
% disp('1er fils');
% disp(wfils1);
% disp(wfils2);

end