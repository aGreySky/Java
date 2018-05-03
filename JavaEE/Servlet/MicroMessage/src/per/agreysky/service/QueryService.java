package per.agreysky.service;

import java.util.List;
import java.util.Random;

import per.agreysky.bean.Command;
import per.agreysky.bean.CommandContent;
import per.agreysky.bean.Message;
import per.agreysky.dao.CommandDAO;
import per.agreysky.dao.MessageDAO;
import per.agreysky.util.Iconst;

public class QueryService {

    public List<Message> queryMessageList(String command, String description) {
        MessageDAO messageDAO = new MessageDAO();
        return messageDAO.queryMessageList(command, description);
    }

    public String queryByName(String name) {
        CommandDAO commandDAO = new CommandDAO();
        List<Command> commandList;
        if (Iconst.HELP_COMMAND.equals(name)) {
            commandList = commandDAO.queryCommandList(null, null);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < commandList.size(); i++) {
                if (i != 0) {
                    result.append("<br/>");
                }
                result.append("回复[" + commandList.get(i).getName() + "]可以查看"
                        + commandList.get(i).getDescription());
            }
            return result.toString();
        }
        commandList = commandDAO.queryCommandList(name, null);
        if (commandList.size() > 0) {
            List<CommandContent> contentList = commandList.get(0)
                    .getContentList();
            return contentList.get(new Random().nextInt(contentList.size()))
                    .getContent();
        }
        return Iconst.NO_MATCHING_CONTENT;
    }

}
