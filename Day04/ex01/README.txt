To execute this program, please follow next steps:

1. find . -name "*.java" > source.txt && cp -r src/resources/ target
2. javac -d target @source.txt
3. jar cvmf src/manifest.txt target/images-to-chars-printer.jar -C target ./edu/school21/printer
4. java -jar target/images-to-chars-printer.jar <1> <2>

<1> - white char
<2> - black char
