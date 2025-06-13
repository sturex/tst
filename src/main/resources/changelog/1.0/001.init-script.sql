-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.2_snapshot20190921
-- PostgreSQL version: 11.0
-- Project Site: pgmodeler.io
-- Model Author: ---


-- Database creation must be done outside a multicommand file.
-- These commands were put in this file only as a convenience.
-- -- object: new_database | type: DATABASE --
-- -- DROP DATABASE IF EXISTS new_database;
-- CREATE DATABASE new_database;
-- -- ddl-end --
-- 

-- object: demo | type: SCHEMA --
-- DROP SCHEMA IF EXISTS demo CASCADE;
CREATE SCHEMA demo;
-- ddl-end --
ALTER SCHEMA demo OWNER TO postgres;
-- ddl-end --

SET search_path TO pg_catalog,public,demo;
-- ddl-end --

-- object: demo."user" | type: TABLE --
-- DROP TABLE IF EXISTS demo."user" CASCADE;
CREATE TABLE demo."user" (
	id uuid NOT NULL,
	creation_timestamp timestamp NOT NULL,
	update_timestamp timestamp NOT NULL
);
-- ddl-end --
ALTER TABLE demo."user" OWNER TO postgres;
-- ddl-end --

-- object: demo.article | type: TABLE --
-- DROP TABLE IF EXISTS demo.article CASCADE;
CREATE TABLE demo.article (
	id uuid NOT NULL,
	title text NOT NULL,
	author text NOT NULL,
	content text NOT NULL,
	publishing_date timestamp NOT NULL,
	creation_timestamp timestamp NOT NULL,
	update_timestamp timestamp NOT NULL
);
-- ddl-end --
ALTER TABLE demo.article OWNER TO postgres;
-- ddl-end --


