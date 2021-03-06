# https://stackoverflow.com/questions/2235276/how-to-run-junit-test-cases-from-the-command-line
# https://stackoverflow.com/questions/3931741/why-does-make-think-the-target-is-up-to-date
# https://www.gnu.org/software/make/manual/html_node/Conditional-Functions.html#Conditional-Functions
.PHONY: doc diff test

jar = lab3.jar

class.production = build/production
class.tests = build/tests

lib = lib
lib.junit = $(lib)/junit-4.12.jar
lib.hamcrest = $(lib)/hamcrest-core-1.3.jar

src = src
src.tests = test

test.report = report

docs = doc
docs.manifest = META-INF/MANIFEST.MF

classes = Point

build: compile
	jar cfe $(jar) Programm -C $(class.production) .

compile:
	javac -d $(class.production) $(src)/*.java
	javac -d $(class.tests) -cp $(class.production):$(lib)/* $(src.tests)/*.java

test: build
	java -cp .:$(class.production):$(class.tests):$(lib.junit):$(lib.hamcrest) org.junit.runner.JUnitCore ProgrammTest > report/$(test.report)

doc:
	javadoc $(src)/*.java -d $(docs)

	tar -c * -f temp --mtime="1970-01-01"
	jar xf lab3.jar $(docs.manifest)

	echo "md5: `md5sum temp | cut -d ' ' -f 1`" >> manifest.temp
	echo "sha1: `sha1sum temp | cut -d ' ' -f 1`" >> manifest.temp

	jar uvfm $(jar) manifest.temp
	rm -f manifest.temp
	rm -f temp

clean:
	rm -f $(class.production)/*.class
	rm -f $(class.tests)/*.class
	rm -f $(docs)/*
	rm -f $(jar)
	rm -f report/$(test.report)

diff:
	./commit.sh $(classes)
