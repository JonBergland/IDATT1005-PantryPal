-- Insert groceries with units
INSERT INTO grocery (name, unit)
VALUES ('Spaghetti', 'grams'),
       ('Bacon', 'grams'),
       ('Eggs', 'units'),
       ('Parmesan cheese', 'grams'),
       ('Black pepper', 'grams'),
       ('Chicken breast', 'grams'),
       ('Soy sauce', 'milliliters'),
       ('Ginger', 'grams'),
       ('Garlic', 'cloves'),
       ('Bell peppers', 'units'),
       ('Broccoli', 'grams'),
       ('Snap peas', 'grams'),
       ('Rice', 'grams'),
       ('Lasagna noodles', 'grams'),
       ('Onions', 'units'),
       ('Mushrooms', 'grams'),
       ('Tomato sauce', 'milliliters'),
       ('Ricotta cheese', 'grams'),
       ('Mozzarella cheese', 'grams'),
       ('Ground beef', 'grams'),
       ('Taco seasoning', 'grams'),
       ('Taco shells', 'units'),
       ('Lettuce', 'grams'),
       ('Tomatoes', 'units'),
       ('Cheese', 'grams'),
       ('Salsa', 'milliliters'),
       ('Sour cream', 'grams'),
       ('Arborio rice', 'grams'),
       ('Chicken broth', 'milliliters'),
       ('Salmon fillets', 'units'),
       ('Lemon juice', 'milliliters'),
       ('Olive oil', 'milliliters'),
       ('Dill', 'grams'),
       ('Asparagus', 'grams'),
       ('Zucchini', 'grams'),
       ('Cherry tomatoes', 'grams'),
       ('Pasta', 'grams'),
       ('Basil pesto sauce', 'grams'),
       ('Pine nuts', 'grams'),
       ('Pork shoulder', 'grams'),
       ('BBQ seasoning', 'grams'),
       ('BBQ sauce', 'milliliters'),
       ('Hamburger buns', 'units'),
       ('Coleslaw mix', 'grams');

-- Sample recipes
-- Recipe 1: Spaghetti Carbonara
INSERT INTO recipe (id, name, description, image)
VALUES (1, 'Spaghetti Carbonara', 'Classic Italian pasta dish with bacon and eggs', 'https://images.immediate.co.uk/production/volatile/sites/30/2020/08/recipe-image-legacy-id-1001491_11-2e0fa5c.jpg?quality=90&resize=440,400');
INSERT INTO step (id, description, recipe_id)
VALUES (1, 'Boil spaghetti in salted water until al dente.', 1),
       (2, 'In a pan, fry chopped bacon until crispy.', 1),
       (3, 'Mix cooked spaghetti with beaten eggs, grated Parmesan cheese, and bacon.', 1),
       (4, 'Serve hot with a sprinkle of black pepper.', 1);
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity)
VALUES (1, 'Spaghetti', 1),
       (1, 'Bacon', 200),
       (1, 'Eggs', 3),
       (1, 'Parmesan cheese', 100),
       (1, 'Black pepper', 1);

-- Recipe 2: Chicken Stir-Fry
INSERT INTO recipe (id, name, description, image)
VALUES (2, 'Chicken Stir-Fry', 'Quick and flavorful chicken stir-fry with vegetables', 'https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2021/05/Chicken-Stir-Fry-main-1.jpg');
INSERT INTO step (id, description, recipe_id)
VALUES (5, 'Marinate chicken strips with soy sauce, ginger, and garlic.', 2),
       (6, 'Stir-fry chicken in a hot pan until cooked through.', 2),
       (7, 'Add sliced bell peppers, broccoli florets, and snap peas.', 2),
       (8, 'Cook until vegetables are tender-crisp.', 2),
       (9, 'Serve over steamed rice.', 2);
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity)
VALUES (2, 'Chicken breast', 500),
       (2, 'Soy sauce', 50),
       (2, 'Ginger', 1),
       (2, 'Garlic', 3),
       (2, 'Bell peppers', 2),
       (2, 'Broccoli', 1),
       (2, 'Snap peas', 100),
       (2, 'Rice', 200);

-- Recipe 3: Vegetable Lasagna
INSERT INTO recipe (id, name, description, image)
VALUES (3, 'Vegetable Lasagna', 'Layers of pasta, tomato sauce, and assorted vegetables', 'https://cdn.loveandlemons.com/wp-content/uploads/2023/12/vegetarian-lasagna-scaled.jpg');
INSERT INTO step (id, description, recipe_id)
VALUES (10, 'Cook lasagna noodles until al dente.', 3),
       (11, 'Sauté sliced onions, garlic, and mushrooms until soft.', 3),
       (12, 'Layer lasagna noodles, tomato sauce, sautéed vegetables, and ricotta cheese.', 3),
       (13, 'Repeat layers and top with mozzarella cheese.', 3),
       (14, 'Bake until golden and bubbly.', 3);
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity)
VALUES (3, 'Lasagna noodles', 1),
       (3, 'Onions', 2),
       (3, 'Garlic', 3),
       (3, 'Mushrooms', 200),
       (3, 'Tomato sauce', 500),
       (3, 'Ricotta cheese', 250),
       (3, 'Mozzarella cheese', 200);

