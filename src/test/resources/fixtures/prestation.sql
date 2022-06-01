insert into gcmd_unite_organisationel (id,description,is_deleted,label,type)
values (1, 'unit1', false,'label1', 'typeUnt1');

insert into gcmd_type_prestation (id, is_deleted, name, unite_organisationel_id)
values (1, false, 'nametypeprestation', 1);

insert into gcmd_soustypeprestation (id, is_deleted, name, type_prestation_id)
values (1, false, 'name soustypeprestation', 1);

insert into gcmd_tarif (id,tarif_ht,tarif_ttc,is_deleted)
values (1, 2.2, 3.3, false);

INSERT INTO gcmd_prestation (id, designation,is_deleted,type_prestation,type_tarif, soustypeprestation_id,tarif_id)
values (1, 'desi pre1',false, 'prestation type', 'type tarif 1', 1, 1);
