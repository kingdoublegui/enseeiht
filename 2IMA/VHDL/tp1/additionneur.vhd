library IEEE;
use IEEE.std_logic_1164.all;

-- interface du composant additionneur
entity additionneur is
  port(
       X, Y, Cin    : in std_logic;
       S, Cout    : out std_logic
      );
end additionneur;

-- première architecture possible : vue structurelle
architecture vue_structurelle of additionneur is

  -- rappel de l'interface des sous-composants utilisés
  -- (génération automatique du code possible)
  component demi_additionneur
    port ( 
           i1, i2 : in std_logic;
           carry, sum  : out std_logic
         );
  end component;

  component porte_ou
    port( i1, i2 : in std_logic;
          o      : out std_logic
        );
  end component;

  -- configuration des architectures des sous-composants inutile :
  -- il n'y a pas le choix

  -- signaux locaux
  signal a, b, c : std_logic;

  begin

    -- instantiations et connexions des 3 sous-composants ==
    -- 3 instructions concurrentes
    -- (génération automatique du code possible)

    U1 : demi_additionneur port map ( Cin, X, a, b );
    U2 : demi_additionneur port map ( b, Y, c, S );
    U3 : porte_ou          port map ( a, c, Cout );
    
  end vue_structurelle;

-- deuxième architecture : vue flot de données
architecture vue_flot of additionneur is

  constant delay : time := 3 ns;

  -- signal local
  signal i : std_logic;
  
  begin

    -- 3 équations logiques qui calculent i, Cout et S ==
    -- 3 instructions concurrentes
    S <= i xor Cin after delay;
    Cout <= (X and Y) or (i and Cin) after 2*delay;
    i <= X xor Y after delay;
  end vue_flot;

-- troisième architecture : vue comportementale == algorithme
architecture vue_comportementale of additionneur is
  
  constant delay : time := 2 ns;

  begin

    -- 1 process pour donner l'algorithme de calcul de S et Cout ==
    -- 1 seule instruction concurrente
    process(X, Y, Cin) -- se réveille dès qu'un de ces 3 signaux évolue
    
      constant vector_sum : std_logic_vector(0 to 3) := "0101";
      constant vector_carry : std_logic_vector(0 to 3) := "0011";
      -- variable locale au process
      variable N : integer := 0;
    
      begin
        -- affectation de variable
        N := 0;
        if(X = '1') then
          N := N + 1;
        end if;

        if(Y = '1') then
          N := N + 1;
        end if;

        if(Cin = '1') then
          N := N + 1;
        end if;

        -- affectations séquentielles de signal
        S <= vector_sum(N) after 2*delay;
        Cout <= vector_carry(N) after 3*delay;

      end process;

  end vue_comportementale;
