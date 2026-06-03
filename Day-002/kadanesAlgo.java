// package Preparation-Guide.Day-002;
// Note: Package declarations in Java cannot contain hyphens (-). 
// Since 'Preparation-Guide' and 'Day-002' contain hyphens, keeping it in the default package allows it to compile successfully.

class Solution {
    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int currentMax = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            maxSoFar = Math.max(maxSoFar, currentMax);
        }
        return maxSoFar;
    }
}

public class kadanesAlgo {
    public static void main(String[] args) {
        Solution solver = new Solution();

        System.out.println("=== Test Case 1 ===");
        int[] nums1 = {2, 3, 5, -2, 7, -4};
        System.out.print("Input Array: ");
        printArray(nums1);
        int result1 = solver.maxSubArray(nums1);
        System.out.println("Max Subarray Sum: " + result1); // Expected: 15

        System.out.println("\n=== Test Case 2 ===");
        int[] nums2 = {-2, -3, -7, -2, -10, -4};
        System.out.print("Input Array: ");
        printArray(nums2);
        int result2 = solver.maxSubArray(nums2);
        System.out.println("Max Subarray Sum: " + result2); // Expected: -2

        System.out.println("\n=== Test Case 3 ===");
        int[] nums3 = {5, 4, -1, 7, 8};
        System.out.print("Input Array: ");
        printArray(nums3);
        int result3 = solver.maxSubArray(nums3);
        System.out.println("Max Subarray Sum: " + result3); // Expected: 23

        System.out.println("\n=== Test Case 4 ===");
        int[] nums4 = {-1};
        System.out.print("Input Array: ");
        printArray(nums4);
        int result4 = solver.maxSubArray(nums4);
        System.out.println("Max Subarray Sum: " + result4); // Expected: -1
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

/*ప్రాబ్లెం: ఒక పూర్ణసంఖ్యల అమరిక (array) ఇస్తే, దానిలోని అత్యధిక మొత్తం (largest sum) కలిగిన కంటిగ్యుయస్ (సమీప/పక్కపక్కనే ఉన్న) ఉప-అమరికను (subarray) కనుగొని, ఆ మొత్తాన్ని తిరిగి ఇవ్వాలి. 

కోడ్ వివరణ (కడానేస్ అల్గారిథమ్ - Kadane's Algorithm):
1. `maxSoFar` మరియు `currentMax` అనే రెండు వేరియబుల్స్ ను అమరికలోని మొదటి ఎలిమెంట్ `nums[0]` తో ప్రారంభిస్తాము.
2. ఇండెక్స్ 1 నుండి అమరిక ముగిసే వరకు ప్రతి ఎలిమెంట్ ను పరిశీలిస్తాము.
3. ప్రతి ఎలిమెంట్ వద్ద, ఆ ఎలిమెంట్ తో కొత్త ఉప-అమరికను ప్రారంభించాలా లేదా ప్రస్తుతం ఉన్న ఉప-అమరికతో కొనసాగించాలా అనేది నిశ్చయిస్తాము (`currentMax = Math.max(nums[i], currentMax + nums[i])`).
4. ఇప్పటివరకు వచ్చిన గరిష్ట మొత్తాన్ని `maxSoFar` లో భద్రపరుస్తాము (`maxSoFar = Math.max(maxSoFar, currentMax)`).
5. చివరగా `maxSoFar` ని రిటర్న్ చేస్తాము.

సమయ సంక్లిష్టత (Time Complexity): O(N) - అమరికలోని ప్రతి ఎలిమెంట్ ని ఒకే ఒకసారి పరిశీలిస్తాము.
స్థల సంక్లిష్టత (Space Complexity): O(1) - అదనంగా ఎటువంటి మెమరీని ఉపయోగించకుండా స్థిరమైన స్పేస్ లోనే పూర్తవుతుంది.*/
