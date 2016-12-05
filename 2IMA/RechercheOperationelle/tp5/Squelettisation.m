squel; % premiere squelettisation

figure('Name','Squelette initial'); % Affichage du squelette;
colormap(gray);
imagesc(im);

% Raccordement du point de depart au squelette
iref = idep;
jref = jdep;
pixleplusproche;
%
% Raccordement du point d'arrivee au squelette
iref = iarr;
jref = jarr;
pixleplusproche;
%

squel; % seconde squelettisation
figure('Name','Squelette etendu'); % Affichage du squelette;
colormap(gray);
imagesc(im);

postsquel; % post-squelettisation
% im(i,j) = 0 ou 1 : pixel "simple" du squelette
% im(i,j) = 2 : pixel "complexe" , voisin d'un carrefour
% im(i,j) = 3 : extremite
% im(i,j) = 4 : carrefour
% im(i,j) = 7 : fond
%
% *******************************
% Traitement du pixel de DEPART (idep,jdep)
% *******************************
disp (['idep = ',num2str(idep), ' jdep = ',num2str(jdep), ' im= ', num2str(im(idep,jdep))]);
etat = im(idep,jdep);
if etat == 7
    % le pixel de depart ne fait pas partie du squelette
        dmin = inf;
        for k = 1:8
            ii = idep + itab(k);
            jj = jdep + jtab(k);
            if im(ii,jj) == 4
                % (ii,jj) est un carrefour
                idep = ii;
                jdep = jj;
                break;
            end
            if im(ii,jj) ~= 7
                % (ii,jj) est un point du squelette
                % (ii,jj) n'est pas un carrefour
                dx2 = double(idep - ii )^2;
                dy2 = double(jdep - jj)^2;
                d = sqrt(dx2 + dy2);
                if d < dmin
                    dmin = d;
                    idep = ii;
                    jdep = jj;
                end
            end
        end
end
% On est maintenant s�r que (idep,jdep) appartient au squelette
etat = im(idep,jdep);
switch etat
    case {0,1}
        % le pixel de depart fait partie du squelette
        % le pixel de depart est un pixel simple
        im(idep,jdep) = 4; % le pixel de depart devient un carrefour
        % On l'ajoute �la liste des carrefours
        nbrecar = nbrecar + 1;
        icar(nbrecar) = idep;
        jcar(nbrecar) = jdep;
        %
        for k = 1:8
            ii = idep + itab(k);
            jj = jdep + jtab(k);
            if im(ii,jj) <= 1 || im(ii,jj) == 3
                im(ii,jj) = 2;
            end
        end
    case 2
        % (idep,jdep) est un pixel complexe voisin d'un carrefour
        % ce carrefour devient le pixel de depart
        for k = 1:8
            ii = idep + itab(k);
            jj = jdep + jtab(k);
            if im(ii,jj) == 4
                % (ii,jj) est un carrefour
                idep = ii;
                jdep = jj;
                break;
            end
        end
end
% On est maintenant s�r que (idep,jdep) appartient au squelette
% On est maintenant s�r que (idep,jdep):
%  -ou bien est une extremite : im(idep,jdep) == 3
%  -ou bien est un carrefour : im(idep,jdep) == 4
%
disp (['idep = ',num2str(idep), ' jdep = ',num2str(jdep), ' im= ', num2str(im(idep,jdep))]);
%
% *******************************
% Traitement du pixel d'ARRIVEE (iarr,jarr)
% *******************************
disp (['iarr= ',num2str(iarr), ' jarr = ',num2str(jarr), ' im= ', num2str(im(iarr,jarr))]);
etat = im(iarr,jarr);
if etat == 7
    % le pixel d'arrivee ne fait pas partie du squelette
        dmin = inf;
        for k = 1:8
            ii = iarr + itab(k);
            jj = jarr + jtab(k);
            if im(ii,jj) == 4
                % (ii,jj) est un carrefour
                iarr = ii;
                jarr = jj;
                break;
            end
            if im(ii,jj) ~= 7
                % (ii,jj) est un point du squelette
                % (ii,jj) n'est pas un carrefour
                dx2 = double(iarr - ii )^2;
                dy2 = double(jarr - jj)^2;
                d = sqrt(dx2 + dy2);
                if d < dmin
                    dmin = d;
                    iarr = ii;
                    jarr = jj;
                end
            end
        end
end
% On est maintenant s�r que (iarr,jarr) appartient au squelette
etat = im(iarr,jarr);
switch etat
    case {0,1}
        % le pixel de depart fait partie du squelette
        % le pixel de depart est un pixel simple
        im(iarr,jarr) = 4; % le pixel de depart devient un carrefour
        % On l'ajoute �la liste des carrefours
        nbrecar = nbrecar + 1;
        icar(nbrecar) = iarr;
        jcar(nbrecar) = jarr;
        %
        for k = 1:8
            ii = iarr + itab(k);
            jj = jarr + jtab(k);
            if im(ii,jj) <= 1 || im(ii,jj) == 3
                im(ii,jj) = 2;
            end
        end
    case 2
        % (iarr,jarr) est un pixel complexe voisin d'un carrefour
        % ce carrefour devient le pixel de depart
        for k = 1:8
            ii = iarr + itab(k);
            jj = jarr + jtab(k);
            if im(ii,jj) == 4
                % (ii,jj) est un carrefour
                iarr = ii;
                jarr = jj;
                break;
            end
        end
end
% On est maintenant s�r que (iarr,jarr) appartient au squelette
% On est maintenant s�r que (iarr,jarr)
% ou bien est une extremite : im(iarr,jarr) == 3
% ou bien est un carrefour : im(iarr,jarr)dmin = inf; == 4
%
disp (['iarr= ',num2str(iarr), ' jarr = ',num2str(jarr), ' im= ', num2str(im(iarr,jarr))]);


