-- PostgreSQL Database Schema
SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);

-- Estructura de la tabla 'persona'
CREATE TABLE public.persona (
    edad integer,
    id bigint NOT NULL,
    direccion character varying(255),
    genero character varying(255),
    identificacion character varying(255),
    nombre character varying(255),
    telefono character varying(255)
);

-- Estructura de la tabla 'cliente'
CREATE TABLE public.cliente (
    id bigint NOT NULL,
    cliente_id character varying(255) NOT NULL,
    contrasena character varying(255),
    estado character varying(255)
);



-- Secuencia para persona
CREATE SEQUENCE public.persona_seq
    START WITH 1
    INCREMENT BY 50;

-- Constraints y Llaves Primarias
ALTER TABLE ONLY public.persona ADD CONSTRAINT persona_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.cliente ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.cliente ADD CONSTRAINT cliente_cliente_id_key UNIQUE (cliente_id);


-- Llaves Foráneas (Foreign Keys)
ALTER TABLE ONLY public.cliente ADD CONSTRAINT fkkpvkbjg32bso6riqge70hwcel FOREIGN KEY (id) REFERENCES public.persona(id);


