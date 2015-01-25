INSERT INTO article (created_at, updated_at, title, subtitle, content, author_id)
VALUES (current_timestamp, current_timestamp, 'Não é só dedicação...', 'A importância da auto-imagem na formação de um novo atleta. Será mesmo que os únicos fatores que separam os vencedores dos outros são dedicação e esforço?',
'<p>Imagine o seguinte cenário hipotético: dois amigos igualmente talentosos e dedicados, mas que vivem em cidades diferentes, decidem que pegarão firme nos treinos este ano. A cidade do amigo A, porém, é atingida logo no primeiro mês por uma frente fria  que torna as visitas à piscina de seu clube bem mais dolorosas. Enquanto isso na cidade do amigo B, temperaturas máximas recordes são registradas, fazendo de suas idas ao treino quase que uma recreação.</p><p>Mesmo que o amigo A se esforce tanto quanto o B, suas chances de manter um ritmo forte de treino acabam de ser reduzidas dramaticamente.</p><p>Qual seria então, a postura de um atleta maduro frente a essas condições adversas ou não? Para alguém que se enxerga como um nadador maduro, faça chuva ou sol, treino é treino e não há negociação. No caso dos menos experientes, por outro lado, a ''''vontade'''' exigida é muito maior!</p><p>O consenso nos diz que para atingir  esta postura precisamos nos dedicar, treinar e adquirir experiência. Mas como chegar a este ponto quando tudo joga contra nós?</p><p>Acontece que essa mudança em sua auto-imagem como atleta pode ocorrer também de forma ativa, ou seja, antes mesmo de ter qualquer experiência podemos nos visualizar como grandes nadadores e agir de forma semelhante a esses.</p><p>Parece até mesmo papo de criança brincando de Super-Herói, não? Mas na hora do vamos ver prefiro ir com o que funciona! Que tal tentar uma mentalização em seus próximos obstáculos antes ou durante os treinos?</p><p>Bom Treino!</p><p>Ideias retiradas do artigo ''''Effort alone isn''t enough'''' por David Cain no Blog <a href=''http://www.raptitude.com/2014/06/effort-alone-isnt-enough/'' target=''blank''>raptitude.com</a></p>',
1);

INSERT INTO uri (created_at, updated_at, uri, friendly_uri, name, parent_id, meta_description)
VALUES (current_timestamp, current_timestamp, '/articles/3', '/nao-e-so-dedicacao', 'Não é só dedicação...' , 11, 'A importância da auto-imagem na formação de um novo atleta. Será mesmo que os únicos fatores que separam os vencedores dos outros são dedicação e esforço?');

INSERT INTO article_is_type (created_at, article_id, article_type_id)
VALUES (current_timestamp, 3, 2);

INSERT INTO article_is_sport (created_at, article_id, sport_id)
VALUES (current_timestamp, 3, 1);

INSERT INTO uri (created_at, updated_at, uri, friendly_uri, name, parent_id, meta_description)
VALUES (current_timestamp, current_timestamp, '/contact-us', '/contato', 'Contato', 1, 'Dúvida, sugestão, crítica? Entre em contato com a Sportaholic e responderemos o mais breve possível.')