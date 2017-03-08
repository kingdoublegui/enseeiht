library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity All7Segments is
    Port ( clk : in  std_logic;
           reset : in std_logic;
           e0 : in std_logic_vector (3 downto 0);
           e1 : in std_logic_vector (3 downto 0);
           e2 : in std_logic_vector (3 downto 0);
           e3 : in std_logic_vector (3 downto 0);
           e4 : in std_logic_vector (3 downto 0);
           e5 : in std_logic_vector (3 downto 0);
           e6 : in std_logic_vector (3 downto 0);
           e7 : in std_logic_vector (3 downto 0);
           an : out std_logic_vector (7 downto 0);
           ssg : out std_logic_vector (7 downto 0));
end All7Segments;

architecture Behavioral of All7Segments is

  component dec7seg
    port (
      value : in std_logic_vector(3 downto 0);
      seg : out std_logic_vector (7 downto 0)
    );
  end component;

  component diviseurClk
    generic(facteur : natural);
    port (
    clk, reset : in  std_logic;
    nclk       : out std_logic);
  end component;
  
  signal nclk : std_logic;
  signal entre : std_logic_vector(3 downto 0) := "0000";

begin


  divClk : diviseurClk
    generic map (100000)
    port map (clk, reset, nclk);

  dec7seg0 : dec7seg
 	port map (entre, ssg);
   
  process(reset, nclk)
    variable segcpt : integer range 0 to 7 := 0;
  begin
    if(reset = '0') then

      segcpt := 0;
      an <= (others => '1');

    elsif rising_edge(nclk) then
      
      segcpt := (segcpt + 1) mod 8;
		
      case segcpt is
        when 0 => 
          entre(3 downto 0) <= e0;
          an <= (0 => '0', others => '1');
        when 1 =>
          entre(3 downto 0) <= e1;
          an <= (1 => '0', others => '1');
        when 2 =>
          entre(3 downto 0) <= e2;
          an <= (2 => '0', others => '1');
        when 3 =>
          entre(3 downto 0) <= e3;
          an <= (3 => '0', others => '1');
        when 4 =>
          entre(3 downto 0) <= e4;
          an <= (4 => '0', others => '1');
        when 5 =>
          entre(3 downto 0) <= e5;
          an <= (5 => '0', others => '1');
        when 6 =>
          entre(3 downto 0) <= e6;
          an <= (6 => '0', others => '1');
        when 7 =>
          entre(3 downto 0) <= e7;
          an <= (7 => '0', others => '1');
        when others => null;  
      end case;

    end if;
  end process;

end Behavioral;

