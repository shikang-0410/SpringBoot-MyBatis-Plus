CREATE TABLE `test01`.`user`  (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(30) NULL,
	`age` INT NULL,
	`email` VARCHAR(50) NULL,
	`gender` INT NULL COMMENT '性别：1-男，2-女',
	`deleted` INT NULL,
	`wallet_id` INT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `test01`.`user_wallet`  (
  `wallet_id` INT NOT NULL,
  `wallet_amount` DECIMAL(10, 2) NULL,
	`user_id` BIGINT NOT NULL
  PRIMARY KEY (`wallet_id`)
);


CREATE TABLE `test01`.`user_trade`  (
  `trade_id` INT NOT NULL AUTO_INCREMENT,
  `trade_amount` DECIMAL(10, 2) NULL,
  `trade_type` INT NULL,
  `description` VARCHAR(255) NULL,
	`wallet_id` INT NOT NULL,
	`created` TIMESTAMP NOT NULL
  PRIMARY KEY (`trade_id`)
);
