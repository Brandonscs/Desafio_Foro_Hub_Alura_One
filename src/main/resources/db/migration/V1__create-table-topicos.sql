CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL UNIQUE,
    mensaje TEXT NOT NULL,
    fecha DATETIME NOT NULL,
    status TINYINT NOT NULL,

    PRIMARY KEY(id)
);