const wsDatabase = require('@ricardofuzeto/ws-database');

let db;
(async () => {
  db = await wsDatabase;
})();

const getCustomerFromUser = (user) => ({
  username: user.username,
});

const getProviderFromUser = (user) => ({
  username: user.username,
  type: user.providerType,
});

const getUsers = (cb) => db.get('user', {}, cb);

const createCustomer = (newCustomer, cb) =>
  db.create('customer', { username: newCustomer.username }, newCustomer, cb);

const createProvider = (newProvider, cb) =>
db.create('provider', { username: newProvider.username }, newProvider, cb);

const createUser = (newUser, cb) =>
  db.create('user', { username: newUser.username }, newUser, (error, result) => {
    if (error) throw error;
    else {
      if (newUser.type.toUpperCase() === 'PROVIDER') createProvider(getProviderFromUser(newUser), cb);
      else createCustomer(getCustomerFromUser(newUser), cb);
    }
  });

const updateUser = (username, newData, cb) =>
  db.update('user', { username }, newData, cb);


module.exports = {
  createUser,
  getUsers,
  updateUser,
};
