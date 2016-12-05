subd = 1000;
xy = nrbeval(nurbsf,linspace(0.0,1.0,subd));
xy2 = xy(1:2,:);
s1 =arclength(xy(1,:),xy(2,:));
for i=1:subd
    p(i) = i;
end
v=1; % vitesse égal à 1 pixel / iteration
s=0:v:s1; % s = 0 v 2v 3v ... donc delta(s) = v donc vitesse constante v
nrbplot(nurbsf,subdiv1,'m');
pp = pdearcl(p,xy2,s,0,s1);
% au bout d'un temps t (=0,1,..., length(pp)) l'obstacle se situe à :
% ( xy(1,round(pp(t))) , xy(2,round(pp(t))) )