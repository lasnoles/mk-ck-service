CREATE TABLE IF NOT EXISTS action_staging (
    action_id int primary key,
    broker_code varchar(20) NOT NULL,
    requestData varchar(65535),
    version int not null,
    action_type varchar(10) not null,
    action_on_entity varchar(50) not null, /*entity name*/
    status varchar(20) not null,
    created_by varchar(50) not null,
    created_on timestamp not null,
    updated_by varchar(20) NOT NULL,
    updated_on TIMESTAMP NOT NULL,
    locked_by varchar(20) NOT NULL,
    locked_on TIMESTAMP NOT NULL
 );

 /** one security_group has multiple user role (e.g. broker_maker, broker_checker) */
 create table if not exists user_role (
    id int primary key,
    security_group varchar(50) not null,
    action_on_entity varchar(50) not null,
    allowed_actions varchar(200),
    inquiry_status varchar(20)
 );

 insert into user_role
 values (1, 'BROKER_INQUIRY', 'BROKER', null, 'APPROVED');

 insert into user_role
 values (2, 'BROKER_MAKER', 'BROKER', 'CREATE, UPDATE, DELETE', null);

insert into user_role
 values (3, 'BROKER_CHECKER', 'BROKER', 'APPROVE', 'PENDING');