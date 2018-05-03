package per.agreysky.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import per.agreysky.bean.Message;
import per.agreysky.db.DBAccess;

public class MessageDAO {

    public List<Message> queryMessageList(String command, String description) {
        DBAccess dbAccess = new DBAccess();
        List<Message> messageList = new ArrayList<Message>();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            Message message = new Message();
            message.setCommand(command);
            message.setDescription(description);
            //Í¨¹ýsqlSessionÖ´ÐÐSQLÓï¾ä
            IMessage iMessage = sqlSession.getMapper(IMessage.class);
            messageList = iMessage.queryMessageList(message);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return messageList;

    }

    public void deleteOne(int id) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.delete("Message.deleteOne", id);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    public void deleteBatch(List<Integer> ids) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.delete("Message.deleteBatch", ids);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    public static void main(String[] args) {
        MessageDAO messageDAO = new MessageDAO();
        messageDAO.queryMessageList("", "");

    }

}
