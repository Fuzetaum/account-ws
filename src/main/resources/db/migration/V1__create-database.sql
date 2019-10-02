create table account (
	id char(32) primary key,
    owner_name char(50) not null,
    currency int unsigned not null,
    balance int not null,
    balance_last_update date not null);
    
create table operation (
	id int primary key auto_increment,
    account_id char(32) not null,
    processing_date datetime not null,
    amount int not null,
    is_withdraw bool not null,
    foreign key (account_id) references account(id));
