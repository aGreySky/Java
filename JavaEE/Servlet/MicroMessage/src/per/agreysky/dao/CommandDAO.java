package per.agreysky.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import per.agreysky.bean.Command;
import per.agreysky.db.DBAccess;

public class CommandDAO {
    public List<Command> queryCommandList(String name, String description) {
        DBAccess dbAccess = new DBAccess();
        List<Command> commandList = new ArrayList<Command>();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            Command command = new Command();
            command.setName(name);
            command.setDescription(description);
            commandList = sqlSession.selectList("Command.queryCommandList",
                    command);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return commandList;

    }
}
