function execbatch() 
%% S.U.N. 2016
%%
clc;
clear all;
check=exist('ResultatsBatch','dir');
if (check~=7) % if the folder 'ResultatsBatch does not exists in the current directory
    display('\n ERRROR !!!! please create the folder ResultatsBatch in the current directory');
    return;
end
fprintf('#################################   ');
disp('Duopole de Cournot - Tournoi:   #################################');
listenomindir = dir('strat_*');
listenoms = char({listenomindir.name});
disp('Liste de strategies a tester (fichiers debutant par "strat_")');
disp(listenoms);
NBJ=input('Nombre de parties par match: ');
contresoimeme=input('Inclure test contre soi-meme ou pas ? Taper "oui" ou "non": ','s');

%NBJ=100;
%contresoimeme = 'oui'; %'non'
%disp('Strategies possibles : cooperatif/noncooperatif/stackelberg');
%NBJ=input('Nombre de parties: ');
%nomx=input('Strat�gie du 1er joueur: ','s');
%strax=[nomx,'1'];
%nomy=input('Strategie du second joueur: ','s');
%stray=[nomy,'2'];
%[tx, ty, gx, gy] = jeu(NBJ,strax,stray);
%fig = afficher(tx,ty,gx,gy,nomx,nomy);

fprintf('\n ---------------------------------   DEBUT DES MATCHS   ---------------------------------\n');


count = 0;
[size1 , ~] = size(listenoms);
MATCHS = [];%struc(zeros(size1*size1,NBJ+3),);
GainTotal = zeros(size1,1);
NbVictoires = zeros(size1,1);
NbDefaites = zeros(size1,1);
NbNuls = zeros(size1,1);
for i=1:size1
    [~, nomx , ~] = fileparts(listenoms(i,:));
    
    
    %nomx = 'strat_patathor_B1';i=14;
        
    if contresoimeme == 'oui'
        count = count+2;
        MATCHS(count-1).J1=nomx;
        MATCHS(count-1).J2=nomx;        
        fprintf('%d) %s vs %s: \n \t -match vs soi-meme: ',count,nomx,nomx);
        [MATCHS(count-1).tx, MATCHS(count-1).ty, MATCHS(count-1).gx, MATCHS(count-1).gy] = jeu(NBJ,nomx,nomx);
        GainTotal(i) = GainTotal(i) + MATCHS(count-1).gx*ones(NBJ,1);
        GainTotal(i) = GainTotal(i) + MATCHS(count-1).gy*ones(NBJ,1);
        if (MATCHS(count-1).gx*ones(NBJ,1) == MATCHS(count-1).gy*ones(NBJ,1))
            NbNuls(i) = NbNuls(i) + 2;
        else
            NbVictoires(i) = NbVictoires(i) + 1;
            NbDefaites(i) = NbDefaites(i) + 1;
        end
        fprintf('%.3f vs %.3f \n',MATCHS(count-1).gx*ones(NBJ,1),MATCHS(count-1).gy*ones(NBJ,1));
        fig = afficher(MATCHS(count-1).tx,MATCHS(count-1).ty,MATCHS(count-1).gx,MATCHS(count-1).gy,nomx,nomx);
        hgexport(fig(1,1),strcat('ResultatsBatch/Strategies_',nomx,'_vs_',nomx,'.eps'));
        hgexport(fig(1,2),strcat('ResultatsBatch/Totalgains_',nomx,'_vs_',nomx,'.eps'));        
        MATCHS(count).J1 = nomx;
        MATCHS(count).J2 = nomx;
        MATCHS(count).tx = MATCHS(count-1).tx;        
        MATCHS(count).ty = MATCHS(count-1).ty;
        MATCHS(count).gx = MATCHS(count-1).tx;        
        MATCHS(count).gy = MATCHS(count-1).ty;  

    end
    
    for j=(i+1):size1
        [~, nomy , ~] = fileparts(listenoms(j,:));
        count = count+2;
        
        %nomy = 'strat_simple_F2'; j = 16;
        
        fprintf('%d) %s vs %s: \n \t -match aller: ',count,nomx,nomy);
        MATCHS(count-1).J1=nomx;
        MATCHS(count-1).J2=nomy;
        [MATCHS(count-1).tx, MATCHS(count-1).ty, MATCHS(count-1).gx, MATCHS(count-1).gy] = jeu(NBJ,nomx,nomy);
        GainTotal(i) = GainTotal(i) + MATCHS(count-1).gx*ones(NBJ,1);
        GainTotal(j) = GainTotal(j) + MATCHS(count-1).gy*ones(NBJ,1);
        if (MATCHS(count-1).gx*ones(NBJ,1) == MATCHS(count-1).gy*ones(NBJ,1))
            NbNuls(i) = NbNuls(i) + 1;
            NbNuls(j) = NbNuls(j) + 1;
        else
            if (MATCHS(count-1).gx*ones(NBJ,1) > MATCHS(count-1).gy*ones(NBJ,1))
                NbVictoires(i) = NbVictoires(i) + 1;
                NbDefaites(j) = NbDefaites(j) + 1;
            else
                NbVictoires(j) = NbVictoires(j) + 1;
                NbDefaites(i) = NbDefaites(i) + 1;                
            end
            
        end
        fprintf('%.3f vs %.3f \n',MATCHS(count-1).gx*ones(NBJ,1),MATCHS(count-1).gy*ones(NBJ,1));
        fig = afficher(MATCHS(count-1).tx,MATCHS(count-1).ty,MATCHS(count-1).gx,MATCHS(count-1).gy,nomx,nomy);
        hgexport(fig(1,1),strcat('ResultatsBatch/Strategies_',nomx,'_vs_',nomy,'.eps'));
        hgexport(fig(1,2),strcat('ResultatsBatch/Totalgains_',nomx,'_vs_',nomy,'.eps'));
        
        fprintf('\t -match retour: ');
        MATCHS(count).J1 = nomy;
        MATCHS(count).J2 = nomx;        
        [MATCHS(count).ty, MATCHS(count).tx, MATCHS(count).gy, MATCHS(count).gx] = jeu(NBJ,nomy,nomx);
        GainTotal(i) = GainTotal(i) + MATCHS(count).gx*ones(NBJ,1);
        GainTotal(j) = GainTotal(j) + MATCHS(count).gy*ones(NBJ,1);
        if (MATCHS(count).gx*ones(NBJ,1) == MATCHS(count).gy*ones(NBJ,1))
            NbNuls(i) = NbNuls(i) + 1;
            NbNuls(j) = NbNuls(j) + 1;
        else
            if (MATCHS(count).gx*ones(NBJ,1) > MATCHS(count).gy*ones(NBJ,1))
                NbVictoires(i) = NbVictoires(i) + 1;
                NbDefaites(j) = NbDefaites(j) + 1;
            else
                NbVictoires(j) = NbVictoires(j) + 1;
                NbDefaites(i) = NbDefaites(i) + 1;                
            end
        end        
        fprintf('%.3f vs %.3f\n',MATCHS(count).gx*ones(NBJ,1),MATCHS(count).gy*ones(NBJ,1));  
        fig = afficher(MATCHS(count).ty,MATCHS(count).tx,MATCHS(count).gy,MATCHS(count).gx,nomy,nomx);
        hgexport(fig(1,1),strcat('ResultatsBatch/Strategies_',nomy,'_vs_',nomx,'.eps'));
        hgexport(fig(1,2),strcat('ResultatsBatch/Totalgains_',nomy,'_vs_',nomx,'.eps'));
    end
    close all;
