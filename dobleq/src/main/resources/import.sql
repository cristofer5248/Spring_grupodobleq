INSERT INTO users (apellidos,nombre,username, password, enabled,telefono) VALUES('Carlos','Hernandez','andres', '$2a$10$pXSjZhKajePgUvQZplTkOuA6n4ee/wHaOF/UJWornHmRxhN3D0Cd6', 1,71208113);
INSERT INTO users (apellidos,nombre,username, password, enabled,telefono) VALUES('Vanessa','Ramirez','admin', '$2a$10$pLiT5n4R/wOZ8SyKgNvvOeRuMd07/m9QzguNQFJPfphWUB0ktQ2zS', 1,83208113);
INSERT INTO users (apellidos,nombre,username, password, enabled,telefono) VALUES('Juan','Carlos','JUAK', '$2a$10$fpZ3pU63UnhsJcAPopsz1.hBzYFe5ptoCXcP4UXkqwv8MBwdHV6x6', 1,7220853);
INSERT INTO users (apellidos,nombre,username, password, enabled,telefono) VALUES('Marlon','Perez','MP123', '$2a$10$IBncau/R54WrXROHE/QEnObji7HOoH5TjrLQSzuJnoVsVlSF73stO', 1,7283113);

INSERT INTO roles (user_id, authority) VALUES(1, 'ROLE_USER');
INSERT INTO roles (user_id, authority) VALUES(2, 'ROLE_ADMIN');
INSERT INTO roles (user_id, authority) VALUES(3, 'ROLE_ADMIN');
INSERT INTO roles (user_id, authority) VALUES(4, 'ROLE_ADMIN');
INSERT INTO roles (user_id, authority) VALUES(4, 'ROLE_USER');

INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('John', 'Doe', 'john.doe@gmail.com', '2017-08-02', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('James', 'Gosling', 'james.gosling@gmail.com', '2017-08-010', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('John', 'Roe', 'john.roe@gmail.com', '2017-08-13', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Janie', 'Doe', 'janie.doe@gmail.com', '2017-08-16', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Phillip', 'Webb', 'phillip.webb@gmail.com', '2017-08-17', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', '2017-08-18', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Sam', 'Brannen', 'sam.brannen@gmail.com', '2017-08-19', '');  
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20', ''); 
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Janie', 'Roe', 'janie.roe@gmail.com', '2017-08-21', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('John', 'Smith', 'john.smith@gmail.com', '2017-08-22', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Joe', 'Bloggs', 'joe.bloggs@gmail.com', '2017-08-23', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('John', 'Stiles', 'john.stiles@gmail.com', '2017-08-24', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Richard', 'Roe', 'stiles.roe@gmail.com', '2017-08-25', '');

insert into condiciones values (null,"bueno");
insert into condiciones values (null,"malo");
insert into condiciones values (null,"decadente");

insert into vehiculos values (null,"Ultimo modelo","BMW","B123","12333213");
insert into vehiculos values (null,"BMW","BMW","B123","12333213");

insert into nombre_taller values(null,"Taller Express");
insert into nombre_taller values(null,"Taller de pintura");
insert into nombre_taller values(null,"Taller de Mecanica General");

insert into servicios values (1,"Indefinido",0);
insert into servicios values (null,"enderezado",4.50);
insert into servicios values (null,"patadon",2.50);
insert into servicios values (null,"limpieza",6.40);
insert into servicios values (null,"fajas",8.80);
insert into servicios values (null,"pintura",4.30);
insert into servicios values (null,"producto 1",11.50);
insert into servicios values(null,"Aceite y filtro del motor",100.30);
insert into servicios values(null,"Bandas y correas",45.00);
insert into servicios values(null,"Batería",35.00);
insert into servicios values(null,"Escape",63.23);
insert into servicios values(null,"Líquido de la dirección hidráulica",9.48);
insert into servicios values(null,"Líquido de la transmision automatica",145.60);

insert into estados values(1,"Cliente registrado");
insert into estados values(2,"Cliente nuevamente asignado");
insert into estados values(null,"Aun no aprobado");
insert into estados values(null,"Aprobado");
insert into estados values(null,"Fase 1");
insert into estados values(null,"Fase 2");
insert into estados values(null,"Fase 3");



