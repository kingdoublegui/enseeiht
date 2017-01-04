%% Tests de pasCauchy
% La quadratique est de la forme q(s) = s.'*grad(x) + 1/2*s.'*hess(x)*s;

delta_max = 0.3;
gamma1 = 1/2;
gamma2 = 2;
eta1 = 1/4;
eta2 = 3/4;
delta = 0.5;

% Quadratique 1
g1 = [0; 0];
H1 = [7 0; 0 2];
% Je ne teste plus e cas car laisse des warnings inutiles dans le terminal
%pasCauchy(g1, H1, delta);

% Quadratique 2
g2 = [6; 2];
H2 = [7 0; 0 2];
pasCauchy(g2, H2, delta)

% Quadratique 3
g3 = [-2; 1];
H3 = [-2 0; 0 10];
pasCauchy(g3, H3, delta)
