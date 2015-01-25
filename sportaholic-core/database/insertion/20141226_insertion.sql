INSERT INTO sport (created_at, updated_at, name)
VALUES (current_timestamp, current_timestamp, 'Natação');

INSERT INTO article_type (created_at, updated_at, name)
VALUES (current_timestamp, current_timestamp, 'Equipamentos');

INSERT INTO uri (created_at, updated_at, uri, friendly_uri, name, parent_id)
VALUES (current_timestamp, current_timestamp, '/', '/', 'Home', NULL),
(current_timestamp, current_timestamp, '/clients/new', '/novo-cadastro', 'Novo cadastro', 1),
(current_timestamp, current_timestamp, '/profile', '/perfil', 'Meu perfil', 1),
(current_timestamp, current_timestamp, '/clients/edit-password', '/alterar-senha', 'Alterar senha', 1),
(current_timestamp, current_timestamp, '/authors', '/autores', 'Nossos autores', 1);

INSERT INTO uri (created_at, updated_at, uri, friendly_uri, name, parent_id)
VALUES (current_timestamp, current_timestamp, '/clients/profile', '/meu-perfil', 'Meu perfil', 1),
(current_timestamp, current_timestamp, '/login', '/login', 'Login', 1);

INSERT INTO uri (created_at, updated_at, uri, friendly_uri, name, parent_id)
VALUES (current_timestamp, current_timestamp, '/articles', '/artigos', 'Artigos', 1);