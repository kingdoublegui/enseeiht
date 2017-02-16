@SET PATH=C:/Program Files (x86)/Esterel Technologies/Esterel SCADE 6.2.1/SCADE Suite/../contrib/Msys/mingw/bin;%PATH%
@SET INCLUDE=C:/Program Files (x86)/Esterel Technologies/Esterel SCADE 6.2.1/SCADE Suite/../contrib/Msys/mingw/include
@SET LIB=C:/Program Files (x86)/Esterel Technologies/Esterel SCADE 6.2.1/SCADE Suite/../contrib/Msys/mingw/lib
@IF "-B"=="%1" SET ARG=%1
@mingw32-make.exe %ARG%
