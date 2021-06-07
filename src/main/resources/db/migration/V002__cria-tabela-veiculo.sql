create table veiculo (
	id bigint not null auto_increment,
	usuario_id bigint not null,
	marca varchar(100) not null,
	modelo varchar(100) not null,
	ano bigint not null,
	valor varchar(100),

	primary key(id)
);

alter table veiculo add constraint fk_veiculo_usuario
foreign key (usuario_id) references veiculo (id);