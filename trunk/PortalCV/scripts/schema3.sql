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
  `timestamp` DATETIME NULL ,
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
-- Table `isg`.`portalcv_homes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_homes` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_homes` (
  `idhomes` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(200) NOT NULL ,
  PRIMARY KEY (`idhomes`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_apgroups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_apgroups` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_apgroups` (
  `idapgroups` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(200) NULL ,
  `idhomes` INT NOT NULL ,
  PRIMARY KEY (`idapgroups`) ,
  INDEX `fk_portalcv_apgroups_portalcv_homes1_idx` (`idhomes` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_aps`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_aps` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_aps` (
  `idAP` INT NOT NULL AUTO_INCREMENT ,
  `MAC` VARCHAR(45) NULL ,
  `Name` VARCHAR(200) NULL ,
  `Descripcion` VARCHAR(200) NULL ,
  `Latitud` VARCHAR(200) NULL ,
  `Longitud` VARCHAR(200) NULL ,
  `Direccion` VARCHAR(200) NULL ,
  `SNMPAPIndex` VARCHAR(45) NULL ,
  `idapgroups` INT NOT NULL ,
  PRIMARY KEY (`idAP`) ,
  INDEX `fk_portalcv_aps_portalcv_apgroups1_idx` (`idapgroups` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_wlcaccounting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_wlcaccounting` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_wlcaccounting` (
  `idwlcAccounting` INT NOT NULL AUTO_INCREMENT ,
  `acctStart` DATETIME NOT NULL ,
  `acctStop` DATETIME NULL ,
  `IPCPE` VARCHAR(45) NOT NULL ,
  `MACCPE` VARCHAR(45) NOT NULL ,
  `idAP` INT NOT NULL ,
  PRIMARY KEY (`idwlcAccounting`) ,
  INDEX `fk_wlcAccounting_AP1_idx` (`idAP` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_useragents`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_useragents` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_useragents` (
  `idUserAgent` INT NOT NULL AUTO_INCREMENT ,
  `DEVICE` VARCHAR(500) NULL ,
  `OS` VARCHAR(500) NULL ,
  `BROWSER` VARCHAR(500) NULL ,
  `OS_VERSION` VARCHAR(500) NULL ,
  `BROWSER_VERSION` VARCHAR(500) NULL ,
  `fullUserAgent` VARCHAR(500) NULL ,
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
  PRIMARY KEY (`idisgaccounting`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_clicks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_clicks` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_clicks` (
  `idclicks` INT NOT NULL AUTO_INCREMENT ,
  `IP` VARCHAR(45) NULL ,
  `mainCount` INT(11) NULL ,
  `mainTime` DATETIME NULL ,
  `stdButtonCount` INT(11) NULL ,
  `stdButtonTime` DATETIME NULL ,
  `premiumButtonCount` INT(11) NULL ,
  `premiumButtonTime` DATETIME NULL ,
  `sucursalVirtualReturnTime` DATETIME NULL ,
  `coaLoginDelay` BIGINT NULL ,
  `coaLoginTime` DATETIME NULL ,
  `loggedInTime` DATETIME NULL ,
  `coaLogoutDelay` BIGINT NULL ,
  `coaLogoutTime` DATETIME NULL ,
  `loggedOutTime` DATETIME NULL ,
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
  INDEX `fk_portalcv_logins_portalcv_aps1_idx` (`aps_idAP` ASC) )
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
-- Table `isg`.`portalcv_homeresources`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_homeresources` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_homeresources` (
  `idhomeresources` INT NOT NULL AUTO_INCREMENT ,
  `idhomes` INT NOT NULL ,
  `description` VARCHAR(200) NULL ,
  `url` VARCHAR(200) NULL ,
  `filePath` VARCHAR(200) NULL ,
  `type` VARCHAR(45) NULL ,
  PRIMARY KEY (`idhomeresources`) ,
  INDEX `fk_portalcv_homeresources_portalcv_homes1_idx` (`idhomes` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_fibertelusers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_fibertelusers` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_fibertelusers` (
  `idfibertelusers` INT NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(200) NOT NULL ,
  PRIMARY KEY (`idfibertelusers`) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_facebookdata`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_facebookdata` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_facebookdata` (
  `idfacebookdata` INT NOT NULL AUTO_INCREMENT ,
  `id` BIGINT NULL ,
  `name` VARCHAR(200) NULL ,
  `gender` VARCHAR(45) NULL ,
  `link` VARCHAR(200) NULL ,
  `age_range` VARCHAR(45) NULL ,
  `birthday` VARCHAR(45) NULL ,
  `email` VARCHAR(45) NULL ,
  `hometown` VARCHAR(200) NULL ,
  `location` VARCHAR(200) NULL ,
  `first_name` VARCHAR(200) NULL ,
  `last_name` VARCHAR(200) NULL ,
  `picture` VARCHAR(500) NULL ,
  `address` VARCHAR(200) NULL ,
  PRIMARY KEY (`idfacebookdata`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_googledata`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_googledata` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_googledata` (
  `idgoogledata` INT NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(200) NULL ,
  `name` VARCHAR(200) NULL ,
  `given_name` VARCHAR(200) NULL ,
  `family_name` VARCHAR(200) NULL ,
  `link` VARCHAR(200) NULL ,
  `picture` VARCHAR(200) NULL ,
  `gender` VARCHAR(45) NULL ,
  `birthday` VARCHAR(45) NULL ,
  `id` BIGINT NULL ,
  PRIMARY KEY (`idgoogledata`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isg`.`portalcv_twitterdata`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `isg`.`portalcv_twitterdata` ;

CREATE  TABLE IF NOT EXISTS `isg`.`portalcv_twitterdata` (
  `idtwitterdata` INT NOT NULL AUTO_INCREMENT ,
  `id` BIGINT NULL ,
  `name` VARCHAR(200) NULL ,
  `screenName` VARCHAR(200) NULL ,
  `location` VARCHAR(200) NULL ,
  `profileImageUrl` VARCHAR(200) NULL ,
  `url` VARCHAR(200) NULL ,
  `followersCount` INT NULL ,
  PRIMARY KEY (`idtwitterdata`) )
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

SELECT idwlcAccounting INTO _idwclaccounting FROM portalcv_wlcaccounting WHERE IPCPE = framed_ip_address and acctStop is null order by idwlcAccounting desc limit 1;

INSERT INTO portalcv_isgaccounting (RadAcctId,AcctSessionId, AcctUniqueId, Username, Realm, acctStart, acctStop, 
AcctSessiontime, AcctInputOctets, AcctOutputOctets, AcctterminateCause, FramedIPAddress, AcctStartDelay, 
AcctStopDelay, ciscoAVPair, ciscoServiceInfo, ciscoControlInfo, wlcAccounting_idwlcAccounting)
 VALUES (0,acct_session_Id,acct_unique_session_id,user_name,rrealm,start_time,NULL,'0','0','0','',
 framed_ip_address,acct_delay_time,'0',cisco_avpair,cisco_service_info,cisco_control_info,_idwclaccounting);


end$$

DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `isg`.`portalcv_homes`
-- -----------------------------------------------------
START TRANSACTION;
USE `isg`;
INSERT INTO `isg`.`portalcv_homes` (`idhomes`, `description`) VALUES (1, 'Portal Default');
INSERT INTO `isg`.`portalcv_homes` (`idhomes`, `description`) VALUES (2, 'Portal G+ y Twitter');
INSERT INTO `isg`.`portalcv_homes` (`idhomes`, `description`) VALUES (3, 'Portal Nuevo');
INSERT INTO `isg`.`portalcv_homes` (`idhomes`, `description`) VALUES (4, 'Portal V2');

COMMIT;

-- -----------------------------------------------------
-- Data for table `isg`.`portalcv_apgroups`
-- -----------------------------------------------------
START TRANSACTION;
USE `isg`;
INSERT INTO `isg`.`portalcv_apgroups` (`idapgroups`, `description`, `idhomes`) VALUES (1, 'APGroup Default', 1);
INSERT INTO `isg`.`portalcv_apgroups` (`idapgroups`, `description`, `idhomes`) VALUES (2, 'APGroup 2', 2);
INSERT INTO `isg`.`portalcv_apgroups` (`idapgroups`, `description`, `idhomes`) VALUES (3, 'APGroup 3', 3);
INSERT INTO `isg`.`portalcv_apgroups` (`idapgroups`, `description`, `idhomes`) VALUES (4, 'APGroup Portalv2.0', 4);

COMMIT;

-- -----------------------------------------------------
-- Data for table `isg`.`portalcv_wlcs`
-- -----------------------------------------------------
START TRANSACTION;
USE `isg`;
INSERT INTO `isg`.`portalcv_wlcs` (`idwlcs`, `name`, `ip`, `snmpPort`, `snmpComunity`, `secret`) VALUES (1, 'WLC1.HOR1', '10.246.128.2', '161', 'F1b3rt3lZ0n3', 'F1b3rt3lz0n3');
INSERT INTO `isg`.`portalcv_wlcs` (`idwlcs`, `name`, `ip`, `snmpPort`, `snmpComunity`, `secret`) VALUES (2, 'WLC2.HOR1', '10.246.128.3', '161', 'F1b3rt3lZ0n3', 'F1b3rt3lz0n3');

COMMIT;

-- -----------------------------------------------------
-- Data for table `isg`.`portalcv_isgs`
-- -----------------------------------------------------
START TRANSACTION;
USE `isg`;
INSERT INTO `isg`.`portalcv_isgs` (`idportalcv_isgs`, `name`, `ip`, `secret`) VALUES (1, 'ISG1', '10.0.0.1', 'callis');

COMMIT;

-- -----------------------------------------------------
-- Data for table `isg`.`portalcv_homeresources`
-- -----------------------------------------------------
START TRANSACTION;
USE `isg`;
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (1, 1, 'Home', '/home', '/WEB-INF/view/default/home.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (2, 1, 'ConnectCV', '/connectCV', 'http://www.cablevisionfibertel.com.ar/handler/oauth.ashx?LoginId=1258&ClientId=', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (3, 1, 'ConnectNOCV', '/connectNOCV', '/WEB-INF/view/default/cliente_no_mail.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (4, 1, 'RegisterNOCV', '/registernocv', '/WEB-INF/view/default/cliente_no_mail_gracias.jsp', 'valid-internet');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (5, 1, 'RegisterNOCV', '/registernocv', '/confirmation', 'valid-no-internet');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (6, 1, 'RegisterNOCV', '/registernocv', '/WEB-INF/view/default/cliente_no_mail.jsp', 'invalid');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (7, 1, 'RegisterNOCV', '/registernocv', '/WEB-INF/view/default/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (8, 1, 'Confirmation', '/confirmation', '/loginNOCV', 'valid-internet');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (9, 1, 'Confirmation', '/confirmation', '/loginNOCV', 'valid-no-internet');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (10, 1, 'Confirmation', '/confirmation', '/WEB-INF/view/default/home.jsp', 'invalid');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (11, 1, 'Confirmation', '/confirmation', '/WEB-INF/view/default/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (12, 1, 'LoginCV', '/loginCV', '/loginCV2', 'valid');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (13, 1, 'LoginCV', '/loginCV', '/WEB-INF/view/default/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (14, 1, 'LoginCV2', '/loginCV2', '/WEB-INF/view/default/cliente_login_successful.jsp', 'valid');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (15, 1, 'LoginCV2', '/loginCV2', '/WEB-INF/view/default/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (16, 1, 'LoginNOCV', '/loginNOCV', '/WEB-INF/view/default/cliente_login_successful.jsp', 'valid');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (17, 1, 'LoginNOCV', '/loginNOCV', '/WEB-INF/view/default/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (18, 1, 'LoginNOCVFacebook', '/loginNOCVFacebook', 'https://www.facebook.com/dialog/oauth?client_id=APPID&redirect_uri=URL/callbackFacebook&scope=email&response_type=token', 'internet');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (19, 1, 'LoginNOCVFacebook', '/loginNOCVFacebook', '/login_success_facebook_nocv', 'no-internet');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (20, 1, 'LoginSuccessFacebook', '/login_success_facebook_nocv', '/loginNOCV', 'valid');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (21, 1, 'LoginSuccessFacebook', '/login_success_facebook_nocv', '/WEB-INF/view/default/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (22, 1, 'Condiciones', '/condiciones', '/WEB-INF/view/default/condiciones.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (23, 1, 'AutoLogin', '/autoLogin', '/WEB-INF/view/default/cliente_login_successful.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (24, 1, 'Logoff', '/logoff', '/WEB-INF/view/default/home.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (25, 1, 'AutoLoginNOCV', '/autoLoginNOCV', '/WEB-INF/view/default/homeNoClientAuth.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (26, 2, 'ConnectNOCV', '/connectNOCV', '/WEB-INF/view/portalDePrueba/cliente_no_mail.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (27, 2, 'LoginNOCVGoogle', '/loginNOCVGoogle', 'PAGINA DE GOOGLE DINAMICA', 'internet');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (28, 2, 'LoginNOCVGoLoginNOCVGoogleoble', '/loginNOCVGoogle', '/login_success_google_nocv', 'no-internet');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (29, 2, 'LoginNOCVTwitter', '/loginNOCVTwitter', 'PAGINA DE TWITTWER DINAMIC', 'internet');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (30, 2, 'LoginNOCVTwitter', '/loginNOCVTwitter', '/login_success_twitter_nocv', 'no-internet');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (31, 2, 'LoginSuccessGoogle', '/login_success_google_nocv', '/loginNOCV', 'valid');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (32, 2, 'LoginSuccessGoogle', '/login_success_google_nocv', '/WEB-INF/view/default/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (33, 2, 'Oauth2callback', '/oauth2callback', '/loginNOCV', 'valid');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (34, 2, 'Oauth2callback', '/oauth2callback', '/WEB-INF/view/default/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (35, 2, 'LoginSuccessTwitter', '/login_success_twitter_nocv', '/loginNOCV', 'valid');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (36, 2, 'LoginSuccessTwitter', '/login_success_twitter_nocv', '/WEB-INF/view/default/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (37, 1, 'AutoLogin', '/autoLogin', '/WEB-INF/view/default/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (38, 1, 'AutoLoginNOCV', '/autoLoginNOCV', '/WEB-INF/view/default/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (39, 1, 'ValidateUserAuth', '/validateUserAuth', '/WEB-INF/view/default/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (40, 3, 'Home', '/home', '/WEB-INF/view/portalNuevo/home.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (41, 1, 'TerminosP', '/terminosP', '/WEB-INF/view/default/terminosP.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (42, 1, 'TerminosS', '/terminosS', '/WEB-INF/view/default/terminosS.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (43, 1, 'FastLogin', '/fastLogin', '/WEB-INF/view/default/cliente_login_successful.jsp', 'valid');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (44, 1, 'FastLogin', '/fastLogin', '/WEB-INF/view/default/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (45, 1, 'Inicio', '/inicio', '/WEB-INF/view/default/index.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (46, 4, 'Inicio', '/inicio', '/WEB-INF/view/portalFZone2/index.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (47, 4, 'Home', '/home', '/WEB-INF/view/portalFZone2/home.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (48, 1, 'CallbackFacebook', '/callbackFacebook', '/WEB-INF/view/default/success_face.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (49, 1, 'Home', '/home', '/autoLoginNOCV', 'autoLoginNOCV');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (50, 1, 'Home', '/home', '/autoLogin', 'autoLogin');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (51, 4, 'Home', '/home', '/autoLogin', 'autoLogin');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (52, 4, 'Premium', '/premium', '/WEB-INF/view/portalFZone2/premium.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (53, 4, 'Premium', '/premium', '/WEB-INF/view/portalFZone2/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (54, 4, 'Standard', '/standard', '/WEB-INF/view/portalFZone2/standard.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (55, 4, 'Standard', '/standard', '/WEB-INF/view/portalFZone2/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (56, 4, 'LoginFacebook', '/loginFacebook', 'https://www.facebook.com/dialog/oauth?client_id=APPID&redirect_uri=URL/callbackFacebook&scope=email&response_type=token', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (57, 4, 'LoginGoogle', '/loginGoogle', 'PAGINA DE GOOGLE DINAMICA', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (58, 4, 'LoginVideo', '/loginVideo', '/WEB-INF/view/portalFZone2/video.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (59, 4, 'LoginFibertel', '/loginFibertel', '/WEB-INF/view/portalFZone2/login_fibertel.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (60, 4, 'Welcome', '/welcome', '/WEB-INF/view/portalFZone2/welcomePremium.jsp', 'premium');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (61, 4, 'Welcome', '/welcome', '/WEB-INF/view/portalFZone2/welcomePToStandard.jsp', 'premium-to-standard');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (62, 4, 'Welcome', '/welcome', '/WEB-INF/view/portalFZone2/welcomeSToPremium.jsp', 'standard-to-premium');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (63, 4, 'Welcome', '/welcome', '/WEB-INF/view/portalFZone2/welcome.jsp', 'standard');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (64, 4, 'LoginCV', '/loginCV', '/welcome', 'valid');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (65, 4, 'LoginCV', '/loginCV', '/WEB-INF/view/portalFZone2/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (66, 4, 'Welcome', '/welcome', '/WEB-INF/view/portalFZone2/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (67, 4, 'CallbackFacebook', '/callbackFacebook', '/WEB-INF/view/portalFZone2/success_face.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (68, 4, 'LoginSuccessFacebook', '/login_success_facebook_nocv', '/welcome', 'valid');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (69, 4, 'LoginSuccessFacebook', '/login_success_facebook_nocv', '/WEB-INF/view/portalFZone2/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (70, 4, 'LoginSuccessGoogle', '/login_success_google_nocv', '/welcome', 'valid');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (71, 4, 'LoginSuccessGoogle', '/login_success_google_nocv', '/WEB-INF/view/portalFZone2/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (72, 4, 'AutoLogin', '/autoLogin', '/welcome', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (73, 4, 'AutoLogin', '/autoLogin', '/WEB-INF/view/portalFZone2/home.jsp', 'other');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (74, 4, 'AutoLogin', '/autoLogin', '/loginVideo', 'video');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (75, 4, 'Logoff', '/logoff', '/WEB-INF/view/portalFZone2/home.jsp', 'default');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (76, 4, 'ValidateFibertel', '/validateFibertel', '/welcome', 'valid');
INSERT INTO `isg`.`portalcv_homeresources` (`idhomeresources`, `idhomes`, `description`, `url`, `filePath`, `type`) VALUES (77, 4, 'ValidateFibertel', '/validateFibertel', '/WEB-INF/view/portalFZone2/login_fibertel.jsp', 'other');

COMMIT;
