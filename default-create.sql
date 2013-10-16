create table tb_user (
  id                        integer auto_increment not null,
  username                  varchar(255),
  password                  varchar(255),
  realname                  varchar(255),
  constraint pk_tb_user primary key (id))
;



