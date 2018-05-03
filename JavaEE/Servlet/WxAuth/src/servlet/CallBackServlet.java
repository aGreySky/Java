package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import util.AuthUtil;

@WebServlet("/callBack")
public class CallBackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CallBackServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("2");
        String code = request.getParameter("code");
        String url = "  https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + AuthUtil.APPID + "&secret=" + AuthUtil.APPSECRET + "&code="
                + code + "&grant_type=authorization_code";
        JSONObject json = AuthUtil.doGetJson(url);
        String openid = json.getString("openid");
        String token = json.getString("token");
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="
                + token + "&openid=" + openid + "&lang=zh_CN";
        JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
        System.out.println(userInfo);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
