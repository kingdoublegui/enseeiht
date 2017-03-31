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
  component All7Segments 
    Port (
	   clk : in  std_logic;
           reset : in std_logic;
           e0 : in std_logic_vector (3 downto 0);
           e1 : in std_logic_vector (3 downto 0);
           e2 : in std_logic_vector (3 downto 0);
           e3 : in std_logic_vector (3 downto 0);
           e4 : in std_logic_vector (3 downto 0);
           e5 : in std_logic_vector (3 downto 0);
           e6 : in std_logic_vector (3 downto 0);
           e7 : in std_logic_vector (3 downto 0);
           an : out std_logic_vector (7 downto 0);
           ssg : out std_logic_vector (7 downto 0)
	);
    end component;

  COMPONENT diviseurClk
	PORT(
		clk : IN std_logic;
		reset : IN std_logic;          
		nclk : OUT std_logic
		);
  END COMPONENT;

  COMPONENT master_joystick
	PORT ( 
	    	en : IN STD_LOGIC;
	    	busy: OUT STD_LOGIC;
	    	sclk : OUT STD_LOGIC;
		miso :IN STD_LOGIC;
		mosi : OUT STD_LOGIC;
		ss : OUT STD_LOGIC;
		led1: IN STD_LOGIC;
		led2: IN STD_LOGIC;
		btn1 : OUT STD_LOGIC;  
		btn2 : OUT STD_LOGIC;
		bntJ : OUT STD_LOGIC;
		x : OUT STD_LOGIC_VECTOR (9 DOWNTO 0);
		y : OUT STD_LOGIC_VECTOR (9 DOWNTO 0);
		clk : IN STD_LOGIC;
		reset : IN STD_LOGIC
	);
  END COMPONENT;
	
  constant facteur: natural := 50;
  signal cclk: std_logic;
  signal x: std_logic_vector(9 downto 0);
  signal y: std_logic_vector(9 downto 0);
  signal can: std_logic_vector(7 downto 0);

  begin
	led(14 downto 3) <= (others => '0');
	an(7) <= '1';
	an(3) <= '1';
	an(6 downto 4) <= can(6 downto 4);
	an(2 downto 0) <= can(2 downto 0);

	Inst_All7Segments: All7Segments
		PORT MAP (
			clk => mclk,
			reset => not btnC,
			e0 => x(3 downto 0),
			e1 => x(7 downto 4),
			e2 =>  "00" & x(9 downto 8),
			e3 => "0000",
			e4 => y(3 downto 0),
			e5 => y(7 downto 4),
			e6 => "00" & y(9 downto 8),
			e7 => "0000",
			an => can,
			ssg => ssg
		);

	Inst_master_joystick: master_joystick
		PORT MAP(
			clk => cclk ,
			en => '1',
			busy => led(15),
			reset => not btnC,
			sclk => sckJ,
			miso => misoJ,
			mosi => mosiJ,
			ss => ssJ,
			led1 => swt(0),
			led2 => swt(1),
			btn1 => led(0),  
			btn2 => led(1),
			bntJ => led(2),
			x => x,
			y => y 
		);

	Inst_diviseurClk: diviseurClk 
		GENERIC MAP(facteur)
		PORT MAP(
			clk => mclk,
			reset => not btnC,
			nclk => cclk
		);  
end synthesis;
