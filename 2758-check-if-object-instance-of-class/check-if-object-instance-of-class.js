/**
 * @param {*} obj
 * @param {*} classFunction
 * @return {boolean}
 */
var checkIfInstanceOf = function(obj, classFunction) {
    if (classFunction === null || classFunction === undefined || typeof classFunction !== 'function') {
        return false;
    }
    if (obj === null || obj === undefined) {
        return false;
    }
    let currentProto = Object.getPrototypeOf(Object(obj));
    const targetProto = classFunction.prototype;
    while (currentProto !== null) {
        if (currentProto === targetProto) {
            return true;
        }
        currentProto = Object.getPrototypeOf(currentProto);
    }
    return false;    
};

/**
 * checkIfInstanceOf(new Date(), Date); // true
 */