drop schema if exists store;
create schema store;
use store;

create table store.customer(
	id int not null auto_increment,
    firstName varchar(45) not null,
    lastName varchar(45) not null,
    email varchar(60) not null,
    phone char(12) not null,
    address varchar(100) not null,   
    
    primary key(id)
);

create table store.product(
	id int not null auto_increment,
    productName varchar(80) not null,
    price double not null,
    quantity int not null,
    
    primary key(id)
);

create table store.request(
	id int not null auto_increment,
    idCustomer int not null,
    idProduct int not null,
    orderedQuantity int not null,
    
    primary key(id)
);

create table store.placedOrder(
	id int not null auto_increment,
    total double not null,
    
    primary key(id)
);

alter table request
add constraint customer_request
foreign key (idCustomer)
references customer (id);

alter table request
add constraint product_request
foreign key (idProduct)
references product (id);


insert into customer(firstName, lastName, email, phone, address) values ("Andrei", "Amariei", "andreiamariei@gmail.com", "0745123456", "Cluj-Napoca");
insert into customer(firstName, lastName, email, phone, address) values ("Bianca", "Blaga", "biancablaga@gmail.com", "0752163458", "Alba Iulia");
insert into customer(firstName, lastName, email, phone, address) values ("Cristian", "Coman", "cristiancoman@gmail.com", "0788425681", "Sebes");
insert into customer(firstName, lastName, email, phone, address) values ("Denis", "Dumitru", "denisdumitru@gmail.com", "0715426895", "Turda");
insert into customer(firstName, lastName, email, phone, address) values ("Emilia", "Ene", "emiliaene@gmail.com", "0732659412", "Hunedoara");
insert into customer(firstName, lastName, email, phone, address) values ("Florin", "Filimon", "florinfilimon@gmail.com", "0754125632", "Iasi");
insert into customer(firstName, lastName, email, phone, address) values ("Georgiana", "Gherman", "gerogianagherman@gmail.com", "0719526456", "Bucuresti");
insert into customer(firstName, lastName, email, phone, address) values ("Henry", "Habuc", "henryhabuc@gmail.com", "0748125469", "Constanta");
insert into customer(firstName, lastName, email, phone, address) values ("Ioana", "Ispas", "ioanaispas@gmail.com", "0735641822", "Alba Iulia");
insert into customer(firstName, lastName, email, phone, address) values ("Jerry", "Jackson", "jerryjackson@gmail.com", "0721321144", "Blaj");
insert into customer(firstName, lastName, email, phone, address) values ("Kathrine", "Konta", "katherinekonta@gmail.com", "0798765432", "Cluj-Napoca");
insert into customer(firstName, lastName, email, phone, address) values ("Lucian", "Londroman", "lucianlondroman@gmail.com", "0716482333", "Teius");
insert into customer(firstName, lastName, email, phone, address) values ("Maria", "Mann", "mariamann@gmail.com", "0712245811", "Sebes");
insert into customer(firstName, lastName, email, phone, address) values ("Nicolae", "Nicoara", "nicolaenicoara@gmail.com", "0794632152", "Sibiu");
insert into customer(firstName, lastName, email, phone, address) values ("Oana", "Olanescu", "oanaolanescu@gmail.com", "0732132456", "Medias");

insert into product(productName, price, quantity) values ("caiet de matematica", "4.5", "30");
insert into product(productName, price, quantity) values ("pix", "1.5", "40");
insert into product(productName, price, quantity) values ("stilou", "39", "10");
insert into product(productName, price, quantity) values ("creion mecanic", "21", "15");
insert into product(productName, price, quantity) values ("bloc de desen", "5.9", "23");
insert into product(productName, price, quantity) values ("rezerva stilou", "0.1", "146");
insert into product(productName, price, quantity) values ("agenda 2021", "47", "7");
insert into product(productName, price, quantity) values ("calendar 2021", "33", "11");
insert into product(productName, price, quantity) values ("semn de carte", "8.6", "26");
insert into product(productName, price, quantity) values ("set creioane colorate", "12", "19");
insert into product(productName, price, quantity) values ("highligther", "3.4", "22");
insert into product(productName, price, quantity) values ("compas", "14", "10");
insert into product(productName, price, quantity) values ("raportor", "2.7", "15");
insert into product(productName, price, quantity) values ("radiera", "5.5", "21");
insert into product(productName, price, quantity) values ("capsator", "17.4", "14");


insert into request(idCustomer, idProduct, orderedQuantity) values (1, 2, 4);


drop trigger if exists update_stock;

delimiter //
create trigger update_stock after insert on request
for each row
begin
	declare i int;
    declare q int;
    select idProduct from request where request.id = new.id into i;
    select orderedQuantity from request where request.id = new.id into q;
    update product set product.quantity = product.quantity - q where product.id = i;
end
//

delimiter ;
drop trigger if exists update_orders_customer;

delimiter //
create trigger update_orders_customer before delete on customer
for each row
begin
	delete from request where request.idCustomer = old.id;
end
//

delimiter ;
drop trigger if exists update_orders_product;

delimiter //
create trigger update_orders_product before delete on product
for each row
begin
	delete from request where request.idProduct = old.id;
end
//
