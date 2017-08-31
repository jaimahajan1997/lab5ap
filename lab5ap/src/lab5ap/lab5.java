package lab5ap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
//Sources-https://stackoverflow.com/questions/15189949/making-a-generic-comparator-class,http://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;


class node<T>{
	T dat;
	node<T> left;
	node<T> right;
	public node(T val){
		dat=val;
		left=null;
		right=null;
	}
}
class bst<T extends Comparable<T>> implements Comparator<T> {
public int compare(T a, T b) {
	    int val= a.compareTo(b);
	    return val;
	  }
public int compareTo(float a, float b) {
	if (a < b) return -1;
	if (a > b) return 1;
	return 0;
  }
node<T> insertRec(node<T> root, T a) {
	 
    if (root == null) {
        root = new node<T>(a);
        return root;
    }
    
     if(compare(a, (T) root.dat)<0){
    	 ////System.out.println(a+"is less than"+root.dat+compare(a,  root.dat));
    	 root.left = insertRec(root.left, a);
	}
	else{
		root.right = insertRec(root.right, a);
	}
    return root;
}
public  node<?> makebst(ArrayList<T> arr){
T val=arr.remove(0);
node<T> head=new node<T>(val);
node<T> point=head;
for(T a:arr){
	insertRec(head, a);
	////System.out.println("inserting"+a);
}
return point;
}
}
public  class lab5 {

static ArrayList<Object> bstorder ;
static <T> void printInorder(node<T> node)
    {
        if (node == null)
            return;
 
        printInorder(node.left);
        ////System.out.println(node.dat);
        bstorder.add(node.dat);
        printInorder(node.right);
    }
public static <T> void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException{
	Scanner s=new Scanner(System.in);
	int n=s.nextInt();
	int x=s.nextInt();
	s.close();
	HashMap<Integer, ArrayList<Object>> stu=new HashMap<Integer, ArrayList<Object>>();
	for (int i=0;i<n;i++){
		stu.put(i+1,new ArrayList<Object>());
	}
	PrintWriter w = new PrintWriter("./src/lab5ap/" + 1 + ".txt", "UTF-8");
	for (int t = 1; t<= x; t++) {
	File file = new File("C:/Users/Preeti/Desktop/JAVA/lab5ap/src/"+ t + ".txt");
//System.out.println(t);
bstorder =new ArrayList<Object>();
    try {

        Scanner sc = new Scanner(file);
        String type=sc.next();
    	n=sc.nextInt();
        //System.out.println(type+n);
    	int count=0;
    	
    	if(type.equals("String")){
    		ArrayList<String> inp=new ArrayList<String>();
    	try{	
    	for(int i=0;i<n;i++){
    		inp.add( sc.next());
    	}}
    	catch(Exception e){
        	;
        }
    	
    	String root=inp.get(0);
    	bst<String> tree=new bst<String>();
    	node<?> nn=tree.makebst( (ArrayList<String>) inp);
    	printInorder(nn);
    	String out="";
    	int cfinal=-1;
    	boolean found=false;
    	for(Object a:bstorder){
    		count+=1;
    		if(a.equals(root) &&found==false){
    			cfinal=count;
    			found=true;
    		}
    		out+=a;
    	}
    	if(found&& cfinal<=n){
    		stu.get(cfinal).add(out);
    	}
    	}
    	else if(type.equals("Float")){
    		ArrayList<Float> inp=new ArrayList<Float>();

    		for(int i=0;i<n;i++){
    			inp.add((sc.nextFloat()));
    		}
    		float root=inp.get(0);
    		bst<Float> tree=new bst<Float>();
    		node<?> nn=tree.makebst( inp);
    		printInorder(nn);
    		float sum=0;
    		int cfinal=-1;
    		boolean found=false;
    		for(Object a:bstorder){
    			//System.out.println(a);
    			a=(float)a;
    			count+=1;
    			if(a.equals(root) &&found==false){
    				cfinal=count;
    				found=true;
    			}
    			sum+=(float)a;
    		}	
    		//System.out.println("ccc"+cfinal+"   "+n);
    		if(found&& cfinal<=n){
    			stu.get(cfinal).add(sum);
    		}
    		//System.out.println(sum);
    	}
    	else if(type.equals("Integer")){
    		ArrayList<Integer> inp=new ArrayList<Integer>();

    		for(int i=0;i<n;i++){
    			inp.add((sc.nextInt()));
    		}
    		int root=inp.get(0);
    		bst<Integer> tree=new bst<Integer>();
    		node<?> nn=tree.makebst(inp);
    		printInorder(nn);
    		int sum=0;
    		int cfinal=-1;
    		boolean found=false;
    		for(Object a:bstorder){
    			count+=1;
    			if(a.equals(root) &&found==false){
    				cfinal=count;
    				found=true;
    			}
    			sum+=(int)a;
    		}	
    		if(found && cfinal<=n){
    			stu.get(cfinal).add(sum);
    		}
    		//System.out.println(sum);
    	}
        sc.close();
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
	}
	int choco=0;

	for(int i=0;i<n;i++){
		if(stu.get(i+1).size()!=0){
		String a=String.format("%d ", i+1);
		for(Object aaa:stu.get(i+1)){
			
		
			a+=aaa+" ";
		}
		
		w.println(a);}
		else{
		choco+=1;
		}

	}
	w.println(choco);
	w.flush();
}
}
