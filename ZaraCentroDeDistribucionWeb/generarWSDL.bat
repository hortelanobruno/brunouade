@echo on
set axis_home=D:\axis-1_3

set NAME=Servicios
set PACKAGE=interfaz/webservices

set cp=%axis_home%\lib\axis.jar;%axis_home%\lib\axis-ant.jar;%axis_home%\lib\commons-discovery-0.2.jar;%axis_home%\lib\commons-logging-1.0.4.jar;%axis_home%\lib\jaxrpc.jar;%axis_home%\lib\log4j-1.2.8.jar;%axis_home%\lib\saaj.jar;%axis_home%\lib\wsdl4j-1.5.1.jar
set cp=%cp%;.\bin;.;%CLASSPATH%;

rem la compilación no es necesaria, porque la hace el eclipse
rem d:\j2sdk1.4.1_01\bin\javac %PACKAGE%\%NAME%.java

java -classpath %cp% org.apache.axis.wsdl.Java2WSDL -o %NAME%.wsdl  -l"http://localhost:8080/axis/services/%NAME%" -n "urn:%PACKAGE%" %PACKAGE%.%NAME%

 