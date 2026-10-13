-- El instante de la lectura pasa a representarse con created_at (BaseEntity):
-- los registros de readings se crean en el momento de la ingesta (POST /api/v1/readings),
-- por lo que la columna timestamp resultaba redundante.
ALTER TABLE readings DROP COLUMN timestamp;
