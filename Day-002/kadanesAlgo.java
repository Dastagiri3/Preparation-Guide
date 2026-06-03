// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
class Solution {
    Solution() {
    }

    public int maxSubArray(int[] var1) {
        int var2 = var1[0];
        int var3 = var1[0];

        for (int var4 = 1; var4 < var1.length; ++var4) {
            var3 = Math.max(var1[var4], var3 + var1[var4]);
            var2 = Math.max(var2, var3);
        }

        return var2;
    }
}
