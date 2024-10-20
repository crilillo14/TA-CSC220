package lab10;

public class Node {
	
    public char content;
    public int row;
    public int col;
	public boolean marked;
	public Node parent;
    
    public Node(int x, int y, char c){
        marked = false;
        content = c;
        parent =  null;
        this.row = x; 
        this.col = y;
    }    
    
    public void Mark(){
        marked = true;
    }
    
    public boolean isWall(){
    	if (content == 'X')
    		return true;
    	else
    		return false;
    }
    
    public boolean isMarked(){
    	return marked;
    }
    

}
