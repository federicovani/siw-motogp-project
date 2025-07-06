-- ========================
-- TEAM
-- ========================
INSERT INTO team (id, nome, marcaMoto) VALUES (10001, 'Ducati Lenovo Team', 'Ducati');
INSERT INTO team (id, nome, marcaMoto) VALUES (10002, 'Red Bull KTM Factory Racing', 'KTM');
INSERT INTO team (id, nome, marcaMoto) VALUES (10003, 'Aprilia Racing', 'Aprilia');
INSERT INTO team (id, nome, marcaMoto) VALUES (10004, 'Monster Energy Yamaha MotoGP', 'Yamaha');
INSERT INTO team (id, nome, marcaMoto) VALUES (10005, 'Repsol Honda Team', 'Honda');
INSERT INTO team (id, nome, marcaMoto) VALUES (10006, 'Gresini Racing MotoGP', 'Ducati');
INSERT INTO team (id, nome, marcaMoto) VALUES (10007, 'Mooney VR46 Racing Team', 'Ducati');
INSERT INTO team (id, nome, marcaMoto) VALUES (10008, 'LCR Honda', 'Honda');
INSERT INTO team (id, nome, marcaMoto) VALUES (10009, 'GasGas Factory Racing Tech3',  'KTM');
INSERT INTO team (id, nome, marcaMoto) VALUES (10010, 'Prima Pramac Racing', 'Ducati');
INSERT INTO team (id, nome, marcaMoto) VALUES (10011, 'RNF MotoGP Team', 'Aprilia');


-- ========================
-- PILOTI
-- ========================
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10001, 'Francesco', 'Bagnaia', 63, 'Italia', 175, 67, 10001);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10002, 'Enea', 'Bastianini', 23, 'Italia', 171, 64, 10001);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10003, 'Brad', 'Binder', 33, 'Sudafrica', 173, 65, 10002);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10004, 'Jack', 'Miller', 43, 'Australia', 173, 72, 10002);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10005, 'Aleix', 'Espargaro', 41, 'Spagna', 180, 66, 10003);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10006, 'Maverick', 'Vinales', 12, 'Spagna', 171, 64, 10003);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10007, 'Fabio', 'Quartararo', 20, 'Francia', 177, 66, 10004);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10008, 'Franco', 'Morbidelli', 21, 'Italia', 176, 66, 10004);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10009, 'Marc', 'Marquez', 93, 'Spagna', 169, 64, 10005);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10010, 'Joan', 'Mir', 36, 'Spagna', 181, 69, 10005);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10011, 'Alex', 'Marquez', 73, 'Spagna', 180, 66, 10006);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10012, 'Fabio', 'Di Giannantonio', 49, 'Italia', 177, 67, 10006);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10013, 'Luca', 'Marini', 10, 'Italia', 184, 69, 10007);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10014, 'Marco', 'Bezzecchi', 72, 'Italia', 176, 66, 10007);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10015, 'Takaaki', 'Nakagami', 30, 'Giappone', 168, 64, 10008);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10016, 'Stefan', 'Bradl', 6, 'Germania', 178, 68, 10008);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10017, 'Pol', 'Espargaro', 44, 'Spagna', 171, 65, 10009);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10018, 'Augusto', 'Fernandez', 37, 'Spagna', 180, 67, 10009);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10019, 'Johann', 'Zarco', 5, 'Francia', 177, 66, 10010);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10020, 'Jorge', 'Martin', 89, 'Spagna', 168, 64, 10010);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10021, 'Miguel', 'Oliveira', 88, 'Portogallo', 170, 65, 10011);
INSERT INTO pilota (id, nome, cognome, numero_identificativo, nazionalita, altezza, peso, team_id) VALUES (10022, 'Raul', 'Fernandez', 25, 'Spagna', 177, 66, 10011);


-- ======================================
-- CIRCUITI
-- ======================================

INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES(10001, 'Mugello', 'Italia', 6, 6, 14.0, 5245.0, 1141.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES(10002, 'Silverstone', 'Regno Unito', 8, 10, 15.0, 5891.0, 770.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES(10003, 'Assen', 'Paesi Bassi', 12, 6, 14.0, 4542.0, 560.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES(10004, 'Jerez', 'Spagna', 5, 8, 11.0, 4423.0, 607.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES(10005, 'Le Mans', 'Francia', 9, 4, 13.0, 4185.0, 674.0);


-- ======================================
-- GRAN PREMI
-- ======================================

INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10001, 23, '2025-05-10', 10001);
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10002, 20, '2025-06-02', 10002);
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10003, 24, '2025-06-15', 10003);
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10004, 22, '2025-07-01', 10004);
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10005, 21, '2025-07-20', 10005);


-- ======================================
-- SPONSOR
-- ======================================

INSERT INTO sponsor (id, nome) VALUES (10001, 'Monster');
INSERT INTO sponsor (id, nome) VALUES (10002, 'Red Bull');
INSERT INTO sponsor (id, nome) VALUES (10003, 'Michelin');


-- ======================================
-- RELAZIONI GRAN_PREMIO - SPONSOR
-- ======================================

INSERT INTO gran_premio_sponsor (gran_premi_id, sponsor_id) VALUES (10001, 10001);
INSERT INTO gran_premio_sponsor (gran_premi_id, sponsor_id) VALUES (10002, 10002);
INSERT INTO gran_premio_sponsor (gran_premi_id, sponsor_id) VALUES (10003, 10003);
INSERT INTO gran_premio_sponsor (gran_premi_id, sponsor_id) VALUES (10004, 10001);
INSERT INTO gran_premio_sponsor (gran_premi_id, sponsor_id) VALUES (10005, 10002);
