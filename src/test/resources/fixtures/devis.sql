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

