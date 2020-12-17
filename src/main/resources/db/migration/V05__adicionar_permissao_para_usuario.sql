INSERT INTO permissao (codigo, descricao) values (10, 'ROLE_PESQUISAR_USUARIO');
INSERT INTO permissao (codigo, descricao) values (11, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO permissao (codigo, descricao) values (12, 'ROLE_REMOVER_USUARIO');

-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 10);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 11);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 12);