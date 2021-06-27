DROP TABLE IF EXISTS customerlist;

CREATE TABLE customerlist (id VARCHAR(64) PRIMARY KEY, customer_id VARCHAR(64), phone_number VARCHAR(64));

INSERT INTO customerlist(id, customer_id, phone_number) VALUES ('1', '100', '0400000001');
INSERT INTO customerlist(id, customer_id, phone_number) VALUES ('2', '100', '0400000002');
INSERT INTO customerlist(id, customer_id, phone_number) VALUES ('3', '101', '0400000003');

DROP TABLE IF EXISTS phonelist;

CREATE TABLE phonelist (id VARCHAR(64) PRIMARY KEY, phone_number VARCHAR(64), active BOOLEAN);

INSERT INTO phonelist(id, phone_number, active) VALUES ('1', '0400000001', true);
INSERT INTO phonelist(id, phone_number, active) VALUES ('2', '0400000002', true);
INSERT INTO phonelist(id, phone_number, active) VALUES ('3', '0400000003', false);

COMMIT;