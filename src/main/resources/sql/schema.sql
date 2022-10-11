drop table if exists CartItem cascade;
drop table if exists FavoriteItem cascade;
drop table if exists MagazineItem cascade;
drop table if exists OrderItem cascade;
drop table if exists Question cascade;
drop table if exists Image cascade;
drop table if exists Review cascade;
drop table if exists Product cascade;
drop table if exists Sort_men cascade;
drop table if exists Sort_women cascade;
--drop table if exists Sort cascade;
--drop table if exists SubCategory cascade ;
--drop table if exists TopCategory cascade ;
--drop table if exists Categorys cascade ;




create table Product
(
    product_id      bigint  not null auto_increment,
    createdAt       date,
    modifiedAt      date,
    brand           varchar(255),
    brandImg        varchar(2000),
    brandKo         varchar(255),
    tags            varchar(255),
    detailList      varchar(2000),
    detailThumbList varchar(2000),
    price           integer not null,
    productName     varchar(255),
    productOption   varchar(255),
    sale            integer,
    stock           integer,
    summary         varchar(255),
    thumbnail       varchar(2000),
    primary key (product_id)
) engine = InnoDB;

create table CartItem
(
    cart_item_id bigint  not null auto_increment,
    createdAt    date,
    modifiedAt   date,
    count        integer not null,
    cart_cart_id bigint,
    product_id   bigint,
    primary key (cart_item_id)
) engine = InnoDB;

create table FavoriteItem
(
    favorite_item_id     bigint not null auto_increment,
    createdAt            date,
    modifiedAt           date,
    favorite_favorite_id bigint,
    product_id           bigint,
    primary key (favorite_item_id)
) engine = InnoDB;

create table MagazineItem
(
    magazine_item_id bigint not null auto_increment,
    createdAt        date,
    modifiedAt       date,
    magazine_id      bigint,
    product_id       bigint,
    primary key (magazine_item_id)
) engine = InnoDB;

create table OrderItem
(
    order_item_id  bigint  not null auto_increment,
    createdAt      date,
    modifiedAt     date,
    orderCount     integer not null,
    orderPrice     integer not null,
    order_order_id bigint,
    product_id     bigint,
    primary key (order_item_id)
) engine = InnoDB;

create table Question
(
    question_id  bigint  not null auto_increment,
    answerStatus varchar(255),
    content      longtext,
    createdAt    datetime,
    password     integer not null,
    privateYn    varchar(255),
    title         varchar(255),
    answer_id    bigint,
    member_id    bigint,
    product_id   bigint,
    primary key (question_id)
) engine = InnoDB;

create table Review
(
    review_id   bigint not null auto_increment,
    content     longtext,
    createdDate datetime,
    title       varchar(255),
    member_id   bigint,
    product_id  bigint,
    primary key (review_id)
) engine = InnoDB;

create table Image (
       id bigint not null auto_increment,
        filePath varchar(255) not null,
        fileSize bigint,
        origFileName varchar(255) not null,
        review_id bigint,
        primary key (id)
    ) engine=InnoDB;

--create table Categorys (
--                          category_id bigint not null auto_increment,
--                          primary key (category_id)
--) engine=InnoDB;
--
--create table TopCategory (
--                             topCategory_id bigint not null auto_increment,
--                             topCategory_name varchar(255),
--                             category_id bigint,
--                             primary key (topCategory_id)
--) engine=InnoDB;
--
--create table SubCategory (
--                             subCategory_id bigint not null auto_increment,
--                             subCategory_imgSrc varchar(255),
--                             subCategory_name varchar(255),
--                             topCategory_id bigint,
--                             primary key (subCategory_id)
--) engine=InnoDB;
--
--create table Sort (
--                      sort_id bigint not null auto_increment,
--                      sort_imgSrc varchar(255),
--                      sort_name varchar(255),
--                      subCategory_id bigint,
--                      primary key (sort_id)
--) engine=InnoDB;
--
--create table Sort_men (
--                          Sort_sort_id bigint not null,
--                          men_category varchar(255)
--) engine=InnoDB;
--
--create table Sort_women (
--                            Sort_sort_id bigint not null,
--                            women_category varchar(255)
--) engine=InnoDB;
