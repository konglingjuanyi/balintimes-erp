/**
 * Created by AlexXie on 2015/8/11.
 */
'use strict';
var express = require('express'),
    path = require('path'),
    favicon = require('serve-favicon'),
//logger = require('morgan'),
    cookieParser = require('cookie-parser'),
    bodyParser = require('body-parser'),
    session = require('express-session'),
    passport = require('passport'),
    flash = require('connect-flash'),
    uuid = require('node-uuid'),
    log4js = require("log4js");
var RedisStore = require('connect-redis')(session);
var settings = require("./settings");
var requestError = require("../app/util/requestErrorUtil");

module.exports = function () {
    var app = express();

// view engine setup
    app.set('views', path.join(__dirname, '../app/views'));
    app.set('view engine', 'ejs');

// uncomment after placing your favicon in /public
    app.use(favicon(path.join(__dirname, '../static', 'favicon.ico')));
    //app.use(logger('dev'));
    var logger = log4js.getLogger("normal");
    logger.setLevel("INFO");
    app.use(log4js.connectLogger(logger, {
        level: "auto",
        format: ':method :url :status :response-time ms - :res[content-length]'
    }));
    app.use(bodyParser.json());
    app.use(bodyParser.urlencoded({extended: false}));
    app.use(cookieParser());
    app.use(session({
        genid: function () {
            return uuid.v4();
        },
        secret: 'balintimes-erp-secret',
        resave: true,
        saveUninitialized: true,
        store: new RedisStore({
            "host": settings.redis.host,
            "port": settings.redis.port,
            "ttl": settings.sessionexpire
        })
    }));
    app.use(flash());

    app.use(passport.initialize());
    app.use(passport.session());

    app.use(express.static(path.join(__dirname, '../static')));

    /* ==== middlewares begin ==== */

    //var authMid = require("./app/authentication/authentication.server.controller");
    //
    //app.use(authMid.MidShow);

    /* ==== middlewares end ==== */

    /* ==== routers begin ==== */

    var homeroute = require('../app/home/home.server.route'),
        ucenter = require("../app/center/center.server.routes"),
        bmms = require('../app/bmms/bmms.server.routes'),
        crm = require('../app/crm/crm.server.routes');

    app.use('/', homeroute);
    app.use('/center', ucenter);
    app.use('/bmms', bmms);
    app.use('/crm', crm);
    /* ==== routers end ==== */


// catch 404 and forward to error handler
    app.use(function (req, res, next) {
        var err = new Error('Not Found');
        err.status = 404;
        next(err);
    });

// customer error handlers
    app.use(requestError);

// development error handler
// will print stacktrace
    if (app.get('env') === 'development') {
        app.use(function (err, req, res, next) {
            res.status(err.status || 500);
            res.render('error', {
                message: err.message,
                error: err
            });
        });
    }

// production error handler
// no stacktraces leaked to user
    app.use(function (err, req, res, next) {
        res.status(err.status || 500);
        res.render('error', {
            message: err.message,
            error: {}
        });
    });

    return app;
};

//module.exports = app;
