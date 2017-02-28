VHDL
====
TD3 - La Fifo
------------------

[TOC]

### Objectif
+ automate
+ affectation de signaux
+ notion de `process`

### Sujet
On desire realiser la conception synthetisable d'un composant Fifo synchrone sur une horloge

#### Interface
Ce composant Fifa dispose en entree:

+ d'un bus de donnees a stocker, `data_in`
+ d'une horloge `clk
+ d'un signal de remise a zero asynchrone `reset`, actif a `'0'`
+ de deux signaux de commande de lecture `read` et d'ecriture `write`, actif a `'1'`

Ce composant est implante comme un tableau de registres fonctionnant de maniere asynchrone sur le signal de remise a zero et de maniere synchrone sur le front montant de l'horloge. Ses sorties sont:

+ un bus de donnes, `data_out`
+ deux signaux `empty` et `full` qui indiquent l'etat du composant

L'interface VHDL de la Fifo est la suivante

```vhdl
entity fifo is
	port (
		clk, reset  : in  std_logic;
		write, read : in  std_logic;
		data_in     : in  std_logic_vector(3 downto 0);
		data_out    : out std_logic_vector(3 downto 0);
		empty, full : out std_logic);
end fifo;
```

#### Fonctionnement du composant Fifo
Les signaux de commandes `read` et `write` fonctionnent de maniere independante ce qui permet une lecture et une ecriture simultannees. Ce parallelisme est interessant car generalement la lecture et l'ecriture ne se font pas sur la meme case de la Fifo.
Lorsqu'on lit la Fifo vide ou lorsqu'on ecrit dans la Fifo pleine, il ne se passe rien : la Fifo ne reagit pas et on ne modifie rien.
Par convention une lecture simultannees dans une file pleine sont possibles (la valeur en entree prend la place de la valeur lue); par contre en cas de lecture et d'ecriture simultanees dans une file vide, seule l'ecriture est effectuee.

### Travail a realiser
> Decrire en VHDL une architecture de ce composant Fifo repondant a la description deprecendte. Cette architecture sera decrite en utilisant un automate a etas qui permet de decomposer le fonctionnement en plusieurs process.

