@echo on
set project_bin_nacho = C:/Documents and Settings/dext/Mis documentos/UADE/2º cuatrimestre 5to año/Ingenieria de  software II/ZaraCentroDeDistribucionWeb/bin
set project_bin_bruno = C:/Documents and Settings/dext/Mis documentos/UADE/2º cuatrimestre 5to año/Ingenieria de  software II/ZaraCentroDeDistribucionWeb/bin

set jboss_nacho = C:/jboss-4.0.5.GA/server/default/deploy/axis.war/WEB-INF/classes/webservices
set jboss_bruno = C:/jboss-4.0.5.GA/server/default/deploy/axis.war/WEB-INF/classes/webservices

echo Copiando archivos
copy %project_bin_nacho%/ServiciosSoapBindingImpl.class %jboss_nacho%/ServiciosSoapBindingImpl.class

echo Desregistando el servicio
unregisterService


echo Registando el servicio
registerService


echo FIN