-- 作者
INSERT INTO authors(name) VALUES
('門畑顕博'),
('夏目漱石'),
('太宰治'),
('J. K. Rowling');

-- 本
INSERT INTO books(title, author_id, published_at) VALUES
('AWSコスト最適化ガイドブック', 1, '2023-03-29'),
('吾輩は猫である', 2, '1907-05-19'),
('人間失格', 3, '1948-07-25'),
('斜陽', 3, '1947-12-15'),
('Harry Potter and the Philosopher''s Stone', 4, '1997-06-26'),
('Harry Potter and the Chamber of Secrets', 4, '1998-07-02'),
('the Bible', null, null);