// Java program to find closest point
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
class ClosestPairOfPointsNaiveApproach {
    // Function to compute Euclidean distance between two points
    static double distance(double[] p1, double[] p2) {
        return Math.sqrt((p1[0] - p2[0]) * (p1[0] - p2[0]) +
                          (p1[1] - p2[1]) * (p1[1] - p2[1]));
    }
    // Function that returns the smallest distance 
    // between any pair of points
    static double minDistance(List<double[]> points) {
        int n = points.size();
        double minDist = Double.MAX_VALUE;

        // Brute force to check all pairs
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                double dist = distance(points.get(i), points.get(j));
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }
        // Return the smallest distance
        return minDist;
    }

    public static void main(String[] args) {
        List<double[]> points = new ArrayList<>();
        points.add(new double[]{-1, -2});
        points.add(new double[]{0, 0});
        points.add(new double[]{1, 2});
        points.add(new double[]{2, 3});

        double res = minDistance(points);

        System.out.printf("%.6f\n", res);
    }
}