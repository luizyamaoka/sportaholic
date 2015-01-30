INSERT INTO article (created_at, updated_at, title, subtitle, content, author_id)
VALUES (current_timestamp, current_timestamp, 'Desafio 30 dias de alongamento', 'Precisando de um incentivo para fazer alguma atividade física? Conheça os benefícios do alongamento e encare este desafio!', 
'<p>Como já disse na minha <a href=''http://www.sportaholic.com.br/review-fitbit-flex'' target=''blank''>review sobre o Fitbit Flex</a>, um dos meus maiores problemas é encontrar motivação e incentivo para me exercitar. Por isso, vou propor a vocês um desafio para nos ajudarmos!</p><p>O alongamento é um exercício físico com foco principal na manutenção e/ou melhora da flexibilidade. Geralmente precede e/ou sucede o exercício físico, no entanto, ele também pode ser praticado sozinho e contém muitos benefícios. Dentre estes pontos positivos estão:</p><ul><li>Aumentar a flexibilidade</li><li>Previnir lesões</li><li>Ativar a circulação sanguínea</li><li>Relaxar o corpo</li></ul><p>Imaginem o quanto seu dia melhoraria se ele começasse com uma série de alongamentos. Você sairia de casa com uma disposição muito maior, a circulação ativa e além disso, fazendo um bem a sua <strong>saúde física e mental</strong>. Então este é o meu desafio para vocês (e para mim também).</p><p><strong>20 minutos de alongamento pelos próximos 30 dias!</strong></p><p>Acordar 20 minutos mais cedo não matará ninguém, certo? E vocês podem fazer os exercícios ainda de pijama.</p><p>Obviamente eu não quero que ninguém se machuque, então segue aqui um <a href=''http://www.youtube.com/watch?v=OPnAfyEL2VA&list=PL9sY-tiuUb-puCz3WJIIo-w0U_DnHKf07'' target=''blank''>vídeo do Canal Nova Pilates</a> com uma série de alongamentos para todo o corpo e também instruções de como fazê-los corretamente.</p><p>Em 30 dias postarei os meus resultados, as minhas experiências e impressões. Espero ouvir também as suas impressões nos comentários deste artigo!</p><p>Boa sorte!</p>',
2);

INSERT INTO article_is_sport (created_at, article_id, sport_id)
VALUES (current_timestamp, 6, 1);

INSERT INTO article_is_type (created_at, article_id, article_type_id)
VALUES (current_timestamp, 6, 5);

INSERT INTO uri (created_at, updated_at, uri, friendly_uri, name, parent_id, meta_description)
VALUES (current_timestamp, current_timestamp, '/articles/6', '/desafio-30-dias-de-alongamento', 'Desafio 30 dias de alongamento', 8, 'Precisando de um incentivo para fazer alguma atividade física? Conheça os benefícios do alongamento e encare este desafio!');

UPDATE article_type 
SET name = 'Reviews',
	updated_at = current_timestamp
WHERE id = 3;

UPDATE uri
SET friendly_uri = '/natacao/reviews',
	name = 'Reviews'
WHERE id = 16;
