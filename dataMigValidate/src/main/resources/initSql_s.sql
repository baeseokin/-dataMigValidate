DROP TABLE public.biz_tb;
CREATE TABLE `data`.biz_tb (
	c1 varchar(100) NOT NULL,
	c2 varchar(100) NOT NULL,
	c3 varchar(100) NULL,
	CONSTRAINT biz_tb_pk PRIMARY KEY (c1)
);



INSERT INTO data.biz_tb(c1, c2, c3) VALUES('1', '1', '1');
INSERT INTO data.biz_tb(c1, c2, c3) VALUES('2', '2', '2');
INSERT INTO data.biz_tb(c1, c2, c3) VALUES('3', '3', '3');
INSERT INTO data.biz_tb(c1, c2, c3) VALUES('4', '4', '4');
INSERT INTO data.biz_tb(c1, c2, c3) VALUES('5', '5', '5');