-- Recipe 4: Beef Tacos
INSERT INTO recipe (id, name, description, image)
VALUES (4, 'Beef Tacos', 'Classic Mexican dish with seasoned beef and toppings', 'https://www.onceuponachef.com/images/2023/08/Beef-Tacos.jpg');
INSERT INTO step (id, description, recipe_id)
VALUES (15, 'Season ground beef with taco seasoning.', 4),
       (16, 'Cook beef in a skillet until browned.', 4),
       (17, 'Warm taco shells in the oven.', 4),
       (18, 'Fill taco shells with beef, shredded lettuce, diced tomatoes, and shredded cheese.',
        4),
       (19, 'Serve with salsa and sour cream.', 4);
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity)
VALUES (4, 'Ground beef', 500),
       (4, 'Taco seasoning', 1),
       (4, 'Taco shells', 12),
       (4, 'Lettuce', 1),
       (4, 'Tomatoes', 2),
       (4, 'Cheese', 200),
       (4, 'Salsa', 1),
       (4, 'Sour cream', 1);

-- Recipe 5: Mushroom Risotto
INSERT INTO recipe (id, name, description, image)
VALUES (5, 'Mushroom Risotto', 'Creamy rice dish with sautéed mushrooms and Parmesan', 'https://lindseyeatsla.com/wp-content/uploads/2020/12/Lindseyeatsla_Mushroom_Risotto-9.jpg');
INSERT INTO step (id, description, recipe_id)
VALUES (20, 'Sauté chopped onions and garlic in olive oil.', 5),
       (21, 'Add Arborio rice and cook until translucent.', 5),
       (22, 'Gradually add warm chicken broth, stirring constantly until absorbed.', 5),
       (23, 'Stir in sliced mushrooms and Parmesan cheese.', 5),
       (24, 'Cook until creamy and tender.', 5);
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity)
VALUES (5, 'Arborio rice', 300),
       (5, 'Onions', 2),
       (5, 'Garlic', 3),
       (5, 'Chicken broth', 500),
       (5, 'Mushrooms', 200),
       (5, 'Parmesan cheese', 150);

-- Recipe 6: Grilled Salmon with Asparagus
INSERT INTO recipe (id, name, description, image)
VALUES (6, 'Grilled Salmon with Asparagus',
        'Fresh salmon fillets grilled to perfection with tender asparagus', 'https://www.somewhatsimple.com/wp-content/uploads/2020/05/grilled_salmon_asparagus_1.jpg');
INSERT INTO step (id, description, recipe_id)
VALUES (25, 'Marinate salmon fillets in lemon juice, olive oil, and dill.', 6),
       (26, 'Grill salmon until cooked through.', 6),
       (27, 'Grill asparagus spears until tender.', 6),
       (28, 'Serve salmon with grilled asparagus on the side.', 6);
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity)
VALUES (6, 'Salmon fillets', 2),
       (6, 'Lemon juice', 1),
       (6, 'Olive oil', 1),
       (6, 'Dill', 1),
       (6, 'Asparagus', 1);

-- Recipe 7: Pasta Primavera
INSERT INTO recipe (id, name, description, image)
VALUES (7, 'Pasta Primavera', 'Delicious pasta dish with assorted sautéed vegetables', 'https://www.budgetbytes.com/wp-content/uploads/2023/05/Pasta-Primavera-fork.jpg');
INSERT INTO step (id, description, recipe_id)
VALUES (29, 'Cook pasta according to package instructions.', 7),
       (30, 'Sauté assorted vegetables (zucchini, bell peppers, cherry tomatoes) in olive oil.', 7),
       (31, 'Toss cooked pasta with sautéed vegetables and a splash of pasta water.', 7),
       (32, 'Season with salt, pepper, and grated Parmesan cheese.', 7),
       (33, 'Serve hot.', 7);
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity)
VALUES (7, 'Pasta', 300),
       (7, 'Zucchini', 2),
       (7, 'Bell peppers', 2),
       (7, 'Cherry tomatoes', 200),
       (7, 'Olive oil', 1),
       (7, 'Parmesan cheese', 150);

-- Recipe 8: Spinach and Feta Stuffed Chicken Breast
INSERT INTO recipe (id, name, description, image)
VALUES (8, 'Spinach and Feta Stuffed Chicken Breast',
        'Tender chicken breasts stuffed with spinach and feta cheese', 'https://spicysouthernkitchen.com/wp-content/uploads/Bacon-Wrapped-Spinach-Stuffed-Chicken-feature-500x500.jpg');
