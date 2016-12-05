
BODYS = $(basename $(wildcard *.adb))

GNATDIR = /opt/bin/

GNATOPT = -a

# lr-affic lr-main lr-simu lr-synchro lr-synchro-basique lr-synchro-exclusive lr-tasks

all :
	$(GNATDIR)gnatmake $(GNATOPT) $(BODYS) `$(GNATDIR)gtkada-config` -lexpat -Wl,-rpath -Wl,/opt/lib/gtkada/relocatable

clean :
	rm -f *.o *.ali
