package interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        // ȡ��������ص�ActionContextʵ��
        ActionContext ctx = invocation.getInvocationContext();
        // ��ȡsessionʵ��
        Map<String, Object> session = ctx.getSession();
        // ȡ����Ϊusername��Session����
        String username = (String) session.get("username");
        if (username != null && username.equals("admin")) {
            return invocation.invoke();
        }
        session.put("tip", "����û�е�¼����ʹ��admin�û�����¼");
        return Action.LOGIN;
    }
}
