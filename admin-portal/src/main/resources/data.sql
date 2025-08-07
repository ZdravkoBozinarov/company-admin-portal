INSERT INTO quests (name, subtitle, description, category, enabled, hidden, not_before, expiry, template, data)
VALUES
('Connect X to Profile', 'Connect your X account to Codex', 'Step-by-step to connect X account...', 'social', true, false, null, null, null, '{}'),
('Unlock a Revolver', 'RAM + Strategy', 'Unlock weapon in-game', 'gamer', true, false, null, '2025-12-31 23:59:59', 'has_weapon', '{"weapon": [7]}');
