INSERT INTO users (id, username, password, active)
    VALUES (1, 'admin', 'admin', true);

INSERT INTO users_roles (user_id, roles)
    VALUES (1, 'USER'), (1, 'ADMIN');

