-- INSERT INTO public.users(
--     id, active_level, daily_kcal, height, username, weight)
-- VALUES (7, 7, 4000, 1.80, 'mvwojcik', 73.5),
-- VALUES (8, 2, 3000, 1.72, 'admin', 80.5),
-- VALUES (9, 4, 2500, 1.60, 'ola', 52.5);

INSERT INTO allergens (id, name)
VALUES (0, 'gluten')
     , (1, 'mleko')
     , (2, 'laktoza')
     , (3, 'kazeina')
     , (4, 'ryby')
     , (5, 'jaja')
     , (6, 'soja')
     , (7, 'orzechy')
     , (8, 'owoce')
     , (9, 'skorupiaki')
     , (10, 'wołowina')
     , (11, 'baranina')
     , (12, 'wieprzowina')
     , (13, 'mięso');


INSERT INTO public.ingredients(id, carbohydrates, fat, kcal, proteins, name, unit)
VALUES (1, 0.0, 0.9, 108, 23.4, 'tuńczyk', 'G100'),
       (2, 0.0, 0.8, 116, 25.5, 'tuńczyk z puszki z wodą', 'G100'),
       (3, 0.0, 8.2, 198, 29.1, 'tuńczyk z puszki z olejem', 'G100'),
       (4, 12.1, 25.1, 415, 17.1, 'łosoś', 'G100'),
       (5, 12.1, 25.1, 415, 17.1, 'łosoś wędzony', 'G100'),
       (6, 5.6, 0.1, 170, 10.5, 'mleko 3%', 'ML100'),
       (7, 5.6, 0.1, 170, 10.5, 'mleko 6%', 'ML100'),
       (8, 5.6, 0.1, 170, 10.5, 'mleko 3% bez laktozy', 'ML100'),
       (9, 0.6, 11.6, 262, 8.9, 'mleko kokosowe', 'ML100'),
       (10, 5.6, 0.1, 170, 10.5, 'jogurt naturalny', 'ML100'),
       (11, 5.6, 0.1, 170, 10.5, 'jogurt naturalny bez laktozy', 'ML100'),
       (12, 0.5, 1.5, 345, 24.1, 'wołowina', 'G100'),
       (13, 0.5, 1.5, 345, 24.1, 'wieprzowina', 'G100'),
       (14, 0.5, 1.5, 345, 24.1, 'kurczak', 'G100'),
       (15, 60.6, 11.6, 361, 8.9, 'migdał', 'G100'),
       (16, 60.6, 11.6, 361, 8.9, 'pistacja', 'G100'),
       (17, 60.6, 11.6, 361, 8.9, 'orzech włoski', 'G100'),
       (18, 60.6, 11.6, 361, 8.9, 'daktyl', 'G100'),
       (19, 60.6, 11.6, 361, 8.9, 'orzech laskowy', 'G100'),
       (20, 60.6, 11.6, 361, 8.9, 'masło orzechowe', 'G100'),
       (21, 21.6, 11.6, 361, 8.9, 'pomidor', 'G100'),
       (22, 21.6, 11.6, 361, 8.9, 'brokuł', 'G100'),
       (23, 21.6, 11.6, 361, 8.9, 'ogórek', 'G100'),
       (24, 21.6, 11.6, 361, 8.9, 'papryka', 'G100'),
       (25, 21.6, 11.6, 361, 8.9, 'kalafior', 'G100'),
       (26, 21.6, 11.6, 361, 8.9, 'cebula', 'G100'),
       (27, 21.6, 11.6, 361, 8.9, 'czosnek', 'G100'),
       (28, 21.6, 11.6, 361, 8.9, 'fasola', 'G100'),
       (29, 40.6, 11.6, 361, 8.9, 'ziemniak', 'G100'),
       (30, 40.6, 11.6, 361, 8.9, 'soczewica', 'G100'),
       (31, 40.6, 11.6, 361, 8.9, 'ryż', 'G100'),
       (32, 40.6, 11.6, 361, 8.9, 'ciecierzyca', 'G100'),
       (33, 40.6, 11.6, 361, 8.9, 'pieprz czarny', 'G100'),
       (34, 40.6, 11.6, 361, 8.9, 'kurkuma', 'G100'),
       (35, 40.6, 11.6, 361, 8.9, 'papryka mielona', 'G100'),
       (36, 40.6, 11.6, 361, 8.9, 'pieprz cayenne', 'G100'),
       (37, 40.6, 11.6, 361, 8.9, 'bazylia', 'G100'),
       (38, 40.6, 11.6, 361, 8.9, 'oregano', 'G100'),
       (39, 1.6, 0.6, 5, 0.5, 'sól', 'G100');


INSERT INTO public.ingredient_allergen(ingredient_id, allergen_id)
VALUES (1, 3),
       (2, 1),
       (2, 2),
       (3, 4),
       (4, 3);


