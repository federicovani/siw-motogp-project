-- ========================
-- TEAM
-- ========================
INSERT INTO team (id, nome, marca_Moto) VALUES (10001, 'Ducati Lenovo Team', 'Ducati');
INSERT INTO team (id, nome, marca_Moto) VALUES (10002, 'Red Bull KTM Factory Racing', 'KTM');
INSERT INTO team (id, nome, marca_Moto) VALUES (10003, 'Aprilia Racing', 'Aprilia');
INSERT INTO team (id, nome, marca_Moto) VALUES (10004, 'Monster Energy Yamaha MotoGP', 'Yamaha');
INSERT INTO team (id, nome, marca_Moto) VALUES (10005, 'Repsol Honda Team', 'Honda');
INSERT INTO team (id, nome, marca_Moto) VALUES (10006, 'Gresini Racing MotoGP', 'Ducati');
INSERT INTO team (id, nome, marca_Moto) VALUES (10007, 'Mooney VR46 Racing Team', 'Ducati');
INSERT INTO team (id, nome, marca_Moto) VALUES (10008, 'LCR Honda', 'Honda');
INSERT INTO team (id, nome, marca_Moto) VALUES (10009, 'GasGas Factory Racing Tech3',  'KTM');
INSERT INTO team (id, nome, marca_Moto) VALUES (10010, 'Prima Pramac Racing', 'Ducati');
INSERT INTO team (id, nome, marca_Moto) VALUES (10011, 'RNF MotoGP Team', 'Aprilia');


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
-- CIRCUITI DEL 2025
-- ======================================
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10006, 'Losail', 'Qatar', 10, 6, 12.0, 5380.0, 1068.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10007, 'Portimao', 'Portogallo', 9, 6, 14.9, 4653.0, 969.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10008, 'Termas de Río Hondo', 'Argentina', 9, 5, 16.0, 4806.0, 1076.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10009, 'Austin', 'Stati Uniti', 11, 9, 15.5, 5513.0, 1200.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10010, 'Jerez', 'Spagna', 5, 8, 11.0, 4423.0, 607.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10011, 'Le Mans', 'Francia', 9, 4, 13.0, 4185.0, 674.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10012, 'Mugello', 'Italia', 6, 6, 14.0, 5245.0, 1141.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10013, 'Sachsenring', 'Germania', 3, 10, 12.0, 3671.0, 700.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10014, 'Assen', 'Paesi Bassi', 12, 6, 14.0, 4542.0, 560.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10015, 'Silverstone', 'Regno Unito', 8, 10, 15.0, 5891.0, 770.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10016, 'Spielberg', 'Austria', 7, 3, 13.0, 4318.0, 626.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10017, 'Barcellona', 'Spagna', 8, 6, 12.0, 4627.0, 1047.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10018, 'Misano', 'Italia', 10, 6, 12.0, 4226.0, 530.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10019, 'Buddh', 'India', 8, 5, 14.0, 5073.0, 1100.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10020, 'Motegi', 'Giappone', 8, 6, 15.0, 4801.0, 762.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10021, 'Buriram', 'Thailandia', 7, 5, 12.0, 4554.0, 1000.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10022, 'Phillip Island', 'Australia', 7, 5, 13.0, 4448.0, 900.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10023, 'Sepang', 'Malesia', 10, 5, 16.0, 5543.0, 920.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10024, 'Lusail', 'Qatar', 6, 10, 12.0, 5380.0, 1068.0);
INSERT INTO circuito (id, "città", paese, curve_dx, curve_sx, larghezza, lunghezza, rettilineo) VALUES (10025, 'Valencia', 'Spagna', 9, 5, 12.0, 4005.0, 876.0);


-- ======================================
-- GRAN PREMI DEL 2025
-- ======================================
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10006, 22, '2025-03-10', 10007); -- Portogallo
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10007, 25, '2025-03-24', 10008); -- Argentina
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10008, 20, '2025-04-07', 10009); -- Austin
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10009, 27, '2025-05-05', 10010); -- Jerez
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10010, 24, '2025-05-19', 10011); -- Le Mans
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10011, 23, '2025-06-02', 10012); -- Mugello
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10012, 30, '2025-06-16', 10013); -- Sachsenring
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10013, 28, '2025-06-30', 10014); -- Assen
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10014, 24, '2025-07-07', 10015); -- Silverstone
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10015, 26, '2025-07-21', 10016); -- Austria
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10016, 22, '2025-08-11', 10017); -- Catalogna
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10017, 27, '2025-09-01', 10018); -- Misano
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10018, 23, '2025-09-22', 10019); -- India
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10019, 26, '2025-10-06', 10020); -- Motegi
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10020, 25, '2025-10-20', 10021); -- Thailandia
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10021, 26, '2025-11-03', 10022); -- Phillip Island
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10022, 20, '2025-11-17', 10023); -- Sepang
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10023, 29, '2025-12-01', 10024); -- Qatar
INSERT INTO gran_premio (id, numero_di_giri, data, circuito_id) VALUES (10024, 22, '2025-12-15', 10025); -- Valencia



-- ======================================
-- SPONSOR
-- ======================================

INSERT INTO sponsor (id, nome) VALUES (10001, 'Monster');
INSERT INTO sponsor (id, nome) VALUES (10002, 'Red Bull');
INSERT INTO sponsor (id, nome) VALUES (10003, 'Michelin');


-- ======================================
-- RELAZIONI GRAN_PREMIO - SPONSOR
-- ======================================

INSERT INTO gran_premio_sponsor (gran_premi_id, sponsor_id) VALUES (10006, 10001);
INSERT INTO gran_premio_sponsor (gran_premi_id, sponsor_id) VALUES (10007, 10002);
INSERT INTO gran_premio_sponsor (gran_premi_id, sponsor_id) VALUES (10008, 10003);
INSERT INTO gran_premio_sponsor (gran_premi_id, sponsor_id) VALUES (10009, 10001);
INSERT INTO gran_premio_sponsor (gran_premi_id, sponsor_id) VALUES (10010, 10002);
