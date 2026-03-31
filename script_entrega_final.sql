-- SCRIPT DE ENTREGA FINAL - SISTEMA DE RESERVAS DE VUELOS
-- Generado para: reservaJPASql

CREATE DATABASE IF NOT EXISTS reservaJPASql;
USE reservaJPASql;

-- 1. ELIMINACIÓN DE TABLAS (ORDEN INVERSO DE DEPENDENCIAS)
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS vuelo_aeropuerto;
DROP TABLE IF EXISTS tarifa;
DROP TABLE IF EXISTS reserva;
DROP TABLE IF EXISTS consulta;
DROP TABLE IF EXISTS vuelo;
DROP TABLE IF EXISTS asiento;
DROP TABLE IF EXISTS avion;
DROP TABLE IF EXISTS aeropuerto;
DROP TABLE IF EXISTS ciudad;
DROP TABLE IF EXISTS fecha;
DROP TABLE IF EXISTS aerolinea;
DROP TABLE IF EXISTS tarjeta;
DROP TABLE IF EXISTS pago;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS piloto;
DROP TABLE IF EXISTS persona;
SET FOREIGN_KEY_CHECKS = 1;

-- 2. CREACIÓN DE TABLAS (DDL)

-- Herencia Persona (JOINED)
CREATE TABLE persona (
    id_persona BIGINT AUTO_INCREMENT PRIMARY KEY,
    dni_persona INT,
    nombre_persona VARCHAR(100) NOT NULL,
    apellido_persona VARCHAR(100) NOT NULL,
    dtype VARCHAR(31)
) ENGINE=InnoDB;

CREATE TABLE usuario (
    id_persona BIGINT PRIMARY KEY,
    numero_usuario INT NOT NULL UNIQUE,
    celular VARCHAR(255),
    contrasena_usuario VARCHAR(255) NOT NULL,
    correo_electronico_usuario VARCHAR(150) NOT NULL UNIQUE,
    CONSTRAINT fk_usuario_persona FOREIGN KEY (id_persona) REFERENCES persona(id_persona)
) ENGINE=InnoDB;

CREATE TABLE piloto (
    id_persona BIGINT PRIMARY KEY,
    numero_piloto INT NOT NULL UNIQUE,
    CONSTRAINT fk_piloto_persona FOREIGN KEY (id_persona) REFERENCES persona(id_persona)
) ENGINE=InnoDB;

