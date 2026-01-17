
--DATA

-- PostgreSQL Database Data
SET statement_timeout = 0;
SELECT pg_catalog.set_config('search_path', '', false);

-- Datos: persona
INSERT INTO public.persona (id, direccion, edad, genero, identificacion, nombre, telefono) 
VALUES (1, 'Otavalo sn y principal', 30, 'Masculino', '000000001', 'Jose Lema', '098254785') ON CONFLICT (id) DO NOTHING;
INSERT INTO public.persona (id, direccion, edad, genero, identificacion, nombre, telefono) 
VALUES (2, 'Amazonas y NNUU', 30, 'Femenino', '000000002', 'Marianela Montalvo', '097548965') ON CONFLICT (id) DO NOTHING;
INSERT INTO public.persona (id, direccion, edad, genero, identificacion, nombre, telefono) 
VALUES (3, '13 junio y Equinoccial', 30, 'Masculino', '000000003', 'Juan Osorio', '098874587') ON CONFLICT (id) DO NOTHING;

-- Datos: cliente
INSERT INTO public.cliente (cliente_id, contrasena, estado, id) 
VALUES ('joselema', '1234', 'True', 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.cliente (cliente_id, contrasena, estado, id) 
VALUES ('marianelamontalvo', '5678', 'True', 2) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.cliente (cliente_id, contrasena, estado, id) 
VALUES ('juanosorio', '1245', 'True', 3) ON CONFLICT (id) DO NOTHING;

-- Datos: cuenta
INSERT INTO public.cuenta (id, estado, numero_cuenta, saldo_disponible, saldo_inicial, tipo_cuenta, cliente_id) 
VALUES (5, 'True', '585545', 1000.00, 1000.00, 'Corriente', 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.cuenta (id, estado, numero_cuenta, saldo_disponible, saldo_inicial, tipo_cuenta, cliente_id) 
VALUES (1, 'True', '478758', 1425.00, 2000.00, 'Ahorro', 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.cuenta (id, estado, numero_cuenta, saldo_disponible, saldo_inicial, tipo_cuenta, cliente_id) 
VALUES (2, 'True', '225487', 700.00, 100.00, 'Corriente', 2) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.cuenta (id, estado, numero_cuenta, saldo_disponible, saldo_inicial, tipo_cuenta, cliente_id) 
VALUES (3, 'True', '495878', 150.00, 0.00, 'Ahorros', 3) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.cuenta (id, estado, numero_cuenta, saldo_disponible, saldo_inicial, tipo_cuenta, cliente_id) 
VALUES (4, 'True', '496825', 0.00, 540.00, 'Ahorros', 2) ON CONFLICT (id) DO NOTHING;

-- Datos: movimiento
INSERT INTO public.movimiento (id, fecha, saldo, saldo_inicial, tipo_movimiento, valor, cuenta_id) 
VALUES (1, '2026-01-17 16:36:47.325061', 1425.00, 2000.00, 'RETIRO', -575.00, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.movimiento (id, fecha, saldo, saldo_inicial, tipo_movimiento, valor, cuenta_id) 
VALUES (2, '2026-01-17 16:36:54.36672', 700.00, 100.00, 'DEPOSITO', 600.00, 2) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.movimiento (id, fecha, saldo, saldo_inicial, tipo_movimiento, valor, cuenta_id) 
VALUES (3, '2026-01-17 16:36:59.466922', 150.00, 0.00, 'DEPOSITO', 150.00, 3) ON CONFLICT (id) DO NOTHING;
INSERT INTO public.movimiento (id, fecha, saldo, saldo_inicial, tipo_movimiento, valor, cuenta_id) 
VALUES (4, '2026-01-17 16:37:05.36125', 0.00, 540.00, 'RETIRO', -540.00, 4) ON CONFLICT (id) DO NOTHING;

-- Sincronización de Secuencias
SELECT pg_catalog.setval('public.cuenta_id_seq', 5, true);
SELECT pg_catalog.setval('public.movimiento_id_seq', 4, true);
SELECT pg_catalog.setval('public.persona_seq', 51, true);