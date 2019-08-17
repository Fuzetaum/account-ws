const wsboot = require('@ricardofuzeto/ws-boot');

const {
  createUser,
  getUsers,
} = require('./db');

wsboot.init();

wsboot.get('/users', (req, res) => {
  getUsers().then(data => {
    const page = req.query.page ? parseInt(req.query.page) : 1;
    const lastPage = Math.ceil(Object.keys(data).length/20);
    if (page > lastPage) {
      res.json({
        data: Object.keys(data)
          .slice((lastPage - 1) * 20)
          .map(dataItemKey => data[dataItemKey]),
        pages: lastPage,
        currentPage: lastPage,
      });
    } else {
      res.json({
        data: Object.keys(data)
          .slice((page - 1) * 20, page * 20)
          .map(dataItemKey => data[dataItemKey]),
        pages: lastPage,
        currentPage: page,
      });
    }
  });
});

wsboot.post('/user', (req, res) => {
  createUser(req.body);
  res.json({ status: 202 });
});
