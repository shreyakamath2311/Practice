package spoj.most.solved.probs;

import java.util.*;

/*
 * http://www.spoj.com/problems/ACPC10A/
 */

public class WhatNext {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		List<Integer> n1 = new ArrayList<Integer>();
		List<Integer> n2 = new ArrayList<Integer>();
		List<Integer> n3 = new ArrayList<Integer>();
		int i=0;
		int m1,m2,m3;
		while(scan.hasNext()){
			m1 = scan.nextInt();m2 = scan.nextInt();m3 = scan.nextInt();
			if(m1==0 &&m2==0 && m3==0) break;
			n1.add(i, m1);
			n2.add(i, m2);
			n3.add(i, m3);
			i++;
		}
		float a,b,c;
		for(int j=0;j<i;j++){
			a= n1.get(j);b = n2.get(j);c = n3.get(j);
			if((b-a) == (c-b)) {
				System.out.println("AP "+(int)(c+(c-b)));
			} else {
				float ans = c*(c/b);
				System.out.println("GP "+(int)ans);
			}
		}
	}

}
