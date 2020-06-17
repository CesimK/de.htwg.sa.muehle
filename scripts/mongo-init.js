db = db.getSiblingDB('player');
db.createUser(
    {
        user: 'root',
        pwd: '123',
        roles: [{ role: 'readWrite', db: 'player' }]
    }
);
db.createCollection('players');

db = db.getSiblingDB('game');
db.createUser(
    {
        user: 'root',
        pwd: '123',
        roles: [{ role: 'readWrite', db: 'game' }]
    }
);
db.createCollection('sessions');
