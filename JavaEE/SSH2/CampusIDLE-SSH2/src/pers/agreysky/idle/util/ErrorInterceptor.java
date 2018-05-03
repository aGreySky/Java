package pers.agreysky.idle.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import net.sf.json.JSONObject;
import pers.agreysky.idle.exception.IdleException;

public class ErrorInterceptor implements Interceptor {

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actioninvocation) {

        String result = null; // Action的返回值   
        try {
            // 运行被拦截的Action,期间如果发生异常会被catch住   
            result = actioninvocation.invoke();
            return result;
        } catch (Exception e) {
            JSONObject json = new JSONObject();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/json;charset=utf-8");
            if (e instanceof IdleException) {
                IdleException idleException = (IdleException) e;
                json = JSONObject.fromObject(ResultVOUtil
                        .error(idleException.code, idleException.getMessage()));
            } else {
                e.printStackTrace();
                json = JSONObject.fromObject(ResultVOUtil.error(-1, "未知错误"));
            }
            try {
                PrintWriter out = response.getWriter();
                out.println(json);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return "none";
        }
    }

    @Override
    public void destroy() {

    }

}
