function [courbure, sigma]=CalculCourbure(crv,subd)

[dcrv, d2crv] = nrbderiv(crv);
%hold on;
%nrbplot(dcrv,subd,'b');
ui = linspace(0.0,1.0,subd);

[p1, dp, d2p] = nrbdeval(crv,dcrv, d2crv,ui);

%hold off;
n=size(p1);
courbure=zeros(1,n(2));
courbure_moy=0;
for i=1:n(2)
    courbure(1,i)= (dp(1,i)*d2p(2,i) - d2p(1,i)*dp(2,i))/((dp(1,i)^2 + dp(2,i)^2)^(3/2));
    courbure_moy = courbure_moy +  courbure(1,i);
end
courbure_moy = courbure_moy/n(2);
sigma=std(courbure);
end