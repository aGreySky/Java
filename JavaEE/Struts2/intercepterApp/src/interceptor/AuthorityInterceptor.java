package interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        // 取得请求相关的ActionContext实例
        ActionContext ctx = invocation.getInvocationContext();
        // 获取session实例
        Map<String, Object> session = ctx.getSession();
        // 取出名为username的Session属性
        String username = (String) session.get("username");
        if (username != null && username.equals("admin")) {
            return invocation.invoke();
        }
        session.put("tip", "您还没有登录，请使用admin用户名登录");
        return Action.LOGIN;
    }
}
