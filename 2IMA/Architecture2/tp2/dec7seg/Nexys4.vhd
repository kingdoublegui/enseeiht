library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all;
use IEEE.std_logic_unsigned.all;

entity Nexys4 is
  port (
    -- les 4 switchs utilisés
    swt : in std_logic_vector (3 downto 0);
    -- les anodes pour sélectionner l'afficheur 7 segments
    an : out std_logic_vector (7 downto 0);
    -- afficheur 7 segments (7 segments + point décimal)
    ssg : out std_logic_vector (7 downto 0)
  );
end Nexys4;

architecture synthesis of Nexys4 is

  -- rappel du (des) composant(s)
  component dec7seg
    port (
      value : in std_logic_vector(3 downto 0);
      seg : out std_logic_vector (7 downto 0)
    );
  end component ;

begin

  -- premier 7-segments sélectionné
  an(7 downto 0) <= (0 => '0', others => '1');

  dec7seg0 : dec7seg
    port map (swt(3 downto 0), ssg);

end synthesis;