> **Description par un automate**
> ![Automate Fifo](data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+CjwhRE9DVFlQRSBzdmcgUFVCTElDICItLy9XM0MvL0RURCBTVkcgMS4xLy9FTiIKICJodHRwOi8vd3d3LnczLm9yZy9HcmFwaGljcy9TVkcvMS4xL0RURC9zdmcxMS5kdGQiPgo8IS0tIEdlbmVyYXRlZCBieSBncmFwaHZpeiB2ZXJzaW9uIDIuMzguMCAoMjAxNDA0MTMuMjA0MSkKIC0tPgo8IS0tIFRpdGxlOiBmaW5pdGVfc3RhdGVfbWFjaGluZSBQYWdlczogMSAtLT4KPHN2ZyB3aWR0aD0iMTcwcHQiIGhlaWdodD0iNTE3cHQiCiB2aWV3Qm94PSIwLjAwIDAuMDAgMTcwLjQ5IDUxNy4wNyIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayI+CjxnIGlkPSJncmFwaDAiIGNsYXNzPSJncmFwaCIgdHJhbnNmb3JtPSJzY2FsZSgxIDEpIHJvdGF0ZSgwKSB0cmFuc2xhdGUoNCA1MTMuMDY5KSI+Cjx0aXRsZT5maW5pdGVfc3RhdGVfbWFjaGluZTwvdGl0bGU+Cjxwb2x5Z29uIGZpbGw9IndoaXRlIiBzdHJva2U9Im5vbmUiIHBvaW50cz0iLTQsNCAtNCwtNTEzLjA2OSAxNjYuNDg2LC01MTMuMDY5IDE2Ni40ODYsNCAtNCw0Ii8+CjwhLS0gZmlmb192aWRlIC0tPgo8ZyBpZD0ibm9kZTEiIGNsYXNzPSJub2RlIj48dGl0bGU+Zmlmb192aWRlPC90aXRsZT4KPGVsbGlwc2UgZmlsbD0ibm9uZSIgc3Ryb2tlPSJibGFjayIgY3g9IjgxLjI0MjgiIGN5PSItNDY2LjE3MyIgcng9IjQyLjc5MjYiIHJ5PSI0Mi43OTI2Ii8+Cjx0ZXh0IHRleHQtYW5jaG9yPSJtaWRkbGUiIHg9IjgxLjI0MjgiIHk9Ii00NjIuNDczIiBmb250LWZhbWlseT0iVGltZXMsc2VyaWYiIGZvbnQtc2l6ZT0iMTQuMDAiPmZpZm9fdmlkZTwvdGV4dD4KPC9nPgo8IS0tIGZpZm9fZW50cmVfbGVzX2RldXggLS0+CjxnIGlkPSJub2RlMiIgY2xhc3M9Im5vZGUiPjx0aXRsZT5maWZvX2VudHJlX2xlc19kZXV4PC90aXRsZT4KPGVsbGlwc2UgZmlsbD0ibm9uZSIgc3Ryb2tlPSJibGFjayIgY3g9IjgxLjI0MjgiIGN5PSItMjYxLjAzNCIgcng9IjgxLjQ4NjMiIHJ5PSI4MS40ODYzIi8+Cjx0ZXh0IHRleHQtYW5jaG9yPSJtaWRkbGUiIHg9IjgxLjI0MjgiIHk9Ii0yNTcuMzM0IiBmb250LWZhbWlseT0iVGltZXMsc2VyaWYiIGZvbnQtc2l6ZT0iMTQuMDAiPmZpZm9fZW50cmVfbGVzX2RldXg8L3RleHQ+CjwvZz4KPCEtLSBmaWZvX3ZpZGUmIzQ1OyZndDtmaWZvX2VudHJlX2xlc19kZXV4IC0tPgo8ZyBpZD0iZWRnZTEiIGNsYXNzPSJlZGdlIj48dGl0bGU+Zmlmb192aWRlJiM0NTsmZ3Q7Zmlmb19lbnRyZV9sZXNfZGV1eDwvdGl0bGU+CjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iYmxhY2siIGQ9Ik01Ny41NDgzLC00MzAuMzdDNTMuMjk1NiwtNDIyLjQ0NiA0OS41MDE3LC00MTMuODMyIDQ3LjI0MjgsLTQwNS4yNzcgNDIuMTkxMSwtMzg2LjE0NCA0My4zMDQ1LC0zNjUuNDgzIDQ3LjMxNjEsLTM0NS45NjgiLz4KPHBvbHlnb24gZmlsbD0iYmxhY2siIHN0cm9rZT0iYmxhY2siIHBvaW50cz0iNTAuNzM0NiwtMzQ2LjcxOSA0OS41ODE4LC0zMzYuMTg4IDQzLjkxNTIsLTM0NS4xNCA1MC43MzQ2LC0zNDYuNzE5Ii8+Cjx0ZXh0IHRleHQtYW5jaG9yPSJtaWRkbGUiIHg9IjcxLjI0MjgiIHk9Ii0zNzkuMDc3IiBmb250LWZhbWlseT0iVGltZXMsc2VyaWYiIGZvbnQtc2l6ZT0iMTQuMDAiPndyaXRlPSYjMzk7MSYjMzk7PC90ZXh0Pgo8L2c+CjwhLS0gZmlmb19lbnRyZV9sZXNfZGV1eCYjNDU7Jmd0O2ZpZm9fdmlkZSAtLT4KPGcgaWQ9ImVkZ2UyIiBjbGFzcz0iZWRnZSI+PHRpdGxlPmZpZm9fZW50cmVfbGVzX2RldXgmIzQ1OyZndDtmaWZvX3ZpZGU8L3RpdGxlPgo8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9ImJsYWNrIiBkPSJNOTUuMDAxOCwtMzQxLjI2N0M5Ni45NzQ0LC0zNjIuMTA1IDk3LjYzMjksLTM4NC41NSA5NS4yNDI4LC00MDUuMjc3IDk0Ljg5MzIsLTQwOC4zMDkgOTQuNDQ3MiwtNDExLjQxIDkzLjkzMDYsLTQxNC41MjciLz4KPHBvbHlnb24gZmlsbD0iYmxhY2siIHN0cm9rZT0iYmxhY2siIHBvaW50cz0iOTAuNDY4NCwtNDE0LjAwMSA5Mi4wNzI0LC00MjQuNDc0IDk3LjM0OTQsLTQxNS4yODcgOTAuNDY4NCwtNDE0LjAwMSIvPgo8dGV4dCB0ZXh0LWFuY2hvcj0ibWlkZGxlIiB4PSIxMjAuNzQzIiB5PSItMzk0LjA3NyIgZm9udC1mYW1pbHk9IlRpbWVzLHNlcmlmIiBmb250LXNpemU9IjE0LjAwIj5uYl9lbHQ9MTwvdGV4dD4KPHRleHQgdGV4dC1hbmNob3I9Im1pZGRsZSIgeD0iMTIwLjc0MyIgeT0iLTM3OS4wNzciIGZvbnQtZmFtaWx5PSJUaW1lcyxzZXJpZiIgZm9udC1zaXplPSIxNC4wMCI+d3JpdGU9JiMzOTswJiMzOTs8L3RleHQ+Cjx0ZXh0IHRleHQtYW5jaG9yPSJtaWRkbGUiIHg9IjEyMC43NDMiIHk9Ii0zNjQuMDc3IiBmb250LWZhbWlseT0iVGltZXMsc2VyaWYiIGZvbnQtc2l6ZT0iMTQuMDAiPnJlYWQ9JiMzOTsxJiMzOTs8L3RleHQ+CjwvZz4KPCEtLSBmaWZvX3BsZWluZSAtLT4KPGcgaWQ9Im5vZGUzIiBjbGFzcz0ibm9kZSI+PHRpdGxlPmZpZm9fcGxlaW5lPC90aXRsZT4KPGVsbGlwc2UgZmlsbD0ibm9uZSIgc3Ryb2tlPSJibGFjayIgY3g9IjgxLjI0MjgiIGN5PSItNDkuMzk1NiIgcng9IjQ5LjI5MTUiIHJ5PSI0OS4yOTE1Ii8+Cjx0ZXh0IHRleHQtYW5jaG9yPSJtaWRkbGUiIHg9IjgxLjI0MjgiIHk9Ii00NS42OTU2IiBmb250LWZhbWlseT0iVGltZXMsc2VyaWYiIGZvbnQtc2l6ZT0iMTQuMDAiPmZpZm9fcGxlaW5lPC90ZXh0Pgo8L2c+CjwhLS0gZmlmb19lbnRyZV9sZXNfZGV1eCYjNDU7Jmd0O2ZpZm9fcGxlaW5lIC0tPgo8ZyBpZD0iZWRnZTMiIGNsYXNzPSJlZGdlIj48dGl0bGU+Zmlmb19lbnRyZV9sZXNfZGV1eCYjNDU7Jmd0O2ZpZm9fcGxlaW5lPC90aXRsZT4KPHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSJibGFjayIgZD0iTTM3LjczOTUsLTE5Mi40MUMyNy41NTg3LC0xNjguNTY0IDIxLjgzNjIsLTE0MS41MzYgMzAuMjQyOCwtMTE2Ljc5MSAzMi44NzI4LC0xMDkuMDUgMzYuODA5MywtMTAxLjUwNSA0MS4zNTEyLC05NC40NDM3Ii8+Cjxwb2x5Z29uIGZpbGw9ImJsYWNrIiBzdHJva2U9ImJsYWNrIiBwb2ludHM9IjQ0LjU0LC05NS45ODg0IDQ3LjM3NTMsLTg1Ljc4IDM4Ljc5MjgsLTkxLjk5MjIgNDQuNTQsLTk1Ljk4ODQiLz4KPHRleHQgdGV4dC1hbmNob3I9Im1pZGRsZSIgeD0iNzAuNzQyOCIgeT0iLTE1MC41OTEiIGZvbnQtZmFtaWx5PSJUaW1lcyxzZXJpZiIgZm9udC1zaXplPSIxNC4wMCI+bmJfZWx0PXRhaWxsZSYjNDU7MTwvdGV4dD4KPHRleHQgdGV4dC1hbmNob3I9Im1pZGRsZSIgeD0iNzAuNzQyOCIgeT0iLTEzNS41OTEiIGZvbnQtZmFtaWx5PSJUaW1lcyxzZXJpZiIgZm9udC1zaXplPSIxNC4wMCI+d3JpdGU9JiMzOTsxJiMzOTs8L3RleHQ+Cjx0ZXh0IHRleHQtYW5jaG9yPSJtaWRkbGUiIHg9IjcwLjc0MjgiIHk9Ii0xMjAuNTkxIiBmb250LWZhbWlseT0iVGltZXMsc2VyaWYiIGZvbnQtc2l6ZT0iMTQuMDAiPnJlYWQ9JiMzOTswJiMzOTs8L3RleHQ+CjwvZz4KPCEtLSBmaWZvX3BsZWluZSYjNDU7Jmd0O2ZpZm9fZW50cmVfbGVzX2RldXggLS0+CjxnIGlkPSJlZGdlNCIgY2xhc3M9ImVkZ2UiPjx0aXRsZT5maWZvX3BsZWluZSYjNDU7Jmd0O2ZpZm9fZW50cmVfbGVzX2RldXg8L3RpdGxlPgo8cGF0aCBmaWxsPSJub25lIiBzdHJva2U9ImJsYWNrIiBkPSJNMTAzLjgyNSwtOTMuNDIzOUMxMDYuODk1LC0xMDEuMDI0IDEwOS41NjUsLTEwOS4wMDEgMTExLjI0MywtMTE2Ljc5MSAxMTUuMjc1LC0xMzUuNTEgMTE0LjMzNCwtMTU1LjYyMiAxMTAuOTk3LC0xNzQuNjg0Ii8+Cjxwb2x5Z29uIGZpbGw9ImJsYWNrIiBzdHJva2U9ImJsYWNrIiBwb2ludHM9IjEwNy41NTQsLTE3NC4wNTUgMTA5LjA0OCwtMTg0LjU0NCAxMTQuNDIxLC0xNzUuNDEzIDEwNy41NTQsLTE3NC4wNTUiLz4KPHRleHQgdGV4dC1hbmNob3I9Im1pZGRsZSIgeD0iMTM3LjI0MyIgeT0iLTE0My4wOTEiIGZvbnQtZmFtaWx5PSJUaW1lcyxzZXJpZiIgZm9udC1zaXplPSIxNC4wMCI+d3JpdGU9JiMzOTsmIzM5OzA8L3RleHQ+Cjx0ZXh0IHRleHQtYW5jaG9yPSJtaWRkbGUiIHg9IjEzNy4yNDMiIHk9Ii0xMjguMDkxIiBmb250LWZhbWlseT0iVGltZXMsc2VyaWYiIGZvbnQtc2l6ZT0iMTQuMDAiPnJlYWQ9JiMzOTsxJiMzOTs8L3RleHQ+CjwvZz4KPC9nPgo8L3N2Zz4K)


