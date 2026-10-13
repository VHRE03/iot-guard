CREATE TABLE audit_readings
(
    id           UUID             NOT NULL,
    sensor_id    VARCHAR(50)      NOT NULL,
    temperature  DOUBLE PRECISION NOT NULL,
    humidity     DOUBLE PRECISION NOT NULL,
    timestamp    TIMESTAMPTZ      NOT NULL,
    processed_at TIMESTAMPTZ      NOT NULL DEFAULT NOW(),
    created_at   TIMESTAMPTZ      NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMPTZ      NULL,
    is_deleted   BOOLEAN          NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_audit_readings PRIMARY KEY (id)
);
