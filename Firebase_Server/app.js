var express = require('express');
var http = require('http');
var path = require('path');

var bodyParser = require('body-parser');
var cookieParser = require('cookie-parser');
var static = require('serve-static');
var errorHandler = require('errorhandler');

var expressErrorHandler = require('express-error-handler');

var expressSession = require('express-session');
var mongoose = require('mongoose');
var FCM = require('fcm-push');
var app = express();

app.set('port', process.env.PORT || 3000);
app.set(bodyParser.urlencoded({extended: false}));

app.use(bodyParser.json());

app.use('/public', static(path.join(__dirname, 'public')));

app.use(cookieParser());

app.use(expressSession({
    secret: 'key',
    resave: true,
    saveUninitialized: true
}));

var router = express.Router();

router.route('/process/login').post(function (req, res) {
    console.log('/process/login 호출ㅁ됨');

    var paramHour = req.param('hour');
    var paramMinute = req.param('password');

    if (database) {
        authUser(database, paramHour, paramMinute, function (err, docs) {
            if (err) throw err;

            if (docs) {
                res.writeHead('200', {'Content-Type': 'text/plain;charset=utf8'});
                res.end('잘되요');

            } else {
                res.writeHead('200', {'Content-Type': 'text/plain;charset=utf8'});
                res.end('실패');
            }
        })
    }
});

router.route('/process/setTime').post(function (req, res) {
    console.log('여기까진되는데');
    var paramID = req.param('ID');
    var paramHour = req.param('hour');
    var paramMinute = req.param('minute');
    console.log('여기까진되는데');
    res.writeHead('200', {'Content-Type': 'text/plain;charset=utf8'});
    res.end('되네 ㅋㅋㅋ');
    if (paramID && paramHour, paramMinute)
        sendMessage(paramID, paramHour, paramMinute);
    console.log('여기까진되는데');
    // authTime(database)
});

app.use('/', router);

var MongoClient = require('mongodb').MongoClient;

var database;

function connnectDB() {
    var databaseURL = 'mongodb://localhost:27017/local';

    MongoClient.connect(databaseURL, function (err, db) {
        if (err) throw err;

        console.log('데이터베이스에 연결되었습니다.' + databaseURL);

        database = db;
    })
}

var errorHandler = expressErrorHandler({
    static: {
        '404': './public/404.html'
    }
});

// app.use(expressErrorHandler.httpError(404));
// app.use(errorHandler);


var authUser = function (database, id, password, callback) {
    console.log('authUser 호출됨');

    var users = database.collection('users');

    users.find({"id": id, "password": password}).toArray(function (err, docs) {
        if (err) {
            callback(err, null);
            return;
        }
        if (docs.length > 0) {
            console.log('일치하는 사용자 찾음');
            callback(null, docs);
        } else {
            console.log('못찾음');
            callback(null, null);
        }
    })
};

var authUser = function (database, id, password, callback) {
    console.log('authUser 호출됨');

    var users = database.collection('users');

    users.find({"id": id, "password": password}).toArray(function (err, docs) {
        if (err) {
            callback(err, null);
            return;
        }
        if (docs.length > 0) {
            console.log('일치하는 사용자 찾음');
            callback(null, docs);
        } else {
            console.log('못찾음');
            callback(null, null);
        }
    })
};

/*
var authTime = function (database,hour,minute,callback) {
    console.log('authUser 호출됨');

    var users = database.collection('users');

    users.find({"hour" : hour, "minute" : minute}).toArray(function (err,docs) {
        if(err){
            callback(err,null);
            return;
        }
        if(docs.length > 0){
            console.log('일치하는 사용자 찾음');
            callback(null,docs);
        }else{
            console.log('못찾음');
            callback(null,null);
        }
    })
};
*/

var sendMessage = function (ID, hour, minute) {
    console.log('메세지 전송 실행');
    var FCM = require('fcm').FCM;
    var apiKey = 'AAAAIX79jKM:APA91bH8T6aYP8FtKsS_pHR0scmQBomxJkicFe4OZepDhd5moFxfRi1H-Ad3gLIMrWVGdkv0-XbDpl55MAq-xP-uTyS1Z2H3JdSjq5Z63wL7c1xNWZIqmrUGSdpC5qhRgpw71BB4mIXN';
    var fcm = new FCM(apiKey);
    var message = {
        registration_id: ID,
        data1: '되냐?',
        data2: '되냐?2'
    };
    fcm.send(message, function (err, messageId) {
        if (err) {
            console.log("Something has gone wrong!");
        }
        else {
            console.log("Sent with message ID: ", messageId);
        }
    });

};
//
http.createServer(app).listen(3000, function (req, res) {
    console.log(app.get('port'));
    connnectDB();
});
/*

app.listen(3000,function () {
  console.log('asdf');
});
*/
