-- 1) Insert some genres
INSERT INTO genre (name) VALUES
  ('Fiction'),
  ('Science Fiction'),
  ('Biography'),
  ('Mystery'),
  ('Self-Help');

-- 2) Insert some publishers
INSERT INTO publisher (name, address) VALUES
  ('Penguin Random House', '1745 Broadway, New York, NY 10019'),
  ('HarperCollins', '195 Broadway, New York, NY 10007'),
  ('Simon & Schuster', '1230 Avenue of the Americas, New York, NY 10020'),
  ('Macmillan Publishers', '120 Broadway, New York, NY 10271'),
  ('Hachette Book Group', '1290 Avenue of the Americas, New York, NY 10104');

-- 3) Insert some books (using the genre_id and publisher_id that Postgres will assign above: 1–5)
INSERT INTO book (title, author, genre_id, publisher_id) VALUES
  ('The Midnight Library',           'Matt Haig',          1, 1),
  ('Dune',                           'Frank Herbert',      2, 2),
  ('Becoming',                       'Michelle Obama',     3, 3),
  ('The Da Vinci Code',              'Dan Brown',          4, 4),
  ('Atomic Habits',                  'James Clear',        5, 5),
  ('Project Hail Mary',              'Andy Weir',          2, 1),
  ('Educated',                       'Tara Westover',      3, 2),
  ('Gone Girl',                      'Gillian Flynn',      4, 3),
  ('The Alchemist',                  'Paulo Coelho',       1, 4),
  ('The Power of Habit',             'Charles Duhigg',     5, 5);

-- 4) (Optional) Verify what you just inserted
SELECT
  b.id, b.title, b.author,
  g.name AS genre,
  p.name AS publisher
FROM book b
JOIN genre g     ON b.genre_id     = g.id
JOIN publisher p ON b.publisher_id = p.id
ORDER BY b.id;
