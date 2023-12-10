CREATE DATABASE food_delivery default CHARACTER SET UTF8;
USE food_delivery;

CREATE USER 'test'@'%' IDENTIFIED BY 'test123';
GRANT ALL PRIVILEGES ON *.* TO 'test'@'%';
FLUSH PRIVILEGES;

CREATE TABLE `food_delivery`.`customers`
(
    `customer_id` BIGINT(8)    NOT NULL AUTO_INCREMENT COMMENT '고객 아이디',
    `name`        VARCHAR(45)  NOT NULL COMMENT '고객명',
    `email`       VARCHAR(45)  NOT NULL COMMENT '고객 이메일',
    `password`    VARCHAR(45)  NOT NULL COMMENT '비밀번호',
    `phone`       VARCHAR(45)  NOT NULL COMMENT '고객 폰번호',
    `address`     VARCHAR(255) NOT NULL COMMENT '고객 주소',
    `status`      VARCHAR(45)  NOT NULL COMMENT '고객 상태',
    `is_deleted`  BIT(1)       NOT NULL DEFAULT 0 COMMENT '소프트 삭제',
    `created_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `updated_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
    `created_by`  VARCHAR(45)  NOT NULL COMMENT '생성자',
    `updated_by`  VARCHAR(45)  NOT NULL COMMENT '수정자',
    PRIMARY KEY (`customer_id`),
    INDEX `idx_01_email` USING BTREE (`email`) VISIBLE,
    INDEX `idx_02_phone` (`phone` ASC) VISIBLE
);

CREATE TABLE `food_delivery`.`stores`
(
    `store_id`        BIGINT(8)    NOT NULL AUTO_INCREMENT COMMENT '상점 ID',
    `email`           BIGINT(8)    NOT NULL COMMENT '상점 이메일',
    `business_number` VARCHAR(45)  NOT NULL COMMENT '사업자 번호',
    `name`            VARCHAR(45)  NOT NULL COMMENT '상점 이름',
    `phone`           VARCHAR(45)  NOT NULL COMMENT '상점 대표 폰번호',
    `address`         VARCHAR(255) NOT NULL COMMENT '상점 주소',
    `password`        VARCHAR(45)  NOT NULL COMMENT '비밀번호',
    `bank_account`    VARCHAR(45)  NOT NULL COMMENT '은행 계좌',
    `bank_name`       VARCHAR(45)  NOT NULL COMMENT '은행명',
    `status`          VARCHAR(45)  NOT NULL COMMENT '상점상태',
    `is_deleted`      VARCHAR(45)  NOT NULL DEFAULT 0 COMMENT '소프트 삭제',
    `created_at`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `updated_at`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
    `created_by`      VARCHAR(45)  NOT NULL COMMENT '생성자',
    `updated_by`      VARCHAR(45)  NOT NULL COMMENT '수정자',
    PRIMARY KEY (`store_id`),
#     INDEX `idx_01_store_account_id` (`store_account_id` ASC) VISIBLE,
    INDEX `idx_01_store_email` (`email` ASC) VISIBLE,
    INDEX `idx_02_business_number` (`business_number` ASC) VISIBLE,
    INDEX `idx_03_phone` (`phone` ASC) VISIBLE,
    INDEX `idx_04_bank_account` (`bank_account` ASC) VISIBLE
);

CREATE TABLE `food_delivery`.`delivery_partners`
(
    `delivery_partner_id` BIGINT(8)    NOT NULL AUTO_INCREMENT COMMENT '배달파트너 ID',
    `email`               VARCHAR(55)  NOT NULL COMMENT '이메일',
    `password`            VARCHAR(55)  NOT NULL COMMENT '비밀번호',
    `transportation`      VARCHAR(45)  NOT NULL COMMENT '배달수단',
    `delivery_zone`       VARCHAR(255) NOT NULL COMMENT '배달지, 배달구역',
    `bank_code`           VARCHAR(45)  NULL COMMENT '은행 식별 코드',
    `bank_account`        VARCHAR(45)  NOT NULL COMMENT '은행계좌',
    `bank_name`           VARCHAR(45)  NOT NULL COMMENT '은행명',
    `status`              VARCHAR(45)  NOT NULL COMMENT '배달파트너 상태',
    `is_deleted`          VARCHAR(45)  NOT NULL DEFAULT 0 COMMENT '소프트삭제',
    `created_at`          DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `updated_at`          DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
    `created_by`          VARCHAR(45)  NOT NULL COMMENT '생성자',
    `updated_by`          VARCHAR(45)  NOT NULL COMMENT '수정자',
    PRIMARY KEY (`delivery_partner_id`),
    INDEX `idx_01_email` (`email` ASC) VISIBLE,
    INDEX `idx_02_bank_account` (`bank_account` ASC) VISIBLE
);

