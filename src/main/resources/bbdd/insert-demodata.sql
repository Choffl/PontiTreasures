INSERT INTO pts.pt_usuario(id, email, password, username, tipo, version) VALUES (2, 'gestor1@gmail.com', 'gestor1', 'gestor1', 'GESTOR', 0);
INSERT INTO pts.pt_usuario(id, email, password, username, tipo, version) VALUES (3, 'gestor2@gmail.com', 'gestor2', 'gestor2', 'GESTOR', 0);
INSERT INTO pts.pt_usuario(id, email, password, username, tipo, version) VALUES (4, 'gestor3@gmail.com', 'gestor3', 'gestor3', 'GESTOR', 0);
INSERT INTO pts.pt_usuario(id, email, password, username, tipo, version) VALUES (5, 'gestor4@gmail.com', 'gestor4', 'gestor4', 'GESTOR', 0);
INSERT INTO pts.pt_usuario(id, email, password, username, tipo, version) VALUES (6, 'gestor5@gmail.com', 'gestor5', 'gestor5', 'GESTOR', 0);

INSERT INTO pts.pt_pagina(id, descripcion, nombre, pagina_html, version) VALUES (1, 'Descripcion 1', 'Pagina jquery 1', null, 0);
INSERT INTO pts.pt_pagina(id, descripcion, nombre, pagina_html, version) VALUES (2, 'Descripcion 2', 'Pagina jquery 2', null, 0);
INSERT INTO pts.pt_pagina(id, descripcion, nombre, pagina_html, version) VALUES (3, 'Descripcion 3', 'Pagina jquery 3', null, 0);
INSERT INTO pts.pt_pagina(id, descripcion, nombre, pagina_html, version) VALUES (4, 'Descripcion 4', 'Pagina jquery 4', null, 0);
INSERT INTO pts.pt_pagina(id, descripcion, nombre, pagina_html, version) VALUES (5, 'Descripcion 5', 'Pagina jquery 5', null, 0);
INSERT INTO pts.pt_pagina(id, descripcion, nombre, pagina_html, version) VALUES (6, 'Descripcion 6', 'Pagina jquery 6', null, 0);

INSERT INTO pts.pt_etiqueta(id, codigo, descripcion, latitud, longitud, version) VALUES (1, 'codigo 1', 'descripcion 1', 40.40951, -3.705103, 0);
INSERT INTO pts.pt_etiqueta(id, codigo, descripcion, latitud, longitud, version) VALUES (2, 'codigo 2', 'descripcion 2', 40.40951, -3.705103, 0);
INSERT INTO pts.pt_etiqueta(id, codigo, descripcion, latitud, longitud, version) VALUES (3, 'codigo 3', 'descripcion 3', 40.40951, -3.705103, 0);


INSERT INTO pts.pt_circuito(id, nombre, version) VALUES (1, 'Circuito 1', 0);
INSERT INTO pts.pt_circuito(id, nombre, version) VALUES (2, 'Cirucito 2', 0);


INSERT INTO pts.pt_cir_etq(circ_id, etiq_id) VALUES (1, 1);
INSERT INTO pts.pt_cir_etq(circ_id, etiq_id) VALUES (1, 2);
INSERT INTO pts.pt_cir_etq(circ_id, etiq_id) VALUES (1, 3);

INSERT INTO pts.pt_cir_etq(circ_id, etiq_id) VALUES (2, 1);
INSERT INTO pts.pt_cir_etq(circ_id, etiq_id) VALUES (2, 2);
INSERT INTO pts.pt_cir_etq(circ_id, etiq_id) VALUES (2, 3);



INSERT INTO pts.pt_caza(id, circuito, nombre, fecha_fin, fecha_inicio, gestor)  VALUES (1, 1, 'Caza 1', '2011-05-16 15:36:38', '2011-06-16 15:36:38', null);
INSERT INTO pts.pt_caza(id, circuito, nombre, fecha_fin, fecha_inicio, gestor)  VALUES (2, 2, 'Caza 2', '2011-05-16 15:36:38', '2011-06-16 15:36:38', null);
