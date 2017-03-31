----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    14:51:53 03/13/2017 
-- Design Name: 
-- Module Name:    master_sum - Behavioral 
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

entity master_sum is
    Port ( en : in  STD_LOGIC;
           e1 : in  STD_LOGIC_VECTOR (7 downto 0);
           e2 : in  STD_LOGIC_VECTOR (7 downto 0);
           s : out  STD_LOGIC_VECTOR (7 downto 0);
           carry : out  STD_LOGIC;
           busy : out  STD_LOGIC;
           sclk : out  STD_LOGIC;
           mosi : out  STD_LOGIC;
           miso : in  STD_LOGIC;
			  ss : out STD_LOGIC;
           clk : in  STD_LOGIC;
           reset : in  STD_LOGIC);
end master_sum;

architecture Behavioral of master_sum is

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

type t_etat is (debut, attente, envoi1, envoi2);
signal etat : t_etat;

signal en1 : std_logic;
signal din1 : std_logic_vector(7 downto 0);
signal dout1 : std_logic_vector(7 downto 0);
signal busy1 : std_logic;

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

	p_master_sum : process(clk)
		variable cpt : natural := 7;
		variable i_octet : natural := 0;
	begin
		if(reset = '0') then --remise a zero
			busy <= '0';
			ss <= '1';
			carry <= '0';
			s <= "00000000";
			etat <= debut;
	
		elsif(rising_edge(clk)) then
			case etat is
			when debut =>
				if (en = '1') then
					ss <= '0';
					busy <= '1';
					cpt := 5;
					i_octet := 0;
					etat <= attente;
				end if;
			when attente =>
				if (cpt = 0) then
						en1 <= '1';
					if (i_octet = 0) then
						din1 <= e1;
						etat <= envoi1;
					elsif (i_octet = 1) then
						din1 <= e2;
						etat <= envoi2;
					end if;
				else
					cpt := cpt-1;
				end if;
			when envoi1 =>
				en1 <= '0';
				if(busy1 = '0' and en1 = '0') then
					s <= dout1;
					cpt := 2;
					i_octet := 1;
					etat <= attente;
				end if;
			when envoi2 =>
				en1 <= '0';
				if(busy1 = '0' and en1 = '0') then
					carry <=  dout1(0);
					i_octet := 0;
					s <= dout1;
					ss <= '1'; 
					busy <= '0';
					etat <= debut;
				end if;
			end case;
		end if;
	
	end process;

end Behavioral;

