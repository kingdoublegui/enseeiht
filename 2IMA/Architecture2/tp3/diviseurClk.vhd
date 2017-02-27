library IEEE;
use IEEE.std_logic_1164.all;

entity diviseurClk is
  -- facteur : ratio entre la fréquence de l'horloge origine à 100 MHz
  --           et celle de l'horloge générée
  --  ex : 100 MHz -> 1Hz : facteur = 100 000 000
  --  ex : 100 MHz -> 1kHz : facteur = 100 000
  generic(facteur : natural);
  port (
    clk, reset : in  std_logic;
    nclk       : out std_logic);
end diviseurClk;

architecture Behavioral of diviseurClk is

begin

process (clk, reset)
	variable cpt_aux : natural := 0;

begin
	if (reset = '0') then
		cpt_aux := 0;
		nclk <= '0';
	elsif (rising_edge(clk)) then
		if (cpt_aux = facteur-1) then
			nclk <= '1';
			cpt_aux := 0;
		else
			nclk <= '0';
			cpt_aux := cpt_aux + 1;
		end if;
	end if;
end process;

end Behavioral;