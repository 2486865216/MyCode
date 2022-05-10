"use strict";

var _m = require("./m1.js");

var test = _interopRequireWildcard(_m);

var _jquery331Min = require("./jquery-3.3.1.min.js");

var _jquery331Min2 = _interopRequireDefault(_jquery331Min);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _interopRequireWildcard(obj) { if (obj && obj.__esModule) { return obj; } else { var newObj = {}; if (obj != null) { for (var key in obj) { if (Object.prototype.hasOwnProperty.call(obj, key)) newObj[key] = obj[key]; } } newObj.default = obj; return newObj; } }

console.log(test);

(0, _jquery331Min2.default)('body').css('background', 'pink');