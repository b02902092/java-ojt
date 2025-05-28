-- 作者
CREATE TABLE authors
(
  id SERIAL NOT NULL, -- ID
  name VARCHAR(256) NOT NULL, -- 作者名
  created_timestamp TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(), -- 作成日時
  updated_timestamp TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(), -- 更新日時
  deleted_timestamp TIMESTAMP WITH TIME ZONE, -- 削除日時
  PRIMARY KEY (id)
);

-- 本
CREATE TABLE books
(
  id SERIAL NOT NULL, -- ID
  title VARCHAR(256) NOT NULL, -- 書名
  author_id INT, -- 作者ID
  published_at DATE, -- 発行日
  created_timestamp TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(), -- 作成日時
  updated_timestamp TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(), -- 更新日時
  deleted_timestamp TIMESTAMP WITH TIME ZONE, -- 削除日時
  PRIMARY KEY (id),
  FOREIGN KEY (author_id) REFERENCES authors(id) -- 外部キー制約
);

-- ユーザ
CREATE TABLE users
(
  id SERIAL NOT NULL, -- ID
  name VARCHAR(256) NOT NULL, -- ユーザ名
  email VARCHAR(256), -- eメール
  created_timestamp TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(), -- 作成日時
  updated_timestamp TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(), -- 更新日時
  deleted_timestamp TIMESTAMP WITH TIME ZONE, -- 削除日時
  PRIMARY KEY (id)
);