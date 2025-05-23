ALTER SESSION SET CONTAINER = XEPDB1;

CREATE USER dkuser IDENTIFIED BY dkpassword;
GRANT CONNECT, RESOURCE TO dkuser;
ALTER USER dkuser QUOTA UNLIMITED ON users;

-- Cambiar al esquema del usuario recién creado
ALTER SESSION SET CURRENT_SCHEMA = DKUSER;

-- Crear la tabla en el esquema DKUSER
CREATE TABLE EVENTO (
    CODIGO_EVENTO VARCHAR(20) PRIMARY KEY,
    NOMBRE VARCHAR(255) NOT NULL,
    DESCRIPCION VARCHAR(255),
    FECHA_HORA TIMESTAMP NOT NULL,
    UBICACION VARCHAR(255) NOT NULL,
    CAPACIDAD_MAXIMA INT NOT NULL
);


CREATE TABLE PARTICIPANTE (
    DNI VARCHAR(8) PRIMARY KEY,
    NOMBRE VARCHAR(255) NOT NULL,
    EMAIL VARCHAR(255) NOT NULL
);

CREATE TABLE INSCRIPCION (
    ID NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    DNI_PARTICIPANTE VARCHAR2(8) NOT NULL,
    EVENTO_ID VARCHAR(20) NOT NULL,
    FOREIGN KEY (DNI_PARTICIPANTE) REFERENCES PARTICIPANTE(DNI),
    UNIQUE (DNI_PARTICIPANTE)
);

-- Datos iniciales
INSERT INTO PARTICIPANTE (DNI, NOMBRE, EMAIL) VALUES ('45168670', 'Ana Torres', 'ana@example.com');
INSERT INTO PARTICIPANTE (DNI, NOMBRE, EMAIL) VALUES ('44467619', 'Luis Pérez', 'luis@example.com');
INSERT INTO EVENTO (CODIGO_EVENTO, NOMBRE, DESCRIPCION, FECHA_HORA, UBICACION, CAPACIDAD_MAXIMA)
VALUES ('EVT202505141', 'Conferencia Tech', 'Evento tecnológico', TO_TIMESTAMP('2025-06-20 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Lima', 100);

COMMIT;
