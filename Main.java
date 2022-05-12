package PrepMidterm2;

import java.util.Arrays;
import java.util.HashSet;

public class Main {

    public static void main(String[] args)
    {
        int[] arr = {3,2,3,1,2,4,5,5,6};
        System.out.println("Max is " + recursiveMax(arr, arr.length));
        int max = Integer.MIN_VALUE;
        System.out.println("Max is " + findMaxDivideAndConquer(arr, 0, arr.length - 1, max));
        findKthLargest(arr, 4);
    }
    /** majorityElement using divide and conquer*/
    public static int majorityElementDC(int[] nums)
    {
        return majorityElementHelper(nums, 0, nums.length - 1);
    }
    private static int majorityElementHelper(int[] nums, int low, int high)
    {
        if (low == high) return nums[low];
        int mid = (low + high) / 2;
        int left = majorityElementHelper(nums, low, mid);
        int right = majorityElementHelper(nums, mid + 1, high);
        if (left == right) return left;
        int leftCount = countInRange(nums, left, low, high);
        int rightCount = countInRange(nums, right, low, high);
        return leftCount > rightCount ? left : right;
    }
    private static int countInRange(int[] nums, int num, int low, int high)
    {
        int count = 0;
        for (int i = low; i <= high; i++)
        {
            if (nums[i] == num)
            {
                count++;
            }
        }
        return count;
    }
    /** majorityElement using Boyer-Moore Voting Algorithm*/
    public static int majorityElement(int[] nums)
    {
        int majority = -1;
        int counter = 0;
        for (int num : nums)
        {
            if (counter == 0)
            {
                majority = num;
                counter = 1;
            }
            else if (majority == num)

                counter++;
            else
                counter--;
        }
        return majority;
    }
    /** This is dynamic programming way, O(n) */
    public static int maxSubarray(int[] nums)
    {
        int maxSum = nums[0];
        int currentSum = 0;
        for (int i = 0; i < nums.length; i++)
        {
            if (currentSum < 0)
            {
                currentSum = 0;
            }
            currentSum += nums[i];
            maxSum = Math.max(currentSum, maxSum);
        }
        return maxSum;
    }
    /** Divide and conquer to find the maxSubarray*/
    public static int maxSubarrayDivideConquer(int[] nums)
    {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        return maxSubarrayDivideConquerHelper(nums, 0, nums.length - 1);
    }
    private static int maxSubarrayDivideConquerHelper(int[] nums, int left, int right)
    {
        if (left == right) return nums[left];
        int mid = (left + right) / 2;
        int leftMax = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--)
        {
            sum += nums[i];
            if (sum > leftMax)
            {
                leftMax = sum;
            }
        }
        int rightMax = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++)
        {
            sum += nums[i];
            if (sum > rightMax)
            {
                rightMax = sum;
            }
        }
        int maxLeftRight = Math.max(maxSubarrayDivideConquerHelper(nums, left, mid),
                maxSubarrayDivideConquerHelper(nums, mid + 1, right));
        return Math.max(maxLeftRight, leftMax + rightMax);
    }

    /** Decrease and conquer finding a max*/
    public static int recursiveMax(int[] arr, int n)
    {
        if (n == 1)
        {
            return arr[0];
        }
        return Math.max(recursiveMax(arr, n - 1), arr[n - 1]);
    }
    /** */
    public static int findMaxDivideAndConquer(int[] nums, int left, int right, int max)
    {
        if (left == right)
        {
            if (nums[right] > max)
                return nums[right];
        }
        if (right - left == 1)
        {
            if (nums[left] > nums[right])
            {
                if (nums[left] > max)
                    max = nums[left];
            }
            else
            {
                if (nums[right] > max)
                    max = nums[right];
            }
            return max;
        }
        int mid = (left + right) / 2;
        return Math.max(findMaxDivideAndConquer(nums, left, mid, max),
                findMaxDivideAndConquer(nums, mid + 1, right, max));
    }

    public static void negativeBeforePositive(int[] nums)
    {
        int j = 0; int temp;
        for (int i = 0; i < nums.length; i++)
        {
            if (nums[i] < 0)
            {
                if (i != j)
                {
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
                j++;
            }
        }
    }
    public static void negativeBeforePositiveDNF(int[] nums)
    {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high)
        {
            if (nums[low] <= 0)
            {
                low++;
            }
            else
            {
                swap(nums, low, high--);
            }
        }
    }
    private static void swap(int[] nums, int i, int j)
    {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    public static int findKthLargest(int[] nums, int k)
    {
        System.out.println("kth largest question starts: ");
        Arrays.sort(nums);
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++)
        {
            set.add(nums[i]);
        }
        System.out.println(set + "set ");
        int[] newArr = new int[set.size()];
        int index = 0;
        for (int i : set) newArr[index++] = i;
        System.out.println(newArr[newArr.length - k]);
        return newArr[newArr.length - k];
    }
    /** search element in sorted row and column m by n matrix */
    public static void searchInSortedMatrix(int[][] matrix, int n, int target)
    {
        int i = 0; int j = matrix[i].length;
        while (i < n && j >= 0)
        {
            if (matrix[i][j] == target)
            {
                System.out.println("Found + " + i + j);
                return;
            }
            if (matrix[i][j] < target)
            {
                i++;
            }
            else
            {
                j--;
            }
        }
        System.out.println("Not Found");
        return;
    }
}
/**
 * Hoare's Partition(A[left....right])
 * Partitions a subarray by Hoare's algorithm using first element as a pivot
 * input: subarray of an array A[left...right], left < right are indices
 * output: return the split position of partitioning A[left...right]
 * return the index of the pivot
 *
 * pivot = A[left];
 * i = left, j = right + 1;
 * repeat:
 *      repeat:
 *              i = i + 1 until A[i] >= pivot
 *              j = j - 1 until A[j] <= pivot
 *      swap(A[i], A[j]);
 * until: i >= j (when i and j cross over)
 * swap(A[i], A[j])     // undo the last swap when i >= j
 * swap(A[left], A[j])  // swap the pivot with A[j]
 * return j;
 *
 *
 * QuickSelect(A[left...right], k)
 * int s = HoarePartition(A[left, right])  // s is the pivot index
 * if s = k - 1 return A[s]
 * else if s > left + k - 1 QuickSelect(A[left...s - 1], k)
 * else QuickSelect(A[s + 1, right], k)
 *
 * MergeSort(A[0...n-1])
 * if (n > 1)
 *      Copy A[0, (n | 2) - 1] to B[0, (n | 2) - 1]
 *      Copy A[(n | 2) - 1, n - 1] to C[0, (n | 2) - 1]
 *      MergeSort(B[0, (n | 2) - 1])
 *      MergeSort(C[0, (n | 2) - 1])
 *      Merge(B, C, A)
 *
 * Merge(B[0... p - 1], C[0... q - 1], A[0... p + q - 1])
 * i = 0, j = 0, k = 0;
 * while (i < p && j < q)
 * {
 *     if (B[i] < C[j])
 *          A[k] = B[i]
 *          i++
 *     else
 *          A[k] = C[j]
 *          j++
 *     k++;
 * }
 * if (i = p)
 *      Copy C[j...q - 1] to A[k...p + q - 1]
 *      Copy B[i...p - 1] to A[k...p + q - 1]
 *
 *
 * Horner's Method
 * Horner(P[0...n], x)
 * p = P[n]
 * for (i = n - 1) downto 0 do
 *      p = x * p + P[i]
 * return p
 *
 * */
