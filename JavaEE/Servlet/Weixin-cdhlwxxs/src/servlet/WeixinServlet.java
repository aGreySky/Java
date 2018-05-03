package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import util.CheckUtil;
import util.MessageUtil;

@WebServlet("/weixin")
public class WeixinServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public WeixinServlet() {
        super();

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            out.println(echostr);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        try {
            Map<String, String> map = MessageUtil.xmlToMap(request);
            String fromUserName = map.get("FromUserName");
            String toUserName = map.get("ToUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");

            String message = null;
            if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
                if ("1".equals(content)) {
                    message = MessageUtil.initText(toUserName, fromUserName,
                            MessageUtil.firstMenu());
                } else if ("2".equals(content)) {
                    message = MessageUtil.initText(toUserName, fromUserName,
                            MessageUtil.secondMenu());
                } else if ("?".equals(content) || "£¿".equals(content)) {
                    message = MessageUtil.initText(toUserName, fromUserName,
                            MessageUtil.menuText());
                }
            } else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
                String eventType = map.get("Event");
                if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
                    message = MessageUtil.initText(toUserName, fromUserName,
                            MessageUtil.menuText());
                }
            }
            out.println(message);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }

    }

}
