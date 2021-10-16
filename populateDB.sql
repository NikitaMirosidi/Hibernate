insert into company (id, `name`, registration_country)
values
	(1,'Nanosoft','USA'),
	(2,'Jedi Enginering','Far far Galaxy'),
	(3,'Pineapple','Malaysia'),
	(4,'Mamazon','India'),
	(5,'Facepalm','Ukraine'),
	(6,'Bro Software','Honduras'),
	(7,'So Long Developments','Estonia');
insert into customer (id, `name`, owner_name)
values
	(1,'BMW','Gustav Otto'),
    (2, 'Tesla Motors', 'Elon Reeve Musk'),
	(3,'The Walt Disney Company','Walter Elias Disney'),
	(4,'Virgin Galactic','Richard Charles Nicholas Branson'),
	(5,'Stark Industries','Anthony Edward Stark');
insert into developer (id, `name`, age, gender, email, salary, company_id)
values
	(1,'Developer 1',25,'male','developer_1@mail.com',1000,1),
    (2,'Developer 2',43,'female','developer_2@mail.com',2000,1),
    (3,'Developer 3',23,'female','developer_3@mail.com',1500,2),
    (4,'Developer 4',87,'male','developer_4@mail.com',3000,2),
    (5,'Developer 5',31,'female','developer_5@mail.com',2500,3),
    (6,'Developer 6',22,'female','developer_6@mail.com',2000,3),
    (7,'Developer 7',54,'male','developer_7@mail.com',1000,4),
    (8,'Developer 8',99,'female','developer_8@mail.com',1500,4),
    (9,'Developer 9',34,'male','developer_9@mail.com',2500,5),
    (10,'Developer 10',19,'female','developer_10@mail.com',3500,5),
    (11,'Developer 11',36,'male','developer_11@mail.com',4000,6),
    (12,'Developer 12',27,'male','developer_12@mail.com',1000,6),
    (13,'Developer 13',32,'female','developer_13@mail.com',3500,7),
    (14,'Developer 14',29,'female','developer_14@mail.com',5000,7),
    (15,'Developer 15',18,'male','developer_15@mail.com',3000,7),
    (16,'Developer 16',46,'female','developer_16@mail.com',1500,1),
    (17,'Developer 17',77,'female','developer_17@mail.com',2500,2),
    (18,'Developer 18',38,'male','developer_18@mail.com',2000,3),
    (19,'Developer 19',27,'male','developer_19@mail.com',2500,4),
    (20,'Developer 20',44,'female','developer_20@mail.com',4000,5);
insert into project (id, `name`, cost, creation_date, customer_id, company_id)
values
	(1,'Advanced Autopilot',1000000,'2002-01-15',2,1),
    (2,'Graphic Design Tools',2000000,'2015-06-30',3,2),
    (3,'Space Navigator 2000',3000000,'2020-09-21',4,3),
    (4,'J.A.R.V.I.S.',4000000,'2011-11-13',5,4),
    (5,'Pillow fart',5000000,'2016-03-19',2,5),
    (6,'F.R.I.D.A.Y.',6000000,'1999-10-10',5,6),
    (7,'Lonch Control v.2',7000000,'2021-02-08',1,7),
    (8,'Beerfest Navigator',8000000,'2007-07-02',1,2),
    (9,'Mickey Mouse Animation',9000000,'2019-12-31',3,3),
    (10,'Battery Controller',10000000,'2001-04-22',2,4);
	
insert into skill (id, `name`, `level`)
values
	(1,'Java','Junior'),
    (2,'Java','Middle'),
    (3,'Java','Senior'),
    (4,'C++','Junior'),
    (5,'C++','Middle'),
    (6,'C++','Senior'),
    (7,'C#','Junior'),
    (8,'C#','Middle'),
    (9,'C#','Senior'),
    (10,'JavaScript','Junior'),
    (11,'JavaScript','Middle'),
    (12,'JavaScript','Senior');
insert into developer_and_project (developer_id, project_id)
values
	(1,1),(2,1),(16,1),
    (9,5),(10,5),(20,5),
    (11,6),(12,6),(13,7),
    (14,7),(15,7),(3,2),
    (4,2),(17,8),(3,8),
    (5,3),(6,3),(8,9),
    (5,9),(7,4),(8,4),
    (19,10),(7,10);
insert into developer_and_skill (developer_id, skill_id)
values
	(1,1),(1,11),(2,7),
    (3,5),(4,12),(5,2),
    (6,4),(7,2),(8,4),
    (9,8),(10,9),(11,3),
    (11,5),(12,8),(12,10),
    (13,1),(13,11),(14,3),
    (14,6),(14,7),(15,2),
    (15,10),(16,8),(16,5),
    (17,4),(18,9),(19,7),
    (20,3),(20,6),(20,12);