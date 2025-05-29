var express = require('express');
var router = express.Router();

/* GET users listing. */
// 사용례 -1) http://localhost:3000/users/abcd
//           http://localhost:3000/users/abcd/01012345678
// router.get('/:id', function(req, res, next)
router.get('/:id/:phone', function(req, res, next) {

// 사용례 -2) http://localhost:3000/users?id=abcd
//           http://localhost:3000/users?id=abcd&phone=01012345678

// router.get('/', function(req, res, next) {

  let id = req.params.id; // path 인자 방식
  let phone = req.params.phone; // path 인자 방식

  // let id = req.query.id; // 쿼리(query) 인자 방식
  // let phone = req.query.phone; // query 인자 방식
  
  // res.send('인자(id): ' + id);
  res.send(`id + ${id}, phone = ${phone}`);
});

module.exports = router;
