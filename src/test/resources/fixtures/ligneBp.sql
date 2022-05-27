
INSERT INTO gcmd_bulltin_prestation (id, code_client, code_nature, date, date_depot, date_probable_execution,
                                     description, heure, moyen_odep_client, nom_client, numero_cmd,
                                     numero_dossier_prestation, numero_escale, pre_validation, text, type_paiement, is_deleted, validated, isfactured)
VALUES (1, 2, 22, '2022-05-04', '2022-04-20', '2022-04-13', 'descrp', '18:59:59', true, 'blabla', 222, 2222, 22222,
        false, 'text', 1, false, false, false);

INSERT INTO gcmd_ligne_bp (id,designation_prestation,date,heure,sens_trafic,produit,tc_suppl,tc_conv,nombre,
                           tarif_unifie,tonnage_reel,tonnage_minimum,bulltin_prestation_id,is_deleted)
VALUES (1,'prest1','2022-05-20','18:59:59','IMPORT', 'prdt1','tcS',false,4,true,2,3,1,false);

