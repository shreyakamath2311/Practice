

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class PermOrdered {
public static void main(String args[] ) throws Exception {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    Scanner scan = new Scanner(System.in);
    double m = scan.nextDouble();
    int n = scan.nextInt();
    String str ="";
    for(int i=0;i<n;i++){
        str=str+i;
    }
    List<String> list = new ArrayList<String>();
    permutation("", str,list,m,0); 
      String ans= list.get(0);
    for(int i =0;i<ans.length();i++)
    	System.out.print(ans.charAt(i)+" ");
        
}
    private static void permutation(String prefix, String str,List<String> list,double m , int current){
        int n = str.length();
        if (n == 0) {
            current=current+1;
            if(current==m-1)
            list.add(prefix);
        }
            
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), 
            str.substring(0, i) + str.substring(i+1),list,m,current);
        }
    }
}