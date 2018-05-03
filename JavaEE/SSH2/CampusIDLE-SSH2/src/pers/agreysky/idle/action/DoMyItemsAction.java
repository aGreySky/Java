package pers.agreysky.idle.action;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionSupport;

import pers.agreysky.idle.dto.Items;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.enums.ResultEnum;
import pers.agreysky.idle.exception.IdleException;
import pers.agreysky.idle.service.DoMyItemsActionService;
import pers.agreysky.idle.util.ResultVOUtil;
import pers.agreysky.idle.vo.ResultVO;

/**
 * 对“我”的商品进行增删改，
 * 
 * 1.上传一件商品( url : item?action=add )
 *传入用户编号uid 和multiple表单(图片(无需参数名), 名称(name),价格(price),简介(overview),类型(type),联系方式(contact))
 *传出 成功 {"flag":"true"} 失败 {"flag":"false"}

 *2.修改一件商品( url : item?action=update)
 *传入物品编号id和multiple表单(json+图片)-------(图片(无需参数名), 名称(name),价格(price),简介(overview),类型(type),联系方式(contact))
 *传出 成功 {"flag":"true"} 失败 {"flag":"false"}

 *3.删除一件商品( url :item?action=delete)
 *传入物品编号id
 *传出 成功 {"flag":"true"} 失败 {"flag":"false"}
 * @author zzq
 *
 */
public class DoMyItemsAction extends ActionSupport {
    private DoMyItemsActionService doMyItemsActionService;
    String localPath = "E:/eclipse/CampusIDLE-WEB/WebContent/images/itemsPicture";//本地地址
    String serverPath = "C:/apache-tomcat-8.5.23/wtpwebapps/CampusIDLE-WEB/images/itemsPicture";//服务器地址
    String serverImagesPath = "http://xiaoyou.free.ngrok.cc/CampusIDLE-WEB/images/itemsPicture/";
    private Items item;
    private User user;
    private String action;
    private File[] file;
    private String[] fileFileName;
    private ResultVO resultVO;

    @Override
    public String execute() throws IOException {
        if (action.equals("add")) { // 添加我的闲置信息
            System.out.println("add");
            addAndUpdateMyItem();//新增数据
            user = doMyItemsActionService.getUserByUserId(user.getId());
            if (user == null) {
                throw new IdleException(ResultEnum.USER_NOT_EXIST);
            }
            user.setItemsNum(
                    Integer.toString(
                            (Integer.parseInt(user.getItemsNum()) + 1)));
            doMyItemsActionService.updateUserItemsNum(user);
        } else if (action.equals("delete")) { // 下架我的闲置信息
            System.out.println("delete");
            deleteMyItem();//删除数据库数据
            user = doMyItemsActionService.getUserByUserId(user.getId());
            if (user == null) {
                throw new IdleException(ResultEnum.USER_NOT_EXIST);
            }
            user.setItemsNum(
                    Integer.toString(
                            (Integer.parseInt(user.getItemsNum()) - 1)));
            doMyItemsActionService.updateUserItemsNum(user);
        } else if (action.equals("update")) {// 修改我的闲置信息
            System.out.println("update");
            addAndUpdateMyItem();//更新数据
        } else {
            throw new IdleException(ResultEnum.ACTION_ERROR);
        }
        resultVO = ResultVOUtil.success();
        return SUCCESS;

    }

