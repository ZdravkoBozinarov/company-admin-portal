CREATE TABLE quest (
  id              SERIAL PRIMARY KEY,
  name            TEXT NOT NULL,
  subtitle        TEXT,
  description     TEXT,
  banner_image    TEXT,
  category        TEXT,
  enabled         BOOLEAN DEFAULT true,
  check_cooldown  INT,
  max_completions INT,
  created_at      TIMESTAMPTZ DEFAULT NOW(),
  not_before      TIMESTAMPTZ,
  expiry          TIMESTAMPTZ,
  rrule           TEXT,
  dtstart         TIMESTAMPTZ,
  template        TEXT,
  data            JSONB,
  hidden          BOOLEAN DEFAULT false
);
