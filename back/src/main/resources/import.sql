INSERT INTO roles (name) VALUES('ROLE_ADMIN');
INSERT INTO roles (name) VALUES('ROLE_USER');


INSERT INTO users (idUser,address,city,country,displayname,email,enabled,method,password,phone,picture,token,username,wishlist,date_created_user,date_updated_user) VALUES (null,null,null,null,null,null,1,null,'$2a$10$kznT5fZsI3zxUzw0NXiV/uy./RhXANSLngHCuevludUaRoQ.Icv8W',null,null,null,'admin',null,null,null);
INSERT INTO users (idUser,address,city,country,displayname,email,enabled,method,password,phone,picture,token,username,wishlist,date_created_user,date_updated_user) VALUES (null,null,null,null,null,null,1,null,'$2a$10$lBepSieFcYYE7NhCWtTBIukk/nSD7LgUt4XGuCB8mhkc3q4m6CKyW',null,null,null,'michael',null,null,null);


INSERT INTO users_roles (user_id, role_id) VALUES ('1', '1');
INSERT INTO users_roles (user_id, role_id) VALUES ('1', '2');
INSERT INTO users_roles (user_id, role_id) VALUES ('2', '2');



INSERT INTO products (name, url, price, description, image, gallery, createAt) VALUES('iPhone 8', 'iphone-8', 1200, 'iPhone 8 en buen estado', 'iphone-8.jpg', '["1.jpg","2.jpg","3.jpg"]', NOW());
INSERT INTO products (name, url, price, description, image, gallery, createAt) VALUES('Ventilador ARGB', 'ventilador-argb', 200, 'Darkflash 120 mm RGB Led', 'ventilador-argb.jpg', '["1.jpg","2.jpg","3.jpg"]', NOW());
INSERT INTO products (name, url, price, description, image, gallery, createAt) VALUES('Funda Reversible', 'funda-reversible', 45, 'Funda Reversible Para Laptop 15.6', 'funda-reversible.jpg', '["1.jpg","2.jpg","3.jpg"]', NOW());
INSERT INTO products (name, url, price, description, image, gallery, createAt) VALUES('Kit Inalámbrico', 'kit-inalambrico', 40, 'Kit Inalámbrico de Teclado y Mouse Teros', 'kit-inalambrico.jpg', '["1.jpg","2.jpg","3.jpg"]', NOW());
INSERT INTO products (name, url, price, description, image, gallery, createAt) VALUES('Micrófono Primus', 'microfono-primus', 450, 'Micrófono Primus Gaming Ethos 300P', 'microfono-primus.jpg', '["1.jpg","2.jpg","3.jpg"]', NOW());
