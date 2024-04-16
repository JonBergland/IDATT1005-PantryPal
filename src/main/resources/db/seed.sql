-- Inserting data into the 'recipe' table
INSERT INTO recipe (name, description, image) VALUES ('Spaghetti Bolognese', 'A classic Italian dish', 'spaghetti.jpg');
INSERT INTO recipe (name, description, image) VALUES ('Chicken Curry', 'A spicy Indian dish', 'chicken_curry.jpg');

-- Inserting data into the 'step' table
INSERT INTO step (description, recipe_id) VALUES ('Boil the spaghetti', 1);
INSERT INTO step (description, recipe_id) VALUES ('Cook the Bolognese sauce', 1);
INSERT INTO step (description, recipe_id) VALUES ('Cook the chicken', 2);
INSERT INTO step (description, recipe_id) VALUES ('Prepare the curry sauce', 2);

-- Inserting data into the 'grocery' table
INSERT INTO grocery (name, unit) VALUES ('Spaghetti', 'g');
INSERT INTO grocery (name, unit) VALUES ('Minced Beef', 'g');
INSERT INTO grocery (name, unit) VALUES ('Chicken', 'g');
INSERT INTO grocery (name, unit) VALUES ('Curry Powder', 'g');

-- Inserting data into the 'recipe_grocery' table
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity) VALUES (1, 'Spaghetti', 500);
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity) VALUES (1, 'Minced Beef', 500);
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity) VALUES (2, 'Chicken', 500);
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity) VALUES (2, 'Curry Powder', 50);

-- Inserting data into the 'recipe_favorite' table
INSERT INTO recipe_favorite (recipe_id, user_name) VALUES (1, 'Alice');
INSERT INTO recipe_favorite (recipe_id, user_name) VALUES (2, 'Bob');

-- Inserting data into the 'shopping_list_grocery' table
INSERT INTO shopping_list_grocery (grocery_name, user_name, quantity, is_bought, shelf_name) VALUES ('Spaghetti', 'Alice', 500, 0, 'Main Shelf');
INSERT INTO shopping_list_grocery (grocery_name, user_name, quantity, is_bought, shelf_name) VALUES ('Minced Beef', 'Alice', 500, 0, 'Main Shelf');
INSERT INTO shopping_list_grocery (grocery_name, user_name, quantity, is_bought, shelf_name) VALUES ('Chicken', 'Bob', 500, 0, 'Main Shelf');
INSERT INTO shopping_list_grocery (grocery_name, user_name, quantity, is_bought, shelf_name) VALUES ('Curry Powder', 'Bob', 50, 0, 'Main Shelf');

-- Inserting data into the 'pantry_shelf' table
INSERT INTO pantry_shelf (user_name, name) VALUES ('Alice', 'Main Shelf');
INSERT INTO pantry_shelf (user_name, name) VALUES ('Bob', 'Main Shelf');

-- Inserting data into the 'pantry_shelf_grocery' table
INSERT INTO pantry_shelf_grocery (pantry_shelf_id, grocery_name, quantity) VALUES (1, 'Spaghetti', 500);
INSERT INTO pantry_shelf_grocery (pantry_shelf_id, grocery_name, quantity) VALUES (1, 'Minced Beef', 500);
INSERT INTO pantry_shelf_grocery (pantry_shelf_id, grocery_name, quantity) VALUES (2, 'Chicken', 500);
INSERT INTO pantry_shelf_grocery (pantry_shelf_id, grocery_name, quantity) VALUES (2, 'Curry Powder', 50);