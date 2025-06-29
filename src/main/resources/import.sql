-- Team
INSERT INTO team (id, nome) VALUES (1, 'Ducati Lenovo Team');
INSERT INTO team (id, nome) VALUES (2, 'Red Bull KTM Factory Racing');
INSERT INTO team (id, nome) VALUES (3, 'Aprilia Racing');
INSERT INTO team (id, nome) VALUES (4, 'Monster Energy Yamaha MotoGP');
INSERT INTO team (id, nome) VALUES (5, 'Repsol Honda Team');
INSERT INTO team (id, nome) VALUES (6, 'Gresini Racing MotoGP');
INSERT INTO team (id, nome) VALUES (7, 'Mooney VR46 Racing Team');
INSERT INTO team (id, nome) VALUES (8, 'LCR Honda');
INSERT INTO team (id, nome) VALUES (9, 'GasGas Factory Racing Tech3');
INSERT INTO team (id, nome) VALUES (10, 'Prima Pramac Racing');
INSERT INTO team (id, nome) VALUES (11, 'RNF MotoGP Team');

-- Piloti
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (1, 'Francesco', 'Bagnaia', 63, 'Italia', 175, 67, 1);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (2, 'Enea', 'Bastianini', 23, 'Italia', 171, 64, 1);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (3, 'Brad', 'Binder', 33, 'Sudafrica', 173, 65, 2);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (4, 'Jack', 'Miller', 43, 'Australia', 173, 72, 2);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (5, 'Aleix', 'Espargaro', 41, 'Spagna', 180, 66, 3);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (6, 'Maverick', 'Vinales', 12, 'Spagna', 171, 64, 3);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (7, 'Fabio', 'Quartararo', 20, 'Francia', 177, 66, 4);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (8, 'Franco', 'Morbidelli', 21, 'Italia', 176, 66, 4);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (9, 'Marc', 'Marquez', 93, 'Spagna', 169, 64, 5);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10, 'Joan', 'Mir', 36, 'Spagna', 181, 69, 5);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (11, 'Alex', 'Marquez', 73, 'Spagna', 180, 66, 6);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (12, 'Fabio', 'Di Giannantonio', 49, 'Italia', 177, 67, 6);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (13, 'Luca', 'Marini', 10, 'Italia', 184, 69, 7);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (14, 'Marco', 'Bezzecchi', 72, 'Italia', 176, 66, 7);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (15, 'Takaaki', 'Nakagami', 30, 'Giappone', 168, 64, 8);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (16, 'Stefan', 'Bradl', 6, 'Germania', 178, 68, 8);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (17, 'Pol', 'Espargaro', 44, 'Spagna', 171, 65, 9);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (18, 'Augusto', 'Fernandez', 37, 'Spagna', 180, 67, 9);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (19, 'Johann', 'Zarco', 5, 'Francia', 177, 66, 10);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (20, 'Jorge', 'Martin', 89, 'Spagna', 168, 64, 10);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (21, 'Miguel', 'Oliveira', 88, 'Portogallo', 170, 65, 11);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (22, 'Raul', 'Fernandez', 25, 'Spagna', 177, 66, 11);

-- Circuiti
INSERT INTO circuito (id, nome, citta) VALUES (1, 'Mugello', 'Italia');
INSERT INTO circuito (id, nome, citta) VALUES (2, 'Silverstone', 'Regno Unito');
INSERT INTO circuito (id, nome, citta) VALUES (3, 'Assen', 'Paesi Bassi');
INSERT INTO circuito (id, nome, citta) VALUES (4, 'Jerez', 'Spagna');
INSERT INTO circuito (id, nome, citta) VALUES (5, 'Le Mans', 'Francia');

-- Gran Premi
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (1, 23, '2025-05-10', 1);
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (2, 20, '2025-06-02', 2);
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (3, 24, '2025-06-15', 3);
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (4, 22, '2025-07-01', 4);
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (5, 21, '2025-07-20', 5);

-- Sponsor
INSERT INTO sponsor (id, nome) VALUES (1, 'Monster');
INSERT INTO sponsor (id, nome) VALUES (2, 'Red Bull');
INSERT INTO sponsor (id, nome) VALUES (3, 'Michelin');

-- GranPremio_Sponsor
INSERT INTO gran_premio_sponsor (gran_premio_id, sponsor_id) VALUES (1, 1);
INSERT INTO gran_premio_sponsor (gran_premio_id, sponsor_id) VALUES (2, 2);
INSERT INTO gran_premio_sponsor (gran_premio_id, sponsor_id) VALUES (3, 3);
