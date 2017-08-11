SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `java2` DEFAULT CHARACTER SET utf8 ;
USE `java2` ;

-- -----------------------------------------------------
-- Table `java2`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `products` ;

CREATE TABLE IF NOT EXISTS `products` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(32) NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;


ALTER TABLE `products` ADD `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP();
ALTER TABLE `products` ADD `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP();


-- -----------------------------------------------------
-- Table `java2`.`app_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(32) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;


-- -----------------------------------------------------
-- Table `java2`.`shopping_lists`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopping_lists` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(32) NOT NULL,
  `user_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

ALTER TABLE `shopping_lists`
ADD FOREIGN KEY (`user_id`) REFERENCES `app_users`(`id`);

CREATE INDEX `idx_shopping_lists_user_id`
ON `shopping_lists`(`user_id`);

-- -----------------------------------------------------
-- Table `java2`.`shopping_list_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopping_list_items` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `shopping_list_id` BIGINT(20) NOT NULL,
  `product_id` BIGINT(20) NOT NULL,
  `quantity` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

ALTER TABLE `shopping_list_items`
ADD FOREIGN KEY (`shopping_list_id`) REFERENCES `shopping_lists`(`id`);

CREATE INDEX `idx_shopping_list_items_shopping_list_id`
ON `shopping_list_items`(`shopping_list_id`);

ALTER TABLE `shopping_list_items`
ADD FOREIGN KEY (`product_id`) REFERENCES `products`(`id`);

CREATE INDEX `idx_shopping_list_items_product_id`
ON `shopping_list_items`(`product_id`);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;