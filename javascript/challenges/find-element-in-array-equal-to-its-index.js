/**
 * The algorithm wont work if it has sequence of same values for example: [-2, 1, 1, 1, 1, 1, 6]
 * According to our algorithm a null will return
 *
 */

function findElementValueRecursive(arr, offset = 0) {
  if (arr.length <= 2) {
    for (let i = 0; i < arr.length; ++i) {
      if (arr[i] === offset + i) {
        return arr[i];
      }
    }

    return null;
  }

  // perform binary search
  let middle = Math.ceil(arr.length / 2);

  if (arr[middle] === offset + middle) {
    return arr[middle];
  } else if (arr[middle] < offset + middle) {
    // take right
    return findElementValueRecursive(arr.slice(middle + 1), middle + 1);
  } else {
    // take left
    return findElementValueRecursive(arr.slice(0, middle), offset);
  }
}

console.log(findElementValueRecursive([-12, -1, 0, 2, 3, 5]));
console.log(findElementValueRecursive([-15, 0, 2, 6, 7, 10, 15]));
