INSERT INTO gcmd_navire (id, consignataire,etat,navire_name, numero_escale, is_deleted)
VALUES (1, 'consi n','etat navire', 'navname', 1,false);

insert into gcmd_mouvement (id,date_mouvement,description,is_deleted,navire_id)
values (1, '2022-04-29', 'desc', false,null);