CREATE TABLE `food_delivery`.`menus`
(
    `menu_id`    BIGINT(8)      NOT NULL COMMENT '메뉴 ID',
    `store_id` BIGINT(8) NOT NULL COMMENT '상점 ID',
    `menu_name`  VARCHAR(45)    NOT NULL COMMENT '메뉴명',
    `price`      DECIMAL(10, 2) NOT NULL COMMENT '메뉴 가격',
    `status`     VARCHAR(45)    NOT NULL COMMENT '메뉴 상태',
    `is_deleted` BIT(1)         NOT NULL DEFAULT 0 COMMENT '소프트삭제',
    `created_at` DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `updated_at` DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
    `created_by` VARCHAR(45)    NOT NULL COMMENT '생성자',
    `updated_by` VARCHAR(45)    NOT NULL COMMENT '수정자',
    PRIMARY KEY (`menu_id`),
    INDEX `idx_01_store_id` (`store_id` ASC) VISIBLE
);


CREATE TABLE `food_delivery`.`categories`
(
    `category_id`   BIGINT(8)   NOT NULL AUTO_INCREMENT COMMENT '음식 카테고리 ID',
    `menu_id`    BIGINT(8)      NOT NULL COMMENT '메뉴 ID',
    `category_name` VARCHAR(45) NOT NULL COMMENT '카테고리 이름',
    `rank`          INT         NOT NULL COMMENT '카테고리 내에서 메뉴 순서',
    `is_deleted`    BIT(1)      NOT NULL DEFAULT 0 COMMENT '소프트 삭제',
    `created_at`    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `updated_at`    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
    `created_by`    VARCHAR(45) NOT NULL COMMENT '생성자',
    `updated_by`    VARCHAR(45) NOT NULL COMMENT '수정자',
    PRIMARY KEY (`category_id`),
    INDEX `idx_01_menu_id` (`menu_id` ASC) VISIBLE
);


