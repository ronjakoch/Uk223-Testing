drop table users;
create table users
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY
    CONSTRAINT users_pkey
    PRIMARY KEY,
    password varchar(255)NOT NULL,
    username varchar(255)NOT NULL,
);


drop table auction;
create table auction
(
    id      integer generated by default as identity
        constraint auction_pkey
            primary key,
    auctionName    varchar(255)     not null,
    price   double precision not null,
);
