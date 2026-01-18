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

-- PostgreSQL Database Data
SET statement_timeout = 0;
SELECT pg_catalog.set_config('search_path', '', false);

-- Datos: persona
INSERT INTO public.persona (edad, id, direccion, genero, identificacion, nombre, telefono) 
VALUES (30, 1, 'Otavalo sn y principal', 'Masculino', '000000001', 'Jose Lema', '098254785') ON CONFLICT (id) DO NOTHING;
INSERT INTO public.persona (edad, id, direccion, genero, identificacion, nombre, telefono) 
VALUES (30, 2, 'Amazonas y NNUU', 'Femenino', '000000002', 'Marianela Montalvo', '097548965') ON CONFLICT (id) DO NOTHING;
INSERT INTO public.persona (edad, id, direccion, genero, identificacion, nombre, telefono) 
VALUES (30, 3, '13 junio y Equinoccial', 'Masculino', '000000003', 'Juan Osorio', '098874587') ON CONFLICT (id) DO NOTHING;

-- Datos: cliente
INSERT INTO public.cliente (id, cliente_id, contrasena, estado) 
VALUES (1, 'joselema', '1234', 'True') ON CONFLICT (id) DO NOTHING;
INSERT INTO public.cliente (id, cliente_id, contrasena, estado) 
VALUES (2, 'marianelamontalvo', '5678', 'True') ON CONFLICT (id) DO NOTHING;
INSERT INTO public.cliente (id, cliente_id, contrasena, estado) 
VALUES (3, 'juanosorio', '1245', 'True') ON CONFLICT (id) DO NOTHING;

-- Sincronización de Secuencias

SELECT pg_catalog.setval('public.persona_seq', 51, true);

