library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all;
use IEEE.std_logic_unsigned.all;

entity Nexys4 is
  port (
         -- les switchs
         swt : in std_logic_vector (8 downto 0);
  -- le reset
  btnC : std_logic;
  -- les anodes pour sélectionner l'afficheur 7 segments
  an : out std_logic_vector (7 downto 0);
  -- afficheur 7 segments (point décimal compris, segment 7)
  ssg : out std_logic_vector (7 downto 0);
  -- horloge
  mclk : in std_logic
);
end Nexys4;

architecture synthesis of Nexys4 is

  -- rappel du (des) composant(s)
  component all7segments
	port(
		clk : in std_logic;
		reset : in std_logic;
		e0 : in std_logic_vector(3 downto 0);
		e1 : in std_logic_vector(3 downto 0);
		e2 : in std_logic_vector(3 downto 0);
		e3 : in std_logic_vector(3 downto 0);
		e4 : in std_logic_vector(3 downto 0);
		e5 : in std_logic_vector(3 downto 0);
		e6 : in std_logic_vector(3 downto 0);
		e7 : in std_logic_vector(3 downto 0);          
		an : out std_logic_vector(7 downto 0);
		ssg : out std_logic_vector(7 downto 0)
		);
	end component;

   component additionneur4bits
	port(
		input1 : in std_logic_vector(3 downto 0);
		input2 : in std_logic_vector(3 downto 0);
		carry_in : in std_logic;          
		sum : out std_logic_vector(3 downto 0);
		carry_out : out std_logic
		);
	end component;
	
	signal carry_in, carry_out : std_logic_vector(3 downto 0);
	signal sum : std_logic_vector(3 downto 0);
	signal carry : std_logic;
	signal reset : std_logic;
	
begin

  -- valeurs des sorties (à modifier)

  carry_in <= "000"&swt(8);
  carry_out <= "000"&carry;
  reset <= not btnC;
  
  -- connexion du (des) composant(s) avec les ports de la carte
	Inst_All7Segments: All7Segments port map(
		clk => mclk,
		reset => reset,
		e0 => swt(3 downto 0),
		e1 => swt(7 downto 4),
		e2 => carry_in,
		e3 => "0000",
		e4 => sum,
		e5 => carry_out,
		e6 => "0000",
		e7 => "0000",
		an => an,
		ssg => ssg
	); 
    
	Inst_additionneur4bits: additionneur4bits PORT MAP(
		input1 => swt(3 downto 0),
		input2 => swt(7 downto 4),
		carry_in => swt(8),
		sum => sum,
		carry_out => carry 
	);
end synthesis;
