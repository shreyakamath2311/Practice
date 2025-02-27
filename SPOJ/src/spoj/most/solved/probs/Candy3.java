package spoj.most.solved.probs;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Candy3 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		List<String> ans = new ArrayList<String>();
		for(int i=0;i<test;i++){
			scan.nextLine();
			int num = scan.nextInt();
			BigInteger[] val = new BigInteger[num];
			BigInteger total = new BigInteger(0+"");
			for(int j=0;j<num;j++){ val[j] = scan.nextBigInteger();total= total.add(val[j]);}
			if(total.mod(new BigInteger(num+"")) == BigInteger.ZERO) ans.add("YES");
			else ans.add("NO");
		}
		for(String str : ans)
			System.out.println(str);
	}
}

/*for(BigInteger j = BigInteger.ZERO;j.compareTo(num)==-1;j =j.add(BigInteger.ONE))*/