EGGJAR=./eggc-5.4.1.jar
JDIR=/usr/bin
JAVAC=$(JDIR)/javac $(JOPTS)
JAVA=$(JDIR)/java
JAR=$(JDIR)/jar
JAVADOC=$(JDIR)/javadoc

#################

all : legg compile exec

legg : 
	$(JAVA) -jar $(EGGJAR) GDouble.egg

compile :
	cd egg && \
	$(JAVAC) -classpath ../$(EGGJAR):. *.java&& \
	cd .. && \
	$(JAVAC) -classpath $(EGGJAR):. Main.java

exec :
	$(JAVA) -classpath $(EGGJAR):. Main test.txt

clean :
	rm Main.class && \
	rm -rf egg
