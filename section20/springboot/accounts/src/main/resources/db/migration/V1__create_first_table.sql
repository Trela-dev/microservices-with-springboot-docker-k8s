CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) UNIQUE NOT NULL,
  `mobile_number` VARCHAR(20) UNIQUE NOT NULL,
  `created_at` TIMESTAMP(6) NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` TIMESTAMP(6) DEFAULT NULL,
  `updated_by` VARCHAR(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `accounts` (
  `account_number` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `customer_id` BIGINT NOT NULL,
  `account_type` VARCHAR(100) NOT NULL,
  `branch_address` VARCHAR(200) NOT NULL,
  `communication_sw` BOOLEAN DEFAULT FALSE,
  `created_at` TIMESTAMP(6) NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` TIMESTAMP(6) DEFAULT NULL,
  `updated_by` VARCHAR(20) DEFAULT NULL,
  FOREIGN KEY (`customer_id`) REFERENCES `customer`(`customer_id`) ON DELETE CASCADE
);
