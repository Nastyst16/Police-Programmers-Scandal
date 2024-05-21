.PHONY: build clean run-p1 run-p2 run-p3 run-p4

build: Numarare.class Trenuri.class Drumuri.class Scandal.class

Numarare.class: Numarare/src/Numarare.java Numarare/src/MyScanner.java
	javac -d . $^

run-p1:
	java Numarare


Trenuri.class: Trenuri/src/Trenuri.java
	javac -d . $^

run-p2:
	java Trenuri


Drumuri.class: Drumuri/src/Drumuri.java Drumuri/src/MyScanner.java
	javac -d . $^

run-p3:
	java Drumuri


Scandal.class: Scandal/src/Scandal.java Scandal/src/StructWordProperties.java
	javac -d . $^

run-p4:
	java Scandal


clean:
	rm -f *.class