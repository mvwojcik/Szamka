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
     , (10, 'mięso')
     , (11, 'wołowina')
     , (12, 'baranina')
     , (13, 'wieprzowina')
     , (14, 'kurczak')
     , (15, 'warzywa')
;


INSERT INTO public.ingredients(id, carbohydrates, fat, kcal, proteins, name, unit)
VALUES (1, 0.0, 0.9, 108, 23.4, 'tuńczyk', 'G100'),
       (2, 0.0, 0.8, 116, 25.5, 'tuńczyk z puszki z wodą', 'G100'),
       (3, 0.0, 8.2, 198, 29.1, 'tuńczyk z puszki z olejem', 'G100'),
       (4, 12.1, 25.1, 415, 17.1, 'łosoś', 'G100'),
       (5, 12.1, 20.1, 500, 19.1, 'łosoś wędzony', 'G100'),
       (6, 5.6, 3, 170, 8.5, 'mleko 3%', 'ML100'),
       (7, 6.0, 6, 250, 9.5, 'mleko 6%', 'ML100'),
       (8, 5.6, 3, 150, 10.5, 'mleko 3% bez laktozy', 'ML100'),
       (9, 0.6, 11.6, 262, 8.9, 'mleko kokosowe', 'ML100'),
       (10, 5.6, 0.1, 170, 10.5, 'jogurt naturalny', 'ML100'),
       (11, 5.0, 0.1, 150, 10.5, 'jogurt naturalny bez laktozy', 'ML100'),
       (12, 0.9, 8.2, 450, 30.2, 'wołowina', 'G100'),
       (13, 2.1, 10.1, 345, 24.1, 'wieprzowina', 'G100'),
       (14, 3.1, 5.2, 300, 22.5, 'kurczak', 'G100'),
       (15, 60.6, 11.6, 361, 8.9, 'migdał', 'G100'),
       (16, 60.6, 11.6, 361, 8.9, 'pistacja', 'G100'),
       (17, 60.6, 11.6, 361, 8.9, 'orzech włoski', 'G100'),
       (18, 60.6, 11.6, 361, 8.9, 'orzech laskowy', 'G100'),
       (19, 60.6, 11.6, 361, 8.9, 'daktyl', 'G100'),
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
       (39, 1.6, 0.6, 5, 0.5, 'sól', 'G100'),
       (40, 40.5, 0.6, 250, 0.5, 'płatki kukurydziane', 'G100'),
       (41, 6.5, 70.5, 800, 8.5, 'chia', 'G100'),
       (42, 41.1, 1.5, 60, 0.4, 'wafle ryżowe', 'G100'),
       (43, 32.5, 1.5, 60, 0.4, 'Szpinak', 'G100'),
       (44, 29.1, 1.5, 60, 0.4, 'Jarmuż', 'G100'),
       (45, 27.5, 1.5, 60, 0.4, 'Rukola', 'G100'),
       (46, 47.1, 1.5, 60, 0.4, 'Jabłko', 'G100'),
       (47, 45.1, 1.5, 60, 0.4, 'Maliny', 'G100'),
       (48, 31.1, 1.5, 60, 0.4, 'Imbir', 'G100'),
       (49, 12.1, 1.5, 60, 60.4, 'Białko WPC', 'G100'),
       (50, 4.1, 70.2, 320, 0.4, 'Oliwa', 'ML100'),
       (51, 3.1, 74.2, 325, 0.4, 'Olej słonecznikowy', 'ML100');
;


INSERT INTO public.ingredient_allergen(ingredient_id, allergen_id)
VALUES (1, 4),
       (2, 4),
       (3, 4),
       (4, 4),
       (5, 4),
       (6, 1),
       (6, 2),
       (6, 3),
       (7, 1),
       (7, 2),
       (7, 3),
       (8, 1),
       (8, 3),
       (9, 7),
       (10, 1),
       (10, 2),
       (10, 3),
       (11, 1),
       (11, 3),
       (12, 10),
       (12, 11),
       (13, 10),
       (13, 13),
       (14, 10),
       (14, 14),
       (15, 7),
       (16, 7),
       (17, 7),
       (18, 7),
       (19, 8),
       (20, 7),
       (21, 15),
       (22, 15),
       (23, 15),
       (24, 15),
       (25, 15),
       (26, 15),
       (27, 15),
       (28, 15),
       (29, 15);


INSERT INTO public.recipes(id, description, image_url, name, rating,
                           short_description)
