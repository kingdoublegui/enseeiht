with Ada.Text_IO; use Ada.Text_IO;
with Ada.Exceptions;

-- Version simple : exclusion mutuelle pour toutes les opérations
--  fournit une ossature pour l'approche "service"

package body LR.Synchro.serveur is
   
   function Nom_Strategie return String is
   begin
      return "Exclusion mutuelle simple";
   end Nom_Strategie;

   task LectRedTask is
      entry Demander_Lecture;
      entry Demander_Ecriture;
      entry Terminer_Lecture;
      entry Terminer_Ecriture;
   end LectRedTask;

   task body LectRedTask is
       nbL: Integer := 0;
       nbR: Integer := 0;
   begin
      loop
      	select
        when nbR = 0 =>
			accept Demander_Lecture; nbL := nbL +1;
    	or when nbL = 0 and nbR = 0 =>
                accept Demander_Ecriture; nbR := nbR +1;
        or when nbR > 0 =>
            accept Terminer_Ecriture; nbR := nbR -1;
        or when nbL > 0 =>
            accept Terminer_Lecture; nbL := nbL -1;
        or
            terminate;
        end select;
        -- une fois une opération acceptée, on accepte uniquement sa terminaison
      end loop;
   end LectRedTask;

   procedure Demander_Lecture is
   begin
      LectRedTask.Demander_Lecture;
   end Demander_Lecture;

   procedure Demander_Ecriture is
   begin
      LectRedTask.Demander_Ecriture;
   end Demander_Ecriture;

   procedure Terminer_Lecture is
   begin
      LectRedTask.Terminer_Lecture;
   end Terminer_Lecture;

   procedure Terminer_Ecriture is
   begin
      LectRedTask.Terminer_Ecriture;
   end Terminer_Ecriture;

end LR.Synchro.serveur;
