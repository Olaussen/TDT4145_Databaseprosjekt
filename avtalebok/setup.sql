CREATE TABLE Bruker (bid INT NOT NULL AUTO_INCREMENT,
                    navn VARCHAR(50),
                    epost VARCHAR(30),
                    brukertype INT,
                    PRIMARY KEY(bid));

CREATE TABLE MedlemAv (gruppeId INT REFERENCES Bruker(bid),
                  bid INT REFERENCES Bruker(bid));

CREATE TABLE Avtale (aid INT AUTO_INCREMENT,
                    starttid INT,
                    timer INT,
		    avtaletype INT,
                    PRIMARY KEY(aid));

CREATE TABLE Alarm (alarmId INT AUTO_INCREMENT,
                     tid INT,
		     alamartype INT,
		     program VARCHAR(50),	
                     bid INT REFERENCES Bruker(bid),
		     aid INT REFERENCES Avtale(aid),
                     PRIMARY KEY(alarmId));

CREATE TABLE HarAvtale (bid INT REFERENCES Bruker(bid),
       	                aid INT REFERENCES Avtale(aid));

INSERT INTO Bruker (navn, epost, brukertype) VALUES ('Harry Hole', 'harry@mymail.com', 1),
('Kurt Wallander', 'kurt@polisen.se', 1),
('Van Veeteren', 'vanveeteren@mardaam.nl', 1),
('Martin Beck', 'martin@polisen.se', 1),
('Snuten', 'snuten@mymail.com', 2);
