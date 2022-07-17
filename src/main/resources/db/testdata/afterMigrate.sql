insert into super_hero (name,age,skill) values ('Superman',1000, 'can fly, superhuman strength');
insert into super_hero (name,age,skill) values ('Spiderman',17, 'spider sense, Wallcrawling');
insert into auth_role ( name ) values ('admin');
insert into auth_user ( username, password, email, enabled) values ('admin', '$2a$16$8qiqG.oDuzPeKJYBVYGC8OQ/xfEF26oCyEANJi0y.rKNW94l/FJR.', 'mrtn.maturano@gmail.com',true);
insert into auth_user_roles ( auth_user_id, auth_role_id) values (1,1);