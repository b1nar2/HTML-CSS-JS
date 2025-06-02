var express = require('express');
var router = express.Router();

const dao = require("../db/DAO")

var corsOptions = {
  origin: 'http://localhost:5173',
  optionsSuccessStatus: 200 
  // 주의) 간혹 웹브라우저의 종류에 따라(MS IE(Internet Explorer) 11, SmartTV(스마트 TV) 등) 
  // 204 로 Http 상태 코드를 지정하는 경우도 있음
}

// CORS 추가 : localhost:3000 자체 테스트시에는 생략
const cors = require('cors');

var app = express();

// CORS 추가 : localhost:3000 자체 테스트시에는 생략
app.use(cors());

/* GET 방식 */
// cors(corsOptions) 들어가는 함수 구문 : localhost:3000 자체 테스트시에는 생략
router.get('/:name/:email/:phone/:description', cors(corsOptions), function(req, res, next) {
// router.get('/:name/:email/:phone/:description', function(req, res, next) {

    let name = req.params.name;
    let email = req.params.email;
    let phone = req.params.phone;
    let description = req.params.description;

    console.log("name : ", name);
    console.log("email : ", email);
    console.log("phone : ", phone);
    console.log("description : ", description);


    // 인자들의 객체 멤버필드화
    let user = {};
    user.name = name;
    user.email = email;
    user.phone = phone;
    user.description = description;

    dao.insertUser(user)
    .then((msg) => {
        console.log("msg :", msg);
        res.json(`{"msg" : "${msg}" }`);
    });

  });
  
  module.exports = router;