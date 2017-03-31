library IEEE;
use IEEE.std_logic_1164.all;

entity diviseurClk1Hz is
  port (
    clk, reset : in  std_logic;
    nclk       : out std_logic);
end diviseurClk1Hz;

architecture arch_divClk of diviseurClk1Hz is

  -- horloge 1 Hz
  constant facteur : natural := 100000000;
  signal top : std_logic := '0';
  
begin  

  div : process (clk, reset)
    variable cpt : integer range 0 to facteur-1 := 0;
  begin 
    if reset = '0' then
      nclk <= '0';
      cpt := 0;
    elsif rising_edge(clk) then
      if(cpt = facteur - 1) then
        cpt := 0;
      else
        cpt := cpt + 1;
      end if;
      if cpt = 0 then
        nclk <= '1';
      else
        nclk <= '0';
      end if;
    end if;
  end process;

end arch_divClk;
