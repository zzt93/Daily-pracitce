package interview.leetcode._13x;

import interview.leetcode.RandomListNode;

import java.util.IdentityHashMap;

/**
 * Created by zzt on 10/25/17.
 * <p>
 * <h3></h3>
 */
public class CopyListWithRandomPointer {

    public RandomListNode copyRandomList(RandomListNode head) {
        IdentityHashMap<RandomListNode, RandomListNode> map = new IdentityHashMap<>();
        RandomListNode fake = new RandomListNode(0);
        RandomListNode now = fake;
        while (head != null) {
            if (!map.containsKey(head)) {
                now.next = new RandomListNode(head.label);
                map.put(head, now.next);
            } else {
                now.next = map.get(head);
            }
            if (head.random != null) {
                if (map.containsKey(head.random)) {
                    now.next.random = map.get(head.random);
                } else {
                    now.next.random = new RandomListNode(head.random.label);
                    map.put(head.random, now.next.random);
                }
            }
            now = now.next;
            head = head.next;
        }
        return fake.next;
    }

}


