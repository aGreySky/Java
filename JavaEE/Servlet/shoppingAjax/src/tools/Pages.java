package tools;

import java.util.ArrayList;
import java.util.List;

import entity.Items;

public class Pages {
	public List<Items> list=null;
	private int recordCount=0;//������Ʒ�����ı���
	private int pagesize=1;//����ÿҳ��ʾ�ļ�¼���ı���
	private int maxPage=0;//�������ҳ���ı���
	
	
	public List<Items> getList() {
		return list;
	}
	public void setList(List<Items> list) {
		this.list = list;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getRecordSize() {//��ȡ��Ʒ����
		return recordCount;
	}
	public int getMaxPage(){//��ȡ���ҳ��
		int maxPage=(recordCount%pagesize==0)?(recordCount%pagesize):(recordCount%pagesize+1);
		return maxPage;
	}
	public int getPage(String str){//�������ҳ����ֵ��Page
		if(str==null){
			str="0";
		}
		int Page=Integer.parseInt(str);
		if(Page<1)
			Page=1;
		else if(((Page-1)*pagesize+1)>recordCount){//�������ҳ���������ҳ��ʱ��ʹ��������ҳ��
			Page=maxPage;
		}
		return Page;
	}
	public ArrayList<Items> getInitPage(List<Items> list,int Page,int pagesize){//��ʼ����ҳ��Ϣ
		ArrayList<Items> newList=new ArrayList<Items>();
		this.list=list;
		this.pagesize=pagesize;
		recordCount=list.size();//��ѯ���ļ��ϵ�Ԫ�ظ���
		this.maxPage=getMaxPage();//��ȡ���ҳ��
		for(int i=(Page-1)*pagesize;i<Page*pagesize-1;i++){
			//�����Ѿ������꼯���ˣ�����ֹѭ��
			if(i>=recordCount){
				break;
			}else{
				newList.add(list.get(i));
			}
		}
		return newList;
	}
	
	public ArrayList<Items> getAppointPage(int Page){//��ȡָ��ҳ������
		ArrayList<Items> newList=new ArrayList<Items>();
		for(int i=(Page-1)*pagesize;i<Page*pagesize-1;i++){
			//�����Ѿ������꼯���ˣ�����ֹѭ��
			if(i>=recordCount){
				break;
			}else{
				newList.add(list.get(i));
			}
		}
		return newList;
	}
	
	public String printCtrl(int Page,String url,int maxPage){//�����¼�������ַ���
		String strHtml="<table width='100%' border='0' cellspacing='0' cellpadding='0'>"
				+ "<tr><td height='24' align='right'>��ǰҳ��:��"+Page+"/"+maxPage+"�� &nbsp;";
			if(Page>1){
				strHtml=strHtml+"<a href='"+url+"?Page=1"+"'>��һҳ</a> ";
				strHtml=strHtml+"<a href='"+url+"?Page="+(Page-1)+"'>��һҳ</a> ";
			}
			if(Page<maxPage){
				strHtml=strHtml+"<a href='"+url+"?Page="+(Page+1)+"'>��һҳ</a>"
				+ "	<a href='"+url+"?Page="+maxPage+"'>���һҳ&nbsp;</a>";
			}
			strHtml=strHtml+"</td></tr>	</table>";
			return strHtml;
	}
}
