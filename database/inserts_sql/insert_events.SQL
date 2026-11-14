INSERT INTO local (nome, endereco, capacidade) VALUES
('Teatro Municipal', 'Rua XV de Novembro, 120', 500),
('Arena Cultural', 'Avenida Bento Gonçalves, 850', 2000),
('Espaço Alternativo', 'Rua Andrade Neves, 450', 150),
('Centro de Eventos', 'Avenida Fernando Osório, 1200', 1000),
('Casa da Música', 'Rua Gonçalves Chaves, 320', 200),
('Praça Central', 'Praça Coronel Pedro Osório', 800),
('Galeria das Artes', 'Rua Félix da Cunha, 210', 100),
('Clube Recreativo', 'Rua General Neto, 550', 600),
('Estação Cultural', 'Avenida Duque de Caxias, 700', 300),
('Auditório Universitário', 'Rua Gomes Carneiro, 1', 250);

INSERT INTO evento
(nome, data_evento, status_evento, descricao, local_id, organizador_id)
VALUES

-- LOCAL 1: Teatro Municipal
('Festival de Rock', '2026-11-15', 'ativo', 'Festival com bandas independentes de rock.', 1, 31),
('Concerto de MPB', '2026-12-03', 'ativo', 'Apresentações de música popular brasileira.', 1, 31),
('Noite de Teatro', '2026-12-26', 'ativo', 'Apresentações de peças teatrais.', 1, 31),
('Mostra de Cinema', '2027-01-18', 'ativo', 'Exibição de produções independentes.', 1, 32),
('Mostra de Teatro Independente', '2027-02-20', 'ativo', 'Apresentações de grupos independentes.', 1, 32),

-- LOCAL 2: Arena Cultural
('Festival de Música Eletrônica', '2026-11-22', 'ativo', 'Evento dedicado à música eletrônica.', 2, 31),
('Festival de Samba', '2026-12-10', 'ativo', 'Apresentações de grupos de samba.', 2, 31),
('Festival de Música Independente', '2026-12-20', 'ativo', 'Apresentação de artistas independentes.', 2, 31),
('Concerto de Rock Alternativo', '2027-01-10', 'ativo', 'Apresentação de bandas alternativas.', 2, 32),
('Festival de Música Brasileira', '2027-02-02', 'ativo', 'Apresentações de música brasileira.', 2, 32),

-- LOCAL 3: Espaço Alternativo
('Sarau de Poesia', '2026-11-25', 'ativo', 'Encontro de escritores e poetas.', 3, 33),
('Encontro de Ilustradores', '2026-12-15', 'ativo', 'Encontro de artistas ilustradores.', 3, 33),
('Sarau Cultural', '2027-02-08', 'ativo', 'Apresentações de poesia e música.', 3, 33),
('Workshop de Fotografia', '2026-11-28', 'ativo', 'Oficina de fotografia urbana.', 3, 33),

-- LOCAL 4: Centro de Eventos
('Festival de Dança', '2026-12-01', 'ativo', 'Apresentações de diferentes estilos de dança.', 4, 33),
('Festival de Hip Hop', '2027-01-05', 'ativo', 'Evento de dança, música e cultura urbana.', 4, 33),
('Encontro de Dança Contemporânea', '2027-01-20', 'ativo', 'Apresentações de dança contemporânea.', 4, 33),
('Festival de Arte Urbana', '2027-02-11', 'ativo', 'Evento dedicado à arte urbana.', 4, 33),

-- LOCAL 5: Casa da Música
('Noite do Jazz', '2026-11-18', 'ativo', 'Apresentações de jazz instrumental.', 5, 33),
('Encontro de Escritores', '2026-12-28', 'ativo', 'Debates sobre literatura e escrita.', 5, 33),
('Festival de Jazz', '2027-01-15', 'ativo', 'Festival com músicos de jazz.', 5, 33),
('Concerto Instrumental', '2027-02-14', 'ativo', 'Apresentações de música instrumental.', 5, 34),

-- LOCAL 6: Praça Central
('Feira de Artesanato', '2026-12-05', 'ativo', 'Exposição e comercialização de artesanato.', 6, 34),
('Cinema ao Ar Livre', '2026-12-12', 'ativo', 'Exibição de filmes ao ar livre.', 6, 34),
('Feira Cultural de Verão', '2027-01-08', 'ativo', 'Feira com atrações culturais diversas.', 6, 34),
('Festival Gastronômico', '2027-01-23', 'ativo', 'Evento com culinária regional.', 6, 34),

-- LOCAL 7: Galeria das Artes
('Exposição de Arte Moderna', '2026-11-20', 'ativo', 'Exposição de artistas contemporâneos.', 7, 34),
('Mostra de Esculturas', '2026-12-08', 'ativo', 'Exposição de esculturas contemporâneas.', 7, 34),
('Workshop de Pintura', '2026-12-18', 'ativo', 'Oficina prática de pintura artística.', 7, 34),
('Exposição de Arte Abstrata', '2027-01-28', 'ativo', 'Obras de artistas abstratos.', 7, 34),

-- LOCAL 8: Clube Recreativo
('Feira de Livros', '2027-01-25', 'ativo', 'Exposição e venda de livros.', 8, 34),
('Feira de Arte Independente', '2027-02-17', 'ativo', 'Exposição de trabalhos autorais.', 8, 34),
('Encontro de Compositores', '2027-03-05', 'ativo', 'Encontro de compositores independentes.', 8, 34),

-- LOCAL 9: Estação Cultural
('Exposição Fotográfica', '2026-12-22', 'ativo', 'Exposição de fotografias autorais.', 9, 35),
('Encontro de Fotógrafos', '2027-02-05', 'ativo', 'Compartilhamento de experiências fotográficas.', 9, 35),
('Exposição de Fotografia Urbana', '2027-03-01', 'ativo', 'Fotografias de paisagens urbanas.', 9, 35),

-- LOCAL 10: Auditório Universitário
('Festival Cultural de Outono', '2027-03-10', 'ativo', 'Festival com diversas manifestações artísticas.', 10, 35),
('Workshop de Escultura', '2027-01-12', 'ativo', 'Oficina de técnicas de escultura.', 10, 35),
('Workshop de Dança', '2027-02-26', 'ativo', 'Oficina de dança para iniciantes.', 10, 35),
('Feira de Arte Independente 2', '2027-03-15', 'ativo', 'Exposição de trabalhos autorais.', 10, 35),
('Festival de Música de Câmara', '2027-03-20', 'ativo', 'Apresentações de música de câmara.', 10, 35);