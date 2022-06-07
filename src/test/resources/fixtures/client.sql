INSERT INTO gcmd_type_client (id, name, is_deleted)
VALUES (1, 'typeclient1', false);

INSERT INTO gcmd_client (id, email, name, phone, type_client_id, is_deleted)
VALUES (1, 'client1@mail.com', 'client1', '0111111111', 1, false);