public class ResultClass implements Comparable<ResultClass>{
	
	private String keyword;
	private double score;
	
	double getScore(){
		return score;
	}
	
	String getKeyword(){
		return keyword;
	}
	
	public ResultClass(String keyword, double score){
		this.keyword=keyword;
		this.score=score;
	}
	
	public int compareTo(ResultClass o) {		 
		double diff = this.score - ((ResultClass) o).getScore();
		if(diff<0) return 1;
		else return -1;
	}
	
	public boolean equals(Object o){
		if(o instanceof ResultClass && ((ResultClass) o).getKeyword().equals(keyword)) return true;
		else return false;
	}

}
