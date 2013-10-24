create table tb_customer (
  id                        integer auto_increment not null,
  customer_code             varchar(255),
  status                    tinyint(1) default 0,
  company_name              varchar(255),
  company_address           varchar(255),
  company_post_code         varchar(255),
  company_phone_no          varchar(255),
  company_fax               varchar(255),
  company_email             varchar(255),
  name                      varchar(255),
  nric                      varchar(255),
  nric_pic                  varchar(255),
  phone1                    varchar(255),
  phone2                    varchar(255),
  phone3                    varchar(255),
  email                     varchar(255),
  customer_type             varchar(255),
  customer_payment          varchar(255),
  credit_limit              varchar(255),
  credit_terms              varchar(255),
  sales_rep                 varchar(255),
  driver_alocation          varchar(255),
  create_date               datetime,
  modified_date             datetime,
  constraint pk_tb_customer primary key (id))
;

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



