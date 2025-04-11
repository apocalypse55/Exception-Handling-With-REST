-- Create book table
CREATE TABLE book (
                      id bigserial PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      author VARCHAR(255) NOT NULL,
                      price FLOAT(10) NOT NULL,
                      isbn VARCHAR(20) NOT NULL UNIQUE,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create sequence (though note that sequences are not typically used with MySQL's auto_increment)
CREATE SEQUENCE book_seq START WITH 1 INCREMENT BY 1;