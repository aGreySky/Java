package servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.AuthUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String backUrl = "http://xiaoyou.ngrok.cc/WxAuth/callBack";
        System.out.println("1");
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + AuthUtil.APPID + "&redirect_uri="
                + URLEncoder.encode(backUrl)
                + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        System.out.println(url);
        response.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
