/**
 * @param {Promise} promise1
 * @param {Promise} promise2
 * @return {Promise}
 */
var addTwoPromises = async function(promise1, promise2) {
    // Await both promises and return their sum
    return (await promise1) + (await promise2);
};