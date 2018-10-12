/**
 * Challenge:
 * Implement a function that gets an array of numbers and a natural number K, the function returns an array of two numbers
 * which are elements of this array, if we will sum them, we will get the number K.
 * for example: f([1, 2, 10, 8], 18) => [10, 8]
 *
 * Try to find the most efficient way to do it.
 */
function findNaiveWayNN(arr, k) {
  // O(N^2)
  for (let index1 in arr) {
    for (let index2 in arr) {
      if (index1 != index2 && arr[index1] + arr[index2] == k) {
        return [arr[index1], arr[index2]];
      }
    }
  }

  return [];
}

function findNLogN(arr, k) {
  let middle, start, end;

  let clone = [].concat(arr);

  // O(NlogN)
  clone.sort((a, b) => a - b);

  // O(N)
  for (let index in clone) {
    start = 0;
    end = arr.length - 1;

    // O(LogN)
    while (start <= end) {
      middle = Math.floor((start + end) / 2);

      // check if the value we are searching for (k - clone[index] is the value we are currently looking at)
      if (clone[middle] == k - clone[index] && middle != index) {
        return [k - clone[index], clone[index]];
        // if it is higher, than it must be located in the right half of the array
      } else if (clone[middle] < k - clone[index]) {
        start = middle + 1;
        // if it is lower, than it must be located in the left half of the array
      } else {
        end = middle - 1;
      }
    }
  }

  return [];
}

function findN(arr, k) {
  let cache = {};

  // O(N) - map values to indexes of the array
  for (let index in arr) {
    cache[arr[index]] = index;
  }

  // O(N)
  for (let index in arr) {
    // O(1) - check if the value we are searching for (k - arr[index]) is not the current index and exists
    if (cache[k - arr[index]] && cache[k - arr[index]] != index) {
      return [arr[index], k - arr[index]];
    }
  }

  return [];
}

// Tests
console.log(findNaiveWayNN([2, 4, 8, 7, 10], 15));
console.log(findNLogN([2, 4, 8, 7, 10], 15));
console.log(findN([2, 4, 8, 7, 10], 15));
