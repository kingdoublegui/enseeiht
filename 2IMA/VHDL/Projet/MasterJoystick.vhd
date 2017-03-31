----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    14:51:53 03/13/2017 
-- Design Name: 
-- Module Name:    master_joystick - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments:
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_arith.all;
use IEEE.STD_LOGIC_unsigned.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity master_joystick is
    Port ( 
    	en : in STD_LOGIC;
    	busy: out STD_LOGIC;
    	sclk : out STD_LOGIC;
	miso :in STD_LOGIC;
	mosi : out STD_LOGIC;
	ss : out STD_LOGIC;
	led1: in STD_LOGIC;
	led2: in STD_LOGIC;
	btn1 : out STD_LOGIC;  
	btn2 : out STD_LOGIC;
	btnj : out STD_LOGIC;
	x : out STD_LOGIC_VECTOR (9 downto 0);
	y : out STD_LOGIC_VECTOR (9 downto 0);
	clk : in STD_LOGIC;
	reset : in STD_LOGIC);
end master_joystick;

architecture Behavioral of master_joystick is
  
	COMPONENT er_1octet
	PORT(
		en : IN std_logic;
		din : IN std_logic_vector(7 downto 0);
		miso : IN std_logic;
		clk : IN std_logic;
		reset : IN std_logic;          
		dout : OUT std_logic_vector(7 downto 0);
		busy : OUT std_logic;
		sclk : OUT std_logic;
		mosi : OUT std_logic
		);
	END COMPONENT;


type t_etat is (debut, attente1, envoi1, attente2, envoi2, attente3, envoi3, 
                attente4, envoi4, attente5, final);
variable etat : t_etat;

signal en1 : STD_LOGIC; --
signal busy1 : STD_LOGIC;
signal din1 : STD_LOGIC_VECTOR(7 downto 0);
signal dout1: STD_LOGIC_VECTOR(7 downto 0);

begin
	Inst_er_1octet: er_1octet PORT MAP(
		en => en1,
		din => din1,
		dout => dout1,
		busy => busy1,
		sclk => sclk,
		mosi => mosi,
		miso => miso,
		clk => clk,
		reset => reset
	);

	p_master_joystick: process(clk)
  		variable cpt: natural := 30;
  	begin
    		if (reset = '0') then --remise a zero
      			etat := debut;
      			cpt := 30;
      			busy <= '0';
      			ss <= '1';

    		elsif (rising_edge(clk)) then
      			case etat is
        		when debut =>
				if (en = '1') then
					cpt := 30;
					busy <= '1';
					ss <= '0';
					din1 <= (0 => led1, 1 => led2,  7 => '1',  others => '0');
					etat := attente1;
				end if;
			when attente1 => 
				if (cpt = 0) then
					en1 <= '1';
					etat := envoi1;
				else  --15us de delai
					cpt := cpt - 1;
				end if;
        		when envoi1 =>
				if (busy1 = '0' and en1 = '0') then --fin d'emission
					x(7 downto 0) <= dout1;
					cpt := 20;
					etat := attente2;
				else
					en1 <= '0';
				end if;
        		when attente2 =>
				if (cpt = 0) then
					din1 <= "00000000";
					en1 <= '1';
					etat := envoi2;
				else
					cpt := cpt - 1;
				end if;
        		when envoi2 =>
				if (busy1 = '0' and en1 = '0') then
					x(9 downto 8) <= dout1(1 downto 0);
					cpt := 20;
					etat := attente3;
				else
					en1 <= '0';
				end if;	
        		when attente3 =>
				if (cpt = 0) then
					en1 <= '1';
					etat := envoi3;
				else
					cpt := cpt - 1;
				end if;
        		when envoi3 =>
				if (busy1 = '0' and en1 = '0') then --fin d'emission
					y(7 downto 0) <= dout1;
					cpt := 20;
					etat := attente4;
				else
					en1 <= '0';
				end if;
		        when attente4 =>
				if (cpt = 0) then
					en1 <= '1';
					etat := envoi4;
				else
					cpt := cpt - 1;
				end if;
       			when envoi4 =>
				if (busy1 = '0' and en1 = '0') then
					y(9 downto 8) <= dout1(1 downto 0);
					cpt := 20;
					etat := attente5;
				else
					en1 <= '0';
				end if;
        		when attente5 =>
				if (cpt = 0) then
					en1 <= '1';
					etat := final;
				else
					cpt := cpt - 1;
				end if;
       			when final =>
				if (busy1 = '0' and en1 = '0') then
					busy <= '0';
					ss <= '1';
					btn2 <= dout1(2);
					btn1 <= dout1(1);
					btnj <= dout1(0);
					etat := debut;
				else
					en1 <= '0';
				end if;
      			end case ;
   		 end if ;
  	end process ;
end Behavioral;
