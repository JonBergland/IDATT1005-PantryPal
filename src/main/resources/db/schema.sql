drop table if exists recipe_grocery;
drop table if exists recipe_favorite;
drop table if exists shopping_list_grocery;
drop table if exists pantry_shelf_grocery;
drop table if exists step;
drop table if exists recipe;
drop table if exists grocery;
drop table if exists pantry_shelf;

CREATE TABLE grocery
(
    name VARCHAR(255) PRIMARY KEY,
    unit VARCHAR(255) NOT NULL
);

CREATE TABLE recipe
(
    id          INTEGER PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    image       VARCHAR(255)
);

CREATE TABLE step
(
    id          INTEGER PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    recipe_id   INT          NOT NULL,
    FOREIGN KEY (recipe_id) REFERENCES recipe (id)
);

CREATE TABLE recipe_grocery
(
    recipe_id  INT NOT NULL,
    grocery_name INT NOT NULL,
    quantity   INT NOT NULL,
    FOREIGN KEY (recipe_id) REFERENCES recipe (id),
    FOREIGN KEY (grocery_name) REFERENCES grocery (name),
    PRIMARY KEY (recipe_id, grocery_name)
);

CREATE TABLE recipe_favorite
(
    recipe_id INT          NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (recipe_id) REFERENCES recipe (id),
    PRIMARY KEY (recipe_id, user_name)
);


CREATE TABLE shopping_list_grocery
(
    grocery_name INT          NOT NULL,
    user_name  VARCHAR(255) NOT NULL,
    quantity   INT          NOT NULL,
    is_bought   BOOLEAN      NOT NULL,
    shelf_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (grocery_name) REFERENCES grocery (name),
    PRIMARY KEY (grocery_name, user_name)
);

CREATE TABLE pantry_shelf
(
    id        INTEGER PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    name      VARCHAR(255) NOT NULL
);

CREATE TABLE pantry_shelf_grocery
(
    pantry_shelf_id INT NOT NULL,
    grocery_name      INT NOT NULL,
    quantity        INT NOT NULL,
    FOREIGN KEY (pantry_shelf_id) REFERENCES pantry_shelf (id),
    FOREIGN KEY (grocery_name) REFERENCES grocery (name),
    PRIMARY KEY (pantry_shelf_id, grocery_name)
);