INSERT INTO step (id, description, recipe_id)
VALUES (34, 'Flatten chicken breasts and season with salt and pepper.', 8),
       (35,
        'Spread a mixture of chopped spinach, feta cheese, and garlic over each chicken breast.',
        8),
       (36, 'Roll up chicken breasts and secure with toothpicks.', 8),
       (37, 'Bake in the oven until chicken is cooked through.', 8),
       (38, 'Remove toothpicks before serving.', 8);
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity)
VALUES (8, 'Chicken breasts', 4),
       (8, 'Spinach', 200),
       (8, 'Feta cheese', 150),
       (8, 'Garlic', 2);

-- Recipe 9: Beef and Broccoli Stir-Fry
INSERT INTO recipe (id, name, description, image)
VALUES (9, 'Beef and Broccoli Stir-Fry', 'Quick and easy stir-fry with tender beef and broccoli',
        'https://www.dinneratthezoo.com/wp-content/uploads/2017/10/beef-and-broccoli-stir-fry-14.jpg');
INSERT INTO step (id, description, recipe_id)
VALUES (39, 'Marinate beef slices in soy sauce, garlic, and ginger.', 9),
       (40, 'Stir-fry beef in a hot pan until browned.', 9),
       (41, 'Add broccoli florets and sliced onions.', 9),
       (42, 'Cook until vegetables are tender.', 9),
       (43, 'Serve over cooked rice.', 9);
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity)
VALUES (9, 'Beef slices', 500),
       (9, 'Soy sauce', 50),
       (9, 'Garlic', 3),
       (9, 'Ginger', 1),
       (9, 'Broccoli', 1),
       (9, 'Onions', 2),
       (9, 'Rice', 200);

-- Recipe 10: Caprese Salad
INSERT INTO recipe (id, name, description, image)
VALUES (10, 'Caprese Salad', 'Fresh and vibrant salad with tomatoes, mozzarella, and basil', 'https://whatsgabycooking.com/wp-content/uploads/2023/06/Dinner-Party-__-Traditional-Caprese-1-1200x800-1.jpg');
INSERT INTO step (id, description, recipe_id)
VALUES (44, 'Slice fresh tomatoes and mozzarella cheese.', 10),
       (45, 'Arrange tomato and mozzarella slices on a plate.', 10),
       (46, 'Drizzle with balsamic glaze and sprinkle with fresh basil leaves.', 10),
       (47, 'Season with salt and pepper.', 10),
       (48, 'Serve as a light appetizer or side dish.', 10);
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity)
VALUES (10, 'Tomatoes', 3),
       (10, 'Mozzarella cheese', 250),
       (10, 'Balsamic glaze', 1),
       (10, 'Fresh basil leaves', 1);

-- Recipe 11: Pesto Pasta
INSERT INTO recipe (id, name, description, image)
VALUES (11, 'Pesto Pasta', 'Simple yet flavorful pasta dish with basil pesto sauce', 'https://www.kitchensanctuary.com/wp-content/uploads/2023/07/Pesto-Pasta-square-FS.jpg');
INSERT INTO step (id, description, recipe_id)
VALUES (49, 'Cook pasta according to package instructions.', 11),
       (50, 'Toss cooked pasta with basil pesto sauce.', 11),
       (51, 'Add halved cherry tomatoes and toasted pine nuts.', 11),
       (52, 'Mix well and serve hot.', 11);
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity)
VALUES (11, 'Pasta', 300),
       (11, 'Basil pesto sauce', 200),
       (11, 'Cherry tomatoes', 200),
       (11, 'Pine nuts', 50);

-- Recipe 12: BBQ Pulled Pork Sandwiches
INSERT INTO recipe (id, name, description, image)
VALUES (12, 'BBQ Pulled Pork Sandwiches',
        'Tender pulled pork smothered in BBQ sauce, served on buns', 'https://www.lecremedelacrumb.com/wp-content/uploads/2018/07/instant-pot-bbq-pulled-pork-103.jpg');
INSERT INTO step (id, description, recipe_id)
VALUES (53, 'Rub pork shoulder with BBQ seasoning.', 12),
       (54, 'Slow cook pork shoulder in a crockpot until tender.', 12),
       (55, 'Shred pork and mix with BBQ sauce.', 12),
       (56, 'Toast hamburger buns and fill with pulled pork.', 12),
       (57, 'Serve with coleslaw on the side.', 12);
INSERT INTO recipe_grocery (recipe_id, grocery_name, quantity)
VALUES (12, 'Pork shoulder', 1000),
       (12, 'BBQ seasoning', 1),
       (12, 'BBQ sauce', 500),
       (12, 'Hamburger buns', 6),
       (12, 'Coleslaw mix', 1);