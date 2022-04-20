USE Chadley_Test;
GO

CREATE TABLE ConstellationImages (
    id INT PRIMARY KEY,
	name VARCHAR(64),
	constellationUrl VARCHAR(120)
);
GO

INSERT INTO ConstellationImages(name, constellationUrl) VALUES
("Pices", "https://www.iau.org/static/public/constellations/gif/PSC.gif"),
("Aries", "https://www.iau.org/static/public/constellations/gif/ARI.gif"),
("Taurus", "https://www.iau.org/static/public/constellations/gif/TAU.gif"),
("Gemini", "https://www.iau.org/static/public/constellations/gif/GEM.gif"),
("Cancer", "https://www.iau.org/static/public/constellations/gif/CNC.gif"),
("Leo", "https://www.iau.org/static/public/constellations/gif/LEO.gif"),
("Virgo", "https://www.iau.org/static/public/constellations/gif/VIR.gif"),
("Libra", "https://www.iau.org/static/public/constellations/gif/LIB.gif"),
("Scorpius", "https://www.iau.org/static/public/constellations/gif/SCO.gif"),
("Sagittarius", "https://www.iau.org/static/public/constellations/gif/SGR.gif"),
("Capricornus", "https://www.iau.org/static/public/constellations/gif/CAP.gif"),
("Aquarius", "https://www.iau.org/static/public/constellations/gif/AQR.gif")
GO

