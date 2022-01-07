CREATE SEQUENCE seq_validate START 1;

DROP TABLE val_result;

CREATE TABLE public.val_result (
	seq int8,
	val_date varchar NOT NULL,
	schema_nm varchar NOT NULL,
	table_nm varchar NOT NULL,
	status varchar NULL,
	src_value text NULL,
	tgt_value text null,
	
	CONSTRAINT val_result_pk PRIMARY KEY (seq, val_date, schema_nm, table_nm)
);

ALTER TABLE public.val_result OWNER TO postgres;
GRANT ALL ON TABLE public.val_result TO postgres;


DROP TABLE public.biz_tb;
CREATE TABLE public.biz_tb (
	c1 varchar NOT NULL,
	c2 varchar NULL,
	c3 varchar NULL,
	CONSTRAINT biz_tb_pk PRIMARY KEY (c1)
);



