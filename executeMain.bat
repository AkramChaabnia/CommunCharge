@echo off

rem Set the path to the bin directory
set BIN_PATH=bin

rem Compile the Java code with UTF-8 encoding
javac -encoding UTF-8 -d "%BIN_PATH%" "src\up\mi\ara\terminal\Main.java"

rem Run the compiled Java program with UTF-8 encoding
java -Dfile.encoding=UTF-8 -cp "bin" up.mi.ara.terminal.Main




pause
