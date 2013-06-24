- Database: pontitre

-- DROP DATABASE pontitre;

CREATE DATABASE pontitre
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'is_IS.UTF-8'
       LC_CTYPE = 'is_IS.UTF-8'
       CONNECTION LIMIT = -1;

-- Schema: pts

-- DROP SCHEMA pts;

CREATE SCHEMA pts
  AUTHORIZATION pontiadmin;

GRANT ALL ON SCHEMA pts TO pontiadmin;
GRANT ALL ON SCHEMA pts TO public;

-- Table: pts.pt_usuario

-- DROP TABLE pts.pt_usuario;

CREATE TABLE pts.pt_usuario
(
  id bigint NOT NULL,
  email character varying(255),
  password character varying(255),
  tipo character varying(255),
  username character varying(255),
  version integer NOT NULL,
  CONSTRAINT pt_usuario_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pts.pt_usuario
  OWNER TO pontiadmin;
  
-- Table: pts.pt_jugador

-- DROP TABLE pts.pt_jugador;

CREATE TABLE pts.pt_jugador
(
  username character varying(255),
  id bigint NOT NULL,
  CONSTRAINT pt_jugador_pkey PRIMARY KEY (id),
  CONSTRAINT fk_71y3lg22jpwf3lokac0q9uc6i FOREIGN KEY (id)
      REFERENCES pts.pt_usuario (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pts.pt_jugador
  OWNER TO pontiadmin;

-- Table: pts.pt_pagina

-- DROP TABLE pts.pt_pagina;

CREATE TABLE pts.pt_pagina
(
  id bigint NOT NULL,
  version integer NOT NULL,
  CONSTRAINT pt_pagina_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pts.pt_pagina
  OWNER TO pontiadmin;

  
-- Table: pts.pt_etiqueta

-- DROP TABLE pts.pt_etiqueta;

CREATE TABLE pts.pt_etiqueta
(
  id bigint NOT NULL,
  version integer NOT NULL,
  CONSTRAINT pt_etiqueta_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pts.pt_etiqueta
  OWNER TO pontiadmin;

  
  -- Table: pts.pt_circuito

-- DROP TABLE pts.pt_circuito;

CREATE TABLE pts.pt_circuito
(
  id bigint NOT NULL,
  version integer NOT NULL,
  CONSTRAINT pt_circuito_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pts.pt_circuito
  OWNER TO pontiadmin;
  
  
  -- Table: pts.pt_caza

-- DROP TABLE pts.pt_caza;

CREATE TABLE pts.pt_caza
(
  id bigint NOT NULL,
  circuito bytea,
  fecha_fin timestamp without time zone,
  fecha_inicio timestamp without time zone,
  gestor bytea,
  CONSTRAINT pt_caza_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pts.pt_caza
  OWNER TO pontiadmin;
