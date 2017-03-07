library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all;
use IEEE.std_logic_unsigned.all;

-- Quelques changements par rapport au TP :
-- (a) les noms des signaux pour être cohérent avec la dernière partie où on va utiliser une Fifo de Xilinx ;
-- (b) LE RESET EST ACTIF À ’1’ pour la même raison,
-- (c) nous avons rajouté en sortie le nombre d’éléments contenus dans la fifo pour aider au debugagge

entity fifo is
  port (clk : std_logic;
        rst : in std_logic;
        din : in std_logic_vector(3 downto 0);
        wr_en : in std_logic;
        rd_en : in std_logic;
        dout  : out std_logic_vector(3 downto 0);
        full : out std_logic;
        empty : out std_logic;
        data_count : out std_logic_vector(3 downto 0));
  end fifo;

architecture trois_process of fifo is

  constant taille : natural := 16;  -- puissance de 2 si on veut pouvoir
                                    -- utiliser le modulo en synthèse
  constant largeur : natural := 4;

  type memoire is array (taille-1 downto 0) of
                  std_logic_vector(largeur-1 downto 0);

  type t_etat is (fifo_vide, fifo_entre_les_deux, fifo_pleine);
  signal etat : t_etat;

  -- indices d'écriture et de lecture en signaux => permet le debuggage
  signal i_ecr, i_lec : integer range 0 to taille - 1 ;

  -- utilisation d'un signal pour le tableau => plusieurs process possibles
  signal la_fifo : memoire := (others => (others => '1'));

  -- attention différent du TD(utilisation d'un std_logic_vector)
  -- modifier le nombre de bits si vous changez la taille
  --
  -- on compte de 0 à taille - 1
  -- nb + 1 quand nb = taille - 1 nous fera passer à 0 : cela ne pause pas de problème
  -- on sera dans l'état fifo_pleine et une lecture refera passer nb à taille -1
  signal nb : std_logic_vector(3 downto 0);
                                            
begin

  empty <= '1' when (etat = fifo_vide) else '0';
  full <= '1' when (etat = fifo_pleine) else '0';
  
  data_count <= nb;
  
  p_remplissage : process (clk, rst)
  -- process qui positionne le signal etat
  -- suivant l'action en cours : écriture seule, lecture seule
  --                             ou écriture/lecture simultanées
  -- et du nombre d'éléments présents dans la fifo
  begin

    -- RESET ACTIF À '1'
    if (rst = '1') then

      etat <= fifo_vide;
      nb <= (others => '0');

    elsif (rising_edge(clk)) then

      case etat is
        when fifo_vide  =>
          if (wr_en ='1') then
            etat <= fifo_entre_les_deux;
            nb <= nb + 1;
          end if;
        when fifo_entre_les_deux  =>
          if ((rd_en = '1') and (wr_en = '0')) then
            if nb = 1 then
              etat <= fifo_vide;
            else
              etat <= fifo_entre_les_deux; -- inutile
            end if;
            nb <= nb - 1;
          elsif ((rd_en = '0') and (wr_en = '1')) then
            if nb = taille - 1 then
              etat <= fifo_pleine;
            else
              etat <= fifo_entre_les_deux; -- inutile
            end if;
            nb <= nb + 1;
          end if;
        when fifo_pleine =>
          if ((rd_en ='1') and (wr_en ='0')) then
            etat <= fifo_entre_les_deux;
            nb <= nb - 1;
          end if;
      end case; --etat
    end if; --reset

  end process;

	p_ecriture : process(clk, rst)
	begin
		if (rst = '1') then
			i_ecr <= 0;
			la_fifo <= (others => (others => 'U'));
		elsif (rising_edge(clk)) then
			if (wr_en = '1' and (etat /= fifo_pleine or rd_en = '1')) then
				la_fifo(i_ecr) <= din;
				i_ecr <= (i_ecr+1) mod taille;
			end if;
		end if;
	end process;
	
	p_lecture : process(clk, rst)
	begin
		if (rst = '1') then
			i_lec <= 0;
			dout <= (others =>  '0');
		elsif (rising_edge(clk)) then
			if (rd_en = '1' and etat /= fifo_vide) then
				dout <= la_fifo(i_lec);
				i_lec <= (i_lec+1) mod taille;
			end if;
		end if;
	end process;
	

end trois_process;
