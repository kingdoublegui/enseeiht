library ieee ;
use ieee.std_logic_1164.all;

-- (génération automatique du code du test possible)
entity test_additionneur is
end test_additionneur;

architecture arch_test of test_additionneur is

  -- rappel de l'interface du composant testé
  component additionneur
    port(
         X, Y ,Cin : in std_logic;
         S, Cout   : out std_logic
        );
  end component;

  -- signaux locaux à connecter au composant
  signal A, B, CarryIn : std_logic;
  signal Sum, CarryOut : std_logic;

  -- configuration pour indiquer quelle architecture de l'additionneur
  -- tester (si omis, le logiciel en choisit une)
  for a1: additionneur use entity work.additionneur(vue_flot);
  
  begin

   -- instantiations et connexions du composant à tester
   a1 : additionneur
     port map(A, B, CarryIn, Sum, CarryOut);

   -- valeurs données aux signaux en entrée
   -- (exhaustif pour cet exemple)
   -- attention : les valeurs doivent être maintenus, le temps de calculer les
   -- signaux en sortie de l'additionneur
   A <= '0',              '1' after 20 ns,
        '0' after  40 ns, '1' after 60 ns,
        '0' after  80 ns, '1' after 100 ns,
        '0' after 120 ns, '1' after 140 ns,
        '0' after 160 ns;
        
   B <= '0',              '1' after 40 ns, 
        '0' after  80 ns, '1' after 120 ns,
        '0' after 160 ns ;
        
   CarryIn <= '0', '1' after 80 ns;

  end arch_test;
