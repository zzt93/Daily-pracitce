/**
 * - Given an array of n elements, where each element is at most k away from its target position,
 * devise an algorithm that sorts in O(n log k) time. -- Heap Sort or Balanced BST
 * - Iterative quick sort: core is partition, stack has range of array to partition (the range in stack become smaller)
 * - Iterative merge sort: core is merge, stack pop two elements and put one (the range in stack become larger)
 */
package methodology.sort;