CREATE TABLE `orders`
(
    `order_id`           int            NOT NULL AUTO_INCREMENT COMMENT '주문 ID',
    `order_amount`       decimal(10, 0) NOT NULL COMMENT '주문 금액',
    `discount_amount`    decimal(10, 0) NOT NULL COMMENT '할인 금액',
    `delivery_fee`       decimal(10, 0) NOT NULL COMMENT '배달료',
    `total_order_amount` decimal(10, 0) NOT NULL COMMENT '총 주문 금액',
    `order_status`       varchar(45)    NOT NULL COMMENT '주문 상태',
    `is_deleted`         varchar(45)    NOT NULL DEFAULT '0' COMMENT '소프트 삭제',
    `created_at`         datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `updated_at`         datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
    `created_by`         varchar(45)    NOT NULL COMMENT '생성자',
    `updated_by`         varchar(45)    NOT NULL COMMENT '수정자',
    PRIMARY KEY (`order_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `order_items`
(
    `order_item_id` bigint         NOT NULL AUTO_INCREMENT COMMENT '주문 아이템 ID',
    `order_id`      bigint         NOT NULL COMMENT '주문 ID',
    `menu_id`       bigint         NOT NULL COMMENT '음식 메뉴 ID',
    `menu_price`    decimal(10, 0) NOT NULL COMMENT '음식 메뉴 가격',
    `is_deleted`    bit(1)         NOT NULL DEFAULT b'0' COMMENT '소프트 삭제',
    `created_at`    datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `updated_at`    datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
    `created_by`    varchar(45)    NOT NULL COMMENT '생성자',
    `updated_by`    varchar(45)    NOT NULL COMMENT '수정자',
    PRIMARY KEY (`order_item_id`),
    KEY `idx_01_order_id` (`order_id`),
    KEY `idx_02_menu_id` (`menu_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `store_settlements`
(
    `store_settlement_id` bigint         NOT NULL COMMENT '정산 ID',
    `store_id`            bigint         NOT NULL COMMENT '상점 ID',
    `order_id`            bigint         NOT NULL COMMENT '주문 ID',
    `order_amount`        decimal(10, 0) NOT NULL COMMENT '주문 금액',
    `discount_amount`     decimal(10, 0) NOT NULL COMMENT '할인 금액',
    `delivery_fee`        decimal(10, 0) NOT NULL COMMENT '배달료',
    `total_order_amount`  decimal(10, 0) NOT NULL COMMENT '총 주문 금액',
    `service_fee`         decimal(10, 0) NOT NULL COMMENT '서비스 수수료',
    `promotion_fee`       decimal(10, 0) NOT NULL COMMENT '프로모션 수수료',
    `settlement_amount`   decimal(10, 0) NOT NULL COMMENT '정산 금액',
    `is_deleted`          bit(1)         NOT NULL DEFAULT b'0' COMMENT '소프트 삭제',
    `created_at`          datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `updated_at`          datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
    `created_by`          varchar(45)    NOT NULL COMMENT '생성자',
    `updated_by`          varchar(45)    NOT NULL COMMENT '수정자',
    PRIMARY KEY (`store_settlement_id`),
    KEY `idx_01_store_id` (`store_id`),
    KEY `idx_02_order_id` (`order_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `store_wallets`
(
    `store_wallet_id` bigint         NOT NULL AUTO_INCREMENT COMMENT '상점 지갑 ID',
    `store_id`        bigint         NOT NULL COMMENT '상점 ID',
    `deposit`         decimal(10, 0) NOT NULL COMMENT '예금, 예치금',
    `is_deleted`      bit(1)         NOT NULL DEFAULT b'0' COMMENT '소프트 삭제',
    `created_at`      datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `updated_at`      datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
    `created_by`      varchar(45)    NOT NULL COMMENT '생성자',
    `updated_by`      varchar(45)    NOT NULL COMMENT '수정자',
    PRIMARY KEY (`store_wallet_id`),
    KEY `idx_01_store_id` (`store_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `delivery_partner_settlements`
(
    `delivery_partner_settlement_id` bigint         NOT NULL AUTO_INCREMENT COMMENT '배달파트너 정산ID',
    `delivery_partner_id`            bigint         NOT NULL COMMENT '배달파트너 ID',
    `order_id`                       bigint         NOT NULL COMMENT '주문 ID',
    `order_delivery_id`              bigint         NOT NULL COMMENT '주문 배달 ID',
    `delivery_fee`                   decimal(10, 0) NOT NULL COMMENT '배달료',
    `delivery_promotion_fee`         decimal(10, 0) NOT NULL COMMENT '배달 미션 프로모션 금액',
    `settlement_amount`              decimal(10, 0) NOT NULL COMMENT '정산 금액',
    `is_deleted`                     bit(1)         NOT NULL COMMENT '소프트 삭제',
    `created_at`                     datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `updated_at`                     datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
    `created_by`                     varchar(45)    NOT NULL COMMENT '생성자',
    `updated_by`                     varchar(45)    NOT NULL COMMENT '수정자',
    PRIMARY KEY (`delivery_partner_settlement_id`),
    KEY `idx_01_delivery_partner_id` (`delivery_partner_id`),
    KEY `idx_02_order_id` (`order_id`),
    KEY `idx_03_order_delivery_id` (`order_delivery_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `delivery_partner_wallets`
(
    `delivery_partner_wallet_id` bigint         NOT NULL AUTO_INCREMENT COMMENT '배달파트너 지갑 ID',
    `delivery_partner_id`        bigint         NOT NULL COMMENT '배달파트너 ID',
    `deposit`                    decimal(10, 0) NOT NULL COMMENT '예금, 예치금',
    `is_deleted`                 bit(1)         NOT NULL DEFAULT b'0' COMMENT '소프트 삭제',
    `created_at`                 datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `updated_at`                 datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
    `created_by`                 varchar(45)    NOT NULL COMMENT '생성자',
    `updated_by`                 varchar(45)    NOT NULL COMMENT '수정자',
    PRIMARY KEY (`delivery_partner_wallet_id`),
    KEY `idx_01_delivery_partner_id` (`delivery_partner_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

