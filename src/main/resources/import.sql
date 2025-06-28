-- Inserisci Team di esempio
INSERT INTO team (id, nome) VALUES 
  (1, 'Team Ducati'),
  (2, 'Team Yamaha');

-- Inserisci Piloti di esempio
INSERT INTO pilota_gp (id, nome, cognome, numero, nazionalita, team_id) VALUES
  (1, 'Francesco', 'Bagnaia', 63, 'Italia', 1),
  (2, 'Fabio', 'Quartararo', 20, 'Francia', 2),
  (3, 'Marc', 'Marquez', 93, 'Spagna', 1),
  (4, 'Maverick', 'Vinales', 12, 'Spagna', 2);