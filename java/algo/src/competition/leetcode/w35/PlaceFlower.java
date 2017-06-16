package competition.leetcode.w35;

/**
 * Created by zzt on 6/4/17.
 * <p>
 * <h3></h3>
 */
public class PlaceFlower {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int s = -2, e = flowerbed.length + 1;
        int m = s;
        int res = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                m = i;
                res += count(s, m);
                s = m;
            }
        }
        res += count(m, e);
        return res >= n;
    }

    private int count(int s, int e) {
        int i = e - 1 - (s + 1) - 1;
        return i < 0 ? 0 : (i % 2 == 0 ? i / 2 : i / 2 + 1);
    }

    public static void main(String[] args) {
        PlaceFlower flower = new PlaceFlower();
        System.out.println(flower.canPlaceFlowers(new int[]{0}, 1));
        System.out.println(flower.canPlaceFlowers(new int[]{0, 0}, 1));
        System.out.println(flower.canPlaceFlowers(new int[]{1, 0, 0}, 1));
        System.out.println(flower.canPlaceFlowers(new int[]{1, 0, 0, 1}, 1));
        System.out.println(flower.canPlaceFlowers(new int[]{0, 0, 0, 0, 0, 0}, 3));
        System.out.println(flower.canPlaceFlowers(new int[]{1, 0, 0, 1}, 0));
        System.out.println(flower.canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 1}, 1));
        System.out.println(flower.canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 1}, 2));
        System.out.println(flower.canPlaceFlowers(new int[]{0, 0, 0, 0, 0, 1}, 2));
        System.out.println(flower.canPlaceFlowers(new int[]{0, 0, 0, 0, 0, 1}, 3));
    }
}
