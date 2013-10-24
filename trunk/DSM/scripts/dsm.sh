#!/bin/bash
################################################################################################
#
                #
#       DSM Script
                #
#       Developed by Callis Technologies
                #
#
                #
################################################################################################
java_home="/usr/bin"
local_ip=10.101.248.51


case $1 in
	--start) 
		if [ -f .dsm ]; then
			echo "DSM is running..."
		else
			echo "Starting DSM"
			java -d64 -server -XX:ThreadStackSize=160 -Xms2G -Xmx2G -XX:+UseParallelGC -XX:+UseParallelOldGC -cp ../lib/DSM-0.0.1-SNAPSHOT.jar:../lib/* com.callistech.policyserver.dsm.run.RunClass ../config/log4j.dsm.xml &> ../outputConsole.dsm.log & echo $! > .dsm
			echo "DSM started"
	fi
	;;
	--kill) 
		if [ -f .dsm ]; then
			echo "Killing DSM" 		
			pid=`cat .dsm`
			kill $pid        
			rm .dsm
			echo "DSM killed"
		else
			echo "DSM is not running..."
		fi
	;;
	*) echo "Wrong Command";;
esac
