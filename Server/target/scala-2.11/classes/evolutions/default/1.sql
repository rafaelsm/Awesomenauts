# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table awesomenaut (
  id                        bigint not null,
  name                      varchar(255),
  backstory                 varchar(255),
  icon                      varchar(255),
  image                     varchar(255),
  unlocked_at_level         integer,
  release_date              timestamp,
  constraint pk_awesomenaut primary key (id))
;

create sequence awesomenaut_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists awesomenaut;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists awesomenaut_seq;

