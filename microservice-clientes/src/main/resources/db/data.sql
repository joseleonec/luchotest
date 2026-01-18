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

