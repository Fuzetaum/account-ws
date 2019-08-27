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

const getUserFromUserRequest = (user) => ({
  username: user.username,
  name: user.name,
  phoneNumber: user.phoneNumber,
  email: user.email,
});

const getUser = (username, cb) => db.get('user', { username }, cb);

const getUsers = (cb) => db.get('user', {}, cb);

const createCustomer = (newCustomer, cb) =>
  db.create('customer', { username: newCustomer.username }, newCustomer, cb);

const createProvider = (newProvider, cb) =>
  db.create('provider', { username: newProvider.username }, newProvider, cb);

const createUser = (newUser, cb) =>
  db.create('user',
    { username: newUser.username },
    getUserFromUserRequest(newUser),
    (error, _) => {
      if (error) throw error;
      switch (newUser.type.toUpperCase()) {
        case 'PROVIDER':
          createProvider(getProviderFromUser(newUser), cb);
          break;
        default:
          createCustomer(getCustomerFromUser(newUser), cb);
      }
    });

const updateUser = (username, newData, cb) =>
  db.update('user', { username }, newData, cb);


module.exports = {
  createUser,
  getUser,
  getUsers,
  updateUser,
};
