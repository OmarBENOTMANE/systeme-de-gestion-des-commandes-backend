INSERT INTO gcmd_navire (id, consignataire, etat,navire_name, numero_escale, is_deleted)
VALUES (1, 'consi n', 'etat navire', 'navname', 2,false);

INSERT INTO gcmd_escale (id, navire_id, numero_escale,is_deleted,isfactured)
VALUES (1, 1, 2,false,false);

insert into gcmd_marchandise (id,designation,is_deleted,quantite,reference,escale_id)
values (1, 'desi m', false, 2.3, 'ref',null)