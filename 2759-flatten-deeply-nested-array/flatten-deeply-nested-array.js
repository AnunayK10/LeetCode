/**
 * @param {Array} arr
 * @param {number} depth
 * @return {Array}
 */
var flat = function (arr, n) {
    const res = [];
    
    function helper(element, currentDepth) {
        if (Array.isArray(element) && currentDepth < n) {
            for (let item of element) {
                helper(item, currentDepth + 1);
            }
        } else {
            res.push(element);
        }
    }
    
    for (let item of arr) {
        helper(item, 0);
    }
    
    return res;
};