
create table folder (
     id bigint not null auto_increment,
     name_folder varchar(25),
     folder_id bigint not null,
     primary key (id)) engine=InnoDB;
create table note (
     id bigint not null auto_increment,
     finish_note bit,
     note_name varchar(35),
     text_note varchar(355),
     folder_id bigint not null,
     user_id bigint not null,
     primary key (id)) engine=InnoDB;
create table users (
    id bigint not null auto_increment,
    password varchar(255),
    user_name varchar(45),
    primary key (id)) engine=InnoDB;
create table folder_name_inner (
    folder_id bigint not null,
    name_inner varchar(255)) engine=InnoDB;

alter table users add constraint UK_k8d0f2n7n88w1a16yhua64onx unique (user_name);

alter table folder add constraint FKhvx2hu58yb73fpiqeymywkvqo foreign key (folder_id) references users (id);

alter table folder_name_inner add constraint FKqgmyj2f1feqk10vhfrauviumd foreign key (folder_id) references folder (id);

alter table note add constraint FK2316sprb5ket7ap1l33v4y1g2 foreign key (folder_id) references folder (id);

alter table note add constraint FKaxew7axjawf2la92pc4yxcm87 foreign key (user_id) references users (id)