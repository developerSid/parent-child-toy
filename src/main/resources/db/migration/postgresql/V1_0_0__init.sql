create table child (id  bigserial not null, date_created timestamp, last_updated timestamp, age int4 not null, first_name varchar(255) not null, last_name varchar(255) not null, parent_id BIGINT not null, primary key (id));
create table grand_parent (id  bigserial not null, date_created timestamp, last_updated timestamp, age int4 not null, first_name varchar(255) not null, last_name varchar(255) not null, primary key (id));
create table parent (id  bigserial not null, date_created timestamp, last_updated timestamp, age int4 not null, first_name varchar(255) not null, last_name varchar(255) not null, grand_parent_id BIGINT not null, primary key (id));
create table toy (id  bigserial not null, date_created timestamp, last_updated timestamp, condition varchar(255) not null, manufacturer varchar(255) not null, name varchar(255) not null, child_id BIGINT not null, primary key (id));
alter table child add constraint FK7dag1cncltpyhoc2mbwka356h foreign key (parent_id) references parent;
alter table parent add constraint FKqrlvddrxn90fd33q3u9cc8xb1 foreign key (grand_parent_id) references grand_parent;
alter table toy add constraint FKmmbsbh9ju4s5j85207qomy9kb foreign key (child_id) references child;
