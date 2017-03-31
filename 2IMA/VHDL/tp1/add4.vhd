----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    11:10:55 02/08/2017 
-- Design Name: 
-- Module Name:    additionneur4 - structurel 
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

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity additionneur4 is
    Port ( A : in  STD_LOGIC_VECTOR (3 downto 0);
           B : in  STD_LOGIC_VECTOR (3 downto 0);
           Cin : in  STD_LOGIC;
           S : out  STD_LOGIC_VECTOR (3 downto 0);
           Cout : out  STD_LOGIC);
end additionneur4;

architecture structurel of additionneur4 is

	COMPONENT additionneur
	PORT(
		X : IN std_logic;
		Y : IN std_logic;
		Cin : IN std_logic;          
		S : OUT std_logic;
		Cout : OUT std_logic
		);
	END COMPONENT;

  -- signaux locaux
  signal c0, c1, c2 : std_logic;
  
begin
	U0 : additionneur port map ( A(0), B(0), Cin, S(0), c0 );
	U1 : additionneur port map ( A(1), B(1), c0, S(1), c1 );
	U2 : additionneur port map ( A(2), B(2), c1, S(2), c2 );
	U3 : additionneur port map ( A(3), B(3), c2, S(3), Cout );
end structurel;

