create database `venus` default character set utf8;

insert into sys_pool(creation, last_modified, version, demo, internal, level, name, removed) value
(now(),now(),0,demo,false,10,'Kevin科技',false);

insert into sys_user(creation, last_modified, version, account, active, audited, cellphone_verified, email_verified, name, password, removed, salt, sys_pool_id) value
(now(), now(), 0, '123', true, false, false, false, 'Kevin', '061e18f7bf98ee2eadda385a6f0bc739', false, 'y5o8rjzwaszf68s0', 1);










