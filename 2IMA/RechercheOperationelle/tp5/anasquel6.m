%       **********************
%       ANALYSE D'UN SQUELETTE
%       **********************
% En ENTREE :
% 1) Une image im telle que :
% im(i,j) = 0 ou 1 : pixel "simple" du squelette
% im(i,j) = 2 : pixel "complexe" , voisin d'un carrefour
% im(i,j) = 3 : extremite
% im(i,j) = 4 : carrefour (au moins 3 pixels 8-voisins)
% im(i,j) = 7 : fond
%
% 2) nbrecar : nombre de carrefours
% icar contient les numeros de ligne des carrefours suivi des numeros de
% ligne des sommets simples trouves lors de l'analyse du squelette
% jcar contient les numeros de colonne des carrefours suivi des numeros de
% colonnes des sommets simples trouves lors de l'analyse du squelette
%
% En SORTIE :
% 1) L'image im telle que :
% im(i,j) = 0 ou 1 : pixel "simple" du squelette qui n'est pas un sommet
% im(i,j) = 2 : pixel "complexe" , voisin d'un carrefour
% im(i,j) = 3 : extrémité
% im(i,j) = 4 : carrefour donc sommet
% im(i,j) = 5 : sommet simple
% im(i,j) = 7 : fond
%
% 2) un graphe de sommets "graphe" de type cell représentant le squelette
% chaque noeud du graphe est un sommet (carrefour ou sommet simple ou
% extrémité) représenté par son numéro
% graphe{i}(j) : donne le numéro du jème sommet adjacent à i
% (i, graphe{i}(j)
%
% 3) nbrecar : nombre de carrefours
% 4) nsomsimple : nombre de sommets simples
% 5) ntotsom : nombre total de sommets
% 6)icar(i) : donne les numéros de ligne du ième sommet
%   jcar(i) : donne les numéros de colonne du ième sommet
% si 1<= 1 <= nbrecar : i est un carrefour
% si nbrecar < i <= ntotsom : i est un sommet simple ou une extrémité
% une extrémité est un pixel du squelette qui n'est reliée qu'à un seul
% sommet.
%
%  ********************* INITIALISATIONS ***********************
%
ipilex = zeros(1,600,'int16'); % pile des carrefours
ipiley = zeros(1,600,'int16'); % pile des carrefours
graphe = cell(1,600); % graphe des sommets
%
indsom = 1; % indsom = nombre de sommets détectés + 1
indpil = 1; % indpil = nombre de carrefours dans la pile + 1
idersomren = -1; % Numero du dernier sommet rencontre
nsomsimple = 0; % Nombre de sommets simple
numsom = nbrecar; % Numero du sommet courant. Au debut de l'analyse numsom
% est le numero du dernier carrefour trouvé dans l'étape de
% postsquelettisation ou encore le nombre total de carrefours
%
carrefour_deja_rencontre = false;
%
imsauv = im; %sauvegarde de im
%
%      ********** Debut de  l'ANALYSE DU SQUELETTE **********
%
% L'analyse démarre d'un carrefour
i = icar(1);
j = jcar(1);
% On introduit le pixel P = (i,j) dans la pile des carrefours
while indpil ~= 0
    etat = im(i,j);
    switch etat
        %
        case 4 % P = (i,j) est un carrefour
            disp(['**** carrefour ',num2str(i),' ',num2str(j)]);
            [numsom,qq] = sommet(i,j,icar,jcar);
            % On crée l'arc(idersomren,numsom) si idersomren ~= -1
            if idersomren~= -1
                graphe{idersomren} = [graphe{idersomren} numsom];
                graphe{numsom} = [graphe{numsom} idersomren];
            end
            idersomren = numsom;
            carrefour_deja_rencontre = false;
            %
            % Recherche d'un carrefour 8-voisin du carrefour P = (i,j)
            carrefour_8_voisin = false;
            for k =1:8
                iq = i + itab(k);
                jq = j + jtab(k);
                if im(iq,jq) == 4
                    carrefour_8_voisin = true;
                    break;
                end
            end
            if ~carrefour_8_voisin
                % Recherche d'un pixel complexe Q du squelette 4-voisin du carrefour P = (i,j)
                complexe_4_voisin = false;
                if im(i-1,j) == 2
                    iq = i-1;
                    jq = j;
                    complexe_4_voisin = true;
                else
                    if im(i,j-1) == 2
                        iq = i;
                        jq = j-1;
                        complexe_4_voisin = true;
                    else
                        if im(i,j+1) == 2
                            iq = i;
                            jq = j + 1;
                            complexe_4_voisin = true;
                        else
                            if im(i+1,j) == 2
                                iq = i+1;
                                jq = j;
                                complexe_4_voisin = true;
                            end
                        end
                    end
                end
                if complexe_4_voisin
                    % Q = (iq,jq) est un pixel 4-voisin du carrefour P = (i,j)
                    % Calcul de la droite d'approximation DELTA du nouvel arc
                    % dont le sommet initial est P en utilisant P et Q
                    nbrepix = 2;
                    snx = double(i + iq);
                    sny = double(j + jq);
                    vnx = double(i*i + iq*iq);
                    vny = double(j*j + jq*jq);
                    t = double(i*j + iq*jq);
                    xg = snx / nbrepix;
                    yg = sny / nbrepix;
                    alpha = (i-xg)*(i-xg) + (iq-xg)*(iq-xg);
                    beta = (i-xg)*(j-yg) + (iq-xg)*(jq-yg);
                    gamma = (j-yg)*(j-yg) + (jq-yg)*(jq-yg);
                else
                    % Ni carrefour 8-voisin Ni pixel complexe 4-voisin
                    % Recherche d'un pixel complexe 8-voisin
                    complexe_8_voisin = false;
                    for k =1:8
                        iq = i + itab(k);
                        jq = j + jtab(k);
                        if im(iq,jq) == 2
                            complexe_8_voisin = true;
                            % Q = (iq,jq) est un pixel 8-voisin
                            % et pas 4-voisin du carrefour P = (i,j)
                            % Calcul de la droite d'approximation DELTA du nouvel arc
                            % dont le sommet initial est P en utilisant P et Q
                            nbrepix = 2;
                            snx = double(i + iq);
                            sny = double(j + jq);
                            vnx = double(i*i + iq*iq);
                            vny = double(j*j + jq*jq);
                            t = double(i*j + iq*jq);
                            xg = snx / nbrepix;
                            yg = sny / nbrepix;
                            alpha = (i-xg)*(i-xg) + (iq-xg)*(iq-xg);
                            beta = (i-xg)*(j-yg) + (iq-xg)*(jq-yg);
                            gamma = (j-yg)*(j-yg) + (jq-yg)*(jq-yg);
                            break;
                        end
                    end
                end
            end
            if  ~carrefour_8_voisin && ~complexe_4_voisin && ~complexe_8_voisin
                % Le carrefour P est isole et a deja ete rencontre
                if indpil ~= 1
                    im(i,j)= 7;
                    indpil = indpil - 1;
                    i = ipilex(indpil);
                    j = ipiley(indpil);
                    %
                    carrefour_deja_rencontre = true;
                    %
                    idersomren = -1;
                    %
                    im(i,j) = 4;
                else
                    im(i,j) = 7;
                    indpil = 0; % ARRET de l'analyse
                end
            else
                % P possede un pixel voisin appartenant au squelette.
                % On empile (memorise) P
                ipilex(indpil) = i;
                ipiley(indpil) = j;
                indpil = indpil + 1;
                % On efface P
                im(i,j) = 7;
                % P est l'extrémité initiale d'un nouvel arc du
                % graphe des sommets
                [idersomren,qqq] = sommet(i,j,icar,jcar);
                %  P <- Q
                i = iq;
                j = jq;
            end
            %
        case 2 % P = (i,j) pixel "complexe" , voisin d'un carrefour
            disp(['** pixel complexe ',num2str(i),' ',num2str(j)]);
            % On regarde si un carrefour non déja rencontré est un 8-voisin
            % de P !!!!!!!!!!
            carrefour_8_voisin = false;
            for k = 1:8
                iq = i + itab(k);
                jq = j + jtab(k);
                if im(iq,jq) == 4
                    carrefour_8_voisin = true;
                    break;
                end
            end
            if ~carrefour_8_voisin
                % Recherche d'un pixel simple Q du squelette voisin de P = (i,j)
                simple_voisin = false;
                for k = 1:8
                    iq = i + itab(k);
                    jq = j + jtab(k);
                    if im(iq,jq) == 0 || im(iq,jq) == 1 || im(iq,jq) == 3
                        simple_voisin = true;
                        break;
                    end
                end
            end
            
           
            % On efface P
            im(i,j) = 7;
            %
            if ~carrefour_8_voisin && ~simple_voisin
                % Il n'y a pas de carrefour 8-voisin non deja rencontre et
                % il n'y a pas de voisin simple
                % On regarde si un carrefour déja rencontré (et donc
                % effacé) est un 8-voisin de P
                for k =1:8
                    iq = i + itab(k);
                    jq = j + jtab(k);
                    [numsom,carrefour_dejarencontre] = sommet(iq,jq,icar,jcar);
                    if carrefour_dejarencontre
                        % numsom est donc un carrefour deja rencontre
                        % et un 8-voisin de P =>  on crée donc
                        % les arcs (idersomren,numsom) et
                        % (numsom,idersomren)
                        graphe{idersomren} = [graphe{idersomren} numsom];
                        graphe{numsom} = [graphe{numsom} idersomren];
                        break;
                    end
                end
                
                % On revient au dernier carrefour rencontré
                disp('****** retour au dernier carrefour rencontre');
                indpil = indpil - 1;
                if indpil > 0
                    i = ipilex(indpil);
                    j = ipiley(indpil);
                    %
                    carrefour_deja_rencontre = true;
                    %
                    idersomren = -1;
                    %
                    im(i,j) = 4;
                end
            else
                %  P <- Q
                i = iq;
                j = jq;
            end
            %
        case {0,1} % P = (i,j) pixel "simple" du squelette
            %             disp(['* pixel simple ',num2str(i),' ',num2str(j)]);
            % On reactualise la droite d'approximation du segment de
            %  contour courant en introduisant le point P
            nbrepix = nbrepix + 1;
            snx = snx + double(i);
            sny = sny + double(j);
            vnx = vnx + double(i*i);
            vny = vny + double(j*j);
            t = t + double(i*j);
            xg = snx / nbrepix;
            yg = sny / nbrepix;
            alpha = vnx - snx*snx / nbrepix;
            beta = t - snx*sny / nbrepix;
            gamma = vny - sny*sny / nbrepix;
            % Il n'y a pas de carrefour voisin de P
            %
            % On calcule la droite d'approximation aux
            % moindres carres du segment de squelette courant
            delta = gamma - alpha;
            teta = beta + beta;
            ub = delta + sqrt(delta * delta + teta * teta);
            if abs(teta) < 0.0001 && delta < 0
                ua = 1;
                uc = ub * xg - yg;
            else
                ua = teta;
                uc = ub * xg - ua * yg;
            end
            % On cherche à savoir si P est un sommet simple ou non
            sommet_simple = false;
            % Recherche d'un pixel simple Q du squelette voisin de P = (i,j)
            for k = 1:8
                iq = i + itab(k);
                jq = j + jtab(k);
                if im(iq,jq) <= 3
                    break;
                end
            end
            %  Calcul de la distance de Q a la droite d'approximation DELTA
            %  du segment de squelette courant
            distq = abs(ua * double(jq) - ub * double(iq) + uc) / sqrt(ua * ua + ub * ub);
            if distq >= 0.51
                % la distance de Q à DELTA est supérieure à 0.51
                if im(iq,jq) == 2
                    % P est un sommet simple
                    sommet_simple = true;
                    im(i,j) = 5;
                    imsauv(i,j) = 5;
                    % On crée l'arc(idersomren,numsom)
                    nsomsimple = nsomsimple + 1;
                    numsom = nbrecar + nsomsimple;
                    icar(numsom) = i;
                    jcar(numsom) = j;
                    graphe{idersomren} = [graphe{idersomren} numsom];
                    graphe{numsom} = [graphe{numsom} idersomren];
                    idersomren = numsom; %numsom devient
                    % l'extremite initiale d'un nouvel arc
                else
                    % (iq,jq) est un pixel simple
                    % Existe-t-il un point de contour R tel que
                    %             R est un 8-voisin de Q
                    %  et
                    %             R n'est pas egal a P
                    %  et
                    %            DISTANCE(R,DELTA) <= DISTANCE(Q,DELTA)  ?
                    % DISTANCE(Q,DELTA) < 0,51 ?
                    for k = 1:8
                        ir = iq + itab(k);
                        jr = jq + jtab(k);
                        if im(ir,jr) ~= 7 && (ir ~= i || jr ~= j)
                            % R = (ir,jr) est un pixel du squelette
                            % 8-voisin de Q et diffrent de P
                            % Calcul de la distance de R à DELTA
                            distr = abs(ua * double(jr) - ub * double(ir) + uc) / sqrt(ua * ua + ub * ub);
                            if  distr > distq
                                % On s'eloigne de la direction courante de
                                % DELTA
                                % P est un sommet simple
                                sommet_simple = true;
                                im(i,j) = 5;
                                imsauv(i,j) = 5;
                                % On crée l'arc(idersomren,numsom)
                                nsomsimple = nsomsimple + 1;
                                numsom = nbrecar + nsomsimple;
                                icar(numsom) = i;
                                jcar(numsom) = j;
                                graphe{idersomren} = [graphe{idersomren} numsom];
                                graphe{numsom} = [graphe{numsom} idersomren];
                                idersomren = numsom; %numsom devient
                                % le dernier sommet rencontre
                            end
                        end
                    end
                end
            end
            % On sait maintenant si P est un sommet simple ou non
            if ~sommet_simple
                % On efface P qui n'est pas un sommet simple
                im(i,j) = 7;
                %  P <- Q
                i = iq;
                j = jq;
            end
        case 5 % P est un sommet simple du squelette
            disp(['***** sommet simple ',num2str(i),' ',num2str(j)]);
            % Recherche d'un pixel simple Q du squelette voisin de P = (i,j)
            for k = 1:8
                iq = i + itab(k);
                jq = j + jtab(k);
                if im(iq,jq) <= 3
                    %                 if im(iq,jq) == 0 || im(iq,jq) == 1
                    break;
                end
            end
            % Q = (iq,jq) est un pixel 8-voisin du sommet simple P = (i,j)
            % Calcul de la droite d'approximation DELTA du nouvel arc
            % dont le sommet initial est P en utilisant P et Q
            nbrepix = 2;
            snx = double(i + iq);
            sny = double(j + jq);
            vnx = double(i*i + iq*iq);
            vny = double(j*j + jq*jq);
            t = double(i*j + iq*jq);
            xg = snx / nbrepix;
            yg = sny / nbrepix;
            alpha = (i-xg)*(i-xg) + (iq-xg)*(iq-xg);
            beta = (i-xg)*(j-yg) + (iq-xg)*(jq-yg);
            gamma = (j-yg)*(j-yg) + (jq-yg)*(jq-yg);
            % On efface P
            im(i,j) = 7;
            %  P <- Q
            i = iq;
            j = jq;
            %
        case 3 % P est une extrémité
            disp(['*** extremite ',num2str(i),' ',num2str(j)]);
            % On efface P
            im(i,j) = 7;
            nsomsimple = nsomsimple + 1;
            numsom = nbrecar + nsomsimple;
            icar(numsom) = i;
            jcar(numsom) = j;
            graphe{idersomren} = [graphe{idersomren} numsom];
            graphe{numsom} = [graphe{numsom} idersomren];
            disp('****** retour au dernier carrefour rencontre');
            indpil = indpil - 1;
            if indpil > 0
                i = ipilex(indpil);
                j = ipiley(indpil);
                carrefour_deja_rencontre = true;
                im(i,j) = 4;
                idersomren = -1;
            end
        otherwise
            disp (' ??? Cas non prevu');
            indpil = 0;
    end
end
im =imsauv;
ntotsom = nbrecar + nsomsimple;
