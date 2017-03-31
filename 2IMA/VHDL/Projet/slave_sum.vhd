library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity slave_sum is
  port ( 
         sclk : in std_logic ;
         mosi : in std_logic ;
         miso : out std_logic ;
         ss   : in  std_logic
       );
end slave_sum;

architecture behavioral of slave_sum is

  -- la valeur initiale est la valeur émise lors de la première transmission
  signal v1 : std_logic_vector(7 downto 0) := "00110011"; 
  signal v2 : std_logic_vector(7 downto 0) := "00110011"; 
  signal sum : std_logic_vector(7 downto 0) := "00110011"; 
  signal sum2 : std_logic_vector(7 downto 0) := "00110011"; 
  signal retenue : std_logic_vector(7 downto 0) := "10000001"; 

begin

  -- process de capture des 2 octets sur front montant de sclk
  capture : process(sclk)
    variable cpt : natural := 7;
    variable r, aux : std_logic := '0';
    variable n : natural;
    variable num_octet : natural := 0;
  begin

    if(rising_edge(sclk)) then

      if(num_octet = 0) then
        -- premier octet
        v1(cpt) <= mosi;
        if(cpt = 0) then
          cpt := 7;
          num_octet := 1;
        else
          cpt := cpt - 1;
        end if;
      else
        -- second octet
        v2(cpt) <= mosi;
        if(cpt = 0) then
          cpt := 7;
          num_octet := 0;
          -- calcul de la somme et de la retenue
          r := '0';
          -- octet 0 particulier
          -- la valeur n'est pas encore "entrée" dans v2(0)
          aux := v1(0) xor mosi;
          sum(0) <= r xor aux;
          r := (v1(0) and mosi) or (aux and r);
          for i in 1 to 7 loop
            aux := v1(i) xor v2(i);
            sum(i) <= r xor aux;
            r := (v1(i) and v2(i)) or (aux and r);
          end loop;
          retenue <= (0 => r, others => '0');
        else
          cpt := cpt - 1;
        end if;
      end if;
    end if;

  end process capture;

  -- process de "présentation" des 2 octets : sum et retenue
  -- le premier bit de la somme est présenté 
  -- sur front descendant de ss 
  -- (qd l'esclave voit que la transmission est initiée)
  -- ensuite les bits sont présentés sur front descendant de sclk
  envoi : process
    variable cpt : natural := 7;
  begin

    wait until falling_edge(ss);
    -- todo : vérifier que ça marche encore (modif retenue)
    -- puis modif pour se passer de cpt ?
    miso <= sum(cpt);
    cpt := cpt - 1;
    for i in 1 to 7 loop
      wait until falling_edge(sclk);
      miso <= sum(cpt);
      if(cpt = 0) then
        cpt := 7;
      else
        cpt := cpt - 1;
      end if;
    end loop;

    for i in 0 to 7 loop
      wait until falling_edge(sclk);
      miso <= retenue(cpt);
      if(cpt = 0) then
        cpt := 7;
      else
        cpt := cpt - 1;
      end if;
    end loop;

  end process envoi;

end behavioral;
