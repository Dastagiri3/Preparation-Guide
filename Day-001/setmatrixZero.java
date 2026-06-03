class Solution {
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        boolean firstRowZero = false;
        boolean firstColZero = false;
        
        // Check if first row has zero
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }
        
        // Check if first column has zero
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }
        
        // Use first row and first column as markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        // Set zeros for inner matrix based on markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // Set first row to zero if needed
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
        
        // Set first column to zero if needed
        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix1 = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };

        System.out.println("=== Test Case 1 ===");
        System.out.println("Original Matrix:");
        printMatrix(matrix1);

        setZeroes(matrix1);

        System.out.println("Matrix after setZeroes:");
        printMatrix(matrix1);

        System.out.println("\n=== Test Case 2 ===");
        int[][] matrix2 = {
            {0, 1, 2, 0},
            {3, 4, 5, 2},
            {1, 3, 1, 5}
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix2);

        setZeroes(matrix2);

        System.out.println("Matrix after setZeroes:");
        printMatrix(matrix2);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}


/*ప్రాబ్లెం: ఒక m x n మ్యాట్రిక్స్ ఇస్తే, దానిలో ఏదైనా ఎలిమెంట్ 0 అయితే ఆ మొత్తం అడ్డు వరుస (row) మరియు నిలువు వరుస (column) ను 0 గా మార్చాలి. ఇన్-ప్లేస్ (in-place) గా చేయాలి.

కోడ్ వివరణ:

int m = matrix.length;
మ్యాట్రిక్స్ లోని అడ్డు వరుసల సంఖ్యను m లో నిల్వ చేస్తుంది.

int n = matrix[0].length;
నిలువు వరుసల సంఖ్యను n లో నిల్వ చేస్తుంది.

boolean firstRowZero = false;
మొదటి అడ్డు వరుస (row 0) లో 0 ఉందో లేదో గుర్తుంచుకోవడానికి ఒక బూలియన్ వేరియబుల్.

boolean firstColZero = false;
మొదటి నిలువు వరుస (column 0) లో 0 ఉందో లేదో గుర్తుంచుకోవడానికి ఇంకొక బూలియన్ వేరియబుల్.

మొదటి లూప్:
for (int j = 0; j < n; j++) { if (matrix[0][j] == 0) { firstRowZero = true; break; } }

మొదటి అడ్డు వరుసలో ప్రతి ఎలిమెంట్ తనిఖీ చేస్తుంది.

ఎక్కడైనా 0 కనిపిస్తే firstRowZero ని true చేసి లూప్ నుండి బయటకు వస్తుంది.

రెండవ లూప్:
for (int i = 0; i < m; i++) { if (matrix[i][0] == 0) { firstColZero = true; break; } }

మొదటి నిలువు వరుసలో ప్రతి ఎలిమెంట్ తనిఖీ చేస్తుంది.

0 కనిపిస్తే firstColZero ని true చేస్తుంది.

మార్కర్లను సెట్ చేసే లూప్:
for (int i = 1; i < m; i++) { for (int j = 1; j < n; j++) { if (matrix[i][j] == 0) { matrix[i][0] = 0; matrix[0][j] = 0; } } }

మొదటి అడ్డు వరుసను, మొదటి నిలువు వరుసను మినహాయించి మిగిలిన అన్ని సెల్లను (i=1 నుండి, j=1 నుండి) చూస్తుంది.

ఏదైనా సెల్ 0 అయితే, దాని అడ్డు వరుసకు సూచికగా matrix[i][0] ని 0 గా, దాని నిలువు వరుసకు సూచికగా matrix[0][j] ని 0 గా మారుస్తుంది.

ఇలా చేయడం వలన అదనపు మెమరీ లేకుండా ఏ రోలు, ఏ కాలమ్ లో 0 ఉందో గుర్తించుకోవచ్చు.

మార్కర్ల ఆధారంగా లోపలి మ్యాట్రిక్స్ ను 0 చేసే లూప్:
for (int i = 1; i < m; i++) { for (int j = 1; j < n; j++) { if (matrix[i][0] == 0 || matrix[0][j] == 0) { matrix[i][j] = 0; } } }

మళ్ళీ అదే (i=1, j=1) ప్రాంతంలో ప్రతి సెల్ ని చూస్తుంది.

ఆ సెల్ యొక్క అడ్డు వరుస (మొదటి కాలమ్ విలువ matrix[i][0]) లేదా నిలువు వరుస (మొదటి అడ్డు వరుస విలువ matrix[0][j]) 0 అయితే, ఆ సెల్ ని 0 గా మారుస్తుంది.

మొదటి అడ్డు వరుసను 0 చేయడం (అవసరమైతే):
if (firstRowZero) { for (int j = 0; j < n; j++) { matrix[0][j] = 0; } }

ముందే గుర్తుపెట్టుకున్న firstRowZero నిజమైతే, మొదటి అడ్డు వరుసలోని అన్ని ఎలిమెంట్లను 0 చేస్తుంది.

మొదటి నిలువు వరుసను 0 చేయడం (అవసరమైతే):
if (firstColZero) { for (int i = 0; i < m; i++) { matrix[i][0] = 0; } }

firstColZero నిజమైతే, మొదటి నిలువు వరుసలోని అన్ని ఎలిమెంట్లను 0 చేస్తుంది.

ముగింపు: ఈ విధంగా ఎక్కువ మెమరీ ఉపయోగించకుండా (O(1) space) మ్యాట్రిక్స్ లో 0 ఉన్న రోలు మరియు కాలమ్ లను పూర్తిగా 0 గా మార్చవచ్చు.*/