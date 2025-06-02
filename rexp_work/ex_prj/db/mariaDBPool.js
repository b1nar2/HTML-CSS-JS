// const maria = require('mysql'); // 1)
const maria = require('mariadb'); // 2)
// https://github.com/mariadb-corporation/mariadb-connector-nodejs/blob/master/documentation/promise-api.md

/* 1)
const conn = maria.createConnection({
    host : '127.0.0.1',
    port : 3306,
    user:'root',
    password:'1234',
    database: 'express'
});
*/

const pool = maria.createPool({
    host : '127.0.0.1',
    port : 3306,
    user:'root',
    password:'1234',
    database: 'express',
    connectionLimit : 20
});


// module.exports = conn; // 1)
module.exports = pool; // 2)