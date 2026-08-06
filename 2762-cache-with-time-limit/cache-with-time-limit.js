var TimeLimitedCache = function() {
    this.cache = new Map();
};

/** 
 * @param {number} key
 * @param {number} value
 * @param {number} duration time until expiration in ms
 * @return {boolean} if un-expired key already existed
 */
TimeLimitedCache.prototype.set = function(key, value, duration) {
    const isExisting = this.cache.has(key);
    
    // If the key already exists, clear the old timeout so it doesn't expire prematurely
    if (isExisting) {
        clearTimeout(this.cache.get(key).timer);
    }
    
    // Set a new timeout to delete the key from the cache after the duration
    const timer = setTimeout(() => {
        this.cache.delete(key);
    }, duration);
    
    // Store both the value and the timer reference in the map
    this.cache.set(key, { value: value, timer: timer });
    
    return isExisting;
};

/** 
 * @param {number} key
 * @return {number} value associated with key
 */
TimeLimitedCache.prototype.get = function(key) {
    if (this.cache.has(key)) {
        return this.cache.get(key).value;
    }
    return -1;
};

/** 
 * @return {number} count of non-expired keys
 */
TimeLimitedCache.prototype.count = function() {
    return this.cache.size;
};