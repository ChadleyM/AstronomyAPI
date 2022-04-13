CREATE TABLE Stars (
    id INT PRIMARY KEY,
	star_name VARCHAR(64)
);
GO

CREATE TABLE Star_Almanac (
    [date] DATE,
	star_id INT FOREIGN KEY REFERENCES Stars(id),
	gha FLOAT NOT NULL,
	declination FLOAT NOT NULL
);
GO

CREATE VIEW Star_Details
AS
SELECT Star_Almanac.date, Stars.star_name, Star_Almanac.gha, Star_Almanac.declination FROM Star_Almanac INNER JOIN Stars ON Star_Almanac.star_id = Stars.id
GO
