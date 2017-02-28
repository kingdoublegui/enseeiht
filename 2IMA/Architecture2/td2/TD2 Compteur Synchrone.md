VHDL
====
TD2 - Compteurs Synchrones
----------------------------------------

[TOC]

### Adaptation du Compteur
```vdhl
library IEEE;
use IEEE.std_logic_1164.all, IEEE.std_logic_arith.all, IEEE.std_logic_unsigned.all;

entity compteur is
	port(
		clk, next : in std_logic;
		cpt: out std_logic_vector(3 downto 0);
		carry: out std_logic
	);
end compteur;

architecture behavioral of compteur is
	begin
		process(clk, next)
			variable cpt_aux : std_logic_vector(3 downto 0) := (others => '0');
		begin
			if (next = '0') then
				cpt_aux := (others => '0');
				cpt <= cpt_aux;
				carry <= '0';
			elsif (rising_edge(clk)) then
				cpt_aux := cpt_aux + 1;
				cpt <= cpt_aux;
			if (cpt_aux = '0') then
				carry <= '1';
			else
				carry <= '0';
			end if;
		end process;
end behavioral;
```

### Un Compteur synchrone

+ *pack_cpt.vdhl*
```vdhl
package pack_cpt is
type t_commande is (RAZ, INC, DEC, LOAD, STAY);
end pack_cpt;
```
+ *compteur2.vdhl*
```vdhl
library IEEE;
use IEEE.std_logic_1164.all, IEEE.std_logic_arith.all, IEEE.std_logic_unsigned.all;
use work.pack_cpt.all;

entity compteur2 is
	generic(n : natural := 4);
	port(
		clk : in std_logic;
		cmd : in t_commande;
		data : in std_logic_vector(size-1 downto 0);
		cpt : out std_logic_vector(size-1 downto 0);
		carry : out
	);
end compteur2;

architecture behavioral of compteur is
	begin
	process(clk)
		signal cpt_aux : std_logic_vector(3 downto 0) := (others => '0');
		begin
		if (clk = '1') then
			case cmd is
			when STAY => null;
			when LOAD =>
				cpt_aux <= data;
			when INC =>
				cpt_aux <= cpt_aux + 1;
			when DEC =>
				cpt_aux <= cpt_aux - 1;
			when RAZ =>
				cpt_aux <= (others => '0');
			end case;
		end if;
	end process;
	cpt <= cpt_aux;
	-- Il faudrait gerer la carry dans le process
	carry <= '1' when (cpt_aux = '0' and (cmd = INC or cmd = DEC))
		else '0';
end behavioral;
```

+ *test_compteur2.vdhl*
```vdhl
library IEEE;
use IEEE.std_logic_1164.all, IEEE.std_logic_arith.all, IEEE.std_logic_unsigned.all;
use work.pack_cpt.all;

entity test_compteur2 is
end test_compteur2;
		
architecture behavioral of test_compteur2 is
	component compteur2
		generic(n : natural := 4);
		port(
			clk : in std_logic;
			cmd : in t_commande;
			data : in std_logic_vector(size-1 downto 0);
			cpt : out std_logic_vector(size-1 downto 0);
			carry : out
		);
	end component;

	signal ...
	signal clk = std_logic;
	constant clk_period : time := 5 ns;

	begin
		I : compteur2
			generic map(10)
			port map( command? );

end test_compteur2;
```

### Un compteur non trivial decrit sous forme d'un automate a etats
```vhdl
entity compteur_btn is
	port (
		clk, reset: in std_logic;
		btn: in std_logic;
		mode: in std_logic;
		data_in: in std_logic_vector(3 downto 0);
		data_out: out std_logic_vector(3 downto 0)
	);
```

Decoupage fonctionnel
```flow
btn=>start: bouton
tb=>operation: traitement operation
na=>operation: nombre d'appui
cpt=>end: compteur
	  [avec mode]

btn->tb->na->cpt
```

```vhdl
architecture arch of compteur_btn is
	signal nb_appui: std_logic_vector(1 downto 0)

	begin
		compteur: process(clk, reset)
			type t_etat is (attente, ..., decrement);
			variable etat: t_etat;
			variable aux: std_logic_vector(3 downto 0);
			begin
				if (reset = '0') then
					etat := attente;
					aux := (others => '0');
					data_out <= aux;
				elsif (riging_edge(clk)) then
					case etat is
					when attente =>
						if (nb_appui = 1) then
							if (mode = '0') then
								etat := increment;
								aux := aux+1
							else
								etat := chargement;
								aux := data_in;
							end if;
						end if;
					when increment =>
						if (nb_appui = 2) then
							etat := stop;
						else
							aux := aux-1;
						end if;
					when stop =>
						if (nb_appui = 3) then
							etat := attente;
							aux := (others => '0');
						end if;
					end case;
					data_out <= aux;
				end if;
		end process;

		-- Premiere version (marche pas sur Nexys 2)
		traitement_bouton: process(btn)
			begin
				if (btn = '1') then
					if (nb_appui = "10") then
						nb_appui <= "00";
					else
						nb_appui <= nb_appui + 1;
					end if;
				end if;
		end process;
		
		-- Seconde version
		traitement_bouton: process(clk, reset)
			type t_etat is (relache,appuye);
			variable etat: t_etat
			begin
				if (reset = '0') then
					nb_appui <= (others => '0');
					etat := relache;
				elsif (rising_edge_clk) then
					if (btn-'1') then
						if (etat = relache) then
							etat := appuye;
							-- Ici on reprend le code
						end if;
					else
						etat := relache;
					end if;
				end if;
		end process;

end compteur_btn;
```

Automate du fonctionnement du compteur