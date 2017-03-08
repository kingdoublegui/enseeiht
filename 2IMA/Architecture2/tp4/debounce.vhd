library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity debounce is
  port(
    si  : in  std_logic;  -- input signal to be debounced
    clk : in  std_logic;  -- input clock
    so  : out std_logic); -- debounced signal
end debounce;

architecture logic of debounce is
  
  signal tempo       : std_logic_vector(1 downto 0); -- memorization of past values
  signal counter_set : std_logic;                    -- sync reset to zero
  signal counter     : natural := 0;                 -- counter
  
begin

  counter_set <= tempo(0) xor tempo(1);           -- determine when to start/reset counter
                                                  -- (two consecutive values are different)
  
  process(clk)
  begin
    if(clk'event and clk = '1') then
      tempo(0) <= si;
      tempo(1) <= tempo(0);
      if(counter_set = '1') then                  -- reset counter because input is changing
        counter <= 0;
      elsif(counter /= 999999) then               -- stable input time is not yet met (10 ms for a 100mhz clk)
        counter <= counter + 1;
      else                                        -- stable input time is met
        so <= tempo(1);
      end if;    
    end if;
  end process;
  
end logic;
