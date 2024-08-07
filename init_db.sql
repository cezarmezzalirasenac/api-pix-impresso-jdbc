DROP table if exists contas;
DROP table if exists caixas;

ALTER TABLE if exists transacoes 
DROP CONSTRAINT transacoes_conta;

ALTER TABLE if exists transacoes 
drop CONSTRAINT transacoes_caixa;

DROP table if exists transacoes;

CREATE TABLE contas (
  ID SERIAL ,
  AGENCIA INT,
  NUMERO_CONTA INT,
  DIGITO_VERIFICADOR INT,
  NOME VARCHAR,
  CPF VARCHAR,
  TIPO_CONTA INT,
  NUMERO_CARTAO VARCHAR,
  SENHA VARCHAR,
  SALDO DOUBLE PRECISION,
  PRIMARY KEY (ID)
);

CREATE TABLE caixas (
  ID INT,
  LOCALIZACAO VARCHAR,
  SALDO DOUBLE PRECISION,
  PRIMARY KEY (ID)
);

alter table caixas alter column ID 
ADD GENERATED ALWAYS AS IDENTITY;

CREATE TABLE transacoes (
  ID INT GENERATED ALWAYS AS IDENTITY,
  CONTA_ID INT,
  CAIXA_ID INT,
  DATA_HORA TIMESTAMP,
  TIPO_TRANSACAO char,
  VALOR DOUBLE PRECISION,
  PRIMARY KEY (ID)
);

ALTER TABLE transacoes 
ADD CONSTRAINT transacoes_conta 
FOREIGN KEY (CONTA_ID) 
REFERENCES contas (ID);

ALTER TABLE transacoes 
ADD CONSTRAINT transacoes_caixa 
FOREIGN KEY (CAIXA_ID) 
REFERENCES caixas (ID);


ALTER TABLE public.transacoes 
ADD latitude float8 NULL;


ALTER TABLE public.transacoes 
ADD longitude float8 NULL;