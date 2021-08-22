DROP TABLE IF EXISTS stock;
CREATE TABLE IF NOT EXISTS stock ( id INTEGER NOT NULL PRIMARY KEY auto_increment,
                        name VARCHAR(64) UNIQUE,
                        current_price INTEGER NOT NULL,
                        last_update timestamp with time zone NOT NULL
                        );