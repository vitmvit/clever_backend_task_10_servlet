-- Adminer 4.8.1 PostgreSQL 15.3 (Debian 15.3-1.pgdg120+1) dump

DROP TABLE IF EXISTS "cat";
DROP SEQUENCE IF EXISTS cat_id_seq;
CREATE SEQUENCE cat_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."cats"
(
    "id"    bigint DEFAULT nextval('cats_id_seq') NOT NULL,
    "name"  text                                  NOT NULL,
    "color" text                                  NOT NULL,
    "breed" text                                  NOT NULL,
    "age"   integer                               NOT NULL,
    CONSTRAINT "cats_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "cats" ("id", "name", "color", "breed", "age")
VALUES (1, 'name', 'color', 'breed', 2),
       (2, 'name2', 'color1', 'breed2', 2),
       (3, 'name3', 'color3', 'breed3', 3),
       (4, 'name4', 'color2', 'breed2', 5),
       (5, 'name5', 'color5', 'breed1', 5),
       (6, 'name6', 'color4', 'breed2', 3),
       (7, 'name7', 'color3', 'breed3', 2),
       (8, 'name8', 'color2', 'breed1', 2),
       (9, 'name9', 'color1', 'breed1', 1),
       (10, 'name10', 'color1', 'breed2', 2),
       (11, 'name11', 'color3', 'breed3', 3),
       (12, 'name12', 'color6', 'breed2', 5),
       (13, 'name13', 'color5', 'breed1', 5),
       (14, 'name14', 'color4', 'breed2', 3),
       (15, 'name15', 'color3', 'breed3', 2),
       (16, 'name16', 'color3', 'breed3', 2),
       (17, 'name17', 'color5', 'breed4', 0),
       (18, 'name18', 'color3', 'breed1', 4),
       (19, 'name19', 'color7', 'breed2', 1),
       (20, 'name20', 'color3', 'breed3', 3),
       (21, 'name21', 'color2', 'breed1', 2);

-- 2023-12-23 19:46:49.366493+00