% ****************************************
% SQUELETTISATION PAR CALCUL D'UNE FONCTION DISCRIMINANTE SUR UN VOISINAGE DE 8 POINTS
% ****************************************
% im(m,n) =TABLEAU CONTENANT L'IMAGE BINAIRE A SQUELETTISER de type int16
%  LES PIXELS "NOIRS" DOIVENT APPARTENIR A im(3:m-2,3:n-2)
%  LES PIXELS "BLANCS" DOIVENT ETRE EGAUX A 7
%  EN SORTIE LES PIXELS DU SQUELETTE SONT DIFFERENTS DE 7,LES AUTRES A 7
% 
% Initialisation
itab = int16([-1,0,0,1,-1,-1,1,1]); % on consulte d'abord les 4-voisins
jtab = int16([0,-1,1,0,-1,1,-1,1]); % on consulte d'abord les 4-voisins
m = int16(size(im,1)); % nombre de lignes
n = int16(size(im,2)); % nombre de colonnes
nvois = int16(0);
i = int16(0);
j = int16(0);
k = int16(0);
x = int16(0);
y = int16(0);
indic = true;
%
m = m - 2;
n = n - 2;
%****************
% PREMIER BALAYAGE
%****************
while indic
    indic = false;
for i = 3:m
    for j = 3:n
        if im(i,j) ~= 7
            % Calcul de abs(x) , abs(y) , nvois, im(i,j) = abs(x) + abs(y)
            nvois = 0;
            x = 0;
            y = 0;
            for k = int16(1):int16(8)
                ii = i + itab(k);
                jj = j + jtab(k);
                if im(ii,jj) ~= 7
                    nvois = nvois + int16(1);
                    x = x + itab(k);
                    y = y + jtab(k);
                end
            end
            im(i,j) = abs(x) + abs(y);
%
            if im(i,j) == 3
                im(i,j) = 5;
                if ~(((nvois ~= 3) && (nvois ~= 5)) || ((abs(x) ~= 2) && (abs(y) ~= 2)))
                    im(i,j) = 3;
                else
                    if nvois == 4
                        im(i,j) = 3;
                    else
                        if ~( im(i,j-2) ~= 7 || im(i,j-1) ~= 5 || im(i,j+1) ~= 7) 
                            im(i,j) = 3; % ligne double verticale
                        else
                            if ~( im(i-2,j) ~= 7 || im(i-1,j) ~= 5 || im(i+1,j) ~= 7)
                                im(i,j) = 3; % ligne double horizontale
                            end
                        end
                    end
                end
            end
        end
    end
end
%
% *****************
% DEUXIEME BALAYAGE
% *****************
%
for i = 3:m
    for j = 3:n
        if ~(im(i,j) == 7 || im(i,j) < 4)
            indic = true; % indic == true <=> on a enlevé au moins 1 pixel de la forme
            im(i,j) = 7; % elimination des pixels t.q. im(i,j) == 4 ou 5
        end
    end
end
end
indic = true;
while indic
    indic = false;
    for i = 3:m
        for j = 3:n
            if im(i,j) ~= 7
                % Calcul de abs(x) , abs(y) , nvois, im(i,j) = abs(x) + abs(y)
                nvois = 0;
                x = 0;
                y = 0;
                for k = int16(1):int16(8)
                    ii = i + itab(k);
                    jj = j + jtab(k);
                    if im(ii,jj) ~= 7
                        nvois = nvois + int16(1);
                        x = x + itab(k);
                        y = y + jtab(k);
                    end
                end
                im(i,j) = abs(x) + abs(y);
                if abs(x) + abs(y) ==  2
                    if min(abs(x),abs(y)) ~= 1
                        if nvois == 3  % elimination des pixels / ( (abs(x)==2, abs(y)==0) ou (abs(x)==0, abs(y)==2) ) et nvois ==3
                            im(i,j) = 7;
                            indic = true;
                            break;
                        end
                    else
                        if nvois == 2 % elimination des pixels / abs(x) == abs(y) == 1 et nvois ==2
                            im(i,j) = 7;
                            indic=true;
                        end
                    end
                end
            end
        end
    end
end
m = m + 2;
n = n + 2;

