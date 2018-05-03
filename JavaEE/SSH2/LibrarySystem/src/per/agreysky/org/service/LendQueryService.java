package per.agreysky.org.service;

import java.util.List;

import per.agreysky.org.vo.Student;

public interface LendQueryService {

    //����ѧ��
    public Student selectByReaderId(String readerId);

    //��ҳ���������ĵ�ͼ��
    public List selectLend(String readerId, int pageNow, int pageSize);

    //����������ͼ�������
    public int selectLendSize(String readerId);

}
