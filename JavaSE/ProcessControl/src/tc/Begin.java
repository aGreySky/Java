package tc;

public class Begin<LinkNode, Page>{
	LinkNode first;
	LinkNode last;
	int maxSize=10;//缓存的尺寸
	int currentSize=0;
	public Begin(int maxSize){
		this.maxSize=maxSize;
	}
	public void changePage(Page p){//LRU算法，每次读取新页都重新调整缓存结构
		LinkNode newItem=new LinkNode();
		newItem.page=p;
		newItem.next=null;
		if(first==null){
			first =newItem;
			last=first;
			currentSize++;
		}
		
	}
}