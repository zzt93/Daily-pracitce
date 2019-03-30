  
## General Problem Solving
 
 - Similar problem: what’s the differences here?
 - Specific example: understand how it works
 - General case
 - Reverse: induction from conclusion
 - 避免错误的法宝：将loop或recur的不变式写下来

## Resources

 - [GeeksforGeeks](https://www.geeksforgeeks.org/fundamentals-of-algorithms/)
 - [Leetcode](https://leetcode.com/problemset/algorithms/)
 - [Tech guide with google](https://techdevguide.withgoogle.com/)

## Methodology

### Random

- 适用范围：存在性问题
- e.g. 两个等式是否相等

 
shuffle: 袋子中摸球不放回，按顺序排在外面，每次选择范围变小了
random sample：袋子中选取一部分球--shuffle特例
 
### Dynamic programming(WIS, knapsack, lcs)
 - 最值问题
 - 数列递归式：F(n) = F(n-1) + f(n) -- 核心是如何定义子问题
 - 维度：操作顺序；数组维度（1d，2d）： 如果发现单1维度不能推导，不妨增加维度，增加信息([max vacation](https://mikecoder.github.io/oj-code/2017/05/01/MaximumVacationDays/): 按照天数的维度不行，加上城市的维度；[min swap](https://leetcode.com/contest/weekly-contest-76/problems/minimum-swaps-to-make-sequences-increasing/): 按照数组的维度不行，加上是否swap)
 - 方向：正向，逆向
 
[https://en.wikipedia.org/wiki/Bellman_equation](https://en.wikipedia.org/wiki/Bellman_equation)
 
Dynamic programming: from small to big. more like analyse a linear queue.
Very easy to be used in some linear problem. e.g.
([revenge of pancake](https://code.google.com/codejam/contest/6254486/dashboard#s=p1))
([a tutorial and some example](https://www.topcoder.com/community/data-science/data-science-tutorials/dynamic-programming-from-novice-to-advanced/))
 
 - index from 0 → n
 - index from n → 0: [slides: the reverse direction of sub-structure of problem](https://code.google.com/codejam/contest/4314486/dashboard#s=p1)
 
After solving several "Game Playing" questions in leetcode, we can find them to be pretty similar. Most of them can be solved using the top-down DP approach, which "brute-forcely" simulates every possible state of the game.
The key part for the top-down dp strategy is that we need to avoid repeatedly solving sub-problems. Instead, we should use some strategy to "remember" the outcome of sub-problems. Then when we see them again, we instantly know their result.
If we don’t need to know the state of game (like chess board) but just a number/statistic of game, we can usually reduce the complexity from O(n!) / O(2^n) to O(n) after memorization.
### Divide and Conquer
Divide and conquer and Dynamic are the same in essence. They are all solving the small problem to solve big one. They all start from small to big.
 
 - first make condition true, then recursion: quick sort
 - first recursion, then make it true for large problem: merge sort
 
example:
 - to reverse an array -- to reverse bit in a integer: based on (a*b)^r = (b^r)(a^r)
 - [slides: the reverse direction of sub-structure of problem](https://code.google.com/codejam/contest/4314486/dashboard#s=p1)
 
 
longest-common-substring: find continuous sequence, sub-string <--> longest palindrom substring
max sum of sub-vector: can find result of state of n just from state of n-1
longest-common-subsequence: find sequence ←---> longest increasing sequence: find the result of next state from all previous state
 
subsequence definition:
 1.delete char in longer to match shorter -- time complexity to high
 2.for each char in shorter string, find in longer -- with Map<char, TreeSet<Integer>>
 
 
#### Sequence or not

 - sequence: 子问题就是本身
 - sub-(string/vector): 子问题多是“xxxEndHere, xxxStartHere”，因为要求连续，所以子问题要想推出父问题，子问题的答案必须在其边界，才能与父问题连续起来
 
### Greedy (minimum spinning tree)
 
### Mis

#### Quick way to find some elements(dynamic sets == associative array)
 
 - Binary search tree
 - Sorted array with binary search
 - B tree -- red-black tree
 - Bloom filter
 - Skip list
 - Selection problem: find k-th element; find k smallest element(first find k-th, then iterate)
     - heap
     - quick sort like
 - When comes to string(or array of T), prefix tree/ hashing also works well

#### Tree
 - Asking the right question, exclude more possibility叶节点
 - 压扁平衡二叉树 -> 二分搜索（点切分线）； 平衡二叉树中的节点由数字泛化为线段(升维度) -> [Interval tree (naive approach)](https://en.wikipedia.org/wiki/Interval_tree) （给定许多线段，找出与特定线段或点相交的所有线段），最终转化为点（线段中间某点）切分线空间，利用了分类 -> [线段树（segment tree)](https://en.wikipedia.org/wiki/Segment_tree) （线切分线）给定许多线段，找点(k dimension)是否在线段(k+1 dimension)中 -> [kd-tree](https://en.wikipedia.org/wiki/K-d_tree) （线切分面）找点
 - [range tree](https://en.wikipedia.org/wiki/Range_tree) 点切分线（与B+树类似，只有叶节点为输入数据 （先给定许多点，再给定一个线段，寻找在线段范围内的点）
 - [a question about this](http://stackoverflow.com/questions/17466218/what-are-the-differences-between-segment-trees-interval-trees-binary-indexed-t)
 
#### Find string
 1.kmp: prefix and suffix
 2.[suffix tree:](http://stackoverflow.com/questions/9452701/ukkonens-suffix-tree-algorithm-in-plain-english) a compressed trie of all suffix of string; core differences between tire -- the suffix link, which point to the same suffix
 3.prefix tree(tire): handle a collection of string
 
e.g. longest palindromic substring(与最长公共字串的联系与区别), 最长重复子串（abcdabcefda里abc同da都重复出现，而最长重复子串是abc -- trie or suffix tree or suffix array）， 最长公共子串（找出字符串S1同S2的最长公共子串。注意不是常用作动态规划例子的LCS-sequence哈。比如字符串acdfg同akdfc的最长公共子串为df，而他们的LCS是adf）
 
3. Trie (prefix tree): handle a set of string, handle the prefix look up
 
#### Find Integer
 1.bitmap
#### 分类方法
 - 并查集
 - 图（线性，树形）：涂色
 - give the same signature to the elements in the same equivalent class then to find a specific signature(turning the problem into a finding problem, then sort/hash …)
 
分类与查找在本质上是相似的； 分类是按照一定标准将原来的集合切分成多个等价类，查找则是从这些个等价类中找出一个或多个符合要求的。
分类可以在某些情况下加快查找的速度。
 

#### Inspiring example:
 - longest-palindromic-substring: S = “abaaba”, T = “#a#b#a#a#b#a#”. [More](http://articles.leetcode.com/longest-palindromic-substring-part-ii/)
 - 矩形联通问题：随机从其中抽取一定数量的格子，问从一边是否可以通过选中的格子到达对边 -- 多加两个格子，连接（抽象）首行（所有开始格子）、连接末行（所有结束格子）-- disjoint set
 - 对forest进行遍历 -- 将forest变成tree： 添加一个共同的开始节点
 - rotation match: abc & bca is rotation match?
 - rotate string & compare
 - add index offset & compare
 - (A+A).indexOf(B)
 
 
#### Use of binary search:
 - 二分法解方程
 - quicksort/selection problem
 - tree
 - program debug
 - 4*10^9 integers, find a missing one: classify integers into two categories: this bit is 0 or 1; 4.3*10^9 integers, find a duplicate one. 鸽巢原理
 
 
 
 

#### 排列、组合问题：递归
#### 链表技巧：
 - empty head/ fake head
 - multiple pointer at different position, move at the same time
 - save special pointer before setting
 
#### 估算判断方法！
 - 2**n, n! --> dp
 - 几千？brute force -- 3600s 秒换算
 - 10**4, n**2 -- TLE
 
 
#### 部分正确
 - WA
 - Corner case
 - 边界：最大，最小，Int_MAX,MIN,0
 - overflow
 - TLE
 
##### Longest Palindromic substring
 - definition method:
     - dp -- dimension is string index, O(n**2)
     - 2n-1 center, expand to find -- O(n**2)
     - [Manacher’s algo](https://articles.leetcode.com/longest-palindromic-substring-part-ii/)
 - conversion:
     - LCS + position check: reverse, then compare to find common. e.g. cdeaaedc
     - Longest repeated substring (suffix array solution) + position check: reverse and connect, then compare to find repeated part. e.g. cdefabacdef
 
 
#### Detect Loop in Graph
 - Slow fast pointer
 - Dfs with visiting set
 - Dfs with node state change
 
