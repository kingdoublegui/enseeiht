--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   15:10:52 03/08/2017
-- Design Name:   
-- Module Name:   /home/tmeunier/2IMA/Architecture2/Projet/er_1octet_tests.vhd
-- Project Name:  Projet_VHDL
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: er_1octet
-- 
-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
--
-- Notes: 
-- This testbench has been automatically generated using types std_logic and
-- std_logic_vector for the ports of the unit under test.  Xilinx recommends
-- that these types always be used for the top-level I/O of a design in order
-- to guarantee that the testbench will bind correctly to the post-implementation 
-- simulation model.
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
ENTITY er_1octet_tests IS
END er_1octet_tests;
 
ARCHITECTURE behavior OF er_1octet_tests IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT er_1octet
    PORT(
         en : IN  std_logic;
         din : IN  std_logic_vector(7 downto 0);
         dout : OUT  std_logic_vector(7 downto 0);
         busy : OUT  std_logic;
         sclk : OUT  std_logic;
         mosi : OUT  std_logic;
         miso : IN  std_logic;
         clk : IN  std_logic;
         reset : IN  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal en : std_logic := '0';
   signal din : std_logic_vector(7 downto 0) := (others => '0');
   signal miso : std_logic := '0';
   signal clk : std_logic := '0';
   signal reset : std_logic := '0';

 	--Outputs
   signal dout : std_logic_vector(7 downto 0);
   signal busy : std_logic;
   signal sclk : std_logic;
   signal mosi : std_logic;

   -- Clock period definitions
   constant clk_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: er_1octet PORT MAP (
          en => en,
          din => din,
          dout => dout,
          busy => busy,
          sclk => sclk,
          mosi => mosi,
          miso => miso,
          clk => clk,
          reset => reset
        );

   -- Clock process definitions
 
   clk_process :process
   begin
		clk <= '0';
		wait for clk_period/2;
		clk <= '1';
		wait for clk_period/2;
   end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
      -- hold reset state for 100 ns.
		
		
		reset <= '1';
      wait for clk_period;
		reset <= '0';

      -- insert stimulus here
		din <= "11110111";
		en <= '1';
		wait for clk_period;
		en <= '0';
		
		wait for clk_period*20;
		din <= "00001111";
		en <= '1';
		wait for clk_period;
		en <= '0';

      wait;
   end process;
	
	miso_proc: process
	begin
		if (miso = '1') then
			miso <= '0';
		else
			miso <= '1';
		end if;
		wait on clk;
		wait on clk;
		wait on clk;
		wait on clk;
		wait on clk;
		wait on clk;
		wait on clk;
	end process;

END;
