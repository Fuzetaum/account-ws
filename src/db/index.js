const wsDatabase = require('@ricardofuzeto/ws-database');

const getUsers = () => {
  return wsDatabase.get('/user');
};

const createUser = (newUser) =>
  wsDatabase.create(`/user/${newUser.username}`, newUser);

module.exports = {
  createUser,
  getUsers,
};