INSERT INTO public.recipes(id, description, image_url, name, rating,
                           short_description)
VALUES (1, 'description', 'https://google.pl', 'tuna with potatoes', 2.6, 'short Description'),
       (2, 'description', 'https://google.pl', 'Salomon in coconut', 3.1, 'short Description'),
       (3, 'description', 'https://google.pl', 'Tomatoes with potatoes', 3.6, 'short Description'),
       (4, 'description', 'https://google.pl', 'Almond with meat', 5.0, 'short Description')
;


INSERT INTO public.recipe_ingredient(amount, recipe_id, ingredient_id)
VALUES (3.0, 1, 1),
       (2.0, 1, 7),
       (0.01, 1, 9),
       (2.0, 2, 4),
       (1.0, 2, 8),
       (1.0, 3, 6),
       (1.0, 3, 7),
       (1.5, 4, 3),
       (1.0, 4, 5);


INSERT INTO public.vitamin(id, daily_requirement, name, scale, type)
VALUES (1, 0.0008, 'Witamina A', 'G1', 'VITAMIN'),
       (2, 0.0011, 'Witamina B1 Tiamina', 'G1', 'VITAMIN'),
       (3, 0.0014, 'Witamina B2 Rybolawina', 'G1', 'VITAMIN'),
       (4, 0.016, 'Witamina B3 Niacyna', 'G1', 'VITAMIN'),
       (5, 0.016, 'Witamina B4 Cholina', 'G1', 'VITAMIN'),
       (6, 0.006, 'Witamina B5 Kwas pantotenowy', 'G1', 'VITAMIN'),
       (7, 0.0014, 'Witamina B6 Pirydoksyna', 'G1', 'VITAMIN'),
       (8, 0.00005, 'Witamina B7 Biotyna', 'G1', 'VITAMIN'),
       (9, 0.0000025, 'Witamina B12', 'G1', 'VITAMIN'),
       (10, 0.08, 'Witamina C', 'G1', 'VITAMIN'),
       (11, 0.000005, 'Witamina D', 'G1', 'VITAMIN'),
       (12, 0.012, 'Witamina E', 'G1', 'VITAMIN'),
       (13, 0.000075, 'Witamina K', 'G1', 'VITAMIN'),
       (14, 0.375, 'Magnez', 'G1', 'VITAMIN'),
       (15, 0.8, 'Wapń', 'G1', 'MINERAL'),
       (16, 0.24, 'Sód', 'G1', 'MINERAL'),
       (17, 2.0, 'Potas', 'G1', 'MINERAL'),
       (18, 0.7, 'Fosfor', 'G1', 'MINERAL'),
       (19, 0.014, 'Żelazo', 'G1', 'MINERAL'),
       (20, 0.01, 'Cynk', 'G1', 'MINERAL'),
       (21, 0.001, 'Miedź', 'G1', 'MINERAL'),
       (22, 0.002, 'Mangan', 'G1', 'MINERAL'),
       (23, 0.000055, 'Selen', 'G1', 'MINERAL'),
       (24, 0.00015, 'Omega 3', 'G1', 'MINERAL'),
       (25, 0.00015, 'Omega 6', 'G1', 'MINERAL'),
       (26, 0.00015, 'Omega 9', 'G1', 'MINERAL');


INSERT INTO public.vitamin_in_ingredient(ingredient_id, vitamin_id, amount)
VALUES (1, 2, 0.4),
       (1, 4, 9.8),
       (1, 5, 0.9),
       (1, 6, 65),
       (1, 9, 0.5),
       (1, 18, 16),
       (1, 23, 243),
       (2, 5, 9),
       (2, 6, 15),
       (2, 9, 0.7),
       (2, 18, 1),
       (2, 23, 23);

INSERT INTO public.dietplans(id, name, description)
VALUES (1, 'Pierwszy plan żywieniowy', 'Check it out'),
       (2, 'Plan bez glutenowy', 'Check this out');

INSERT INTO public.diet_plan_recipe(amount, meal_time, recipe_id, diet_plan_id)
VALUES (1.0, 'BREAKFAST', 2, 1),
       (0.2, 'BREAKFAST', 1, 1),
       (1.0, 'DINNER', 3, 1),
       (1.0, 'BREAKFAST', 4, 2),
       (1.0, 'SUPPER', 2, 2);

INSERT INTO public.diet_plan_ingredient(amount, meal_time, ingredient_id, diet_plan_id)
VALUES (0.1, 'BREAKFAST', 5, 1),
       (2.1, 'DINNER', 2, 1),
       (1.5, 'DINNER', 6, 2);


-- INSERT INTO public.diet_plan_user(
--     fk_dietplan, fk_user)
-- VALUES (1, 7),
-- VALUES (2, 7),
-- VALUES (1, 8),
