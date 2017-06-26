import java.util.ArrayList;

public class mainClass {
	
	public static void main(String args[]){
		ArrayList<String> phrases=Input.parsePunct("input.txt");
		ArrayList<String> allWords = new ArrayList<String>();
		ArrayList<ArrayList<Integer>> keywords = new ArrayList<ArrayList<Integer>>();
		//Read input
		Input.getKeywords(phrases, "stopwords.txt", allWords, keywords);
		
		int N = allWords.size();
		int[] deg = new int[N];
		int[] freq = new int[N];
		double[] ratio = new double[N];
		
		//main algorithm
		int[][] coOccuranceMatrix = CalcMetrics.coOccuranceMatrix(allWords, keywords);
		CalcMetrics.wordScores(N, coOccuranceMatrix, deg, freq, ratio);
		ArrayList<ResultClass> finalKeywords=CalcMetrics.keywordScores(allWords, ratio, keywords);
		
		//Output results
		Utils.printCoOccM(allWords, coOccuranceMatrix);
		Utils.printWordScores(allWords, deg, freq, ratio);
		Utils.printResults(finalKeywords);	

	}

}