```vhdl
architecture 3_process of fifo is
	constant taille : natural := 10;
	type t_memoire is array (taille-1 downto 0) of std_logic_vector (3 downto 0);
	signal la_fifo : t_memoire := (others => (others => ' '));
	type t_etat is (fifo_vide, fifo_entre_les_deux, fifo_pleine);
	signal etat : t_etat;

	begin
		full <= '1' when (etat=fifo_pleine) else '0';
		empty <= '1' when (etat=fifo_vide) else '0';

process (clk, reset)
	variable nb_elt : natural range 0 to taille-1;

	begin
		if (reset = '0') then
			nb_elt := 0;
			etat := fifo_vide;
		elsif (rising_edge(clk)) then
			case etat is
			when fifo_vide =>
				if (write = '1') then
					nb_elt := 1;
					etat <= fifo_entre_les_deux;
				end if;
			when fifo_pleine =>
				if (read = '1' and write = '0')
					nb_elt := taille-1;
					etat <= fifo_pleine;
				end if;
			when fifo_entre_les_deux =>
				if (write = '0' and read = '1')
					if (nb_elt = 1) then
						etat <= fifo_vide;
					end if;
					nb_elt := nb_elt+1;
				elsif (write = '1' and read = '0') then
					if (nb_elt = taille-1) then
						etat <= fifo_pleine;
					end if;
					nb_elt := nb_elt+1;
				end if;
			end case;
		end if;
end process;
```

```vhdl
p_ecriture : process(clk, reset)
	variable i_ecr : natural range - to taille-1;

	begin
		if (reset = '0') then
			i_ecr := 0;
			la_fifo <= (others => (others => ' '));
		elsif (rising_edge(clk)) then
			if (write = '1' and (etat /= fifo_pleine or read = '1)') then
				memoire(i_lec) <= data_in;
				i_ecr := (iecr+1) mod taille;
			end if;
```