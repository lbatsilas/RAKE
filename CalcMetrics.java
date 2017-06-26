import java.util.ArrayList;
import java.util.Collections;

public class CalcMetrics {
	
	public static int[][] coOccuranceMatrix(ArrayList<String> words, ArrayList<ArrayList<Integer>> keywords){
		int N=words.size();
		int[][] coOccMat = new int[N][N];
		
		for(int i=0; i<N; i++) for(int j=0; j<N; j++) coOccMat[i][j]=0;
		
		for(ArrayList<Integer> keyword:keywords){
			switch(keyword.size()){
			case 3:
				coOccMat[keyword.get(2)][keyword.get(0)]++;
				coOccMat[keyword.get(2)][keyword.get(1)]++;
				coOccMat[keyword.get(2)][keyword.get(2)]++;
				coOccMat[keyword.get(0)][keyword.get(2)]++;
				coOccMat[keyword.get(1)][keyword.get(2)]++;
			case 2:
				coOccMat[keyword.get(1)][keyword.get(0)]++;
				coOccMat[keyword.get(1)][keyword.get(1)]++;
				coOccMat[keyword.get(0)][keyword.get(1)]++;				
			case 1:
				coOccMat[keyword.get(0)][keyword.get(0)]++;
				break;				
			}
		}
		return coOccMat;
	}
	
	public static void wordScores(int N, int[][]  coOccuranceMatrix, int[] deg, int[] freq, double[] ratio){
		for(int i=0; i<N; i++){
			deg[i]=0;
			for(int j=0; j<N; j++) deg[i]+=coOccuranceMatrix[i][j];
			freq[i]=coOccuranceMatrix[i][i];
			ratio[i]=((double) deg[i])/freq[i];
		}
	}
	
	public static ArrayList<ResultClass> keywordScores(ArrayList<String> words, double[] wordScores, ArrayList<ArrayList<Integer>> keywords){
		ArrayList<ResultClass> result=new ArrayList<ResultClass>();
		
		for(ArrayList<Integer> keyword:keywords){
			String str="";
			int i;
			for(i=0; i<keyword.size()-1; i++) str+=words.get(keyword.get(i))+" ";
			str+=words.get(keyword.get(i));
			double score=0.0;
			for(Integer ind:keyword) score+=wordScores[ind];
			ResultClass newKw = new ResultClass(str, score);
			if(!result.contains(newKw)) result.add(new ResultClass(str, score));
		}
		Collections.sort(result);
		return result;
	}

}
