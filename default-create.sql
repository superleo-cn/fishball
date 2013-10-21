create table tb_user (
  id                        integer auto_increment not null,
  username                  varchar(255),
  password                  varchar(255),
  realname                  varchar(255),
  usertype                  varchar(255),
  status                    tinyint(1) default 0,
  user_ip                   varchar(255),
  create_date               datetime,
  modified_date             datetime,
  last_login_date           datetime,
  constraint pk_tb_user primary key (id))
;



