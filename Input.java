import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Input {

	public static ArrayList<String> parsePunct(String filename){		

		ArrayList<String> result = new ArrayList<String>();

		String delimiters = ".,:?";	//Define punctuation marks to use as delimiters

		try{
			FileReader fr = new FileReader(filename);
			BufferedReader reader = new BufferedReader(fr);
			while(true){
				String line = reader.readLine();
				if(line==null) break;
				StringTokenizer tok = new StringTokenizer(line, delimiters);

				while(true){
					if(!tok.hasMoreTokens()) break;
					String phrase = tok.nextToken();
					phrase=phrase.trim();
					phrase=phrase.toLowerCase();
					if(!phrase.isEmpty()) result.add(phrase);
				}

			}
			reader.close();
		}
		catch(Exception e){
			System.out.println("Error reading input file");
			//e.printStackTrace();
		}
		return result;
	}

	public static void getKeywords(ArrayList<String> phrases, String stopWordsFile, ArrayList<String> allWords, ArrayList<ArrayList<Integer>> keywords) {
		ArrayList<String> stopWords = new ArrayList<String>();

		try{
			FileReader fr = new FileReader(stopWordsFile);
			BufferedReader reader = new BufferedReader(fr);
			while(true){
				String word = reader.readLine();
				if(word==null) break;
				word=word.trim().toLowerCase();
				stopWords.add(word);
			}
			reader.close();
		}catch(Exception e){
			System.out.println("Error parsing stopwords file");
			//e.printStackTrace();
		}

		for(String phrase:phrases){
			boolean startKeyword=true;
			ArrayList<Integer> keyword=null;
			StringTokenizer tok = new StringTokenizer(phrase, " ");
			while(true){
				if(!tok.hasMoreTokens()) {
					if(!keyword.isEmpty()&&(!startKeyword)) keywords.add(keyword);
					break;
				}
				String word = tok.nextToken();
				word=word.trim();
				if(startKeyword){
					if(Utils.wordExists(word, stopWords)==-1){//non stop word
						keyword=new ArrayList<Integer>();
						startKeyword=false;
						int x=Utils.wordExists(word, allWords);
						if(x!=-1) keyword.add(x);
						else {
							allWords.add(word);
							keyword.add(allWords.size()-1);
						}
					}else{//stop word
						//do nothing
					}
				}else{//startKeyword is false
					if(Utils.wordExists(word, stopWords)==-1){//non stop word
						int x=Utils.wordExists(word, allWords);
						if(x!=-1) keyword.add(x);
						else{
							allWords.add(word);
							keyword.add(allWords.size()-1);
						}						
					}else{//stop word
						startKeyword=true;
						if(!keyword.isEmpty()) keywords.add(keyword);
					}
				}
			}
		}
	}

}
