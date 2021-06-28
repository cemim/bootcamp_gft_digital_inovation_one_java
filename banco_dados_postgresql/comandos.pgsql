-- Criar Banco de Dados e Tabelas
CREATE DATABASE financas;

CREATE TABLE IF NOT EXISTS banco (
	numero INTEGER NOT NULL,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL DEFAULT TRUE,
	data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (numero)
);

CREATE TABLE IF NOT EXISTS agencia (
	banco_numero INTEGER NOT NULL,
	numero INTEGER NOT NULL,
	nome VARCHAR(80) NOT NULL,
	ativo BOOLEAN NOT NULL DEFAULT TRUE,
	data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (banco_numero, numero),
	FOREIGN KEY (banco_numero) REFERENCES banco (numero)
);

CREATE TABLE IF NOT EXISTS cliente(
	numero BIGSERIAL PRIMARY KEY,
	nome VARCHAR(120) NOT NULL,
	email VARCHAR(250) NOT NULL,
	ativo BOOLEAN NOT NULL DEFAULT TRUE,
	data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS conta_corrente(
	banco_numero INTEGER NOT NULL,
	agencia_numero INTEGER NOT NULL,
	numero BIGINT NOT NULL,	
	digito SMALLINT NOT NULL,	
	cliente_numero BIGINT NOT NULL,
	ativo BOOLEAN NOT NULL DEFAULT TRUE,
	data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (banco_numero, agencia_numero, numero, digito, cliente_numero),
	FOREIGN KEY (banco_numero, agencia_numero) REFERENCES agencia (banco_numero, numero),
	FOREIGN KEY (cliente_numero) REFERENCES cliente (numero)
);

CREATE TABLE IF NOT EXISTS tipo_transacao(
	id SMALLSERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL DEFAULT TRUE,
	data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP	
);

CREATE TABLE IF NOT EXISTS cliente_transacoes(
	id BIGSERIAL PRIMARY KEY,
	banco_numero INTEGER NOT NULL,
	agencia_numero INTEGER NOT NULL,
	conta_corrente_numero BIGINT NOT NULL,
	conta_corrente_digito SMALLINT NOT NULL,
	cliente_numero BIGINT NOT NULL,
	tipo_transacao_id SMALLINT NOT NULL,
	valor NUMERIC(15,2) NOT NULL,	
	data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (banco_numero, agencia_numero, conta_corrente_numero, conta_corrente_digito, cliente_numero) 
	REFERENCES conta_corrente (banco_numero, agencia_numero, numero, digito, cliente_numero)
);

-- Importar o arquivo scripts_professor/dml.sql para popular as tabelas

-- SELECTS BÁSICOS
select numero, nome from banco;
select banco_numero, numero, nome from agencia;
select numero, nome, email from cliente;
select banco_numero, agencia_numero, cliente_numero from cliente_transacoes;

-- Ver todos as colunas de uma tabela
select * from information_schema.columns where table_name = 'banco';
select column_name, data_type from information_schema.columns where table_name = 'banco';

-- Pegar média dos valores
select avg(valor) from cliente_transacoes;

-- Contar
select count(numero) from cliente;

-- Contar os emails agrupados
select count(numero), email from cliente where email like '%gmail.com' group by email;

-- Selecionar o maior número para cada tipo de transação
select max(valor), tipo_transacao_id from cliente_transacoes group by tipo_transacao_id;

-- Selecionar o menor número para cada tipo de transação
select min(valor), tipo_transacao_id from cliente_transacoes group by tipo_transacao_id;

-- Ignora a contagem dos ids menor ou igual a 150
select count(id), tipo_transacao_id from cliente_transacoes group by tipo_transacao_id having count(id) > 150;

-- Somar valores
select sum(valor) from cliente_transacoes;
select sum(valor), tipo_transacao_id from cliente_transacoes group by tipo_transacao_id order by tipo_transacao_id desc;

-- Trabalhando com Join
select banco.numero, banco.nome, agencia.numero, agencia.nome
from  banco
join  agencia on agencia.banco_numero = banco.numero;

select count(distinct banco.numero)
from  banco
join  agencia on agencia.banco_numero = banco.numero;

select banco.numero, banco.nome, agencia.numero, agencia.nome
from  banco
left join  agencia on agencia.banco_numero = banco.numero;

select agencia.numero, agencia.nome, banco.numero, banco.nome
from  agencia
RIGHT join  banco on banco.numero = agencia.banco_numero;

-- Traz todos os dados de ambos os lados
select banco.numero, banco.nome, agencia.numero, agencia.nome
from  banco
full join  agencia on agencia.banco_numero = banco.numero;

-- Traz todos os dados de ambos os lados sem precisar especificar o on, cruzando todas os dados iguais das tabelas
select banco.numero, banco.nome, agencia.numero, agencia.nome
from  banco
cross join  agencia;

-- Common Table Expression (CTE)
-- Cria uma tabela temporária, semelhante aos sub selects, mas de maneira mais organizada
with tb_temporaria_banco as (
	select numero, nome from banco
)
select numero, nome from tb_temporaria_banco

with params as (
	select 213 as banco_numero
), tbl_tmp_banco as ( select numero, nome from banco join params on params.banco_numero = banco.numero)
select numero, nome from tbl_tmp_banco


-- VIEWS
create or replace view vw_bancos as (
	select numero, nome, ativo
	from banco
);
select numero, nome, ativo from vw_bancos;

create or replace view vw_bancos2 (banco_numero, banco_nome, banco_ativo) as (
	select numero, nome, ativo
	from banco
);
select banco_numero, banco_nome, banco_ativo from vw_bancos2;

-- Nas views só é permitido insert, update ou delete quando não houver join
-- Ao inserir um registro usando uma view esse registro é inserido na tabela em que a view faz referência
insert into vw_bancos2 (banco_numero, banco_nome, banco_ativo) values (51, 'Banco Boa Ideia', true);
update vw_bancos2 set banco_ativo = false where banco_numero = 51;
delete from vw_bancos2 where banco_numero = 51;;

-- Temporary cria uma view temporária que só pode ser consultada na própria janela ou terminal do SQL
create or replace temporary view vw_agencia as (
	select nome
	from agencia
);
select * from vw_agencia;

-- local check option faz a validação ao fazer um insert ou update para que o valor seja o mesmo do where, no exemplo faz a verificação se o campo esta ativo
create or replace view vw_bancos_ativos as (
	select numero, nome, ativo 
	from banco
	where ativo is true
) with local check option; 

-- Gera um erro por causa da validação acima
insert into vw_bancos_ativos (numero, nome, ativo) values (51, 'Banco Boa Ideia', false);

-- Ao trocar o local por cascade ele irá validar as regras de todas as views
create or replace view vw_bancos_com_a as (
	select numero, nome, ativo 
	from vw_bancos_ativos
	where nome ilike 'a%'
) with local check option; 
insert into vw_bancos_com_a (numero, nome, ativo) values (333, 'Alfa Omega', true);
select numero, nome, ativo from vw_bancos_com_a;


-- TRANSAÇÕES
-- Inicia uma transação e o commit efetiva a transação
-- Caso o desenvolvedor queira cancelar a transação utiliza o rollback
update banco set ativo = false where numero = 0;
begin;
update banco set ativo = true where numero = 0;
rollback;

select numero, nome, ativo from banco where banco.numero = 0;

update banco set ativo = false where numero = 0;
begin;
update banco set ativo = true where numero = 0;
commit;

select numero, nome, ativo from banco where banco.numero = 0;

-- savepoint salva as informações até um determinado ponto
select numero, nome, ativo from banco where numero = '654' or numero = '246' or numero = 0;
begin;
	update banco set ativo = false where numero = '654';
	savepoint sf_banco;
	update banco set ativo = false where numero = '246';
rollback to sf_banco;
	update banco set ativo = false where numero = '0';
commit;
select numero, nome, ativo from banco where numero = '654' or numero = '246' or numero = 0;


-- FUNÇÕES
create or replace function func_somar(integer, integer)
returns integer
security definer -- Peermite qualquer usuário executar
-- returns null on null input -- Usando esse parâmetro a função não será executada se um dos parametros for null e retornará null
called on null input -- Permite a função executar com parâtro null
language sql
as $$
	-- coalesce retorna o primeiro valor não nulo, 
	-- então caso o primeiro ou o segundo parametro sejam nulo retorna 0
	select coalesce($1,0) + coalesce($2,0);
$$;

select func_somar(1,null);

create or replace function bancos_add(p_numero integer, p_nome varchar, p_ativo boolean)
returns integer
security invoker -- Ao executar os comandos faz a verificação das permissões de usuário
called on null input 
language plpgsql
as $$
	declare variavel_id integer; -- Declaração variável
	begin
		-- Verifica se algum dos parâmetros for nulo
		if p_numero is null or p_nome is null or p_ativo is null then
			return -1;
		end if;
		
		-- Insere na variavel_id o número do banco informado no parâmetro, caso exista algum banco com esse id cadastrado
		select into variavel_id numero from banco where numero = p_numero;
		
		-- Verifica se a variavel_id é nula, se for nula, significa que não há banco com o numero informado no parâmetro
		if variavel_id is null then
			insert into banco(numero, nome, ativo) values(p_numero, p_nome, p_ativo);
			select into variavel_id numero from banco where numero = p_numero;
			return variavel_id;
		else
			return -2;
		end if;
	end;
$$;
select bancos_add(1, 'banco novo', null); -- Retorna -1, cod para valor null
select bancos_add(1, 'banco novo', false); -- Retorna -2, pois o número do banco já existe
select bancos_add(5433, 'banco novo', false); -- Cadastra novo banco e retorna o número cadastrado

select * from banco where numero = 5433;