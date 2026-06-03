class Solution {
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int i = nums.length - 2;
        // Step 1: Find the first decreasing element from the right
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        
        // Step 2: If the entire array is not decreasing
        if (i >= 0) {
            int j = nums.length - 1;
            // Find the element just larger than nums[i] from the right
            while (nums[j] <= nums[i]) {
                j--;
            }
            // Swap them
            swap(nums, i, j);
        }
        
        // Step 3: Reverse the elements after index i to get the next lexicographical order
        reverse(nums, i + 1, nums.length - 1);
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Test Case 1 ===");
        int[] nums1 = {1, 2, 3};
        System.out.print("Original Array: ");
        printArray(nums1);
        nextPermutation(nums1);
        System.out.print("Next Permutation: ");
        printArray(nums1);

        System.out.println("\n=== Test Case 2 ===");
        int[] nums2 = {3, 2, 1};
        System.out.print("Original Array: ");
        printArray(nums2);
        nextPermutation(nums2);
        System.out.print("Next Permutation: ");
        printArray(nums2);

        System.out.println("\n=== Test Case 3 ===");
        int[] nums3 = {1, 1, 5};
        System.out.print("Original Array: ");
        printArray(nums3);
        nextPermutation(nums3);
        System.out.print("Next Permutation: ");
        printArray(nums3);

        System.out.println("\n=== Test Case 4 ===");
        int[] nums4 = {1, 3, 2};
        System.out.print("Original Array: ");
        printArray(nums4);
        nextPermutation(nums4);
        System.out.print("Next Permutation: ");
        printArray(nums4);
    }

    private static void printArray(int[] nums) {
        System.out.print("[");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            if (i < nums.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}

/*ప్రాబ్లెం: ఒక పూర్ణసంఖ్యల (integers) అమరిక (array) ఇస్తే, దాని తదుపరి నిఘంటువు క్రమ అమరికను (next lexicographically greater permutation) కనుగొనాలి. 
ఒకవేళ అలాంటి అమరిక సాధ్యం కాకపోతే (అనగా ఇచ్చిన అమరిక చివరి అమరిక అయితే), దానిని అతి తక్కువ క్రమ అమరికగా (ఆరోహణ క్రమం - ascending order లో) మార్చాలి. 
ఈ మార్పులను అదనపు మెమరీ ఉపయోగించకుండా (O(1) auxiliary space) ఇన్-ప్లేస్ (in-place) గా చేయాలి.

కోడ్ వివరణ:

1. step 1: కుడి నుండి ఎడమకు ప్రయాణిస్తూ, nums[i] < nums[i+1] అయ్యే మొదటి ఇండెక్స్ `i` ని కనుగొనాలి.
   - ఒకవేళ అలాంటి ఇండెక్స్ `i` లభించకపోతే (అనగా i = -1), ఇచ్చిన అమరిక పూర్తిగా అవరోహణ క్రమంలో (descending order) ఉన్నట్లు. అప్పుడు డైరెక్ట్ గా స్టెప్ 3 కి వెళ్ళి మొత్తం అమరికను రివర్స్ చేస్తే సరిపోతుంది.

2. step 2: ఒకవేళ ఇండెక్స్ `i` లభిస్తే (i >= 0), మళ్ళీ కుడి నుండి ప్రయాణిస్తూ nums[i] కంటే పెద్దదైన మొదటి ఎలిమెంట్ ఇండెక్స్ `j` ని కనుగొనాలి.
   - nums[i] మరియు nums[j] లను swap చేయాలి.

3. step 3: ఇండెక్స్ `i+1` నుండి చివరి వరకు ఉన్న భాగమును రివర్స్ (reverse) చేయాలి.
   - దీనివల్ల ఆ భాగం ఆరోహణ క్రమంలోకి మారి, ఆ అమరిక తదుపరి అతి చిన్న నిఘంటువు అమరికగా మారుతుంది.

సమయ సంక్లిష్టత (Time Complexity): O(N) - ఇక్కడ N అనేది అమరిక పొడవు. ఒకే ఒక పాస్ తో ప్రక్క ప్రక్క ఎలిమెంట్స్ ను పోల్చడం, మరియు రివర్స్ చేయడం జరుగుతుంది.
స్థల సంక్లిష్టత (Space Complexity): O(1) - అదనంగా ఎటువంటి డేటా స్ట్రక్చర్ ఉపయోగించలేదు కాబట్టి స్థిరమైన స్థలాన్ని తీసుకుంటుంది.*/
