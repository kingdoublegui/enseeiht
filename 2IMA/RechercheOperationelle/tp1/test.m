% [TEST1] Modifications des contraintes pour tests minimisation des trous
% 10 cours par semaine pour chaque promo, on ne s'attend à observer que des cours que sur le 2e et 3e créneau de la journée
Aeq(1,1,2,:) = 1;     beq(1) = 0;
Aeq(2,2,1,:) = 1;     beq(2) = 0;
Aeq(3,4,2,:) = 1;     beq(3) = 0;
Aeq(4,5,1,:) = 1;     beq(4) = 0;
Aeq(5,7,2,:) = 1;     beq(5) = 0;
Aeq(6,8,1,:) = 1;     beq(6) = 0;
Aeq(7,1,1,:) = 1;     beq(7) = 3;
Aeq(8,2,2,:) = 1;     beq(8) = 3;
Aeq(9,3,1,:) = 1;     beq(9) = 1;
Aeq(10,3,2,:) = 1;    beq(10) = 1;
Aeq(11,4,1,:) = 1;    beq(11) = 3;
Aeq(12,5,2,:) = 1;    beq(12) = 3;
Aeq(13,6,1,:) = 1;    beq(13) = 2;
Aeq(14,6,2,:) = 1;    beq(14) = 2;
Aeq(15,7,1,15) = 1;   beq(15) = 1;
Aeq(16,8,2,15) = 1;   beq(16) = 1;
Aeq(17,:,:,1) = 1;    beq(17) = 0;
Aeq(18,2,2,2) = 1;    beq(18) = 0;
Aeq(19,3,:,9:12) = 1; beq(19) = 0;



% [TEST2] Rajout de contraintes pour tester chaque contrainte imposée (aucune solution trouvée attendue lors de l'utilisation d'une de ces contraintes)
Aeq(20,1,2,20) = 1;     beq(20) = 1;
Aeq(20,2,1,20) = 1;     beq(20) = 1;
Aeq(20,4,2,20) = 1;     beq(20) = 1;
Aeq(20,5,1,20) = 1;     beq(20) = 1;
Aeq(20,7,2,20) = 1;     beq(20) = 1;
Aeq(20,8,1,20) = 1;     beq(20) = 1;

% [TEST3] Pour vérifier l'efficacité de la 9eme contrainte, on impose 4 cours pour le groupe 1 à Mme Proton (alors qu'elle ne doit en assurer que 3 par semaine). Le raisonnement sera le même pour les autres profs (leur nombre de cours à respecter) et l'autre groupe.
Aeq(20,3,1,20) = 1;     beq(20) = 1;
Aeq(21,3,1,5) = 1;     beq(21) = 1;
Aeq(22,3,1,13) = 1;     beq(22) = 1;
Aeq(23,3,1,17) = 1;     beq(23) = 1;

% [TEST4] Pour vérifier que le créneau dédié au sport est réservé (le 15)
Aeq(20,4,2,15) = 1;     beq(20) = 1;

% [TEST5] Pour vérifier que le créneau dédié au partiel est réservé (le 1)
Aeq(20,4,2,1) = 1;     beq(20) = 1;
