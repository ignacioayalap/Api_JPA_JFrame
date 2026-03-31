-- ================================================================
-- SCRIPT DE DATOS DE PRUEBA - Sistema de Reserva de Vuelos
-- Base de datos: reservaJPASql
-- Ejecutar COMPLETO en MySQL Workbench (Ctrl+Shift+Enter)
-- ================================================================

CREATE DATABASE IF NOT EXISTS reservaJPASql
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE reservaJPASql;

-- ----------------------------------------------------------------
-- PASO 0: LIMPIAR DATOS EN ORDEN CORRECTO (respeta las FK)
--         Solo limpia si la tabla existe
-- ----------------------------------------------------------------
SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM reserva;
DELETE FROM tarifa;
DELETE FROM vuelo_aeropuerto;
DELETE FROM vuelo;
DELETE FROM aeropuerto;
DELETE FROM ciudad;
DELETE FROM aerolinea;
DELETE FROM avion;
DELETE FROM asiento;
DELETE FROM fecha;
DELETE FROM piloto;
-- Borrar solo las personas que NO sean usuarios (preservar usuarios registrados)
DELETE FROM persona WHERE id_persona NOT IN (
    SELECT id_persona FROM usuario
);

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------------------------------------------
-- PASO 1: CIUDADES
-- ----------------------------------------------------------------
INSERT INTO ciudad (nombre_ciudad) VALUES
    ('Buenos Aires'),
    ('Córdoba'),
    ('Mendoza'),
    ('Bariloche'),
    ('Salta');

-- ----------------------------------------------------------------
-- PASO 2: AEROPUERTOS
-- ----------------------------------------------------------------
INSERT INTO aeropuerto (nombre_aeropuerto, id_ciudad) VALUES
    ('Aero. Ministro Pistarini',          1),  -- Buenos Aires
    ('Aero. Ambrosio Taravella',          2),  -- Córdoba
    ('Aero. El Plumerillo',               3),  -- Mendoza
    ('Aero. Teniente Luis Candelaria',    4),  -- Bariloche
    ('Aero. Martín Miguel de Güemes',     5);  -- Salta

-- ----------------------------------------------------------------
-- PASO 3: AEROLÍNEAS
-- ----------------------------------------------------------------
INSERT INTO aerolinea (nombre_aerolinea) VALUES
    ('Aerolíneas Argentinas'),
    ('LATAM Argentina'),
    ('Flybondi');

-- ----------------------------------------------------------------
-- PASO 4: AVIONES
-- ----------------------------------------------------------------
INSERT INTO avion (numero_avion) VALUES
    (101),
    (202),
    (303);

-- ----------------------------------------------------------------
-- PASO 5: FECHAS DE VUELOS
-- ----------------------------------------------------------------
INSERT INTO fecha (fecha) VALUES
    ('2026-04-20 07:00:00'),
    ('2026-04-21 10:30:00'),
    ('2026-04-22 15:45:00'),
    ('2026-04-25 08:00:00'),
    ('2026-04-28 18:30:00');

-- ----------------------------------------------------------------
-- PASO 6: PILOTOS (herencia JOINED: primero persona, luego piloto)
-- ----------------------------------------------------------------
INSERT INTO persona (dni_persona, nombre_persona, apellido_persona)
    VALUES (33111222, 'Carlos', 'Rodríguez');
SET @p1 = LAST_INSERT_ID();
INSERT INTO piloto (numero_piloto, id_persona) VALUES (1001, @p1);

INSERT INTO persona (dni_persona, nombre_persona, apellido_persona)
    VALUES (33222333, 'Laura', 'González');
SET @p2 = LAST_INSERT_ID();
INSERT INTO piloto (numero_piloto, id_persona) VALUES (1002, @p2);

INSERT INTO persona (dni_persona, nombre_persona, apellido_persona)
    VALUES (33444555, 'Marcos', 'López');
SET @p3 = LAST_INSERT_ID();
INSERT INTO piloto (numero_piloto, id_persona) VALUES (1003, @p3);

-- ----------------------------------------------------------------
-- PASO 7: VUELOS
--    Capturamos los IDs de fecha y piloto según el orden insertado
-- ----------------------------------------------------------------
-- Obtenemos los primeros IDs de cada tabla auxiliar
SET @id_fecha1  = (SELECT MIN(id_fecha)  FROM fecha);
SET @id_fecha2  = @id_fecha1 + 1;
SET @id_fecha3  = @id_fecha1 + 2;
SET @id_fecha4  = @id_fecha1 + 3;
SET @id_fecha5  = @id_fecha1 + 4;

SET @id_avion1  = (SELECT MIN(id_avion)  FROM avion);
SET @id_avion2  = @id_avion1 + 1;
SET @id_avion3  = @id_avion1 + 2;

SET @id_piloto1 = (SELECT MIN(id_persona) FROM piloto);
SET @id_piloto2 = @id_piloto1 + 1;
SET @id_piloto3 = @id_piloto1 + 2;

