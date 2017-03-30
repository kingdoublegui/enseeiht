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
    led : out std_logic_vector (15 downto 0);
	 -- le joystick
	 ssJ : out std_logic;
	 mosiJ : out std_logic;
	 misoJ : in std_logic;
	 sckJ : out std_logic
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

COMPONENT diviseurClk1Hz
	PORT(
		clk : IN std_logic;
		reset : IN std_logic;          
		nclk : OUT std_logic
		);
	END COMPONENT;

COMPONENT master_joystick
	PORT(
		en : IN std_logic;
		miso : IN std_logic;
		din : IN std_logic_vector(7 downto 0);
		clk : IN std_logic;
		reset : IN std_logic;          
		ss : OUT std_logic;
		mosi : OUT std_logic;
		sck : OUT std_logic;
		dout : OUT std_logic_vector(39 downto 0)
		);
	END COMPONENT;
	
	--Inputs
   signal miso : std_logic := '0';
   signal clk : std_logic := '0';
   signal reset : std_logic := '0';

 	--Outputs
   signal sck : std_logic;
   signal mosi : std_logic;
   signal ss : std_logic;
	signal din : std_logic_vector(7 downto 0);
	signal dout : std_logic_vector(39 downto 0);
	signal en : std_logic;

   -- Clock period definitions
   constant clk_period : time := 10 ns;
	
begin

  -- convention afficheur 7 segments 0 => allumé, 1 => éteint
  ssg <= (others => '1');
  -- aucun afficheur sélectionné
  an(7 downto 0) <= (others => '1');
  -- 16 leds éteintes
  led(15 downto 0) <= (others => '0');

	en <= '1';
	
  --dec7seg0 : dec7seg
  --  port map (swt(3 downto 0), ssg);
	 
	Inst_diviseurClk1Hz: diviseurClk1Hz PORT MAP(
		clk => mclk,
		reset => swt(15),
		nclk => sck
	);

	Inst_master_joystick: master_joystick PORT MAP(
		en => en,
		ss => ss,
		mosi => mosi,
		miso => miso,
		sck => sck,
		din => din,
		dout => dout,
		clk => clk,
		reset => swt(15)
	);

end synthesis;