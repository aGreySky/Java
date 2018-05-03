package pers.agreysky.idle.action;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionSupport;

import pers.agreysky.idle.dto.Friends;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.enums.ResultEnum;
import pers.agreysky.idle.exception.IdleException;
import pers.agreysky.idle.service.DoMyFriendsActionService;
import pers.agreysky.idle.util.ResultVOUtil;
import pers.agreysky.idle.vo.ResultVO;

public class DoMyFriendsAction extends ActionSupport {
    String localPath = "E:/eclipse/CampusIDLE-WEB/WebContent/images/friendsPicture";//���ص�ַ
    String serverPath = "C:/apache-tomcat-8.5.23/wtpwebapps/CampusIDLE-WEB/images/friendsPicture";//��������ַ
    String serverImagesPath = "http://xiaoyou.free.ngrok.cc/CampusIDLE-WEB/images/friendsPicture/";
    private Friends friend;
    private File[] file;
    private String[] fileFileName;
    private User user;
    private DoMyFriendsActionService doMyFriendsActionService;
    private String action;
    private ResultVO resultVO;

    @Override
    public String execute() throws IOException {
        if (action.equals("add")) { // ����ҵĽ�����Ϣ
            System.out.println("add");
            addAndUpdateMyFriend();
        } else if (action.equals("delete")) { // �¼ܽ�����Ϣ
            System.out.println("delete");
            deleteMyFriend();
        } else if (action.equals("update")) {// �޸Ľ�����Ϣ
            System.out.println("update");
            addAndUpdateMyFriend();
        } else {
            throw new IdleException(ResultEnum.ACTION_ERROR);
        }
        resultVO = ResultVOUtil.success();
        return SUCCESS;
    }

