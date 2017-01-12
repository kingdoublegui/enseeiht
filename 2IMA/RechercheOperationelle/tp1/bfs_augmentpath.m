%WHITE =0;
%GRAY=1;
%BLACK=2

function chemin_ameliorant=bfs_augmentpath(source,puit,current_flow,capacity,n)
    BLANC =0; GRIS=1; NOIR=2;
    marquage(1:n)=BLANC;
    q=[];
    chemin_ameliorant=[];
    
    %ENQUEUE
    q=[source q];
    marquage(source)=GRIS;
    
    pred=zeros(1,n);
    %Marquage du chemin
    while ~isempty (q) % Tant qu'il reste des noeuds dont les arcs sortants sont inexplores
            u=q(end);
            q(end)=[];
            marquage(u)=NOIR; % On inclut le noeud dans le chemin
            
            for v=1:n
                if (marquage(v)==BLANC && capacity(u,v)>current_flow(u,v) )
                    % Si le flot est plus important que le flot courant, on
                    % ajoute le noeud au chemin
                    q=[v q];
                    marquage(v)=GRIS;
                    pred(v)=u;                        
                end
            end
    end
    if marquage(puit)==NOIR       %Si le puit est accessible
       temp=puit;
       while pred(temp)~=source
        chemin_ameliorant = [pred(temp) chemin_ameliorant];     %Le chemin ne contient pas la source et le puit
        temp=pred(temp);
       end
       chemin_ameliorant=[source chemin_ameliorant puit];
    else
        chemin_ameliorant=[];         %Si une condition n'est pas respecte il n'y a pas de chemin
    end
    
end