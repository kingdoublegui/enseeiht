library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all;
use IEEE.std_logic_unsigned.all;

entity Nexys4 is
  port (
    -- les 16 switchs
    swt : in std_logic_vector (15 downto 0);
    -- les anodes pour sélectionner l'afficheur 7 segments
    an : out std_logic_vector (7 downto 0);
    -- afficheur 7 segments (point décimal compris, segment 7)
    ssg : out std_logic_vector (7 downto 0);
    -- horloge
    mclk : in std_logic;
    -- les 5 boutons noirs
    btnC, btnU, btnL, btnR, btnD : in std_logic;
    -- les 16 leds
    led : out std_logic_vector (15 downto 0)
  );
end Nexys4;

architecture synthesis of Nexys4 is

  -- rappel du (des) composant(s)
	COMPONENT additionneur
	PORT(
		X : IN std_logic;
		Y : IN std_logic;
		Cin : IN std_logic;          
		S : OUT std_logic;
		Cout : OUT std_logic
		);
	END COMPONENT;
	
	COMPONENT additionneur4
	PORT(
		A : IN std_logic_vector(3 downto 0);
		B : IN std_logic_vector(3 downto 0);
		Cin : IN std_logic;          
		S : OUT std_logic_vector(3 downto 0);
		Cout : OUT std_logic
		);
	END COMPONENT;

	COMPONENT dec7seg
	PORT(
		value : IN std_logic_vector(3 downto 0);          
		seg : OUT std_logic_vector(7 downto 0)
		);
	END COMPONENT;

	signal to7seg : std_logic_vector(3 downto 0);

begin

  -- convention afficheur 7 segments 0 => allumé, 1 => éteint
  -- ssg <= (others => '1');
  -- aucun afficheur sélectionné
  an(7 downto 0) <= (0 => '0', others => '1');
  -- 16 leds éteintes
  led(14 downto 2) <= (others => '0');

	Inst_additionneur: additionneur PORT MAP(
		X => swt(0),
		Y => swt(1),
		Cin => swt(2),
		S => led(0),
		Cout => led(1)
	);
	
	Inst_additionneur4: additionneur4 PORT MAP(
		A => swt(15 downto 12),
		B => swt(11 downto 8),
		Cin => swt(7),
		S => to7seg,
		Cout => led(15)
	);
	
	Inst_dec7seg: dec7seg PORT MAP(
		value => to7seg,
		seg => ssg
	);
 

end synthesis;
