package sorting;

/*
 * Consider the problem of adding two n-bit binary integers, stored in two n-element
arrays A and B. The sum of the two integers should be stored in binary form in an .n C 1/-element array C.
 */
public class AddBinNum {
	int[][] values; 
	int[] result;
	
	public AddBinNum(int[][] values) {
	this.values = values;
	result = new int[values[0].length];
	}
	
	public void addNumbers(){
		int totalDigits = values[0].length;
		int carryOver=0;
		for(int i =0; i <totalDigits; i++){
			if(values[0][i]+values[1][i] +carryOver == 2 ){result[i] = 0; carryOver=1;}	
			else if(values[0][i]+values[1][i] +carryOver == 3 ){result[i] = 1; carryOver=1;}	
			else if(values[0][i]+values[1][i] +carryOver == 1 ){result[i] = 1; carryOver=0;}	
			else {result[i]=0; carryOver=0;}
		}
	}	
	public void printResult(){
		for(int i=result.length-1; i >=0 ; i--)
			System.out.print(result[i]+", ");
	}
	
	public static void main(String[] args) {
	AddBinNum obj = new AddBinNum(new int[][]{{0,1,1,0},{0,1,1,0}});
	obj.addNumbers();
	obj.printResult();
	
	}

}
