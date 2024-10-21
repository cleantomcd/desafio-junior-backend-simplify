CREATE TABLE todo (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    finished BOOLEAN NOT NULL,
    priority INT NOT NULL
);