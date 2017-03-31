library IEEE;
use IEEE.std_logic_1164.all;

entity demi_additionneur is
  port ( 
        i1, i2 : in std_logic;
        carry, sum  : out std_logic
       );
  end demi_additionneur;

architecture vue_flot of demi_additionneur is

  constant delay : time := 2 ns;

  begin
    sum <= i1 xor i2 after delay;
    carry <= i1 and i2 after delay;
  end vue_flot;
