package math;


import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

/**
 * Created by zzt on 11/16/17. <p> <h3></h3>
 */
public class Equation {


  private static double[][] mul(double[][] l, double[][] r) {
    int n = l.length;
    int m = l[0].length;

    return null;
  }

  private static void prepare(double[][] matrix, int n, double[][] d, double[][] l,
      double[][] r, double[][] e) {
    for (int x = 0; x < matrix.length; x++) {
      for (int y = 0; y < n; y++) {
        if (x == y) {
          d[x][y] = matrix[x][y];
          e[x][y] = 1;
        } else if (x < y) {
          r[x][y] = matrix[x][y];
        } else {
          l[x][y] = matrix[x][y];
        }
      }
    }
  }

  private static double[] mul(double[][] l, double[] r) {
    int n = l.length;
    int m = l[0].length;
    int rl = r.length;
    if (rl == m) {
      double[] res = new double[rl];
      for (int i = 0; i < res.length; i++) {
        res[i] = bitMulSum(l[i], r);
      }
      return res;
    } else {
      throw new IllegalArgumentException();
    }
  }

  private static double bitMulSum(double[] f, double[] s) {
    double res = 0;
    assert f.length == s.length;
    for (int i = 0; i < f.length; i++) {
      res += (f[i] * s[i]);
    }
    return res;
  }

  private static double[][] mi(double[][] l, double[][] r) {
    return bitSum(l, r, (f, s) -> f - s);
  }

  private static double[][] bitSum(double[][] l, double[][] r,
      BiFunction<Double, Double, Double> f) {
    int n = l.length;
    int m = l[0].length;
    double[][] res = new double[n][m];
    for (int x = 0; x < n; x++) {
      for (int y = 0; y < m; y++) {
        res[x][y] = f.apply(l[x][y], r[x][y]);
      }
    }
    return res;
  }

  private static double[][] add(double[][] l, double[][] r) {
    return bitSum(l, r, (f, s) -> f + s);
  }

  private static double[][] _1(double[][] l) {
    return null;
  }

  public static double[] Jacobi(double[][] A, double error, double[] init, double[] b,
      int maxIteration) {
    int n = A.length;
    double[] now = new double[n];
    System.arraycopy(init, 0, now, 0, n);
    double[] next = new double[n];
    int count = 0;
    while (true) {
      count++;
      for (int i = 0; i < next.length; i++) {
        double tmp = 0;
        for (int j = 0; j < n; j++) {
          if (i != j) {
            tmp += A[i][j] * now[j];
          }
        }
        next[i] = (b[i] - tmp) / A[i][i];
      }
      double maxDelta = Double.MIN_NORMAL;
      System.arraycopy(next, 0, now, 0, n);
      for (int i = 0; i < n; i++) {
        maxDelta = Math.max(maxDelta, Math.abs(next[i] - now[i]));
      }
      if (count >= maxIteration || maxDelta < error) {
        break;
      }
    }
    return next;
  }

  public static double[] sor(double[][] A, double error, double[] init, double[] b,
      int maxIteration, double w) {
    int n = A.length;
    double[] now = new double[n];
    System.arraycopy(init, 0, now, 0, n);
    double[] next = new double[n];
    int count = 0;
    while (true) {
      count++;
      for (int i = 0; i < next.length; i++) {
        double tmp = 0;
        for (int j = 0; j < n; j++) {
          if (i < j) {
            tmp += A[i][j] * next[j];
          } else if (i > j) {
            tmp += A[i][j] * now[j];
          }
        }
        next[i] = (1 - w) * now[i] + w * (b[i] - tmp) / A[i][i];
      }
      double maxDelta = Double.MIN_NORMAL;
      System.arraycopy(next, 0, now, 0, n);
      for (int i = 0; i < n; i++) {
        maxDelta = Math.max(maxDelta, Math.abs(next[i] - now[i]));
      }
      if (count >= maxIteration || maxDelta < error) {
        break;
      }
    }
    return next;
  }


  public static double[] Seidel(double[][] A, double error, double[] init, double[] b,
      int maxIteration) {
    int n = A.length;
    double[] now = new double[n];
    System.arraycopy(init, 0, now, 0, n);
    double[] next = new double[n];
    int count = 0;
    while (true) {
      count++;
      for (int i = 0; i < next.length; i++) {
        double tmp = 0;
        for (int j = 0; j < n; j++) {
          if (i < j) {
            tmp += A[i][j] * next[j];
          } else if (i > j) {
            tmp += A[i][j] * now[j];
          }
        }
        next[i] = (b[i] - tmp) / A[i][i];
      }
      double maxDelta = Double.MIN_NORMAL;
      System.arraycopy(next, 0, now, 0, n);
      for (int i = 0; i < n; i++) {
        maxDelta = Math.max(maxDelta, Math.abs(next[i] - now[i]));
      }
      if (count >= maxIteration || maxDelta < error) {
        break;
      }
    }
    return next;
  }

  public static double Newton(Function<Double, Double> f, double error,
      int maxIteration) {
    double now = 0, next = 0;
    for (int i = 0; i < maxIteration; i++) {
      now = next;
      double x1 = f.apply(now);
      double x2 = f.apply(x1);
      next = now - Math.pow(x2 - x1, 2) / (x2 - 2 * x1 + now);
      if (Math.abs(next - now) > error) {
        break;
      }
    }
    return next;
  }

  public static double Steffensen(Function<Double, Double> f, double error,
      int maxIteration) {
    double now = 0, next = 0;
    for (int i = 0; i < maxIteration; i++) {
      now = next;
      next = now - f.apply(now);
      if (Math.abs(next - now) > error) {
        break;
      }
    }
    return next;
  }

  public static void main(String[] args) {
    int max = 1000;
    double e = Math.pow(10, -6);
    double[] init = {0, 0, 0, 0};
    double[][] A = {
        {8.3, 2.1, -1.2, 0.5},
        {0.8, 10.2, 3.5, -1.8},
        {1.2, 0.2, -4, -0.5},
        {-0.2, 0.3, 0.4, -2}
    };
    double B[] = {-3.02, 4.79, -6.72, 8.89};
    System.out.println(Arrays.toString(Jacobi(A, e, init, B, max)));
    System.out.println(Arrays.toString(Seidel(A, e, init, B, max)));
    double[][] A2 = {
        {5, 2, 1},
        {-1, 4, 1},
        {2, -3, -4}
    };
    double[] init2 = {0, 0, 0};
    double B2[] = {5.2, -6.2, -4.9};
    System.out.println(Arrays.toString(sor(A2, e, init2, B2, max, 1.25)));

    Function<Double, Double> f1 = (x) -> x - (Math.exp(x) - x * x + 3 * x - 2) / (
        Math.exp(x) - 2 * x + 3);
    Function<Double, Double> f2 = (x) ->
        x - (x * x + 10 * Math.cos(x)) / (2 * x - 10 * Math.sin(x));
    System.out.println(Newton(f1, e, max));
    System.out.println(Steffensen(f2, e, max));
  }
}
