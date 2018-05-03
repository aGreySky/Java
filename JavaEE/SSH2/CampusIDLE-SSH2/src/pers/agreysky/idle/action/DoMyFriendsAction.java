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
    String localPath = "E:/eclipse/CampusIDLE-WEB/WebContent/images/friendsPicture";//本地地址
    String serverPath = "C:/apache-tomcat-8.5.23/wtpwebapps/CampusIDLE-WEB/images/friendsPicture";//服务器地址
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
        if (action.equals("add")) { // 添加我的交友信息
            System.out.println("add");
            addAndUpdateMyFriend();
        } else if (action.equals("delete")) { // 下架交友信息
            System.out.println("delete");
            deleteMyFriend();
        } else if (action.equals("update")) {// 修改交友信息
            System.out.println("update");
            addAndUpdateMyFriend();
        } else {
            throw new IdleException(ResultEnum.ACTION_ERROR);
        }
        resultVO = ResultVOUtil.success();
        return SUCCESS;
    }

    //删除交友
    private void deleteMyFriend() {
        System.out.println("id=" + friend.getId());
        friend = doMyFriendsActionService.getFriendsById(friend.getId());
        if (friend == null) {
            throw new IdleException(ResultEnum.FRIEND_NOT_EXIST);
        }
        doMyFriendsActionService.deleteFriends(friend);//删除数据库中的信息
        String[] picUrl = friend.getPicture().split("\\+");//图片数组
        for (int i = 0; i < picUrl.length; i++) {
            String picUrlName[] = picUrl[i].split("/");//单个图片url名称分割数组
            picUrl[i] = picUrlName[picUrlName.length - 1];//获得单独的图片地址
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
    //添加、更新交友
    private void addAndUpdateMyFriend() {
        String AllPictureName = null;//将要存入数据库的文件名
        if (action.equals("add")) {
            if (doMyFriendsActionService.ifFriendsExist(//判断数据库中是否有该
                    friend.getOverview(), friend.getContact())) {
                throw new IdleException(ResultEnum.FRIEND_EXISTED);
            }
        }
        //struts2文件上传
        try {
            //判断路径是否存在,不存在创建  
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
                    String pic = picture[picture.length - 1];//图片格式
                    String picNewName = null;
                    String picNewNameWithServer = null;//带服务器地址
                    System.out.println("friend.id:" + friend.getId());
                    if (friend.getId() == null) {
                        System.out.println("add操作");
                        int num = doMyFriendsActionService.getLatestId();
                        picNewNameWithServer = serverImagesPath + user.getId()
                                + "000" + (num + 1) + "000" + (k + 1) + "."
                                + pic;
                        picNewName = user.getId() + "000" + (num + 1) + "000"
                                + (k + 1) + "." + pic;
                    } else {
                        System.out.println("update操作");
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
                    //第一个参数是文件 ,第二个参数是文件在服务器中的位置,fileUtils 是org.apache.commons.io.FileUtils提供好的现成的方法  
                    FileUtils.copyFile(file[k], file1);//copy文件,服务器中有备份文件  
                    FileUtils.moveFile(file[k], file2);//剪切文件,无备份文件
                }
            } else {
                throw new IdleException(ResultEnum.FILE_NOT_EXIST);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置联系方式
        Pattern p = Pattern.compile(
                "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
        Matcher m = p.matcher(friend.getContact());
        if (m.matches()) {
            friend.setWay("手机");
        } else {
            friend.setWay("QQ");
        }
        //设置图片名
        friend.setPicture(AllPictureName);
        user = doMyFriendsActionService.getUserByUserId(user.getId());
        if (user == null) {
            throw new IdleException(ResultEnum.USER_NOT_EXIST);
        }
        //设置用户
        friend.setUser(user);
        if (action.equals("add")) {
            doMyFriendsActionService.addFriends(friend);
        } else if (action.equals("update")) {
            doMyFriendsActionService.updateFriends(friend);
        }
    }
    /**
     * servlet上传
     * @param friend
     */
    //        try {
    //            DiskFileUpload fu = new DiskFileUpload();
    //            // 设置最大文件尺寸，这里是12MB
    //            fu.setSizeMax(4194304 * 3);
    //            // 设置缓冲区大小，这里是4kb
    //            fu.setSizeThreshold(4096);
    //            // 设置上传字符
    //            fu.setHeaderEncoding("UTF-8");
    //            // 设置临时目录：
    //            //fu.setRepositoryPath(this.getServletContext().getRealPath("/"));
    //
    //            // 得到所有的文件：
    //            HttpServletRequest request = ServletActionContext.getRequest();
    //            List fileItems = fu.parseRequest(request);
    //            Iterator i = fileItems.iterator();
    //            // 依次处理每一个文件：
    //            int j = 0;//数组计数
    //            int k = 0;//图片计数
    //            String AllPictureName = null;//数据库图片
    //            String[] update = new String[4];// 添加的参数数组
    //            while (i.hasNext()) {
    //                FileItem fi = (FileItem) i.next();
    //                if (fi.isFormField()) { // 如果是普通表单字段
    //                    System.out.println("普通表单");
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
    //                                update[j++] = "手机";
    //                            } else {
    //                                update[j++] = "QQ";
    //                            }
    //                        }
    //                        update[j++] = value;
    //                    }
    //                } else { // 文件字段
    //                    if (fi.getName() != null && fi.getName() != "") {
    //                        k++;
    //                        String[] picture = fi.getName().split("\\.");
    //                        String pic = picture[picture.length - 1];//图片格式
    //                        String picNewName = null;
    //                        if (friend.getId() == null) {
    //                            int num = doMyFriendsActionService.getLatestId();
    //                            picNewName = serverImagesPath + user.getId() + "000"
    //                                    + (num + 1) + "000" + k + "." + pic;
    //                        } else {
    //                            picNewName = serverImagesPath + user.getId() + "000"
    //                                    + friend.getId() + "000" + k + "." + pic;
    //                        }
    //                        AllPictureName += "+" + picNewName;//将图片名称加入数据库
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
