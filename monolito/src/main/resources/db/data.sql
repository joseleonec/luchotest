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

-- Datos: cuenta
INSERT INTO public.cuenta (saldo_disponible, saldo_inicial, cliente_id, id, estado, numero_cuenta, tipo_cuenta) 
VALUES (1000.00, 1000.00, 1, 5, 'True', '585545', 'Corriente') ON CONFLICT (id) DO NOTHING;
INSERT INTO public.cuenta (saldo_disponible, saldo_inicial, cliente_id, id, estado, numero_cuenta, tipo_cuenta) 
VALUES (1425.00, 2000.00, 1, 1, 'True', '478758', 'Ahorro') ON CONFLICT (id) DO NOTHING;
INSERT INTO public.cuenta (saldo_disponible, saldo_inicial, cliente_id, id, estado, numero_cuenta, tipo_cuenta) 
VALUES (700.00, 100.00, 2, 2, 'True', '225487', 'Corriente') ON CONFLICT (id) DO NOTHING;
INSERT INTO public.cuenta (saldo_disponible, saldo_inicial, cliente_id, id, estado, numero_cuenta, tipo_cuenta) 
VALUES (150.00, 0.00, 3, 3, 'True', '495878', 'Ahorros') ON CONFLICT (id) DO NOTHING;
INSERT INTO public.cuenta (saldo_disponible, saldo_inicial, cliente_id, id, estado, numero_cuenta, tipo_cuenta) 
VALUES (0.00, 540.00, 2, 4, 'True', '496825', 'Ahorros') ON CONFLICT (id) DO NOTHING;

-- Datos: movimiento
INSERT INTO public.movimiento (saldo, saldo_inicial, valor, cuenta_id, fecha, id, tipo_movimiento) 
VALUES (1425.00, 2000.00, -575.00, 1, '2026-01-18 09:20:39.30821', 1, 'RETIRO') ON CONFLICT (id) DO NOTHING;
INSERT INTO public.movimiento (saldo, saldo_inicial, valor, cuenta_id, fecha, id, tipo_movimiento) 
VALUES (700.00, 100.00, 600.00, 2, '2026-01-18 09:20:41.801552', 2, 'DEPOSITO') ON CONFLICT (id) DO NOTHING;
INSERT INTO public.movimiento (saldo, saldo_inicial, valor, cuenta_id, fecha, id, tipo_movimiento) 
VALUES (150.00, 0.00, 150.00, 3, '2026-01-18 09:20:47.057106', 3, 'DEPOSITO') ON CONFLICT (id) DO NOTHING;
INSERT INTO public.movimiento (saldo, saldo_inicial, valor, cuenta_id, fecha, id, tipo_movimiento) 
VALUES (0.00, 540.00, -540.00, 4, '2026-01-18 09:21:00.039209', 4, 'RETIRO') ON CONFLICT (id) DO NOTHING;

-- Sincronización de Secuencias
SELECT pg_catalog.setval('public.cuenta_id_seq', 5, true);
SELECT pg_catalog.setval('public.movimiento_id_seq', 4, true);
SELECT pg_catalog.setval('public.persona_seq', 51, true);

