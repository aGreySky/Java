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
 * �ԡ��ҡ�����Ʒ������ɾ�ģ�
 * 
 * 1.�ϴ�һ����Ʒ( url : item?action=add )
 *�����û����uid ��multiple��(ͼƬ(���������), ����(name),�۸�(price),���(overview),����(type),��ϵ��ʽ(contact))
 *���� �ɹ� {"flag":"true"} ʧ�� {"flag":"false"}

 *2.�޸�һ����Ʒ( url : item?action=update)
 *������Ʒ���id��multiple��(json+ͼƬ)-------(ͼƬ(���������), ����(name),�۸�(price),���(overview),����(type),��ϵ��ʽ(contact))
 *���� �ɹ� {"flag":"true"} ʧ�� {"flag":"false"}

 *3.ɾ��һ����Ʒ( url :item?action=delete)
 *������Ʒ���id
 *���� �ɹ� {"flag":"true"} ʧ�� {"flag":"false"}
 * @author zzq
 *
 */
public class DoMyItemsAction extends ActionSupport {
    private DoMyItemsActionService doMyItemsActionService;
    String localPath = "E:/eclipse/CampusIDLE-WEB/WebContent/images/itemsPicture";//���ص�ַ
    String serverPath = "C:/apache-tomcat-8.5.23/wtpwebapps/CampusIDLE-WEB/images/itemsPicture";//��������ַ
    String serverImagesPath = "http://xiaoyou.free.ngrok.cc/CampusIDLE-WEB/images/itemsPicture/";
    private Items item;
    private User user;
    private String action;
    private File[] file;
    private String[] fileFileName;
    private ResultVO resultVO;

    @Override
    public String execute() throws IOException {
        if (action.equals("add")) { // ����ҵ�������Ϣ
            System.out.println("add");
            addAndUpdateMyItem();//��������
            user = doMyItemsActionService.getUserByUserId(user.getId());
            if (user == null) {
                throw new IdleException(ResultEnum.USER_NOT_EXIST);
            }
            user.setItemsNum(
                    Integer.toString(
                            (Integer.parseInt(user.getItemsNum()) + 1)));
            doMyItemsActionService.updateUserItemsNum(user);
        } else if (action.equals("delete")) { // �¼��ҵ�������Ϣ
            System.out.println("delete");
            deleteMyItem();//ɾ�����ݿ�����
            user = doMyItemsActionService.getUserByUserId(user.getId());
            if (user == null) {
                throw new IdleException(ResultEnum.USER_NOT_EXIST);
            }
            user.setItemsNum(
                    Integer.toString(
                            (Integer.parseInt(user.getItemsNum()) - 1)));
            doMyItemsActionService.updateUserItemsNum(user);
        } else if (action.equals("update")) {// �޸��ҵ�������Ϣ
            System.out.println("update");
            addAndUpdateMyItem();//��������
        } else {
            throw new IdleException(ResultEnum.ACTION_ERROR);
        }
        resultVO = ResultVOUtil.success();
        return SUCCESS;

    }

    //ɾ���ҵĽ���
    private void deleteMyItem() {
        System.out.println("id=" + item.getId());
        item = doMyItemsActionService.getItemsById(item.getId());
        if (item == null) {
            throw new IdleException(ResultEnum.ITEM_NOT_EXIST);
        }
        /**
         * 1.ɾ������
         */
        doMyItemsActionService.deleteItem(item);

        /**
         * 2.ɾ��ͼƬ
         */
        String[] picUrl = item.getPicture().split("//+");//ͼƬ����
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
    //��ӡ������ҵ�����
    private void addAndUpdateMyItem() {
        System.out.println(fileFileName[0]);
        String AllPictureName = null;//��Ҫ�������ݿ���ļ���
        if (action.equals("add")) {

            /**
             * 1.�ж������Ƿ񷢲���
             */
            if (doMyItemsActionService.ifItemsExist(//�ж����ݿ����Ƿ��и�����
                    item.getOverview(), item.getContact())) {
                throw new IdleException(ResultEnum.ITEM_EXIST);
            }
        }

        /**
         * 2.�ϴ��ļ�
         */
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

        /**
         * 3.�޸����ݿ�
         */
        //������ϵ��ʽ
        Pattern p = Pattern.compile(
                "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
        Matcher m = p.matcher(item.getContact());
        if (m.matches()) {
            item.setWay("�ֻ�");
        } else {
            item.setWay("QQ");
        }
        //����ͼƬ��
        item.setPicture(AllPictureName);

        user = doMyItemsActionService.getUserByUserId(user.getId());
        if (user == null) {
            throw new IdleException(ResultEnum.USER_NOT_EXIST);
        }
        //�����û�
        item.setUser(user);
        if (action.equals("add")) {
            doMyItemsActionService.addItems(item);//�������ݿ�
        } else if (action.equals("update")) {
            doMyItemsActionService.updateItems(item);//�޸����ݿ�
        }
    }
    /**
    * servlet�ļ��ϴ�
    */
    //        try {
    //            DiskFileUpload fu = new DiskFileUpload();
    //            // ��������ļ��ߴ磬������12MB
    //            fu.setSizeMax(4194304 * 3);
    //            // ���û�������С��������4kb
    //            fu.setSizeThreshold(4096);
    //            // �����ϴ��ַ�
    //            fu.setHeaderEncoding("UTF-8");
    //            //������ʱĿ¼��
    //            fu.setRepositoryPath(
    //                    ServletActionContext.getServletContext().getRealPath("/"));
    //
    //            // �õ����е��ļ���
    //            HttpServletRequest request = ServletActionContext.getRequest();
    //            List fileItems = fu.parseRequest(request);
    //            Iterator i = fileItems.iterator();
    //            // ���δ���ÿһ���ļ���
    //            int j = 0;//�������
    //            int k = 0;//ͼƬ����
    //            String AllPictureName = null;//���ݿ�ͼƬ
    //            String[] update = new String[9];// ��ӵĲ�������
    //            while (i.hasNext()) {
    //                FileItem fi = (FileItem) i.next();
    //                if (fi.isFormField()) { // �������ͨ���ֶ�
    //                    System.out.println("��ͨ��");
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
    //                        if (item.getId() == null) {
    //                            int num = doMyItemsActionService.getLatestId();
    //                            picNewName = serverImagesPath + user.getId() + "000"
    //                                    + (num + 1) + "000" + k + "." + pic;
    //                        } else {
    //                            picNewName = serverImagesPath + user.getId() + "000"
    //                                    + item.getId() + "000" + k + "." + pic;
    //                        }
    //                        AllPictureName += "+" + picNewName;//��ͼƬ���Ƽ������ݿ�
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
