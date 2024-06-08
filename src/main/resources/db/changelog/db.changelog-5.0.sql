alter table users
    add column password varchar(64) default '{noop}123';

alter table users_aud
    add column password varchar(64);
