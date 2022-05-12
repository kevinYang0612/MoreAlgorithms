package PrepMidterm2;

import java.util.Arrays;
import java.util.HashSet;

public class Algorithm
{
    private int max;
    public static void main(String[] args)
    {
    }
    /**Transform and conquer*/
    public static void closestNumbers(int[] nums)
    {
        Arrays.sort(nums);
        int minDifference = nums[1] - nums[0];
        for (int i = 2; i < nums.length; i++)
        {
            minDifference = Math.min(minDifference, nums[i] - nums[i - 1]);
        }
        for (int i = 1; i < nums.length; i++)
        {
            if (nums[i] - nums[i - 1] == minDifference)
            {
                System.out.println(nums[i] + ", " + nums[i - 1] +
                        " smallest difference " + minDifference);
            }
        }
    }
    public static int[] intersection(int[] num1, int[] num2)
    {
        HashSet<Integer> set1 = new HashSet<>();
        for (int i: num1) set1.add(num1[i]);
        HashSet<Integer> set2 = new HashSet<>();
        for (int i: num2) set2.add(num2[i]);

        set1.retainAll(set2);
        int[] output = new int[set1.size()];
        int index = 0;
        for (int i: set1) output[index++] = i;
        return output;
    }
    public static int powerDivideAndConquer(int base, int exp)
    {
        if (base == 0) return 0;
        if (exp == 0) return 1;
        if (exp % 2 == 0) return powerDivideAndConquer(base * base, exp / 2);
        else
            return base * powerDivideAndConquer(base * base, exp / 2);
    }
    public static void dutchNationalFlag(int[] arr)
    {
        int low = 0, high = arr.length - 1;
        int mid = 0, temp;
        while (mid <= high)
        {
            switch (arr[mid])       // mid is the counter here
            {
                case 0 -> {     // case 0 is swapping all
                    temp = arr[low];
                    arr[low] = arr[mid];
                    arr[mid] = temp;
                    low++;                  // swap the R from the middle to front
                    mid++;
                }
                case 1 -> mid++;     // the W is supposed to stay the middle, no swap, keep moving
                case 2 -> {
                    temp = arr[mid];        // when found B, swap to the rear, decrement high
                    arr[mid] = arr[high];
                    arr[high] = temp;
                    high--;
                }
            }
        }
    }

    public void findMaxDivideAndConquer(int[] nums, int left, int right, Algorithm al)
    {
        if (left == right)
        {
            if (nums[right] > al.max)
                al.max = nums[right];
            return;
        }
        if (right - left == 1)
        {
            if (nums[left] > nums[right])
            {
                if (nums[left] > al.max)
                    al.max = nums[left];
            }
            else
            {
                if (nums[right] > al.max)
                    al.max = nums[right];
            }
        }
        int mid = (left + right) / 2;
        findMaxDivideAndConquer(nums, left, mid, al);
        findMaxDivideAndConquer(nums, mid + 1, right, al);
    }
}
