create table usuario_registrado (
    usuario_ID          SERIAL PRIMARY KEY,
    email               varchar(255) NOT NULL UNIQUE,
    nome                varchar(100) NOT NULL,
    sobrenome           varchar(100),
    senha_hash          TEXT NOT NULL
);

create table artista (
    usuario_ID          INT PRIMARY KEY REFERENCES usuario_registrado(usuario_ID) ON DELETE CASCADE,
    tipo_arte           varchar(32) NOT NULL,
    sub_genero          varchar(64),
    descricao           TEXT
);

CREATE TABLE organizador (
    usuario_ID          INT PRIMARY KEY REFERENCES usuario_registrado(usuario_ID) ON DELETE CASCADE
);

create table local (
    local_id            SERIAL PRIMARY KEY,
    nome                varchar(100) NOT NULL,
    endereco            varchar(255) NOT NULL,
    capacidade          int NOT NULL CHECK (capacidade > 0)
);

create table evento (
    evento_ID           SERIAL PRIMARY KEY,
    nome                VARCHAR(100) NOT NULL,   
    data_evento         DATE NOT NULL,
    status_evento       VARCHAR(20) NOT NULL DEFAULT 'ativo'
                        CHECK (status_evento IN ('ativo', 'cancelado', 'encerrado')),
    descricao           TEXT,
    local_id            INT REFERENCES local (local_id) ON DELETE CASCADE,
    organizador_id      INT REFERENCES organizador(usuario_ID) ON DELETE CASCADE
);


-- Inscrição de usuário no evento
CREATE TABLE inscricao (
    usuario_ID          INT REFERENCES usuario_registrado(usuario_ID) ON DELETE CASCADE,
    evento_ID           INT REFERENCES evento(evento_ID) ON DELETE CASCADE,
    PRIMARY KEY(usuario_ID, evento_ID )
);

-- Lista de seguidores usuario-artista
CREATE TABLE seguindo_artista (
    usuario_ID          INT REFERENCES usuario_registrado (usuario_ID) ON DELETE CASCADE,
    artista_ID          INT REFERENCES artista(usuario_ID) ON DELETE CASCADE,
    PRIMARY KEY(usuario_ID, artista_ID)
);

-- LIsta de seguidores usuario-local
CREATE TABLE seguindo_local (
    usuario_ID          INT REFERENCES usuario_registrado(usuario_ID) ON DELETE CASCADE,
    local_id            INT REFERENCES local(local_id) ON DELETE CASCADE,
    PRIMARY KEY (usuario_ID, local_id)
);

-- Tabela com artistas Y que participam de Eventos X
CREATE TABLE participa_evento (
    artista_ID          INT REFERENCES artista (usuario_ID) ON DELETE CASCADE,
    evento_ID           INT REFERENCES evento (evento_ID) ON DELETE CASCADE,
    PRIMARY KEY (artista_ID, evento_ID)
);