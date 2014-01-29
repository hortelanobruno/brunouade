SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `portalcv` ;
CREATE SCHEMA IF NOT EXISTS `portalcv` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `portalcv` ;

-- -----------------------------------------------------
-- Table `portalcv`.`portalcv_registered_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `portalcv`.`portalcv_registered_users` ;

CREATE  TABLE IF NOT EXISTS `portalcv`.`portalcv_registered_users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `lastname` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `serviceprovider` VARCHAR(45) NOT NULL ,
  `activated` TINYINT(1) NOT NULL DEFAULT false ,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `date_validated` TIMESTAMP NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portalcv`.`portalcv_account_validation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `portalcv`.`portalcv_account_validation` ;

CREATE  TABLE IF NOT EXISTS `portalcv`.`portalcv_account_validation` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(45) NOT NULL ,
  `hashcode` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portalcv`.`portalcv_recover_password`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `portalcv`.`portalcv_recover_password` ;

CREATE  TABLE IF NOT EXISTS `portalcv`.`portalcv_recover_password` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(45) NOT NULL ,
  `hashcode` VARCHAR(255) NOT NULL ,
  `daterequested` TIMESTAMP NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portalcv`.`portalcv_quota_config`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `portalcv`.`portalcv_quota_config` ;

CREATE  TABLE IF NOT EXISTS `portalcv`.`portalcv_quota_config` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `defaulttime` INT NOT NULL ,
  `defaultvolume` INT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portalcv`.`portalcv_user_quota`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `portalcv`.`portalcv_user_quota` ;

CREATE  TABLE IF NOT EXISTS `portalcv`.`portalcv_user_quota` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(45) NOT NULL ,
  `time` INT NOT NULL ,
  `volume` INT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