end

fprintf('\n ---------------------------------   FIN DES MATCHS   ---------------------------------\n');

[~,StrategiesTriees] = sort(GainTotal,'descend'); %sortrows(GainTotal,1,'descent');

fprintf('\n Classement par Gain Total:\n\n');

for i=1:size1
    fprintf('%d) %s : %.3f : %d victoires : %d nuls : %d defaites\n',i,listenoms(StrategiesTriees(i),:),GainTotal(StrategiesTriees(i)), NbVictoires(StrategiesTriees(i)),NbNuls(StrategiesTriees(i)),NbDefaites(StrategiesTriees(i)));
end

fprintf('\n\n Strategie gagnante pour critere Gain Total: %s \n\n', listenoms(StrategiesTriees(1),:));


fprintf('\n Classement par NbVictoires/Nuls/Defaites:\n\n');

mm = [GainTotal NbVictoires NbNuls NbDefaites];
[~, StrategiesTriees] = sortrows(mm,[4,3]);

for i=1:size1
    fprintf('%d) %s : %.3f : %d victoires : %d nuls : %d defaites\n',i, listenoms(StrategiesTriees(i),:),GainTotal(StrategiesTriees(i)), NbVictoires(StrategiesTriees(i)),NbNuls(StrategiesTriees(i)),NbDefaites(StrategiesTriees(i)));
end


fprintf('\n\n Strategie gagnante pour critere NbVictoires/Nuls/Defaites: %s \n\n', listenoms(StrategiesTriees(1),:));


fprintf('################################# FIN DU TOURNOI  #################################');

close all;