    //删除我的交友
    private void deleteMyItem() {
        System.out.println("id=" + item.getId());
        item = doMyItemsActionService.getItemsById(item.getId());
        if (item == null) {
            throw new IdleException(ResultEnum.ITEM_NOT_EXIST);
        }
        /**
         * 1.删除数据
         */
        doMyItemsActionService.deleteItem(item);

        /**
         * 2.删除图片
         */
        String[] picUrl = item.getPicture().split("//+");//图片数组
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
    //添加、更新我的闲置
    private void addAndUpdateMyItem() {
        System.out.println(fileFileName[0]);
        String AllPictureName = null;//将要存入数据库的文件名
        if (action.equals("add")) {

            /**
             * 1.判断闲置是否发布过
             */
            if (doMyItemsActionService.ifItemsExist(//判断数据库中是否有该闲置
                    item.getOverview(), item.getContact())) {
                throw new IdleException(ResultEnum.ITEM_EXIST);
            }
        }

        /**
         * 2.上传文件
         */
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
                    if (item.getId() == null) {
                        int num = doMyItemsActionService.getLatestId();
                        picNewNameWithServer = serverImagesPath + user.getId()
                                + "000" + (num + 1) + "000" + (k + 1) + "."
                                + pic;
                        picNewName = user.getId() + "000" + (num + 1) + "000"
                                + (k + 1) + "." + pic;
                    } else {
                        picNewNameWithServer = serverImagesPath + user.getId()
                                + "000" + item.getId() + "000" + (k + 1) + "."
                                + pic;
                        picNewName = user.getId() + "000" + item.getId() + "000"
                                + (k + 1) + "." + pic;
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

        /**
         * 3.修改数据库
         */
        //设置联系方式
        Pattern p = Pattern.compile(
                "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
        Matcher m = p.matcher(item.getContact());
        if (m.matches()) {
            item.setWay("手机");
        } else {
            item.setWay("QQ");
        }
        //设置图片名
        item.setPicture(AllPictureName);

        user = doMyItemsActionService.getUserByUserId(user.getId());
        if (user == null) {
            throw new IdleException(ResultEnum.USER_NOT_EXIST);
        }
        //设置用户
        item.setUser(user);
        if (action.equals("add")) {
            doMyItemsActionService.addItems(item);//插入数据库
        } else if (action.equals("update")) {
            doMyItemsActionService.updateItems(item);//修改数据库
        }
    }
    /**
    * servlet文件上传
    */
    //        try {
    //            DiskFileUpload fu = new DiskFileUpload();
    //            // 设置最大文件尺寸，这里是12MB
    //            fu.setSizeMax(4194304 * 3);
    //            // 设置缓冲区大小，这里是4kb
    //            fu.setSizeThreshold(4096);
    //            // 设置上传字符
    //            fu.setHeaderEncoding("UTF-8");
    //            //设置临时目录：
    //            fu.setRepositoryPath(
    //                    ServletActionContext.getServletContext().getRealPath("/"));
    //
    //            // 得到所有的文件：
    //            HttpServletRequest request = ServletActionContext.getRequest();
    //            List fileItems = fu.parseRequest(request);
    //            Iterator i = fileItems.iterator();
    //            // 依次处理每一个文件：
    //            int j = 0;//数组计数
    //            int k = 0;//图片计数
    //            String AllPictureName = null;//数据库图片
    //            String[] update = new String[9];// 添加的参数数组
    //            while (i.hasNext()) {
    //                FileItem fi = (FileItem) i.next();
    //                if (fi.isFormField()) { // 如果是普通表单字段
    //                    System.out.println("普通表单");
    //                    String values = fi.getString("utf-8");
    //                    JSONObject getParas = JSONObject.fromObject(values);
    //                    String paras[] = {"item.name", "item.price",
    //                            "item.overview", "item.type", "item.contact",
    //                            "item.ifNew", "item.origPrice"};
    //                    if (action.equals("add")) {
    //                        if (doMyItemsActionService.ifItemsExist(
    //                                getParas.getString("item.overview"),
    //                                getParas.getString("item.contact"))) {
    //                            return false;
    //                        }
    //                    }
    //                    for (int s = 0; s < paras.length; s++) {
    //                        String value = getParas.getString(paras[s]);
    //                        if (paras[s].equals("item.contact")) {
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
    //                        if (item.getId() == null) {
    //                            int num = doMyItemsActionService.getLatestId();
    //                            picNewName = serverImagesPath + user.getId() + "000"
    //                                    + (num + 1) + "000" + k + "." + pic;
    //                        } else {
    //                            picNewName = serverImagesPath + user.getId() + "000"
    //                                    + item.getId() + "000" + k + "." + pic;
    //                        }
    //                        AllPictureName += "+" + picNewName;//将图片名称加入数据库
    //                        File f1 = new File(
    //                                localPath + picNewName);
    //                        fi.write(f1);
    //                        File f2 = new File(
    //                                serverPath 
    //                                        + picNewName);
    //                        copyFile.fileChannelCopy(f1, f2);
    //                        update[8] = AllPictureName;
    //                    }
    //                }
    //            }
    //            for (int m = 0; m < update.length; m++) {
    //                System.out.println(update[m]);
    //            }
    //            if (action.equals("add")) {
    //                if (doMyItemsActionService.addItems(update, user.getId())) {
    //                    return true;
    //                }
    //            } else if (action.equals("update")) {
    //                if (doMyItemsActionService.updateItems(item.getId(), update)) {
    //                    return true;
    //                }
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            return false;
    //        }
    //        return false;

    public File[] getFile() {
        return file;
    }

    public void setFile(File[] file) {
        this.file = file;
    }
    public Items getItem() {
        return item;
    }
    public void setItem(Items item) {
        this.item = item;
    }

    public DoMyItemsActionService getDoMyItemsActionService() {
        return doMyItemsActionService;
    }

    public void setDoMyItemsActionService(
            DoMyItemsActionService doMyItemsActionService) {
        this.doMyItemsActionService = doMyItemsActionService;
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
