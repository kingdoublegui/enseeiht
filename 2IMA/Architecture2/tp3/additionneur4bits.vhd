library IEEE;

use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all;

entity additionneur4bits is
  port(
       input1    : in std_logic_vector(3 downto 0);
       input2    : in std_logic_vector(3 downto 0);
       carry_in  : in std_logic;
       sum       : out std_logic_vector(3 downto 0);
       carry_out : out std_logic);
end additionneur4bits;

architecture structurelle of additionneur4bits is

  component additionneur
    port(
         input1    : in std_logic;
         input2    : in std_logic;
         carry_in  : in std_logic;
         sum       : out std_logic;
         carry_out : out std_logic);
  end component;

  signal internal : std_logic_vector(2 downto 0);

  begin

    a0: additionneur
      port map(input1(0), input2(0), carry_in, sum(0), internal(0));

    a1: additionneur
      port map(input1(1), input2(1), internal(0), sum(1), internal(1));

    a2: additionneur
      port map(input1(2), input2(2), internal(1), sum(2), internal(2));

    a3: additionneur
      port map(input1(3), input2(3), internal(2), sum(3), carry_out);

  end structurelle;
