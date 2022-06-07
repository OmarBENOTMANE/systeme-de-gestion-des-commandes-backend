INSERT INTO gcmd_bulltin_prestation (id, code_client, code_nature, date, date_depot, date_probable_execution,
                                     description, heure, moyen_odep_client, nom_client, numero_cmd,
                                     numero_dossier_prestation, numero_escale, pre_validation, text, type_paiement,
                                     is_deleted, is_sent)
VALUES (1, 2, 22, '2022-05-04', '2022-04-20', '2022-04-13', 'blabla', '18:59:59', true, 'blabla', 222, 2222, 22222,
        false, 'blabla', 1, false, false);

INSERT INTO gcmd_type_client (id, name, is_deleted)
VALUES (1, 'typeclient1', false);

INSERT INTO gcmd_client (id, email, name, phone, type_client_id, is_deleted)
VALUES (1, 'client1@mail.com', 'client1', '0111111111', 1, false);

INSERT INTO gcmd_navire (id, name, numero_llyod, longeur, tiranteau, type_navire, is_deleted)
VALUES (1, 'navire name', 'n l', 3.2, 4.5, 'navtype', false);

INSERT INTO gcmd_escale (id, numero_escale, numero_llyod, date_arrivee, navire_id, is_deleted, isfactured)
VALUES (1, 1, 'n l', '2020-02-03', 1, false, false);

INSERT INTO gcmd_devis (id, bl, client_id, date, date_facturation, date_sortie, designation, engins_colis, escale_id,
                        import_export, mm_mc, nom_client, nom_navire, nombre_colis, numero_commande, numero_mafi, poids,
                        is_deleted)
VALUES (1, '1', '1', '2022-05-03', '2022-05-05', '2022-05-05', 'desi', 'ENGINS', '1', 'IMPORT', 'MM', 'nom clie',
        'nom navir', '2', '3', '4', '5.6', false);


INSERT INTO gcmd_commande (id, bulletin_reception, capitaine, connaissement, consignataire,
                           date_amarage, date_desamarage, jauge_brute, lht, numero_commande,
                           numero_credit, navire, numero_bc, numero_escale, poste, is_deleted, devis_id, escale_id,
                           bulltin_prestation_id, isfactured)
VALUES (1, 2, 'capi cmd', 3, 'consi', '2022-04-25', '2022-04-25', 4, 5, 6, 7, 'navire', 8, 9, 'post', false, 1, 1, 1,
        false);
