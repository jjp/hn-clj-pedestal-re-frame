drop table if exists link;
drop table if exists usr;

create table usr (
  id int generated by default as identity primary key,
  name text not null,
  email text not null,
  password text not null,
  created_at timestamp not null default current_timestamp,
  updated_at timestamp not null default current_timestamp);

insert into usr (name, email, password) values
  ('Luis', 'lmrosso@hotmail.com', 'luis'),
  ('Marcelo', 'lmrosso@gmail.com', 'marcelo');

create table link (
  id int generated by default as identity primary key,
  description text,
  url text not null,
  created_at timestamp not null default current_timestamp,
  updated_at timestamp not null default current_timestamp);

insert into link (description, url) values
  ('INIT - Prisma turns your database into a GraphQL API', 'https://www.prismagraphql.com'),
  ('INIT - The best GraphQL client', 'https://www.apollographql.com/docs/react/');
