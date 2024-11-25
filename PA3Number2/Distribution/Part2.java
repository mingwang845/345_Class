import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {
    private static int next;
    private static int[][] vals = new int[][]{{5, 10, 15}, {8, 16, 25}, {5, 10, 50}};
    private static int[][] expCount = new int[][]{{155,183,261}, {436,714,978}, {1250,1716,6071}};
    public static void main(String[] args) {
	int testNum = 1;
	String input = null;
	double score = 0.0;

	while(testNum <= 3) {
	    for(int i = 0; i < 3; i++) 
		score += runTest(testNum, i);
	    testNum++;
	}
	System.out.println("Expected score: " + score);
    }

    /***
     *Should read the integers in from the file and 
     *maintain a list of the top m.
     ***/
	public static Array getTop(String fn, int m) {
		Array nums = new Array(fn);
		Array highest = new Array(m);
	
		for (int i = 0; i < m; i++) {
			highest.setVal(i, nums.getVal(i));
		}
	
		int minIndex = findMinIndex(highest);
	
		for (int i = m; i < nums.length(); i++) {
			int currentValue = nums.getVal(i);
				if (currentValue > highest.getVal(minIndex)) {
				highest.setVal(minIndex, currentValue);
				minIndex = findMinIndex(highest);
			}
		}
		return highest;
	}
	

	private static int findMinIndex(Array arr) {
		int minIndex = 0;
		int minValue = arr.getVal(0);
	
		for (int i = 1; i < arr.length(); i++) {
			int currentValue = arr.getVal(i);
			if (currentValue < minValue) {
				minValue = currentValue;
				minIndex = i;
			}
		}
	
		return minIndex;
	}

    

    

    /***
     *These methods are provided for testing purposes,
     *so it is recommended that you do not change them. 
     *However, the grading is done in another class and
     *will only call your getTop method from here, so
     *if you do change the methods, that is fine (as long
     *as they still compile).
     ***/
    private static double runTest(int testNum, int i) {
	double score = 0.0;
	String input = "input" + testNum + ".txt";
	int m = vals[testNum-1][i];
	System.out.println("\nRunning on " + input + " with m = " + m + "...");
	System.out.println("***********************************************\n");
	Array top = Part2.getTop(input, m);
	//Part2.printTop(top);
	String output = "output" + testNum + "_" + (i+1) + ".txt";
	int[] exp = getExp(output, m);
	if(checkVals(top, exp)) {
	    System.out.println("Accuracy check passed!");
	    score += 1.0;
	}
	score += checkCount(top.getAccessCount(), expCount[testNum-1][i]);
	return score;
    }

    private static double checkCount(int act, int exp) {
	if(act < exp/2) {
	    System.out.println("Something does not appear correct. The access count seems too low.");
	    printCounts(act, exp);
	    return 0.0;
	}
	if(act <= exp) {
	    System.out.println("Access count looks good!");
	    printCounts(act, exp);
	    return 0.5;
	}
	if(act <= 2*exp) {
	    System.out.println("Access count looks okay but could be better.");
	    printCounts(act, exp);
	    return 0.25;
	}
	System.out.println("Access count looks too high.");
	printCounts(act, exp);
	return 0.0;
    }

    private static void printCounts(int act, int exp) {
	System.out.println("Your count: " + act);
	System.out.println("My count: " + exp);
    }

    private static int[] getExp(String output, int m) {
	int[] exp = new int[m];
	BufferedReader br;
	try {
	    br = new BufferedReader(new FileReader(output));
	    String line = br.readLine();
	    int i = 0;
	    while(line != null) {
		int n = Integer.parseInt(line);
		exp[i] = n;
		i++;
		line = br.readLine();
	    }
	    br.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return exp;
    }

    private static boolean checkVals(Array act, int[] exp) {
	return act.compare(exp);
    }

    private static void printTop(int[] top) {
	for(int i = 0; i < top.length; i++) 
	    System.out.println(top[i]);
    }

    private static void sort(int[] top) {
	int i = 1;
	while(i < top.length) {
	    int j = i;
	    int k = j-1;
	    while(j > 0 && top[j] < top[k]) {
		int temp = top[k];
		top[k] = top[j];
		top[j] = temp;
		j--;
		k--;
	    }
	    i++;
	}
    }
}
	
