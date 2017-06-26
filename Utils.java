import java.util.ArrayList;

public class Utils {

	public static int wordExists(String word, ArrayList<String> list){
		if(list.isEmpty()) return -1;	
		for(int i=0; i<list.size(); i++) if(word.equalsIgnoreCase(list.get(i))) return i;
		return -1;
	}

	public static void printWordScores(ArrayList<String> words, int[] deg, int[] freq, double[] ratio){
		for(int i=0; i<words.size(); i++) 
			if(words.get(i).length()<8) System.out.println(words.get(i)+"\t\t"+deg[i]+"\t"+freq[i]+"\t"+ratio[i]);
			else System.out.println(words.get(i)+"\t"+deg[i]+"\t"+freq[i]+"\t"+ratio[i]);
		System.out.println();
	}

	public static void printCoOccM(ArrayList<String> labels, int[][] A){
		int N=labels.size();
		System.out.print("\t\t\t");
		for(int i=1; i<=N; i++){
			if(i<10) System.out.print(i+"  ");
			else System.out.print(i+" ");
		}
		System.out.println();
		for(int i=0; i<N; i++){
			if(i<9) System.out.print(" "+(i+1)+".");
			else System.out.print(i+1+".");
			if(labels.get(i).length()<5) System.out.print(labels.get(i)+"\t\t\t");
			else if(labels.get(i).length()<13) System.out.print(labels.get(i)+"\t\t");
			else	System.out.print(labels.get(i)+"\t");
			for(int j=0; j<N; j++) System.out.print(A[i][j]+"  ");
			System.out.println();
		}
		System.out.println();
	}

	public static void printResults(ArrayList<ResultClass> results){
		for(ResultClass kwd:results){
			if(kwd.getKeyword().length()<8) System.out.print(kwd.getKeyword()+"\t\t\t");
			else if(kwd.getKeyword().length()<16) System.out.print(kwd.getKeyword()+"\t\t");
			else if(kwd.getKeyword().length()<24) System.out.print(kwd.getKeyword()+"\t");
			else if(kwd.getKeyword().length()<30) System.out.print(kwd.getKeyword());
			else System.out.print(kwd.getKeyword());

			System.out.println("\t"+kwd.getScore());
		}
		System.out.println();

	}

}
