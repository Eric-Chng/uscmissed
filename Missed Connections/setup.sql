DROP DATABASE IF EXISTS sal_eats;
CREATE DATABASE sal_eats;

DROP TABLE IF EXISTS sal_eats.Restaurant;
CREATE TABLE sal_eats.Restaurant (
    restaurant_id varchar(255),
    restaurant_name varchar(255),
    image_url varchar(255),
    address varchar(255),
    phone_no varchar(255),
    estimated_price varchar(255),
    yelp_url varchar(255),
    category_name varchar(255),
    review_count varchar(255),
    rating varchar(255),
    PRIMARY KEY(restaurant_id)
);


DROP TABLE IF EXISTS sal_eats.Category;
CREATE TABLE sal_eats.Category (
    category_id varchar(255),
    category_name varchar(255),
    restaurant_id varchar(255)
);
