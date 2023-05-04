import java.util.ArrayList;

public class Methods {
    public static double lagrange(ArrayList<Point> points, double solve) {
        double ans = 0.0;

        for (int i = 0; i < points.size(); i++) {
            double basis = 1.0;

            for (int j = 0; j < points.size(); j++) {
                if (i != j) {
                    basis *= (solve - points.get(j).getX()) / (points.get(i).getX() - points.get(j).getX());
                }
            }

            ans += points.get(i).getY() * basis;
        }

        return ans;
    }

    public static double[] finiteDifferences(ArrayList<Point> points) {
        int n = points.size();
        double[][] table = new double[n][n];
        for (int i = 0; i < n; i++) {
            table[i][0] = points.get(i).getY();
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                table[i][j] = (table[i+1][j-1] - table[i][j-1]) / (points.get(i + j).getX() - points.get(i).getX());
            }
        }
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = table[0][i];
        }
        return result;
    }

    public static double newtonPolynomial(ArrayList<Point> points, double solve) {
        double[] d = finiteDifferences(points);
        double result = d[0];
        double product = 1;
        for (int i = 1; i < points.size(); i++) {
            product *= (solve - points.get(i - 1).getX());
            result += d[i] * product;
        }
        return result;
    }
}