-- Tablas Maestras
CREATE TABLE ciudad (
    id_ciudad BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_ciudad VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE aeropuerto (
    id_aeropuerto BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_aeropuerto VARCHAR(150) NOT NULL,
    id_ciudad BIGINT NOT NULL,
    CONSTRAINT fk_aeropuerto_ciudad FOREIGN KEY (id_ciudad) REFERENCES ciudad(id_ciudad)
) ENGINE=InnoDB;

CREATE TABLE aerolinea (
    id_aerolinea BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_aerolinea VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE avion (
    id_avion BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_avion INT NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE asiento (
    id_asiento BIGINT AUTO_INCREMENT PRIMARY KEY,
    fila_asiento INT NOT NULL,
    letra_asiento CHAR(1) NOT NULL,
    clase_asiento VARCHAR(50) NOT NULL,
    id_avion BIGINT,
    CONSTRAINT fk_asiento_avion FOREIGN KEY (id_avion) REFERENCES avion(id_avion)
) ENGINE=InnoDB;

CREATE TABLE fecha (
    id_fecha BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL
) ENGINE=InnoDB;

-- Vuelos y Relaciones
CREATE TABLE vuelo (
    id_vuelo BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_vuelo INT NOT NULL UNIQUE,
    id_avion BIGINT,
    id_fecha BIGINT,
    id_piloto BIGINT,
    id_aerolinea BIGINT,
    CONSTRAINT fk_vuelo_avion FOREIGN KEY (id_avion) REFERENCES avion(id_avion),
    CONSTRAINT fk_vuelo_fecha FOREIGN KEY (id_fecha) REFERENCES fecha(id_fecha),
    CONSTRAINT fk_vuelo_piloto FOREIGN KEY (id_piloto) REFERENCES piloto(id_persona),
    CONSTRAINT fk_vuelo_aerolinea FOREIGN KEY (id_aerolinea) REFERENCES aerolinea(id_aerolinea)
) ENGINE=InnoDB;

CREATE TABLE vuelo_aeropuerto (
    id_vuelo BIGINT NOT NULL,
    id_aeropuerto BIGINT NOT NULL,
    PRIMARY KEY (id_vuelo, id_aeropuerto),
    CONSTRAINT fk_va_vuelo FOREIGN KEY (id_vuelo) REFERENCES vuelo(id_vuelo),
    CONSTRAINT fk_va_aeropuerto FOREIGN KEY (id_aeropuerto) REFERENCES aeropuerto(id_aeropuerto)
) ENGINE=InnoDB;

CREATE TABLE tarifa (
    id_tarifa BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_tarifa INT NOT NULL,
    impuesto_tarifa INT,
    precio_tarifa INT NOT NULL,
    clase_tarifa VARCHAR(50) NOT NULL,
    id_vuelo BIGINT,
    CONSTRAINT fk_tarifa_vuelo FOREIGN KEY (id_vuelo) REFERENCES vuelo(id_vuelo)
) ENGINE=InnoDB;

-- Pagos y Reservas
CREATE TABLE pago (
    id_pago BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_pago INT NOT NULL,
    cantidad_pago INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE tarjeta (
    id_pago BIGINT PRIMARY KEY,
    numero_tarjeta INT NOT NULL UNIQUE,
    tipo_tarjeta VARCHAR(50) NOT NULL,
    CONSTRAINT fk_tarjeta_pago FOREIGN KEY (id_pago) REFERENCES pago(id_pago)
) ENGINE=InnoDB;

CREATE TABLE reserva (
    id_reserva BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_reserva INT NOT NULL UNIQUE,
    id_vuelo BIGINT,
    id_pago BIGINT,
    id_persona BIGINT,
    CONSTRAINT fk_reserva_vuelo FOREIGN KEY (id_vuelo) REFERENCES vuelo(id_vuelo),
    CONSTRAINT fk_reserva_pago FOREIGN KEY (id_pago) REFERENCES pago(id_pago),
    CONSTRAINT fk_reserva_usuario FOREIGN KEY (id_persona) REFERENCES usuario(id_persona)
) ENGINE=InnoDB;

CREATE TABLE consulta (
    id_consulta BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_consulta INT NOT NULL,
    id_vuelo BIGINT,
    id_usuario BIGINT,
    CONSTRAINT fk_consulta_vuelo FOREIGN KEY (id_vuelo) REFERENCES vuelo(id_vuelo),
    CONSTRAINT fk_consulta_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_persona)
) ENGINE=InnoDB;

-- 3. CARGA DE DATOS DE PRUEBA (DML)

-- Ciudades
INSERT INTO ciudad (nombre_ciudad) VALUES ('Buenos Aires'), ('Madrid'), ('Bogotá'), ('Miami'), ('Santiago');

-- Aeropuertos
INSERT INTO aeropuerto (nombre_aeropuerto, id_ciudad) VALUES 
('Ezeiza MS', 1), ('Barajas MD', 2), ('El Dorado BG', 3), ('Miami Intl', 4), ('Arturo Merino', 5);

-- Aerolineas
INSERT INTO aerolinea (nombre_aerolinea) VALUES ('Aerolíneas Argentinas'), ('Iberia'), ('Avianca'), ('American Airlines'), ('LATAM');

-- Aviones
INSERT INTO avion (numero_avion) VALUES (101), (202), (303), (404), (505);

-- Fechas
INSERT INTO fecha (fecha) VALUES ('2026-05-15 10:00:00'), ('2026-06-20 14:30:00'), ('2026-07-10 08:15:00'), ('2026-08-05 22:00:00'), ('2026-09-12 11:45:00');

-- Pilotos (Persona + Piloto)
INSERT INTO persona (dni_persona, nombre_persona, apellido_persona) VALUES 
(111, 'Capitán Alberto', 'García'),
(222, 'Capitán Roberto', 'Sánchez'),
(333, 'Capitán Juan', 'Pérez'),
(444, 'Capitán Luis', 'Martínez'),
(555, 'Capitán Carlos', 'López');

INSERT INTO piloto (id_persona, numero_piloto) VALUES (1, 1001), (2, 2002), (3, 3003), (4, 4004), (5, 5005);

-- Vuelos
INSERT INTO vuelo (numero_vuelo, id_avion, id_fecha, id_piloto, id_aerolinea) VALUES 
(701, 1, 1, 1, 1),
(702, 2, 2, 2, 2),
(703, 3, 3, 3, 3),
(704, 4, 4, 4, 4),
(705, 5, 5, 5, 5);

-- Relacion Vuelo-Aeropuerto (Origen y Destino)
INSERT INTO vuelo_aeropuerto (id_vuelo, id_aeropuerto) VALUES 
(1, 1), (1, 2), -- Ezeiza -> Barajas
(2, 2), (2, 4), -- Barajas -> Miami
(3, 3), (3, 1), -- El Dorado -> Ezeiza
(4, 4), (4, 5), -- Miami -> Arturo Merino
(5, 5), (5, 3); -- Arturo Merino -> El Dorado

-- Tarifas
INSERT INTO tarifa (numero_tarifa, impuesto_tarifa, precio_tarifa, clase_tarifa, id_vuelo) VALUES 
(1, 100, 1500, 'BUSINESS', 1),
(2, 80, 1200, 'TURISTA', 2),
(3, 50, 900, 'ECONOMY', 3),
(4, 120, 1800, 'BUSINESS', 4),
(5, 70, 1100, 'TURISTA', 5);
