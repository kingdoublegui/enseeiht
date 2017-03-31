library ieee;
use ieee.std_logic_1164.all;

-- allume et éteint le led suivant la fréquence de l'horloge en entrée

entity led_driver is
    Port ( clk : in  std_logic;
           reset : in  std_logic;
           led : out  std_logic);
end led_driver;

architecture Behavioral of led_driver is

  signal ledsig : std_logic;
  
begin

  process(reset, clk)
  begin
    if(reset = '0') then
      ledsig <= '0';
    elsif(clk'event and clk='1') then
      ledsig <= not ledsig;
    end if;
  end process;

  led <= ledsig;
  
end Behavioral;

