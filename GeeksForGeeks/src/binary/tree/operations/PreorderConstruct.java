package binary.tree.operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class preorder{
	List<Integer> preorder;
	int index;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public List<Integer> getPreorder() {
		return preorder;
	}
	public void setPreorder(List<Integer> preorder) {
		this.preorder = preorder;
	}
	
}
/*this will work for BST and BT*/
public class PreorderConstruct {
	
	//complexity : 
	static void construct(TreeNode root , List<Integer> inorder ,int start , int end, List<Integer> preorder){
		if(preorder.size()==0) return;
		if(root==null) return;
		//first element of preorder is a node val. fetch and delete 
		int node = preorder.get(0);
		preorder.remove(new Integer(node));
		root.val=node;
		
		int mid=0;
		boolean leafLeft=false;
		boolean leafRight=false;
		
		//find the node value in the inorder list. split left and right based on this.
		for(int i=start;i<end;i++)
			if(inorder.get(i)==node){mid=i;break;}
		
		//check if node has only leaves
		if(mid-start == 1){
			leafLeft=true;
			int left=inorder.get(mid-1);
			root.left=new TreeNode(left);
			preorder.remove(new Integer(left));
		}
		if(end-mid-1==1){
			leafRight=true;
			int right = inorder.get(mid+1);
			root.right=new TreeNode(right);
			preorder.remove(new Integer(right));
		}

		//if node has more than leaves than split and call again
		if(!leafLeft){ 
			root.left=new TreeNode();
			construct(root.left,inorder,start,mid,preorder);
		}
		if(!leafRight){
			root.right=new TreeNode();
			construct(root.right,inorder,mid+1,end,preorder);
		}
		
		return;
	}
/*this works only for BST*/	
	static void constructPreorder(TreeNode root,preorder p, int start,int end){
		if(p.index==p.preorder.size())return ;
		if(root==null) return;
		
		int node = p.preorder.get(p.index);
		p.index++;
		//preorder.remove(new Integer(node));
		root.val=node;
		root.left=new TreeNode();
		root.right=new TreeNode();
		
		int mid=0;
		for(int i=start;i<end;i++){
			if(p.preorder.get(i) > node){mid=i; break;}
		}
		boolean leftFlag=false;boolean rightFlag=false;
		//left leaf
		if(mid-start ==2){
			p.index++;
			leftFlag=true;
			root.left.val=p.preorder.get(mid-1);
		}
		//right leaf
		if(end-mid==1){
			p.index++;
			rightFlag=true;
			root.right.val=p.preorder.get(mid);
		}
		
		if(!leftFlag){
			constructPreorder(root.left,p,start+1,mid);
		}
		if(!rightFlag){
			constructPreorder(root.right,p,mid,end);
		}
	}
	
	/**
	 * Given only preorder traversal it is possible to use BST properties to construct the BST not possible to do BT
	 * Given inorder and preorder it is possible to construct the BT , and of course BST a well
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		List<Integer> preorder= new ArrayList<Integer>();
//		while(scan.hasNext()){
//			preorder.add(scan.nextInt());
//		}
		preorder.add(10);preorder.add(5);preorder.add(1);preorder.add(7);preorder.add(40);preorder.add(50);
		
		/*construct BST  with only preorder given. extra space allowed.*/
		List<Integer> inorder = new ArrayList<Integer>(preorder);
		Collections.sort(inorder);
		TreeNode root=new TreeNode();
//		construct(root,inorder,0,inorder.size(),preorder);
		
		/*constrict tree with only preorder traversal . no extra space*/
		preorder p =new preorder();
		p.setPreorder(preorder);
		p.setIndex(0);
		constructPreorder(root,p,0,preorder.size());
		
		CommonOperations.print(root);
	}

}
