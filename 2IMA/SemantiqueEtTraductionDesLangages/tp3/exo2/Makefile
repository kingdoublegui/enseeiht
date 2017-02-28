EGGJAR=./eggc-5.4.1.jar
JDIR=/usr/bin
JAVAC=$(JDIR)/javac $(JOPTS)
JAVA=$(JDIR)/java
JAR=$(JDIR)/jar
JAVADOC=$(JDIR)/javadoc

#################

all : legg compile doc exec

doc :
	mkdir doc && \
	cd doc && \
	$(JAVADOC) ../fr/n7/stl/ast/*.java ../fr/n7/stl/ast/impl/*.java

legg : 
	$(JAVA) -jar $(EGGJAR) Bloc.egg

compile :
	cd egg && \
	$(JAVAC) -classpath ../$(EGGJAR):../.:. *.java&& \
	cd .. && \
	$(JAVAC) -classpath $(EGGJAR):. Main.java

exec :
	$(JAVA) -classpath $(EGGJAR):. Main test.txt

clean :
	-rm Main.class
	-rm -rf egg
	-rm -rf doc
	-rm -rf fr/n7/stl/ast/*.class fr/n7/stl/ast/impl/*.class
