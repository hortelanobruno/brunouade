@echo on

set axis_home=D:\axis-1_3

set NAME=Servicios
set PACKAGE=webservices

set cp=%axis_home%\lib\axis.jar;%axis_home%\lib\axis-ant.jar;%axis_home%\lib\commons-discovery-0.2.jar;%axis_home%\lib\commons-logging-1.0.4.jar;%axis_home%\lib\jaxrpc.jar;%axis_home%\lib\log4j-1.2.8.jar;%axis_home%\lib\saaj.jar;%axis_home%\lib\wsdl4j-1.5.1.jar
set cp=%cp%;.\bin;%CLASSPATH%;


set project_bin_nacho="C:\Documents and Settings\dext\Mis documentos\UADE\2º cuatrimestre 5to año\Ingenieria de  software II\ZaraCentroDeDistribucionWeb\bin\webservices
set project_bin_bruno=D:\EclipseyJBoss\workspace2\ZaraCentroDeDistribucionWeb\bin\webservices

set jboss_nacho=C:\jboss-4.0.5.GA\server\default\deploy\axis.war\WEB-INF\classes\webservices
set jboss_bruno=D:\EclipseyJBoss\jboss-4.0.5.GA\server\default\deploy\webapps\axis.war\WEB-INF\classes\webservices

echo Copiando archivos
copy /Y %project_bin_bruno%\ServiciosSoapBindingImpl.class %jboss_bruno%\ServiciosSoapBindingImpl.class

echo Desregistando el servicio

java -classpath %cp% org.apache.axis.client.AdminClient %PACKAGE%\undeploy.wsdd


echo Registando el servicio

java -classpath %cp% org.apache.axis.client.AdminClient %PACKAGE%\deploy.wsdd


echo FIN