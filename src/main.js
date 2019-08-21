const wsboot = require('@ricardofuzeto/ws-boot');

const {
  createUser,
  getUsers,
  updateUser,
} = require('./db');

wsboot.init();

wsboot.get('/users',
  { paginated: true, pageSize: 15 },
  (_, res) => {
    getUsers((_, users) => res.json(users));
  },
);

wsboot.post('/user', (req, res) => {
  createUser(req.body, (_, result) => {
    if (result.username && result.username === req.body.username) {
      res.json({ status: 410, message: `Username "${result.username}" already exists` });
      return;
    }
    res.json({ status: 202 });
  });
});

wsboot.patch('/user/:username', (req, res) => {
  updateUser(req.params.username, req.body, (_, result) => {
    res.json({ patched: result.modifiedCount, status: 202 });
  });
});
