----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    10:33:17 02/27/2017 
-- Design Name: 
-- Module Name:    compteur - Behavioral 
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
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity compteur is
    Port ( clk : in  STD_LOGIC;
           reset : in  STD_LOGIC;
           carry_out : out  STD_LOGIC;
           cpt : out  STD_LOGIC_VECTOR (3 downto 0));
end compteur;

architecture Behavioral of compteur is

begin
process(clk, reset)
	variable cpt_aux : std_logic_vector(3 downto 0) := (others => '0');
	begin
		if (reset = '0') then 
			cpt_aux := (others => '0');
			cpt <= cpt_aux;
			carry_out <= '0';
			cpt <= cpt_aux;
		elsif (rising_edge(clk)) then
			if cpt_aux = "1111" then
				cpt_aux := "0000";
				carry_out <= '1';						
				cpt <= cpt_aux;
				
			else 
				cpt_aux := cpt_aux + '1';
				carry_out <= '0';
				cpt <= cpt_aux;
			end if;
		end if;
	end process;
end Behavioral;