package per.agreysky.service;

import java.util.ArrayList;
import java.util.List;

import per.agreysky.dao.MessageDAO;

public class MaintainService {
    public void deleteOne(String id) {
        if (id != null && !"".equals(id.trim())) {
            MessageDAO messageDAO = new MessageDAO();
            messageDAO.deleteOne(Integer.valueOf(id));
        }
    }
    public void deleteBatch(String[] ids) {
        MessageDAO messageDAO = new MessageDAO();
        List<Integer> idList = new ArrayList<Integer>();
        for (String id : ids) {
            idList.add(Integer.valueOf(id));
        }
        messageDAO.deleteBatch(idList);
    }

}