SET @id_aero1   = (SELECT MIN(id_aerolinea) FROM aerolinea);
SET @id_aero2   = @id_aero1 + 1;
SET @id_aero3   = @id_aero1 + 2;

INSERT INTO vuelo (numero_vuelo, id_avion, id_fecha, id_piloto, id_aerolinea) VALUES
    (1001, @id_avion1, @id_fecha1, @id_piloto1, @id_aero1),  -- Bs.As -> Córdoba
    (2002, @id_avion2, @id_fecha2, @id_piloto2, @id_aero2),  -- Córdoba -> Mendoza
    (3003, @id_avion3, @id_fecha3, @id_piloto3, @id_aero3),  -- Bs.As -> Bariloche
    (4004, @id_avion1, @id_fecha4, @id_piloto1, @id_aero1),  -- Mendoza -> Salta
    (5005, @id_avion2, @id_fecha5, @id_piloto2, @id_aero2);  -- Bs.As -> Salta

-- ----------------------------------------------------------------
-- PASO 8: VUELO - AEROPUERTO (origen y destino de cada vuelo)
-- ----------------------------------------------------------------
SET @id_vuelo1 = (SELECT MIN(id_vuelo) FROM vuelo);
SET @id_vuelo2 = @id_vuelo1 + 1;
SET @id_vuelo3 = @id_vuelo1 + 2;
SET @id_vuelo4 = @id_vuelo1 + 3;
SET @id_vuelo5 = @id_vuelo1 + 4;

SET @id_apto_bsas      = (SELECT MIN(id_aeropuerto) FROM aeropuerto);
SET @id_apto_cordoba   = @id_apto_bsas + 1;
SET @id_apto_mendoza   = @id_apto_bsas + 2;
SET @id_apto_bariloche = @id_apto_bsas + 3;
SET @id_apto_salta     = @id_apto_bsas + 4;

INSERT INTO vuelo_aeropuerto (id_vuelo, id_aeropuerto) VALUES
    -- Vuelo 1001: Buenos Aires -> Córdoba
    (@id_vuelo1, @id_apto_bsas),
    (@id_vuelo1, @id_apto_cordoba),
    -- Vuelo 2002: Córdoba -> Mendoza
    (@id_vuelo2, @id_apto_cordoba),
    (@id_vuelo2, @id_apto_mendoza),
    -- Vuelo 3003: Buenos Aires -> Bariloche
    (@id_vuelo3, @id_apto_bsas),
    (@id_vuelo3, @id_apto_bariloche),
    -- Vuelo 4004: Mendoza -> Salta
    (@id_vuelo4, @id_apto_mendoza),
    (@id_vuelo4, @id_apto_salta),
    -- Vuelo 5005: Buenos Aires -> Salta
    (@id_vuelo5, @id_apto_bsas),
    (@id_vuelo5, @id_apto_salta);

-- ----------------------------------------------------------------
-- PASO 9: TARIFAS (valores según enum Clase: BUSINESS, TURISTA, ECONOMY)
-- ----------------------------------------------------------------
INSERT INTO tarifa (numero_tarifa, impuesto_tarifa, precio_tarifa, clase_tarifa, id_vuelo) VALUES
    (1, 10,  85000, 'ECONOMY',  @id_vuelo1),
    (2, 10,  95000, 'ECONOMY',  @id_vuelo2),
    (3, 15, 130000, 'ECONOMY',  @id_vuelo3),
    (4, 12, 115000, 'ECONOMY',  @id_vuelo4),
    (5, 15, 145000, 'ECONOMY',  @id_vuelo5);

-- ----------------------------------------------------------------
-- VERIFICACIÓN FINAL: mostrar vuelos cargados con sus datos
-- ----------------------------------------------------------------
SELECT
    v.numero_vuelo                        AS 'N° Vuelo',
    a_orig.nombre_aeropuerto              AS 'Origen',
    a_dest.nombre_aeropuerto              AS 'Destino',
    f.fecha                               AS 'Fecha/Hora',
    ae.nombre_aerolinea                   AS 'Aerolínea',
    t.precio_tarifa                       AS 'Precio ($)',
    t.clase_tarifa                        AS 'Clase'
FROM vuelo v
JOIN fecha      f       ON v.id_fecha      = f.id_fecha
JOIN aerolinea  ae      ON v.id_aerolinea  = ae.id_aerolinea
JOIN tarifa     t       ON t.id_vuelo      = v.id_vuelo
JOIN vuelo_aeropuerto va1 ON va1.id_vuelo  = v.id_vuelo
JOIN vuelo_aeropuerto va2 ON va2.id_vuelo  = v.id_vuelo AND va2.id_aeropuerto <> va1.id_aeropuerto
JOIN aeropuerto a_orig  ON a_orig.id_aeropuerto = va1.id_aeropuerto
JOIN aeropuerto a_dest  ON a_dest.id_aeropuerto = va2.id_aeropuerto
ORDER BY v.numero_vuelo, va1.id_aeropuerto;
