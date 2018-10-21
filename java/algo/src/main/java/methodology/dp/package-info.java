/**
 * Like Divide and Conquer, Dynamic Programming combines solutions to sub-problems.
 * Dynamic Programming is mainly used when solutions of same subproblems are needed again and again.
 * In dynamic programming, computed solutions to subproblems are stored in a table
 * so that these don’t have to be recomputed.
 * So Dynamic Programming is not useful when there are no common (overlapping) subproblems
 * because there is no point storing the solutions if they are not needed again.
 *
 * A given problems has Optimal Substructure Property
 * if optimal solution of the given problem can be obtained by using optimal solutions of its subproblems.
 *
 * In order to use DP, the problem state have to be able to represented by one single value (dp[x][y], O(1) is fine),
 * so that we can deduce next state. If the problem state is represented by O(n) value or more, like board games,
 * it can't use DP.
 */
package methodology.dp;
