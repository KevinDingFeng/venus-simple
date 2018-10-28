insert into sys_pool value(1,now(),now(),0,false,false,10,'Kevin科技',null,false,null);
insert into sys_pool value(2,now(),now(),0,false,false,5,'Kevin公司',null,false,null);

insert into sys_user(creation,last_modified,version,account,active,audited,cellphone_verified,email_verified,name,password,removed,salt,sys_id,sys_pool_id,level) 
value(now(),now(),0,'123',true,true,false,false,'Kevin','0d92d17f3dcb8eb80b2bc88edc372178485c08b9282926af88f6faaf3c3dd9f4',false,'bsffidvl0oyxnb6g','System',1,10);
---密码是 Shsun2018
insert into sys_permission(creation,last_modified,version,perm,category,name,remark,seq_num) values
(now(),now(),0,"role:view","角色管理","查看角色","查看角色",0),
(now(),now(),0,"role:create","角色管理","创建角色","创建角色",0),
(now(),now(),0,"role:update","角色管理","修改角色","修改角色",0),
(now(),now(),0,"role:removed","角色管理","删除角色","删除角色",0),
(now(),now(),0,"user:view","用户管理","查看用户","查看用户",0),
(now(),now(),0,"user:create","用户管理","创建用户","创建用户",0),
(now(),now(),0,"user:update","用户管理","修改用户","修改用户",0),
(now(),now(),0,"user:removed","用户管理","删除用户","删除用户",0);

insert into sys_role(creation,last_modified,version,level,name,removed) values
(now(),now(),0,5,'一般管理员',false),
(now(),now(),0,10,'超级管理员',false);
---一般管理员负责可以拥有用户的所有权限，但是只可以查看角色；超级管理员拥有用户和角色的所有权限

insert into sys_role_permission values
(1,1),
(1,5),
(1,6),
(1,7),
(1,8),
(2,1),
(2,2),
(2,3),
(2,4),
(2,5),
(2,6),
(2,7),
(2,8);

insert into sys_user_role values(1,2);



select p.perm from sys_user u join sys_user_role ur on u.id=ur.user_id join sys_role r on ur.role_id=r.id join sys_role_permission rp on rp.role_id=r.id join sys_permission p on p.id=rp.perm_id where u.id=1 group by p.perm;

select p.perm from sys_permission p where p.id in (select rp.perm_id from sys_role_permission rp where rp.role_id in (select ur.role_id from sys_user_role ur where ur.user_id=1)) group by p.perm;
