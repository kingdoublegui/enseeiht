-- Time-stamp: <23 oct 2012 10:41 queinnec@enseeiht.fr>

with Ada.Text_IO; use Ada.Text_IO;
with Ada.Tags;
with Ada.Numerics.Discrete_Random;
with Glib; use Glib;
with Gtkada.Builder; use Gtkada.Builder;
with Gtkada.Handlers;
with Gdk.Threads;
with Gdk.GC;
with Gdk.Color;
with Gdk.Drawable;
with Gdk.Window;
with Glib.Error;
with Gtk.Widget;
with Gtk.Main;
with Gtk.Label;
with Gtk.Adjustment;
with Gtk.GRange;
with Gtk.Handlers;
with LR.Tasks; use LR.Tasks; -- just for Proc_Id
with LR.Simu;

package body LR.Affic is

   Nb_Lecteurs : Natural;
   Nb_Redacteurs : Natural;

   Dialog_Aide : Gtk.Widget.GTK_Widget;

   type Point is record
      X : Natural;
      Y : Natural;
   end record;

   type Objet is record
      Etat : Proc_Etat;
      Pos_Rien : Point;
      Pos_Demande : Point;
      Pos_Utilise : Point;
   end record;

   Nb_Objets : Natural; -- = Nb_Lecteurs + Nb_Redacteurs
   Les_Objets : array(Proc_Id) of Objet;

   -- Event "clicked" on button "aide"
   procedure Aide_Afficher(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) is
   begin
      Gtk.Widget.Show(Dialog_Aide);
   end Aide_Afficher;

   -- Event "clicked" on button "fermer"
   procedure Aide_Fermer(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) is
   begin
      Gtk.Widget.Hide(Dialog_Aide);
   end Aide_Fermer;

   -- Event clicked on button "pause".
   procedure Pause_Or_Run(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) is
   begin
      LR.Simu.Swap_Running;
   end Pause_Or_Run;

   -- Event "clicked" on button "paramètres"
   --  procedure Show_Param(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) is
   --  begin
   --     -- Gtk.Widget.Show(Dialog_Parametres);
   --     null;
   --  end Show_Param;

   -- Event "value_changed" de l'ajustement de l'échelle de vitesse du temps.
   procedure Set_Timespeed(Adj : access Gtk.Widget.Gtk_Widget_Record'Class) is
      Val : Natural;
   begin
      Val := Natural(Gtk.Adjustment.Get_Value(Gtk.GRange.Get_Adjustment(Gtk.GRange.Gtk_Range(Adj))));
      LR.Simu.Set_Timespeed(Val);
   end Set_Timespeed;

   procedure Quitter(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) is
      procedure Libc_Exit(Status: Natural);
      pragma Import(C, Libc_Exit, "exit");
   begin
      -- Gtk.Main.Main_Quit;
      Libc_Exit(0);
   end Quitter;

   ----------------------------------------------------------------

   package Random_Proc is
      function Get(Min, Max: Proc_Id) return Proc_Id;
   end Random_Proc;
   package body Random_Proc is
      package Rand_Proc is new Ada.Numerics.Discrete_Random(Proc_Id);
      Generator : Rand_Proc.Generator;
      function Get(Min, Max: Proc_Id) return Proc_Id Is
         X : Proc_Id;
      begin
         loop
            X := Rand_Proc.Random(Generator);
            exit when (X >= Min and X <= Max);
         end loop;
         return X;
      end Get;
   begin
      Rand_Proc.Reset(Generator);
   end Random_Proc;

   -- Cherche dans l'intervalle [debut..fin] un processus dans l'état
   -- etat1 ou dans l'état etat2. S'il y en a au moins un,
   -- un processus *au hasard* (dans cet état) est réveillé.
   function Chercher_Dormeur (Debut: Proc_Id; Fin: Proc_Id; Etat1: Proc_Etat; Etat2: Proc_Etat) return Boolean is
      Base : Proc_Id;
   begin
      -- Tirons au hasard le point de départ, puis on parcourt circulairement.
      base := Random_Proc.Get(Debut, Fin+1);
      for i in Base .. Fin loop
         if ((Les_Objets(i).Etat = Etat1) or (Les_Objets(i).etat = Etat2)) then
            LR.Simu.wakeup (i);
            return True;
         end if;
      end loop;
      for i in Debut..Base-1 loop
         if ((Les_Objets(i).Etat = Etat1) or (Les_Objets(i).etat = Etat2)) then
            LR.Simu.wakeup (i);
            return True;
         end if;
      end loop;
      return False;
   end Chercher_Dormeur;

     function Click_Event_Red(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) return Boolean is
     begin
        return Chercher_Dormeur(Nb_Lecteurs + 1, Nb_Objets, Redacteur_Rien, Redacteur_Rien);
     end Click_Event_Red;

     function Click_Event_Lect(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) return Boolean is
     begin
        return Chercher_Dormeur(Proc_Id'First, Nb_Lecteurs, Lecteur_Rien, Lecteur_Rien);
     end Click_Event_Lect;

     function Click_Event_Inside(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) return Boolean is
     begin
        return Chercher_Dormeur(Proc_Id'First, Nb_Objets, Lecteur_Lit, Redacteur_Ecrit);
     end Click_Event_Inside;

     ----------------------------------------------------------------

     -- Event configure (= realize or resize) on window redacteur.
     -- XXXX Hypothèses:
     --   - les trois fenêtres rédacteur, inside et lecteur ont la même hauteur;
     --   - la fenêtre inside est deux fois moins large que la fenêtre rédacteur;
     --   - la fenêtre rédacteur est en haut, la fenêtre lecteur en bas.
     function Compute_Placement(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) return Boolean is
        Taille_Fen_Dem_X, Taille_Fen_Inside_X : Float;
        Taille_Fen_Dem_Y, Taille_Fen_Inside_Y : Float;
        Interv_Rien, Interv_Demande, Interv_Utilise : Float;
        Start_Rien, Start_Demande, Start_Utilise : Float;
     begin
        Taille_Fen_Dem_X := Float(Gtk.Widget.Get_Allocation_Width(Gtkada.Builder.Get_Widget(Builder, "drawingarea_redacteur")));
        Taille_Fen_Dem_Y := Float(Gtk.Widget.Get_Allocation_Height(Gtkada.Builder.Get_Widget(Builder, "drawingarea_redacteur")));

        Taille_Fen_Inside_X := Taille_Fen_Dem_X / 2.0;
        Taille_Fen_Inside_Y := Taille_Fen_Dem_Y;

        Interv_Rien := Taille_Fen_Dem_X / (Float(Nb_Lecteurs - 1) + 4.0);
        Interv_Utilise := Taille_Fen_Inside_X / (Float(Nb_Lecteurs - 1) + 4.0);
        Interv_Demande := Interv_Utilise;
        Start_Rien := 2.0 * Interv_Rien;
        Start_Utilise := 2.0 * Interv_Utilise;
        Start_Demande := (taille_fen_dem_x - taille_fen_inside_x) / 2.0 + Start_Utilise;

        for i in Proc_Id'First .. Proc_Id'First + Nb_Lecteurs - 1 loop
           Les_Objets(i).Pos_Rien.x := Natural(Start_Rien);
           Les_Objets(i).Pos_Rien.y := Natural(Taille_Fen_Dem_Y * 0.75);
           Les_Objets(i).Pos_Demande.x := Natural(Start_Demande);
           Les_Objets(i).Pos_Demande.y := Natural(Taille_Fen_Dem_Y * 0.10);
           Les_Objets(i).Pos_Utilise.x := Natural(Start_Utilise);
           Les_Objets(i).Pos_Utilise.y := Natural(Taille_Fen_Inside_Y * 0.75);
           Start_Rien := Start_Rien + Interv_Rien;
           Start_Demande := Start_Demande + Interv_Demande;
           Start_Utilise := Start_Utilise + Interv_Utilise;
        end loop;

        -- Les rédacteurs
        Interv_Rien := Taille_Fen_Dem_X / (Float(Nb_Redacteurs - 1) + 4.0);
        Interv_Utilise := Taille_Fen_Inside_X / (Float(Nb_Redacteurs - 1) + 4.0);
        Interv_Demande := Interv_Utilise;
        Start_Rien := 2.0 * Interv_Rien;
        Start_Utilise := 2.0 * Interv_Utilise;
        Start_Demande := (taille_fen_dem_x - taille_fen_inside_x) / 2.0 + Start_Utilise;

        for i in Proc_Id'First + Nb_Lecteurs .. Proc_Id'First + Nb_Objets - 1 loop
           Les_Objets(i).Pos_Rien.x := Natural(Start_Rien);
           Les_Objets(i).Pos_Rien.y := Natural(Taille_Fen_Dem_Y * 0.25);
           Les_Objets(i).Pos_Demande.x := Natural(Start_Demande);
           Les_Objets(i).Pos_Demande.y := Natural(Taille_Fen_Dem_Y * 0.90);
           Les_Objets(i).Pos_Utilise.x := Natural(Start_Utilise);
           Les_Objets(i).Pos_Utilise.y := Natural(Taille_Fen_Inside_Y * 0.25);
           Start_Rien := Start_Rien + Interv_Rien;
           Start_Demande := Start_Demande + Interv_Demande;
           Start_Utilise := Start_Utilise + Interv_Utilise;
        end loop;

        return False;
     end Compute_Placement;

     ----------------------------------------------------------------

     -- rayon du cercle
     Rayon : Natural := 7;
     -- graphic context des cercles
     GC_Epais : Gdk.GC.Gdk_GC;

     Xfen_Redacteur : Gtk.Widget.Gtk_Widget;
     Xfen_Lecteur : Gtk.Widget.Gtk_Widget;
     Xfen_Inside : Gtk.Widget.Gtk_Widget;

     -- Event expose on any drawingarea.
     function Expose(Builder: access Gtkada.Builder.Gtkada_Builder_Record'class) return Boolean is
        procedure Tracer_Cercle(Win : Gtk.Widget.Gtk_Widget; Fill : Boolean; Center: Point) is
        begin
           Gdk.Drawable.Draw_Arc(Gtk.Widget.Get_Window(Win), GC_Epais, Fill, Gint(Center.X - Rayon), Gint(Center.Y - Rayon), Gint(Rayon*2), Gint(Rayon*2), 0, 360*64);
        end Tracer_Cercle;

     begin
        for I in Proc_Id'First .. Proc_Id'First + Nb_Objets - 1 loop
           case Les_Objets(I).Etat is
              when Redacteur_Demande =>
                 Tracer_Cercle(Xfen_Redacteur, True, Les_Objets(i).Pos_Demande);
              when Redacteur_Ecrit =>
                 Tracer_Cercle(Xfen_Inside, True, Les_Objets(i).Pos_Utilise);
              when Redacteur_Rien =>
                 Tracer_Cercle(Xfen_Redacteur, True, Les_Objets(i).Pos_Rien);
              when Lecteur_Demande =>
                 Tracer_Cercle(Xfen_Lecteur, False, Les_Objets(i).Pos_Demande);
              when Lecteur_Lit =>
                 Tracer_Cercle(Xfen_Inside, False, Les_Objets(i).Pos_Utilise);
              when Lecteur_Rien =>
                 Tracer_Cercle(Xfen_Lecteur, False, Les_Objets(i).Pos_Rien);
           end case;
        end loop;
        return True;
     end Expose;

     ----------------------------------------------------------------

     procedure Init (Nomstrategie: String; Nblect : Natural; Nbred: Natural) is
        Builder : Gtkada.Builder.Gtkada_Builder;
        Error   : Glib.Error.GError;
     begin
        Nb_Lecteurs := NbLect;
        Nb_Redacteurs := NbRed;
        Nb_Objets := Nb_Lecteurs + Nb_Redacteurs;
        for I in Proc_Id'First .. Proc_Id'First + Nb_Lecteurs - 1 loop
           Les_Objets(I).Etat := Lecteur_Rien;
        end loop;
        for I in Proc_Id'First + Nb_Lecteurs .. Proc_Id'First + Nb_Objets - 1 loop
           Les_Objets(I).Etat := Redacteur_Rien;
        end loop;

        Gtkada.Builder.Gtk_New (Builder);
        Error := Gtkada.Builder.Add_From_File (Builder, "lectred.glade");
        Dialog_Aide := Gtkada.Builder.Get_Widget(Builder, "dialog_aide");

        declare
           Nom_Strategie : Gtk.Label.Gtk_Label;
        begin
           Nom_Strategie :=  Gtk.Label.Gtk_Label(Gtkada.Builder.Get_Widget(Builder, "label_strategie"));
           Gtk.Label.Set_Text(Nom_Strategie, Nomstrategie);
        end;

        declare
           Timespeed_Scale : Gtk.GRange.Gtk_Range;
        begin
           Timespeed_Scale := Gtk.GRange.Gtk_Range(Gtkada.Builder.Get_Widget(Builder, "timespeed"));
           Gtkada.Handlers.Widget_Callback.Connect(Timespeed_Scale, "value_changed", Set_Timespeed'Access);
           Set_Timespeed(Timespeed_Scale);
        end;

        Xfen_Lecteur := Gtkada.Builder.Get_Widget(Builder, "drawingarea_lecteur");
        Xfen_Redacteur := Gtkada.Builder.Get_Widget(Builder, "drawingarea_redacteur");
        Xfen_Inside := Gtkada.Builder.Get_Widget(Builder, "drawingarea_inside");
        Gdk.GC.Gdk_New(GC_Epais, Gtk.Widget.Get_Root_Window(Xfen_Inside));
        Gdk.GC.Set_Line_Attributes(GC_Epais, 2, Gdk.GC.Line_Solid, Gdk.GC.Cap_Round, Gdk.GC.Join_Round);
        Gdk.GC.Set_Foreground(GC_Epais, Gdk.Color.Black(Gtk.Widget.Get_Default_Colormap));
        Gdk.GC.Set_Background(GC_Epais, Gdk.Color.White(Gtk.Widget.Get_Default_Colormap));

        Gtkada.Builder.Register_Handler (Builder, "aff_aide_afficher", Aide_Afficher'Access);
        Gtkada.Builder.Register_Handler (Builder, "aff_aide_fermer", Aide_Fermer'Access);
        Gtkada.Builder.Register_Handler (Builder, "aff_click_event_red", Click_Event_Red'Access);
        Gtkada.Builder.Register_Handler (Builder, "aff_click_event_lect", Click_Event_Lect'Access);
        Gtkada.Builder.Register_Handler (Builder, "aff_click_event_inside", Click_Event_Inside'Access);
        Gtkada.Builder.Register_Handler (Builder, "aff_compute_placement", Compute_Placement'Access);
        Gtkada.Builder.Register_Handler (Builder, "aff_expose", Expose'Access);
        Gtkada.Builder.Register_Handler (Builder, "aff_pause_or_run", Pause_Or_Run'Access);
        -- Gtkada.Builder.Register_Handler (Builder, "aff_show_param", Show_Param'Access);
        Gtkada.Builder.Register_Handler (Builder, "aff_quitter", Quitter'Access);
        Gtkada.Builder.Do_Connect(Builder);

        Gtk.Widget.Show_All(Gtkada.Builder.Get_Widget(Builder, "LectRed"));
     end Init;

     procedure Changer_Etat(Id: Proc_Id; Etat: Proc_Etat) is
     begin
        Gdk.Threads.Enter;
        -- Put_Line(Natural'Image(Id) & " -> " & Proc_Etat'Image(Etat));
        Les_Objets(Id).Etat := Etat;
        Gtk.Widget.Draw(Gtk.Widget.Get_Toplevel(Xfen_Inside)); -- this should refresh the three windows
        Gdk.Threads.Leave;
     end Changer_Etat;

   end LR.Affic;
