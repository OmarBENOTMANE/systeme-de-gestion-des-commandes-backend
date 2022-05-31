
INSERT INTO gcmd_bulltin_prestation (id, code_client, code_nature, date, date_depot, date_probable_execution,
                                     description, heure, moyen_odep_client, nom_client, numero_cmd,
                                     numero_dossier_prestation, numero_escale, pre_validation, text, type_paiement, is_deleted, validated, isfactured,is_sent)
VALUES (1, 2, 22, '2022-05-04', '2022-04-20', '2022-04-13', 'blabla', '18:59:59', true, 'blabla', 222, 2222, 22222,
        false, 'blabla', 1, false, false, false, false);

INSERT INTO gcmd_navire (id, consignataire, etat,navire_name, numero_escale, is_deleted)
VALUES (1, 'consi n', 'etat navire', 'navname', 2,false);

INSERT INTO gcmd_escale (id, navire_id, numero_escale,is_deleted,isfactured)
VALUES (1, 1, 2,false,false);

INSERT INTO gcmd_type_client (id, name,is_deleted)
VALUES (1, 'typeclient1',false);

INSERT INTO gcmd_client (id, email, name, phone, type_client_id,is_deleted)
VALUES (1, 'client1@mail.com', 'client1', '0111111111', 1,false);

insert into gcmd_tarif (id,tarif_ht,tarif_ttc,is_deleted)
values (1, 2.2, 3.3, false);

INSERT INTO gcmd_devis (id, bl, client_id, date, date_facturation, date_sortie, designation, engins_colis, escale_id,
                        import_export, mm_mc, nom_client, nom_navire, nombre_colis, numero_commande, numero_mafi, poids,is_deleted)
VALUES (1, '1', '1', '2022-05-03', '2022-05-05', '2022-05-05', 'desi', 'ENGINS', '1', 'IMPORT', 'MM', 'nom clie',
        'nom navir', '2', '3', '4', '5.6',false);

INSERT INTO gcmd_commande (id, bulletin_reception, capitaine, connaissement, consignataire,
                           date_amarage, date_desamarage, jauge_brute, lht, numero_commande,
                           numero_credit, navire, numero_bc, numero_escale, poste, is_deleted, devis_id, escale_id, bulltin_prestation_id)
VALUES (1, 2, 'capi', 3, 'consi', '2022-04-25', '2022-04-25', 4, 5, 6, 7, 'navire', 8, 9, 'post', false, 1,1, 1 );

insert into gcmd_unite_organisationel (id,description,is_deleted,label,type)
values (1, 'unit1', false,'label1', 'typeUnt1');

insert into gcmd_type_prestation (id, is_deleted, name, unite_organisationel_id)
values (1, false, 'nametypeprestation', 1);

insert into gcmd_soustypeprestation (id, is_deleted, name, typeprestation_id)
values (1, false, 'name soustypeprestation', 1);

INSERT INTO gcmd_prestation (id, designation,is_deleted,type_prestation,type_tarif, soustypeprestation_id,tarif_id)
values (1, 'desi pre1',false, 'prestation type', 'type tarif 1', 1, 1);

insert into gcmd_ligne_commande (id,is_deleted,date,description,genlbp,heure,is_affected,nombre,designation_prestation,produit,sens_trafic,tarif_unifie,tc_conv,tc_suppl,tonnage_minimum,tonnage_reel,commande_id,prestation_id,is_selected)
values (1,false,'2022-04-26', 'descri lcmd', 1,'04:00:00', false,2, 'designationPrestation', 'prod', 'TRANSIT', false, false, 'tc_suppl', 4, 3,null,null,true);


