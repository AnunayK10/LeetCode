/**
 * @param {Function} fn
 * @return {Function}
 */
function memoize(fn) {
    const root = new Map();
    const HAS_RESULT = Symbol();

    return function(...args) {
        let curr = root;
        
        // Traverse or build the Map tree for each argument
        for (const arg of args) {
            if (!curr.has(arg)) {
                curr.set(arg, new Map());
            }
            curr = curr.get(arg);
        }

        // If the result is already cached, return it
        if (curr.has(HAS_RESULT)) {
            return curr.get(HAS_RESULT);
        }

        // Otherwise, compute the result, cache it, and return
        const result = fn(...args);
        curr.set(HAS_RESULT, result);
        return result;
    }
}