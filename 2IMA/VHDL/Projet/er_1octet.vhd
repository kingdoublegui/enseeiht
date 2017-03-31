----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    14:19:51 03/08/2017 
-- Design Name: 
-- Module Name:    er_1octet - Behavioral 
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
use IEEE.std_logic_arith.all;
use IEEE.std_logic_unsigned.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity er_1octet is
    Port ( en : in  STD_LOGIC;
           din : in  STD_LOGIC_VECTOR (7 downto 0);
           dout : out  STD_LOGIC_VECTOR (7 downto 0);
           busy : out  STD_LOGIC;
           sclk : out  STD_LOGIC;
           mosi : out  STD_LOGIC;
           miso : in  STD_LOGIC;
			  clk : in STD_LOGIC;
			  reset : in STD_LOGIC);
end er_1octet;

architecture Behavioral of er_1octet is

	type t_etat is (attente, reception, emission);
	signal etat : t_etat;
	
	signal cpt : natural;
	signal registre : std_logic_vector(7 downto 0);

begin

	p_er_octet: process(clk, reset)
	begin
		if (reset = '1') then
			etat <= attente;
			cpt <= 7;
			registre <= (others =>'0');
			dout <= (others =>'0');
			sclk <= '0';
			busy <= '0';
			mosi <= '0';
			
		elsif (rising_edge(clk)) then
			case etat is
			when emission =>
					sclk <= '0';
				if (cpt = 0) then
					dout <= registre;
					busy <= '0';
					etat <= attente;
				else
					mosi <= registre(cpt-1);
					cpt <= cpt-1;
					etat <= reception;
				end if;
			when attente =>
				if (en = '1') then
					busy <= '1';
					cpt <= 7;
					registre <= din;
					mosi <= din(7);
					etat <= reception;
				end if;
			when reception =>
				sclk <= '1';
				registre(cpt) <= miso;
				etat <= emission;
			end case;
		end if;
	
	end process;

end Behavioral;

