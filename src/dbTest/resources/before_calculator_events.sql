DROP TABLE IF EXISTS calculator_events;

CREATE TABLE calculator_events (id long IDENTITY PRIMARY KEY, operator VARCHAR(20), operandA VARCHAR(20), operandB VARCHAR(20));

INSERT INTO calculator_events VALUES (1, 'add', '1', '2');
