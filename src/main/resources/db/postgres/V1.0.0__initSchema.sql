create sequence allergen_seq start 20 increment 5;
create sequence app_user_seq start 20 increment 5;
create sequence dietplan_seq start 20 increment 5;
create sequence ingredient_seq start 50 increment 5;
create sequence recipe_seq start 20 increment 5;
create sequence user_seq start 20 increment 5;
create sequence vitamin_seq start 50 increment 5;

create table allergens
(
    id   int8 not null,
    name varchar(255),
    primary key (id)
);

create table app_users
(
    id             int8 not null,
    activity_level int4,
    daily_kcal     int4,
    height         int4,
    username       varchar(255),
    weight         float8,
    primary key (id)
);

create table application_users
(
    id       int8         not null,
    active   boolean,
    locked   boolean,
    password varchar(255) not null,
    role     varchar(255),
    username varchar(255),
    primary key (id)
);

create table diet_plan_ingredient
(
    amount        float8,
    meal_time     varchar(255),
    diet_plan_id  int8 not null,
    ingredient_id int8 not null,
    primary key (diet_plan_id, ingredient_id)
);

create table dietplans
(
    id          int8 not null,
    access_type int4,
    description varchar(255),
    name        varchar(255),
    primary key (id)
);

create table ingredient_allergen
(
    ingredient_id int8 not null,
    allergen_id   int8 not null,
    primary key (ingredient_id, allergen_id)
);

create table ingredients
(
    id            int8 not null,
    carbohydrates float8,
    fat           float8,
    kcal          int4,
    name          varchar(255),
    proteins      float8,
    unit          varchar(255),
    primary key (id)
);

create table recipe_ingredient
(
    amount        float8,
    ingredient_id int8 not null,
    recipe_id     int8 not null,
    primary key (ingredient_id, recipe_id)
);

create table recipes
(
    id                int8 not null,
    description       varchar(255),
    image_url         varchar(255),
    name              varchar(255),
    rating            float8,
    short_description varchar(255),
    primary key (id)
);

create table user_diet
(
    user_id      int8 not null,
    diet_plan_id int8 not null,
    primary key (user_id, diet_plan_id)
);

create table users_allergen
(
    user_id     int8 not null,
    allergen_id int8 not null,
    primary key (user_id, allergen_id)
);

create table vitamin
(
    id                int8 not null,
    daily_requirement float8,
    name              varchar(255),
    scale             varchar(255),
    type              varchar(255),
    primary key (id)
);

create table vitamin_in_ingredient
(
    amount        float8,
    ingredient_id int8 not null,
    vitamin_id    int8 not null,
    primary key (ingredient_id, vitamin_id)
);

alter table diet_plan_ingredient
    add constraint FKajyngftg056x9xqefi8smjb4q
        foreign key (diet_plan_id)
            references dietplans;

alter table diet_plan_ingredient
    add constraint FKal3qwu275r9jhsmixqhsxyo4o
        foreign key (ingredient_id)
            references ingredients;

alter table ingredient_allergen
    add constraint FK67fw9hiibmd6qgakws7uoekh
        foreign key (allergen_id)
            references allergens;

alter table ingredient_allergen
    add constraint FKc2xuncm1jglcnylg54ec1xwym
        foreign key (ingredient_id)
            references ingredients;

alter table recipe_ingredient
    add constraint FKbult6ksqva3bk1cg64fgl0l00
        foreign key (ingredient_id)
            references ingredients;

alter table recipe_ingredient
    add constraint FK6fyh7xtjni8tgso26h8ek5b96
        foreign key (recipe_id)
            references recipes;

alter table user_diet
    add constraint FK72i4m4ob94j32a11qmhglyc97
        foreign key (diet_plan_id)
            references dietplans;

alter table user_diet
    add constraint FKod70s7nlsmmqpw1fnfkwh9bep
        foreign key (user_id)
            references app_users;

alter table users_allergen
    add constraint FKbim5xxsyxwu5eoleatkkg3vmx
        foreign key (allergen_id)
            references allergens;

alter table users_allergen
    add constraint FKhxqhvu4rgyoxstuk3bk6ndrs6
        foreign key (user_id)
            references app_users;

alter table vitamin_in_ingredient
    add constraint FKcadaf144bj0i91dn6sstiodfp
        foreign key (ingredient_id)
            references ingredients;

alter table vitamin_in_ingredient
    add constraint FKddkhm387h22ox3lkxdvnk41u5
        foreign key (vitamin_id)
            references vitamin