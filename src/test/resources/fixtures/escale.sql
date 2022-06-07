INSERT INTO gcmd_navire (id, name, numero_llyod, longeur, tiranteau, type_navire, is_deleted)
VALUES (1, 'navire name', 'n l', 3.2, 4.5, 'navtype', false);

INSERT INTO gcmd_escale (id, numero_escale, numero_llyod, date_arrivee, navire_id, is_deleted, isfactured)
VALUES (1, 1, 'n l', '2020-02-03', 1, false, false);
