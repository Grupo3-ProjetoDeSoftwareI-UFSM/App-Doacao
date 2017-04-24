use appDoacao;
drop table if exists usuario;
create table usuario(
	uid int(11) primary key auto_increment,
	nome varchar(255) not null,
	tipo char(1) not null,
	cpfcnpj char(14) not null,
	email varchar(255) not null,
	upassword char(60) not null,
	cep char(8) not null,
	logradouro varchar(255) not null,
	numero varchar(255) not null,
	bairro varchar(255) not null,
	cidade varchar(255) not null,
	estado char(2) not null,
	hash char(32) not null,
	active int(1) not null default '0'
);