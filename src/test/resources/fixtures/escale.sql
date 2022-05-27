INSERT INTO gcmd_navire (id, consignataire, etat,navire_name, numero_escale, is_deleted)
VALUES (1, 'consi n','etat navire', 'navname', 2,false);

INSERT INTO gcmd_escale (id, navire_id, numero_escale,is_deleted,isfactured,lamanage_date)
VALUES (1, null, null,false,false,null);
