
%% Algorithme de *Ford-Fulkerson* pour les tensions
%
% Problème d'ordonnancement
%
% Representation du graphe
%
% n == nombre de sommets
%
% m == nombre d'arcs
%
%
% Vecteur des successeurs de chaque sommet : SUC
%
%% Initialisations
NPRED = uint16([0 4 1 1 1 2 1 2 3 2 3]);
n = size(NSUC,2);
X = uint16(1:n); % X contient la liste des sommets de G
m = sum(NSUC);
%
% Vecteur des successeurs de chaque sommet : SUC
PRED = uint16([1 9 10 11 1 1 1 3 4 4 4 5 6 7 8 5 7 6 7 8]);
%
% Vecteur des longueurs de chaque arc
LONGPRED = [inf 15 10 17 5 0 3 16 14 14 14 20 8 18 25 10 18 8 18 25];
%

DATEDEBUT = zeros(1,n);
for i = 3:n
	DATEDEBUT(i)=-1;
end

x = ones(1,n);
while size(x(DATEDEBUT==-1),2)>2
	for i = 3:n % on ne prend pas en compte la source et le puit
		if DATEDEBUT(i)==-1
			prpred = sum(NPRED(1:i-1))+1;
			tmp = [-inf];
			tmp2 = [];
			for j = prpred:prpred+NPRED(i)-1
				pred = PRED(j);
				if DATEDEBUT(pred)==-1
					break
				end
				tmp = [tmp LONGPRED(j)+DATEDEBUT(pred)];
				tmp2 = [tmp2 DATEDEBUT(pred)];
			end
			
			if ~any(tmp2==-1)
				DATEDEBUT(i) = max(tmp);
			end
		end
	end
end

%
% disp (['Plus long chemin : ',num2str(PLUSLONGCHEMIN(1:nsom))]);
% disp (['Longueur du plus long chemin : ', num2str(theta(1))]);
disp (['Dates des débuts : ', num2str(DATEDEBUT(3:n))]);