DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'java') THEN
        CREATE DATABASE java;
END IF;
END
$$;