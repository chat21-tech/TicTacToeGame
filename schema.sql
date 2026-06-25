CREATE DATABASE IF NOT EXISTS game_project;

USE game_project;

CREATE TABLE IF NOT EXISTS players (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    wins INT DEFAULT 0,
    losses INT DEFAULT 0,
    draws INT DEFAULT 0,
    score INT DEFAULT 0
);

INSERT INTO players (username, password, wins, losses, draws, score) VALUES ('rafael99', 'pass123', 0, 0, 0, 0);
INSERT INTO players (username, password, wins, losses, draws, score) VALUES ('citra_w', 'pass123', 0, 0, 0, 0);
INSERT INTO players (username, password, wins, losses, draws, score) VALUES ('budi_santoso', 'pass123', 0, 0, 0, 0);
INSERT INTO players (username, password, wins, losses, draws, score) VALUES ('dewi_lestari', 'pass123', 0, 0, 0, 0);
INSERT INTO players (username, password, wins, losses, draws, score) VALUES ('agus_p', 'pass123', 0, 0, 0, 0);
