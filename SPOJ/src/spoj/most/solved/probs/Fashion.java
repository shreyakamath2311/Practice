package spoj.most.solved.probs;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Fashion {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		LinkedHashMap<int[], int[]> map = new LinkedHashMap<int[], int[]>();
		for(int i=0;i<test;i++){
			int n = scan.nextInt();
			int[] man = new int[n];
			int[] woman = new int[n];
			for(int j=0;j<n;j++) man[j]=scan.nextInt();
			for(int j=0;j<n;j++) woman[j]=scan.nextInt();
			map.put(man, woman);
		}
		
		for(int[] man : map.keySet()){
			int[] woman = map.get(man);
			int length = man.length;
			int total=0;
			
			for(int i=1;i<length;i++){
				int key = man[i];int j =i-1;
				while(j>=0 && man[j]>key){
					man[j+1] = man[j];j--;
				}
				man[j+1] = key;
			}
			
			for(int i=1;i<length;i++){
				int key = woman[i];int j =i-1;
				while(j>=0 && woman[j]>key){
					woman[j+1] = woman[j];j--;
				}
				woman[j+1] = key;
			}
			
			for(int i=0;i<length;i++){
				total+=man[i]*woman[i];
			}
			System.out.println(total);
		}
	}

}
