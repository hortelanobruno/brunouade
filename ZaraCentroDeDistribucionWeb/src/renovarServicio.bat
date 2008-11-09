@echo on
set project_bin_nacho=C:/Documents and Settings/dext/Mis documentos/UADE/2º cuatrimestre 5to año/Ingenieria de  software II/ZaraCentroDeDistribucionWeb/bin
set project_bin_bruno=D:\EclipseyJBoss\workspace2\ZaraCentroDeDistribucionWeb\bin\webservices

set jboss_nacho=C:/jboss-4.0.5.GA/server/default/deploy/axis.war/WEB-INF/classes/webservices
set jboss_bruno=D:\EclipseyJBoss\jboss-4.0.5.GA\server\default\deploy\webapps\axis.war\WEB-INF\classes\webservices

echo Copiando archivos
copy /Y %project_bin_bruno%\ServiciosSoapBindingImpl.class %jboss_bruno%\ServiciosSoapBindingImpl.class

echo Desregistando el servicio
unregisterService


echo Registando el servicio
registerService


echo FIN