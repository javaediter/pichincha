create table PERSONA(
    id int not null primary key,
    nombre varchar(255) not null,
    genero varchar(25) not null,
    edad int not null,
    identificacion varchar(100) not null,
    direccion varchar(255) not null,
    telefono varchar(50) not null
);

create table CLIENTE(
    cliente_id int not null primary key,
    contrasenia varchar(100) not null,
    estado bit not null default 1
);

create table CUENTA(
    cuenta_id int not null auto_increment primary key,
    numero varchar(150) not null,
    tipo varchar(100) not null,
    saldo_inicial decimal(10,2) not null default 0,
    estado bit not null default 1,
    cliente_id int,
    foreign key (cliente_id) references cliente(cliente_id) on delete cascade
);

create table MOVIMIENTOS(
    movimiento_id int not null auto_increment primary key,
    fecha date not null,
    tipo varchar(30) not null,
    valor decimal(10, 2) not null,
    saldo decimal(10, 2) not null,
    cuenta_id int,
    foreign key (cuenta_id) references cuenta(cuenta_id) on delete cascade
);