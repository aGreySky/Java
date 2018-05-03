package interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class TimerIntercepter extends AbstractInterceptor {
/*
 * ����ִ��Action���ѵ�ʱ��
 * */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//ִ��Action֮ǰ
		long start=System.currentTimeMillis();
		//2.ִ����һ��������������Ѿ������һ������������ִ��Ŀ��Action
		String result=invocation.invoke();
		//3.ִ��Action֮��
		long end=System.currentTimeMillis();
		System.out.println("ִ��Action���ѵ�ʱ�䣺"+(end-start)+"ms");
		return result;
		
	}

}
