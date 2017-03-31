library IEEE;
use IEEE.std_logic_1164.all;

entity porte_ou is
  port ( 
        i1, i2 : in std_logic;
        o  : out std_logic
       );
  end porte_ou;

architecture vue_flot of porte_ou is

  constant delay : time := 2 ns;

  begin
    o <= i1 or i2 after delay;
  end vue_flot;
