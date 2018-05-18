var exec = require('cordova/exec');

var payment = function (arg0, success, error) {
    exec(success, error, 'BestPay', 'payment', [arg0]);
};

module.exports = payment
