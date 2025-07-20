import java.util.*;

public class KthSmallestElement {
    // Method to find k-th smallest element in arr
    public static int select(int[] arr, int k) {
        if (arr.length <= 5) {
            Arrays.sort(arr);
            return arr[k - 1];
        }

        // 1. Divide into groups of 5
        List<int[]> groups = new ArrayList<>();
        for (int i = 0; i < arr.length; i += 5) {
            int end = Math.min(i + 5, arr.length);
            groups.add(Arrays.copyOfRange(arr, i, end));
        }

        // 2. Find medians of each group
        int[] medians = new int[groups.size()];
        for (int i = 0; i < groups.size(); i++) {
            int[] group = groups.get(i);
            Arrays.sort(group);
            medians[i] = group[group.length / 2];
        }

        // 3. Find median of medians recursively
        int pivot = select(medians, (medians.length + 1) / 2);

        // 4. Partition array around pivot
        List<Integer> lows = new ArrayList<>();
        List<Integer> highs = new ArrayList<>();
        List<Integer> pivots = new ArrayList<>();

        for (int num : arr) {
            if (num < pivot) lows.add(num);
            else if (num > pivot) highs.add(num);
            else pivots.add(num);
        }

        // 5. Recur on the appropriate part
        if (k <= lows.size()) {
            int[] lowArr = lows.stream().mapToInt(Integer::intValue).toArray();
            return select(lowArr, k);
        } else if (k <= lows.size() + pivots.size()) {
            return pivot;
        } else {
            int[] highArr = highs.stream().mapToInt(Integer::intValue).toArray();
            return select(highArr, k - lows.size() - pivots.size());
        }
    }

    // Driver code to test
    public static void main(String[] args) {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        int k = 3;  // 3rd smallest element
        int kthSmallest = select(arr, k);
        System.out.println(k + "th smallest element is " + kthSmallest + " 🎯");
    }
}
