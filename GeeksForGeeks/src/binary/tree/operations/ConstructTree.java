/**
 * http://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
 */
package binary.tree.operations;

import java.util.LinkedList;
import java.util.Queue;

class Node{
	String val;
	Node left;
	Node right;
	Node(String val){
		this.val=val;
	}
}
public class ConstructTree {
	Node root;
	LinkedList<Character> l1;
	String inorder;
	String preorder;
	
	ConstructTree(String inorder , String preorder){
		this.inorder=inorder;
		this.preorder=preorder;
		l1= new LinkedList<Character>();
		for(int i=0;i<preorder.length();i++)
			l1.addLast(preorder.charAt(i));
		root = new Node(inorder);
	}
	void construct(Node n1){
		if(n1==null) return;
		if(l1.isEmpty()) return;
		char n = l1.removeFirst();
		String val = n1.val;
		if(val.length()==1){l1.remove(n1.val);return;}
		String left;String right;
		int endLeft,endRight,i;
		endLeft=0;
		for(i=0;i<val.length();i++){
			if(val.charAt(i)==n) endLeft=i;
		}
		endRight=i;
		left=val.substring(0,endLeft);
		right=val.substring(endLeft+1,endRight);
		
		n1.val=Character.toString(n);
		n1.left=new Node(left);
		n1.right=new Node(right);
		construct(n1.left);
		construct(n1.right);
	}
	void print(Node root){
		Queue<Node> s= new LinkedList<Node>();
		s.add(root);
		Node tmp;
		while(!s.isEmpty()){
			tmp=s.remove();
			if(tmp.left!=null || tmp.right!=null) System.out.print(tmp.val+" ->");
			if(tmp.left!=null) {System.out.print(tmp.left.val+",");s.add(tmp.left);}
			if(tmp.right!=null) {System.out.print(tmp.right.val+",");s.add(tmp.right);}
			System.out.println();
			
		}
	}
	
	void postOrder(Node n1){
		if(n1==null) return;
		postOrder(n1.left);
		postOrder(n1.right);
		System.out.print(n1.val);
	}
	public static void  main(String args[]){
		String inorder = "DBEAFC";
		String preorder ="ABDECF";
		ConstructTree n1 = new ConstructTree(inorder,preorder);
		n1.construct(n1.root);
		n1.postOrder(n1.root);
	//	n1.print(n1.root);
	}
	
}


