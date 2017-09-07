var express = require('express');
var http = require('http');
var path = require('path');

var bodyParser = require('body-parser');
var cookieParser= require('cookie-parser');
var static = require('serve-static');
var errorHandler = require('errorhandler');

var expressErrorHandler = require('express-error-handler');

var expressSession = require('express-session');
var mongoose = require('mongoose');
var app = express();

app.set('port',process.env.PORT || 3000);
app.set(bodyParser.urlencoded({extended : false}));

app.use(bodyParser.json());

app.use('/public',static(path.join(__dirname,'public')));

app.use(cookieParser());

app.use(expressSession({
    secret : 'key',
    resave : true,
    saveUninitialized : true
}));

var router = express.Router();

router.route('/process/login').post(function (req,res) {
    console.log('/process/login 호출ㅁ됨');

    var paramHour = req.param('hour');
    var paramMinute = req.param('password');

    if(database){
        authUser(database,paramHour,paramMinute,function (err,docs) {
            if(err) throw err;

            if(docs){
                res.writeHead('200',{'Content-Type' : 'text/plain;charset=utf8'});
                res.end('잘되요');

            } else{
                res.writeHead('200',{'Content-Type' : 'text/plain;charset=utf8'});
                res.end('실패');
            }
        })
    }
});

router.route('/process/setTime').post(function (req,res) {

    var paramHour = req.param('hour');
    var paramMinute = req.param('minute');
   if(database){
       authTime(database)
   }
});

app.use('/',router);

var MongoClient = require('mongodb').MongoClient;

var database;

function connnectDB() {
    var databaseURL = 'mongodb://localhost:27017/local';

    MongoClient.connect(databaseURL , function (err,db) {
        if (err) throw err;

        console.log('데이터베이스에 연결되었습니다.' + databaseURL);

        database = db;
    })
}

var errorHandler = expressErrorHandler({
    static : {
        '404' : './public/404.html'
    }
});

// app.use(expressErrorHandler.httpError(404));
// app.use(errorHandler);


var authUser = function (database,id,password,callback) {
    console.log('authUser 호출됨');

    var users = database.collection('users');

    users.find({"id" : id , "password" : password}).toArray(function (err,docs) {
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

var authUser = function (database,id,password,callback) {
    console.log('authUser 호출됨');

    var users = database.collection('users');

    users.find({"id" : id , "password" : password}).toArray(function (err,docs) {
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

http.createServer(app).listen(app.get('port'),function () {
    console.log(app.get('port') + ' 에서 서버가 시작되었습니다.');
    connnectDB();
});