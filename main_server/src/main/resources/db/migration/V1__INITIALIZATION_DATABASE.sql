CREATE TABLE IF NOT EXISTS `roles` (
    `id`                    BIGINT          NOT NULL AUTO_INCREMENT,
    `created_at`            TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `type`                  VARCHAR(20)     NOT NULL,
    `name`                  VARCHAR(20)     NOT NULL,

    PRIMARY KEY(`id`),

    INDEX (`type`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `identities` (
    `id`                    BIGINT          NOT NULL AUTO_INCREMENT,
    `created_at`            TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `login_id`              VARCHAR(50)     UNIQUE,
    `password`              VARCHAR(255),
    `is_deleted`            BOOLEAN         NOT NULL DEFAULT FALSE,

    PRIMARY KEY(`id`),

    INDEX (`login_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `identity_role` (
    `id`                    BIGINT          NOT NULL AUTO_INCREMENT,
    `created_at`            TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `identity_id`           BIGINT          NOT NULL,
    `location_id`           BIGINT          NOT NULL,

    PRIMARY KEY(`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `members` (
    `id`                    BIGINT          NOT NULL AUTO_INCREMENT,
    `created_at`            TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `identity_id`           BIGINT          NOT NULL,
    `last_name`             VARCHAR(255)    NOT NULL,
    `first_name`            VARCHAR(255)    NOT NULL,
    `date_of_birth`         DATE,
    `post_code`             VARCHAR(20),
    `address`               VARCHAR(512),
    `email`                 VARCHAR(255),
    `phone_number`          VARCHAR(20),
    `is_deleted`            BOOLEAN         NOT NULL DEFAULT FALSE,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `managers` (
    `id`                    BIGINT          NOT NULL AUTO_INCREMENT,
    `created_at`            TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `identity_id`           BIGINT          NOT NULL,
    `is_deleted`            BOOLEAN         NOT NULL DEFAULT FALSE,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `manager_location` (
     `id`                    BIGINT          NOT NULL AUTO_INCREMENT,
     `created_at`            TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
     `update_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `manager_id`            BIGINT          NOT NULL,
    `location_id`           BIGINT          NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `companies` (
    `id`                    BIGINT          NOT NULL AUTO_INCREMENT,
    `created_at`            TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `identity_id`           BIGINT          NOT NULL,
    `company_name`          VARCHAR(255)    NOT NULL,
    `address`               VARCHAR(255),
    `phone_number`          VARCHAR(20),
    `is_deleted`            BOOLEAN         NOT NULL DEFAULT FALSE,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `locations` (
    `id`                    BIGINT          NOT NULL AUTO_INCREMENT,
    `created_at`            TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `company_id`            BIGINT          NOT NULL,
    `name`                  VARCHAR(50)     NOT NULL,
    `address`               VARCHAR(50)     NOT NULL,
    `is_deleted`            BOOLEAN         NOT NULL DEFAULT FALSE,

    PRIMARY KEY (`id`),

    INDEX (`company_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `cameras` (
    `id`                    BIGINT          NOT NULL AUTO_INCREMENT,
    `created_at`            TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `location_id`           BIGINT          NOT NULL,
    `name`                  VARCHAR(20)     NOT NULL,
    `type_authen_id`        BIGINT          NOT NULL,
    `is_deleted`            BOOLEAN         NOT NULL DEFAULT FALSE,

    PRIMARY KEY (`id`),

    INDEX (`location_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `authentication_type` (
    `id`                    BIGINT          NOT NULL AUTO_INCREMENT,
    `created_at`            TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `type`                  VARCHAR(20)     NOT NULL,
    `name`                  VARCHAR(20)     NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `authentication_logs` (
    `id`                    BIGINT          NOT NULL AUTO_INCREMENT,
    `created_at`            TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `camera_id`             BIGINT          NOT NULL,
    `evidence_link`         VARCHAR(512),
    `time`                  TIME            NOT NULL,
    `date`                  DATE            NOT NULL,
    `is_deleted`            BOOLEAN         NOT NULL DEFAULT FALSE,

    PRIMARY KEY (`id`),

    INDEX (`camera_id`),
    INDEX (`date`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `authentication_history` (
    `id`                    BIGINT          NOT NULL AUTO_INCREMENT,
    `created_at`            TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `authen_login_id`       BIGINT          NOT NULL,
    `authen_logout_id`      BIGINT,
    `status_parking_id`     BIGINT          NOT NULL,
    `duration`              BIGINT,
    `license_plate_code`    VARCHAR(50)     NOT NULL,

    PRIMARY KEY (`id`),

    INDEX (`license_plate_code`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `status_parking` (
    `id`                    BIGINT          NOT NULL AUTO_INCREMENT,
    `created_at`            TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`             TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `type`                  VARCHAR(20)     NOT NULL,
    `name`                  VARCHAR(20)     NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;
