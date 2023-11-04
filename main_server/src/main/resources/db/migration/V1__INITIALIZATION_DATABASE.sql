CREATE TABLE IF NOT EXISTS `role` (
    `id`            BIGINT          NOT NULL AUTO_INCREMENT,
    `created_at`    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`     TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `name`          VARCHAR(20),

    PRIMARY KEY(`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;