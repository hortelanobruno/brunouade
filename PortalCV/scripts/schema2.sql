SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `isg` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `isg` ;

-- -----------------------------------------------------
-- Table `isg`.`portalcv_stats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_stats` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_stats` (
  `idstats` INT NOT NULL AUTO_INCREMENT ,
  `timestamp` TIMESTAMP NULL ,
  `ap` INT(11) NULL ,
  `device` INT(11) NULL ,
  `userAgent` INT(11) NULL ,
  `connectedAP` INT(11) NULL ,
  `redirectedPortal` INT(11) NULL ,
  `clickPremium` INT(11) NULL ,
  `clickStandard` INT(11) NULL ,
  `loggedPremium` INT(11) NULL ,
  `loggedStandard` INT(11) NULL ,
  PRIMARY KEY (`idstats`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_aps`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_aps` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_aps` (
  `idAP` INT NOT NULL AUTO_INCREMENT ,
  `MAC` VARCHAR(45) NULL ,
  `Name` VARCHAR(45) NULL ,
  `Descripcion` VARCHAR(45) NULL ,
  `Latitud` VARCHAR(45) NULL ,
  `Longitud` VARCHAR(45) NULL ,
  `Direccion` VARCHAR(45) NULL ,
  `SNMPAPIndex` VARCHAR(45) NULL ,
  PRIMARY KEY (`idAP`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_wlcaccounting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_wlcaccounting` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_wlcaccounting` (
  `idwlcAccounting` INT NOT NULL AUTO_INCREMENT ,
  `acctStart` TIMESTAMP NOT NULL ,
  `acctStop` TIMESTAMP NULL ,
  `IPCPE` VARCHAR(45) NOT NULL ,
  `MACCPE` VARCHAR(45) NOT NULL ,
  `idAP` INT NOT NULL ,
  PRIMARY KEY (`idwlcAccounting`) ,
  INDEX `fk_wlcAccounting_AP1_idx` (`idAP` ASC) ,
  CONSTRAINT `fk_wlcAccounting_AP1`
    FOREIGN KEY (`idAP` )
    REFERENCES `isg`.`portalcv_aps` (`idAP` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_useragents`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_useragents` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_useragents` (
  `idUserAgent` INT NOT NULL AUTO_INCREMENT ,
  `DEVICE` VARCHAR(200) NULL ,
  `OS` VARCHAR(200) NULL ,
  `BROWSER` VARCHAR(200) NULL ,
  `OS_VERSION` VARCHAR(200) NULL ,
  `BROWSER_VERSION` VARCHAR(200) NULL ,
  PRIMARY KEY (`idUserAgent`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_isgaccounting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_isgaccounting` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_isgaccounting` (
  `idisgaccounting` INT NOT NULL AUTO_INCREMENT ,
  `RadAcctId` BIGINT(20) NULL ,
  `AcctSessionId` VARCHAR(32) NULL ,
  `AcctUniqueId` VARCHAR(32) NULL ,
  `Username` VARCHAR(64) NULL ,
  `Realm` VARCHAR(64) NULL ,
  `acctStart` DATETIME NULL ,
  `acctStop` DATETIME NULL ,
  `AcctSessiontime` INT(11) NULL ,
  `AcctInputOctets` BIGINT(20) NULL ,
  `AcctOutputOctets` BIGINT(20) NULL ,
  `AcctterminateCause` VARCHAR(32) NULL ,
  `FramedIPAddress` VARCHAR(15) NULL ,
  `AcctStartDelay` INT(11) NULL ,
  `AcctStopDelay` INT(11) NULL ,
  `ciscoAVPair` VARCHAR(200) NULL ,
  `ciscoServiceInfo` VARCHAR(60) NULL ,
  `ciscoControlInfo` VARCHAR(60) NULL ,
  `wlcAccounting_idwlcAccounting` INT NOT NULL ,
  INDEX `fk_ISGAccounting_wlcAccounting1_idx` (`wlcAccounting_idwlcAccounting` ASC) ,
  PRIMARY KEY (`idisgaccounting`) ,
  CONSTRAINT `fk_ISGAccounting_wlcAccounting1`
    FOREIGN KEY (`wlcAccounting_idwlcAccounting` )
    REFERENCES `isg`.`portalcv_wlcaccounting` (`idwlcAccounting` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_clicks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_clicks` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_clicks` (
  `idclicks` INT NOT NULL AUTO_INCREMENT ,
  `IP` VARCHAR(45) NULL ,
  `mainCount` INT(11) NULL ,
  `mainTime` TIMESTAMP NULL ,
  `stdButtonCount` INT(11) NULL ,
  `stdButtonTime` TIMESTAMP NULL ,
  `premiumButtonCount` INT(11) NULL ,
  `premiumButtonTime` TIMESTAMP NULL ,
  `sucursalVirtualReturnTime` TIMESTAMP NULL ,
  `coaLoginDelay` BIGINT NULL ,
  `coaLoginTime` TIMESTAMP NULL ,
  `loggedInTime` TIMESTAMP NULL ,
  `coaLogoutDelay` BIGINT NULL ,
  `coaLogoutTime` TIMESTAMP NULL ,
  `loggedOutTime` TIMESTAMP NULL ,
  PRIMARY KEY (`idclicks`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_wlcs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_wlcs` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_wlcs` (
  `idwlcs` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `ip` VARCHAR(45) NULL ,
  `snmpPort` VARCHAR(45) NULL ,
  `snmpComunity` VARCHAR(45) NULL ,
  `secret` VARCHAR(45) NULL ,
  PRIMARY KEY (`idwlcs`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_isgs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_isgs` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_isgs` (
  `idportalcv_isgs` INT NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `ip` VARCHAR(45) NULL ,
  `secret` VARCHAR(45) NULL ,
  PRIMARY KEY (`idportalcv_isgs`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_logins`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_logins` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_logins` (
  `idlogins` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NULL ,
  `ipAddress` VARCHAR(45) NULL ,
  `CPEMAC` VARCHAR(45) NULL ,
  `timestamp` DATETIME NULL ,
  `logintype` VARCHAR(45) NULL ,
  `useragents_idUserAgent` INT NULL ,
  `aps_idAP` INT NULL ,
  PRIMARY KEY (`idlogins`) ,
  INDEX `fk_portalcv_logins_portalcv_useragents1_idx` (`useragents_idUserAgent` ASC) ,
  INDEX `fk_portalcv_logins_portalcv_aps1_idx` (`aps_idAP` ASC) ,
  CONSTRAINT `fk_portalcv_logins_portalcv_useragents1`
    FOREIGN KEY (`useragents_idUserAgent` )
    REFERENCES `isg`.`portalcv_useragents` (`idUserAgent` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_portalcv_logins_portalcv_aps1`
    FOREIGN KEY (`aps_idAP` )
    REFERENCES `isg`.`portalcv_aps` (`idAP` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_homes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_homes` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_homes` (
  `idhomes` INT NOT NULL AUTO_INCREMENT ,
  `idAP` INT NOT NULL ,
  `home` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idhomes`) ,
  INDEX `fk_portalcv_homes_portalcv_aps1_idx` (`idAP` ASC) ,
  CONSTRAINT `fk_portalcv_homes_portalcv_aps1`
    FOREIGN KEY (`idAP` )
    REFERENCES `isg`.`portalcv_aps` (`idAP` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_autologin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_autologin` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_autologin` (
  `idautologin` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NULL ,
  `cpeMac` VARCHAR(45) NULL ,
  `logintype` VARCHAR(45) NULL ,
  `timestamp` DATETIME NULL ,
  PRIMARY KEY (`idautologin`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- procedure P_CLEAR_DATA
-- -----------------------------------------------------

USE `isg`;
DROP procedure IF EXISTS `isg`.`P_CLEAR_DATA`;

DELIMITER $$
USE `isg`$$


CREATE PROCEDURE P_CLEAR_DATA ()
BEGIN
    TRUNCATE TABLE portalcv_wlcaccounting;
    TRUNCATE TABLE portalcv_aps;
    TRUNCATE TABLE portalcv_clients;
    TRUNCATE TABLE portalcv_isgaccounting;
    TRUNCATE TABLE portalcv_devices;
    TRUNCATE TABLE portalcv_useragents;
END $$

DELIMITER ;

-- -----------------------------------------------------
-- procedure accounting_start_insert2
-- -----------------------------------------------------

USE `isg`;
DROP procedure IF EXISTS `isg`.`accounting_start_insert2`;

DELIMITER $$
USE `isg`$$




create procedure accounting_start_insert2 (
	acct_session_id varchar(32),
	acct_unique_session_id varchar(32), 
	user_name varchar(64), 
	rrealm varchar(64),
	nas_ip_address varchar(15),
	nas_port varchar(15), 
	nas_port_type varchar(32),
	start_time datetime,
	acct_authentic varchar(32),
	connect_info varchar(50),
	called_station_id varchar(50),
	callint_station_id varchar(50), 
	service_type varchar(32),
	framed_protocol varchar(32),
	framed_ip_address varchar(15), 
	acct_delay_time int(12),
	x_ascend_session_srv_key varchar(10), 
	cisco_avpair varchar(200),
	cisco_service_info varchar(60),
	cisco_control_info varchar(60))
begin
declare user_agent varchar(200) default NULL;
declare login_type varchar(45) default NULL;
declare deviceIN varchar(45) default NULL;
declare osIN varchar(45) default NULL;
declare os_versionIN varchar(45) default NULL;
declare browserIN varchar(45) default NULL;
declare browser_versionIN varchar(45) default NULL;

declare _idwclaccounting int;
declare _idclient int;

SELECT idwlcAccounting INTO _idwclaccounting FROM portalcv_wlcaccounting WHERE IPCPE = framed_ip_address order by idwlcAccounting desc limit 1;
SELECT idclient INTO _idclient FROM portalcv_clients WHERE subscriberId = user_name;

INSERT INTO portalcv_isgaccounting (AcctSessionId, AcctUniqueId, idclient, Realm, acctStart, acctStop, 
AcctSessiontime, AcctInputOctets, AcctOutputOctets, AcctterminateCause, FramedIPAddress, AcctStartDelay, 
AcctStopDelay, ciscoAVPair, ciscoServiceInfo, ciscoControlInfo, wlcAccounting_idwlcAccounting)
 VALUES (acct_session_Id,acct_unique_session_id,_idclient,rrealm,start_time,NULL,'0','0','0','',
 framed_ip_address,acct_delay_time,'0',cisco_avpair,cisco_service_info,cisco_control_info,_idwclaccounting);


end$$

DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `isg`.`portalcv_wlcs`
-- -----------------------------------------------------
START TRANSACTION;
USE `isg`;
INSERT INTO `isg`.`portalcv_wlcs` (`idwlcs`, `name`, `ip`, `snmpPort`, `snmpComunity`, `secret`) VALUES (1, 'WLCHornos', '10.246.128.2', '161', 'F1b3rt3lZ0n3', 'callis');

COMMIT;

-- -----------------------------------------------------
-- Data for table `isg`.`portalcv_isgs`
-- -----------------------------------------------------
START TRANSACTION;
USE `isg`;
INSERT INTO `isg`.`portalcv_isgs` (`idportalcv_isgs`, `name`, `ip`, `secret`) VALUES (1, 'ISG1', '10.0.0.1', 'callis');

COMMIT;
