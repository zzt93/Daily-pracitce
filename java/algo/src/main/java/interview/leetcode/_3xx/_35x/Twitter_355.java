package interview.leetcode._3xx._35x;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzt
 */
public class Twitter_355 {

  private HashMap<Integer, HashSet<Integer>> t = new HashMap<>();
  private HashMap<Integer, HashSet<Integer>> fo = new HashMap<>();
  private LinkedList<Integer> feeds = new LinkedList<>();
  /** Initialize your data structure here. */
  public Twitter_355() {

  }

  /** Compose a new tweet. */
  public void postTweet(int userId, int tweetId) {
    feeds.add(tweetId);
    t.computeIfAbsent(userId, k -> new HashSet<>()).add(tweetId);
  }

  /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
  public List<Integer> getNewsFeed(int userId) {
    HashSet<Integer> users = fo.computeIfAbsent(userId, k->new HashSet<>());
    users.add(userId);
    HashSet<Integer> ts = new HashSet<>();
    HashSet<Integer> defaultValue = new HashSet<>();
    for (Integer uid: users) {
      ts.addAll(t.getOrDefault(uid, defaultValue));
    }
    LinkedList<Integer> res = new LinkedList<>();
    for (Integer i: feeds) {
      if (ts.contains(i)) {
        res.addFirst(i);
        if (res.size() > 10) res.removeLast();
      }
    }
    return res;
  }

  /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
  public void follow(int followerId, int followeeId) {
    fo.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
  }

  /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
  public void unfollow(int followerId, int followeeId) {
    fo.computeIfAbsent(followerId, k -> new HashSet<>()).remove(followeeId);
  }

}