    //ɾ������
    private void deleteMyFriend() {
        System.out.println("id=" + friend.getId());
        friend = doMyFriendsActionService.getFriendsById(friend.getId());
        if (friend == null) {
            throw new IdleException(ResultEnum.FRIEND_NOT_EXIST);
        }
        doMyFriendsActionService.deleteFriends(friend);//ɾ�����ݿ��е���Ϣ
        String[] picUrl = friend.getPicture().split("\\+");//ͼƬ����
        for (int i = 0; i < picUrl.length; i++) {
            String picUrlName[] = picUrl[i].split("/");//����ͼƬurl���Ʒָ�����
            picUrl[i] = picUrlName[picUrlName.length - 1];//��õ�����ͼƬ��ַ
            File f1 = new File(localPath + picUrl[i]);
            if (f1.exists()) {
                f1.delete();
            }
            File f2 = new File(
                    serverPath + picUrl[i]);
            if (f2.exists()) {
                f2.delete();
            }
        }
    }
    //��ӡ����½���
    private void addAndUpdateMyFriend() {
        String AllPictureName = null;//��Ҫ�������ݿ���ļ���
        if (action.equals("add")) {
            if (doMyFriendsActionService.ifFriendsExist(//�ж����ݿ����Ƿ��и�
                    friend.getOverview(), friend.getContact())) {
                throw new IdleException(ResultEnum.FRIEND_EXISTED);
            }
        }
        //struts2�ļ��ϴ�
        try {
            //�ж�·���Ƿ����,�����ڴ���  
            File dir1 = new File(serverPath);
            if (!dir1.exists()) {
                dir1.mkdirs();
            }
            File dir2 = new File(localPath);
            if (!dir2.exists()) {
                dir2.mkdirs();
            }
            if (file != null) {
                for (int k = 0; k < file.length; k++) {
                    String[] picture = fileFileName[k].split("\\.");
                    String pic = picture[picture.length - 1];//ͼƬ��ʽ
                    String picNewName = null;
                    String picNewNameWithServer = null;//����������ַ
                    System.out.println("friend.id:" + friend.getId());
                    if (friend.getId() == null) {
                        System.out.println("add����");
                        int num = doMyFriendsActionService.getLatestId();
                        picNewNameWithServer = serverImagesPath + user.getId()
                                + "000" + (num + 1) + "000" + (k + 1) + "."
                                + pic;
                        picNewName = user.getId() + "000" + (num + 1) + "000"
                                + (k + 1) + "." + pic;
                    } else {
                        System.out.println("update����");
                        picNewNameWithServer = serverImagesPath + user.getId()
                                + "000" + friend.getId() + "000" + (k + 1) + "."
                                + pic;
                        picNewName = user.getId() + "000" + friend.getId()
                                + "000" + (k + 1) + "." + pic;
                    }
                    if (k == 0) {
                        AllPictureName = picNewNameWithServer;
                    } else {
                        AllPictureName += picNewNameWithServer;
                    }
                    if (k < file.length - 1) {
                        AllPictureName += "+";
                    }
                    File file1 = new File(dir1, picNewName);
                    File file2 = new File(dir2, picNewName);
                    if (file1.exists()) {
                        file1.delete();
                    }
                    if (file2.exists()) {
                        file2.delete();
                    }
                    //��һ���������ļ� ,�ڶ����������ļ��ڷ������е�λ��,fileUtils ��org.apache.commons.io.FileUtils�ṩ�õ��ֳɵķ���  
                    FileUtils.copyFile(file[k], file1);//copy�ļ�,���������б����ļ�  
                    FileUtils.moveFile(file[k], file2);//�����ļ�,�ޱ����ļ�
                }
            } else {
                throw new IdleException(ResultEnum.FILE_NOT_EXIST);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //������ϵ��ʽ
        Pattern p = Pattern.compile(
                "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
        Matcher m = p.matcher(friend.getContact());
        if (m.matches()) {
            friend.setWay("�ֻ�");
        } else {
            friend.setWay("QQ");
        }
        //����ͼƬ��
        friend.setPicture(AllPictureName);
        user = doMyFriendsActionService.getUserByUserId(user.getId());
        if (user == null) {
            throw new IdleException(ResultEnum.USER_NOT_EXIST);
        }
        //�����û�
        friend.setUser(user);
        if (action.equals("add")) {
            doMyFriendsActionService.addFriends(friend);
        } else if (action.equals("update")) {
            doMyFriendsActionService.updateFriends(friend);
        }
    }
    /**
     * servlet�ϴ�
     * @param friend
     */
    //        try {
    //            DiskFileUpload fu = new DiskFileUpload();
    //            // ��������ļ��ߴ磬������12MB
    //            fu.setSizeMax(4194304 * 3);
    //            // ���û�������С��������4kb
    //            fu.setSizeThreshold(4096);
    //            // �����ϴ��ַ�
    //            fu.setHeaderEncoding("UTF-8");
    //            // ������ʱĿ¼��
    //            //fu.setRepositoryPath(this.getServletContext().getRealPath("/"));
    //
    //            // �õ����е��ļ���
    //            HttpServletRequest request = ServletActionContext.getRequest();
    //            List fileItems = fu.parseRequest(request);
    //            Iterator i = fileItems.iterator();
    //            // ���δ���ÿһ���ļ���
    //            int j = 0;//�������
    //            int k = 0;//ͼƬ����
    //            String AllPictureName = null;//���ݿ�ͼƬ
    //            String[] update = new String[4];// ��ӵĲ�������
    //            while (i.hasNext()) {
    //                FileItem fi = (FileItem) i.next();
    //                if (fi.isFormField()) { // �������ͨ���ֶ�
    //                    System.out.println("��ͨ��");
    //                    String values = fi.getString("utf-8");
    //                    JSONObject getParas = JSONObject.fromObject(values);
    //                    String paras[] = {"friend.overview", "friend.contact"};
    //                    if (action.equals("add")) {
    //                        if (doMyFriendsActionService.ifFriendsExist(
    //                                getParas.getString("friend.overview"),
    //                                getParas.getString("friend.contact"))) {
    //                            return false;
    //                        }
    //                    }
    //                    for (int s = 0; s < paras.length; s++) {
    //                        String value = getParas.getString(paras[s]);
    //                        if (paras[s].equals("friend.contact")) {
    //                            Pattern p = Pattern.compile(
    //                                    "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
    //                            Matcher m = p.matcher(value);
    //                            if (m.matches()) {
    //                                update[j++] = "�ֻ�";
    //                            } else {
    //                                update[j++] = "QQ";
    //                            }
    //                        }
    //                        update[j++] = value;
    //                    }
    //                } else { // �ļ��ֶ�
    //                    if (fi.getName() != null && fi.getName() != "") {
    //                        k++;
    //                        String[] picture = fi.getName().split("\\.");
    //                        String pic = picture[picture.length - 1];//ͼƬ��ʽ
    //                        String picNewName = null;
    //                        if (friend.getId() == null) {
    //                            int num = doMyFriendsActionService.getLatestId();
    //                            picNewName = serverImagesPath + user.getId() + "000"
    //                                    + (num + 1) + "000" + k + "." + pic;
    //                        } else {
    //                            picNewName = serverImagesPath + user.getId() + "000"
    //                                    + friend.getId() + "000" + k + "." + pic;
    //                        }
    //                        AllPictureName += "+" + picNewName;//��ͼƬ���Ƽ������ݿ�
    //                        File f1 = new File(
    //                                localPath + picNewName);
    //                        fi.write(f1);
    //                        File f2 = new File(
    //                                serverPath + "images/friendsPicture/"
    //                                        + picNewName);
    //                        copyFile.fileChannelCopy(f1, f2);
    //                        update[3] = AllPictureName;
    //                    }
    //                }
    //            }
    //            if (action.equals("add")) {
    //                if (doMyFriendsActionService.addFriends(update, user.getId())) {
    //                    return true;
    //                }
    //            } else if (action.equals("update")) {
    //                if (doMyFriendsActionService.updateFriends(friend.getId(),
    //                        update)) {
    //                    return true;
    //                }
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            return false;
    //        }
    //        return false;
    //    }

    public Friends getFriend() {
        return friend;
    }

    public void setFriend(Friends friend) {
        this.friend = friend;
    }

    public DoMyFriendsActionService getDoMyFriendsActionService() {
        return doMyFriendsActionService;
    }

    public void setDoMyFriendsActionService(
            DoMyFriendsActionService doMyFriendsActionService) {
        this.doMyFriendsActionService = doMyFriendsActionService;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public File[] getFile() {
        return file;
    }
    public void setFile(File[] file) {
        this.file = file;
    }
    public String[] getFileFileName() {
        return fileFileName;
    }
    public void setFileFileName(String[] fileFileName) {
        this.fileFileName = fileFileName;
    }
    public ResultVO getResultVO() {
        return resultVO;
    }
    public void setResultVO(ResultVO resultVO) {
        this.resultVO = resultVO;
    }

}
