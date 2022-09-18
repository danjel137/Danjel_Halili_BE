-- Table: public.Person

-- DROP TABLE IF EXISTS public."Person";

CREATE TABLE IF NOT EXISTS public."Person"
(
    id integer,
    name text COLLATE pg_catalog."default",
    surname text COLLATE pg_catalog."default",
    email text COLLATE pg_catalog."default"
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Person"
    OWNER to postgres;

	INSERT INTO  Person" (id,name,surname,email) VALUES (01,"Danjel","Halili","danjel.halili@crystal-system.eu");
	INSERT INTO  Person" (id,name,surname,email) VALUES (02,"Isuf","Muca","isuf.muca@crystal-system.eu");
	INSERT INTO  Person" (id,name,surname,email) VALUES (03,"Elia","Omeri","elia.omeri@crystal-system.eu");
	INSERT INTO  Person" (id,name,surname,email) VALUES (04,"Megi","Lala","megi.lala@crystal-system.eu");
	INSERT INTO  Person" (id,name,surname,email) VALUES (05,"Flavio","Lorenci","flavio.lorenci@crystal-system.eu");
	INSERT INTO  Person" (id,name,surname,email) VALUES (06,"Irena","Shahini","danjel.halili@crystal-system.eu");
	INSERT INTO  Person" (id,name,surname,email) VALUES (07,"Indrit","Vaka","indrit.vaka@crystal-system.eu");

