INSERT INTO gcmd_navire (id, consignataire, etat,navire_name, numero_escale, is_deleted)
VALUES (1, 'consi n', null, 'navname', 1,false);

INSERT INTO gcmd_escale (id, navire_id, numero_escale,is_deleted, isfactured)
VALUES (1, 1, 2,false,false);

insert into gcmd_mouvement (id,date_mouvement,description,is_deleted,escale_id)
values (1, '2022-04-29', 'acostage', false,1);
