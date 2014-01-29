DELIMITER $$

SET GLOBAL event_scheduler=off $$

DROP EVENT IF EXISTS `EVENT_DELETE_ACCOUNTINGS` $$
CREATE EVENT EVENT_DELETE_ACCOUNTINGS
ON SCHEDULE EVERY 1 HOUR
ON COMPLETION PRESERVE
ENABLE
DO BEGIN 
        DECLARE CANTIDAD_A_BORRAR BIGINT;
        DECLARE LIMIT_BORRADO INT DEFAULT 50000;
        DECLARE VUELTAS INT;

        #Borro tabla radacct
	#DELETE FROM radacct WHERE AcctStartTime < DATE_ADD(now(), INTERVAL -7 DAY);

        SELECT count(*) as cant INTO CANTIDAD_A_BORRAR FROM radacct WHERE AcctStartTime < DATE_ADD(now(), INTERVAL -7 DAY);
        SELECT floor(CANTIDAD_A_BORRAR/LIMIT_BORRADO)+1 INTO VUELTAS;
       
        WHILE VUELTAS > 0 DO
            DELETE FROM radacct WHERE AcctStartTime < DATE_ADD(now(), INTERVAL -7 DAY) LIMIT 50000;
            SELECT SLEEP(2);
            SET VUELTAS = VUELTAS - 1;
        END WHILE;

        #Borro tabla portalcv_wlcaccounting
        #DELETE FROM portalcv_wlcaccounting WHERE acctStart < DATE_ADD(now(), INTERVAL -7 DAY);

        SELECT count(*) as cant INTO CANTIDAD_A_BORRAR FROM portalcv_wlcaccounting WHERE acctStart < DATE_ADD(now(), INTERVAL -7 DAY);
        SELECT floor(CANTIDAD_A_BORRAR/LIMIT_BORRADO)+1 INTO VUELTAS;
       
        WHILE VUELTAS > 0 DO
            DELETE FROM portalcv_wlcaccounting WHERE acctStart < DATE_ADD(now(), INTERVAL -7 DAY) LIMIT 50000;
            SELECT SLEEP(2);
            SET VUELTAS = VUELTAS - 1;
        END WHILE;
END $$

SET GLOBAL event_scheduler=on $$
 
DELIMITER ;