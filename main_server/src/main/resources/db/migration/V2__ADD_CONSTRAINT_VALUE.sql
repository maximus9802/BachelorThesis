INSERT INTO `roles` (`type`, `name`)
VALUES ('USER', 'User'),
       ('MANAGER', 'Manager'),
       ('COMPANY', 'Company'),
       ('ADMIN_ADMIN', 'Root Admin');

INSERT INTO `authentication_type` (`type`, `name`)
VALUES ('LOGIN', 'Login'),
       ('LOGOUT', 'Logout');

INSERT INTO `status_parking` (`type`, `name`)
VALUES ('PARKING', 'Parking'),
       ('DONE', 'Done');