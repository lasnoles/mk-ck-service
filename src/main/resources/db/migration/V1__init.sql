CREATE TABLE IF NOT EXISTS broker (
    broker_code varchar(20) NOT NULL PRIMARY KEY,
    broker_name varchar(50),
    status varchar(10) not null,
    updated_by varchar(20) NOT NULL,
    approved_by varchar(20) NOT NULL,
    updated_on TIMESTAMP NOT NULL,
    approved_on TIMESTAMP NOT NULL
 )