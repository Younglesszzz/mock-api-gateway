import java.util.Arrays;

public class Test1 {
    public int[] productExceptSelf(int[] nums) {
        // productL[i]: the value is the product of all elelments before the index i
        int[] productL = new int[nums.length];
        int[] productR = new int[nums.length];

        productL[0] = 1;
        for (int i = 1; i < productL.length; i++) {
            productL[i] = productL[i - 1] * nums[i - 1];
        }
        System.out.println(Arrays.toString(productL));
        productR[productR.length - 1] = 1;
        for (int i = productR.length - 2; i >= 0; i--) {
            productR[i] = productR[i + 1] * nums[i + 1];
        }
        System.out.println(Arrays.toString(productR));
        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = productL[i] * productR[i];
        }
        return res;
    }
    public static void main(String[] args) {
        new Test1().productExceptSelf(new int[]{1, 2, 3, 4});
    }
}
