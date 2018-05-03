package per.agreysky.dao;

import java.util.List;

import per.agreysky.bean.Message;

public interface IMessage {
    public List<Message> queryMessageList(Message message);
}
