library IEEE;
use IEEE.std_logic_1164.all;

entity additionneur is
  port(
       input1    : in std_logic;
       input2    : in std_logic;
       carry_in  : in std_logic;
       sum       : out std_logic;
       carry_out : out std_logic
      );
end additionneur;

architecture vue_flot of additionneur is

  constant delay : time := 2 ns;
  
  begin

    sum <= input1 xor input2 xor carry_in after 2*delay;
    carry_out <= (input1 and input2)
                 or ((input1 xor input2) and carry_in) after 3*delay;

  end vue_flot;
