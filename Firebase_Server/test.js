var fs = require('fs');

// fs.readFile('public/login.html' , function (err,data) {
//     if(err) throw err;
//
//     console.log(data);
// });
console.log('멍청한원준이는이게맨밑에나올거라고생각합니다');
function add(x, y, callback) {
    var result = x + y;
    callback(result);
}

add(10, 30, function (res) {
    console.log(res);
});

console.log('응 이ㅔㄱ 콜백이야');
