const wsDatabase = require('@ricardofuzeto/ws-database');

let db;
(async () => {
  db = await wsDatabase;
})();

const getUsers = (cb) => db.get('user', {}, cb);

const createUser = (newUser, cb) =>
  db.create('user', { username: newUser.username }, newUser, cb);

const updateUser = (username, newData, cb) =>
  db.update('user', { username }, newData, cb);

module.exports = {
  createUser,
  getUsers,
  updateUser,
};
