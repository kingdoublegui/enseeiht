#############################################################
######    Begin of Makefile
#############################################################

SCADE=C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\.

SHELL=cmd.exe

C_FLAGS = -O2 -pedantic

all: counter3.dll counter3.exe 

win32/counter3.o: counter3.c
	gcc -I"." -I"$(SCADE)" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\include" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\libraries\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c "counter3.c" -o "win32\counter3.o"	
	ar -r counter3.a "win32\counter3.o"

win32/counter2.o: counter2.c
	gcc -I"." -I"$(SCADE)" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\include" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\libraries\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c "counter2.c" -o "win32\counter2.o"	
	ar -r counter3.a "win32\counter2.o"

win32/decounter.o: decounter.c
	gcc -I"." -I"$(SCADE)" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\include" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\libraries\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c "decounter.c" -o "win32\decounter.o"	
	ar -r counter3.a "win32\decounter.o"

win32/counter.o: counter.c
	gcc -I"." -I"$(SCADE)" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\include" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\libraries\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c "counter.c" -o "win32\counter.o"	
	ar -r counter3.a "win32\counter.o"

win32/kcg_types.o: kcg_types.c
	gcc -I"." -I"$(SCADE)" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\include" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\libraries\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c "kcg_types.c" -o "win32\kcg_types.o"	
	ar -r counter3.a "win32\kcg_types.o"

win32/kcg_consts.o: kcg_consts.c
	gcc -I"." -I"$(SCADE)" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\include" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\libraries\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c "kcg_consts.c" -o "win32\kcg_consts.o"	
	ar -r counter3.a "win32\kcg_consts.o"

win32/counter3_type.o: counter3_type.c
	gcc -I"." -I"$(SCADE)" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\include" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\libraries\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c "counter3_type.c" -o "win32\counter3_type.o"

win32/counter3_mapping.o: counter3_mapping.c
	gcc -I"." -I"$(SCADE)" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\include" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\libraries\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c "counter3_mapping.c" -o "win32\counter3_mapping.o"

win32/counter3_interface.o: counter3_interface.c
	gcc -I"." -I"$(SCADE)" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\include" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\libraries\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c "counter3_interface.c" -o "win32\counter3_interface.o"

win32/SmuProxy.o: C:/Program\ Files\ (x86)/Esterel\ Technologies/Esterel\ SCADE\ 6.2.1/SCADE\ Suite/lib/SmuProxy.c
	gcc -I"." -I"$(SCADE)" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\include" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\libraries\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c "C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\lib\SmuProxy.c" -o "win32\SmuProxy.o"

counter3.dll: win32/counter3.o win32/counter2.o win32/decounter.o win32/counter.o win32/kcg_types.o win32/kcg_consts.o win32/counter3_type.o win32/counter3_mapping.o win32/counter3_interface.o win32/SmuProxy.o SmuExport.def 
	dllwrap --driver-name=gcc  "win32\counter3_type.o" "win32\counter3_mapping.o" "win32\counter3_interface.o" "win32\SmuProxy.o" "counter3.a" --def "SmuExport.def" -lm -lwsock32 --dllname "counter3.dll" 

SmuExport.def: C:/Program\ Files\ (x86)/Esterel\ Technologies/Esterel\ SCADE\ 6.2.1/SCADE\ Suite/lib/SmuExport.def
	copy "C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\lib\SmuExport.def" "SmuExport.def"

win32/counter3_dll.o: counter3_dll.c
	gcc -I"." -I"$(SCADE)" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\include" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\libraries\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c "counter3_dll.c" -o "win32\counter3_dll.o"

win32/SmuMainWindows.o: C:/Program\ Files\ (x86)/Esterel\ Technologies/Esterel\ SCADE\ 6.2.1/SCADE\ Suite/lib/SmuMainWindows.c
	gcc -I"." -I"$(SCADE)" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\include" -I"C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\libraries\libmathext" $(C_FLAGS) -DBUILD_DLL -DSIM -DWIN32 -D_CONSOLE -D_MBCS -c "C:\Program Files (x86)\Esterel Technologies\Esterel SCADE 6.2.1\SCADE Suite\lib\SmuMainWindows.c" -o "win32\SmuMainWindows.o"

counter3.exe: win32/counter3_dll.o win32/SmuMainWindows.o 
	gcc "win32\counter3_dll.o" "win32\SmuMainWindows.o" -lm -lwsock32 -o "counter3.exe" 

#############################################################
######    End of Makefile
#############################################################