VALUES (1, 'Pyszny zapiekany tuńczyk z ziemniakami', 'https://google.pl', 'Tuńczyk z ziemniakami', 2.6, 'Pyszny zapiekany tuńczyk z ziemniakami'),
       (2, 'Łosoś smażony na mleku kokosowym z zielonym dressingiem', 'https://google.pl', 'Łosoś w mleku kokosowym', 3.1, 'Łosoś smażony na mleku kokosowym z zielonym dressingiem'),
       (3, 'Ostre chili con care', 'https://google.pl', 'Chili con care', 3.6, 'Ostre chili con care'),
       (4, 'Wołowina na ostro doprawiona po seczuańsku', 'https://google.pl', 'Wołowina po seczuańsku', 5.0, 'Wołowina na ostro doprawiona po seczuańsku'),
       (5, 'Klasyczny kurczak wraz z ziemniakami', 'https://google.pl', 'Kurczak z ziemniakami', 4.0, 'Klasyczny kurczak wraz z ziemniakami'),
       (6, 'Brokuły smażone na oliwie wraz z tofu', 'https://google.pl', 'Brokuły smażone z tofu', 3.5, 'Brokuły smażone na oliwie wraz z tofu');
;


INSERT INTO public.recipe_ingredient(amount, recipe_id, ingredient_id)
VALUES (3.0, 1, 1),
       (2.0, 1, 29),
       (2.0, 1, 50),
       (3.0, 2, 4),
       (1.0, 2,22),
       (1.0, 2, 9),
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
VALUES (8, 2, 0.4),
       (8, 4, 9.8),
       (8, 5, 0.9),
       (8, 6, 65),
       (8, 9, 0.5),
       (8, 18, 16),
       (8, 23, 243),
       (40, 5, 9),
       (40, 6, 15),
       (40, 9, 0.7),
       (40, 18, 1),
       (40, 23, 23);

INSERT INTO public.dietplans(id, name, description)
VALUES (1, 'Plan żywieniowy bez laktozy oraz bez glutenu', 'W planie nie zostały uwzględnione składniki z laktozą oraz glutenem'),
       (2, 'Plan żywieniowy antyhistaminowy', 'W planie nie zostały uwzględnione składniki związane z histaminą');


INSERT INTO public.diet_plan_ingredient(diet_plan_id, ingredient_id, meal_time, amount)
VALUES (1, 8, 'BREAKFAST', 0.4),
       (1, 40, 'BREAKFAST', 1.2),
       (1, 41, 'BREAKFAST', 0.2),
       (1, 20, 'BRUNCH', 0.3),
       (1, 42, 'BRUNCH', 2.0),
       (1, 4, 'DINNER', 1.0),
       (1, 22, 'DINNER', 1.5),
       (1, 32, 'DINNER', 1.5),
       (1, 15, 'SUPPER', 1.0),
       (1, 19, 'SUPPER', 1.4),

       (2, 47, 'BREAKFAST', 0.4),
       (2, 43, 'BREAKFAST', 0.4),
       (2, 44, 'BREAKFAST', 0.4),
       (2, 49, 'BEFORE_TRAINING', 0.4),
       (2, 7, 'BEFORE_TRAINING', 0.4),
       (2, 12, 'DINNER', 1.0),
       (2, 30, 'DINNER', 1.5),
       (2, 24, 'DINNER', 1.2),
       (2, 15, 'SUPPER', 1.0),
       (2, 19, 'SUPPER', 1.4);
;


INSERT INTO public.application_users(id, active, locked, username, password, role)
VALUES (7, true, false, 'admin', '$2a$10$LbZqyY7ZWrIafbJ72ftl2uAzZbmEFX2u50BUrS1LmSOac6DlRgsAK', 'ADMIN'),--psswd:test
       (11, true, false, 'mati', '$2a$10$ZAB04.jcc5z1r1vBOFiY.u5DLeokr2m9e4u9euuFoiklnYCzHGB5O', 'USER');--psswd:Passw0rd

INSERT INTO public.app_users(id, activity_level, daily_kcal, height, username, weight)
VALUES (7, 3, 3500, 220, 'admin', 120.5),
       (11, 8, 4000, 183, 'mati', 73.5);
INSERT INTO public.users_allergen(user_id, allergen_id)
VALUES (11, 0),
       (11, 3),
       (11, 8),
       (7, 0);
INSERT INTO public.user_diet(
    user_id, diet_plan_id)
VALUES (11, 1);
