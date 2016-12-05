function U= nrbKnots(n,order)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Cette fonction permet de générer un vecteur nodal
%       non périodique ( possédant des noeuds de multiplicité > 1)
%    et non uniforme ( les valeurs nodales sont réparties non uniformément)
%  input:
%        n= nombre des points de contrôle 
%        order: ordre de la NURBS 
%  output:
%        U: vecteur nodal non périodique et non uniforme
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
N=n-1;%% N+1 points de contrôle
m=N+order; %% nombre des noeuds - 1: m = N+p+1 avec {p=degré ( order= p+1), N+1 pts de contrôle, m+1 knots}
M=m+1;%%nombre des noeuds
%display (M);
%display(M-order);
U=zeros(1,M);
for i=1:M
    if ((i>order) && (i<=M-order))
        U(1,i)=i/M;
    elseif (i>M-order)
        U(1,i)=1;
    end
        
end