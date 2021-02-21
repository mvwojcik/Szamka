INSERT INTO allergens (id, active, created_date, updated_date, name)
VALUES (0,true,NOW(),NOW(),'GLUTEN')
,(1,true,NOW(),NOW(),'MILK')
,(2,true,NOW(),NOW(),'LACTOSE')
,(3,true,NOW(),NOW(),'FISH')
,(4,true,NOW(),NOW(),'MEAT');


INSERT INTO public.ingerdients(id, active, created_date, updated_date, carbohydrates, fat, kcal, proteins, name, unit)
               VALUES
                (1, true, NOW(), NOW(), 20.1, 30.1, 600, 21.1, 'Tuna', 'G100'),
                (2, true, NOW(), NOW(), 5.6, 0.1, 170, 10.5, '3% Milk', 'ML100'),
                (3, true, NOW(), NOW(), 0.5, 1.5, 345, 24.1, 'Beef meat', 'G100'),
                (4, true, NOW(), NOW(), 12.1, 25.1, 415, 17.1, 'Salomon', 'G100'),
                (5, true, NOW(), NOW(), 60.6, 11.6, 361, 8.9, 'Almond', 'G100'),
                (6, true, NOW(), NOW(), 21.6, 11.6, 361, 8.9, 'Tomato', 'G100'),
                (7, true, NOW(), NOW(), 40.6, 11.6, 361, 8.9, 'Potato', 'G100'),
                (8, true, NOW(), NOW(), 0.6, 11.6, 262, 8.9, 'Coconut milk', 'ML100'),
                (9, true, NOW(), NOW(), 1.6, 0.6, 5, 0.5, 'Salt', 'G100');


INSERT INTO public.ingredient_allergen(ingredient_id, allergen_id)
	VALUES (1, 3),
	 (2, 1),
	 (2, 2),
	 (3, 4),
	 (4, 3);


INSERT INTO public.recipes(id, active, created_date, updated_date, description, image_url, name, rating, short_description)
	VALUES
	(1,true, NOW(), NOW(), 'description', 'https://google.pl', 'tuna with potatoes', 2.6, 'short Description'),
	(2,true, NOW(), NOW(), 'description', 'https://google.pl', 'Salomon in coconut', 3.1, 'short Description'),
	(3,true, NOW(), NOW(), 'description', 'https://google.pl', 'Tomatoes with potatoes', 3.6, 'short Description'),
	(4,true, NOW(), NOW(), 'description', 'https://google.pl', 'Almond with meat', 5.0, 'short Description')
	;


INSERT INTO public.recipe_ingredient(
	amount, recipe_id, ingredient_id)
	VALUES
	(3.0, 1, 1),
	(2.0, 1, 7),
	(0.01, 1, 9),
	(2.0, 2, 4),
	(1.0, 2, 8),
	(1.0, 3, 6),
	(1.0, 3, 7),
	(1.5, 4, 3),
	(1.0, 4, 5);


	INSERT INTO public.diet_plan(id,active,created_date,updated_date,name,description)
	VALUES (1,true,NOW(),NOW(),'My first Diet Plan', 'Check it out'),
	(2,true,NOW(),NOW(), 'My second Diet Plan', 'Check this out');



INSERT INTO public.diet_plan_recipe_entry(
	amount, meal_time, recipe_id, diet_plan_id)
	VALUES
	(1.0, 'BREAKFAST', 2, 1),
	(0.2, 'BREAKFAST', 1, 1),
	(1.0, 'DINNER', 3, 1),
	(1.0, 'SUPPER', 2, 1),
	(1.0, 'BREAKFAST', 4, 2),
	(1.0, 'SUPPER', 2, 2);


	INSERT INTO public.diet_plan_ingredient_entry(
	amount, meal_time, ingredient_id, diet_plan_id)
	VALUES (0.1, 'BREAKFAST', 5, 1),
	VALUES (2.1, 'DINNER', 2, 1),
	VALUES (1.5, 'DINNER', 6, 2);



