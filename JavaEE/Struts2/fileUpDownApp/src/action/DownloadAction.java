package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {
    private String subject;
    private String classes;
    private InputStream inputStream;
    private String targetFileName;
    @Override
    public String execute() {
        Map request = (Map) ActionContext.getContext().get("request");
        if (subject.equals(null) || classes.equals(null)) {
            request.put("error", "请选择专业和年级!");
            return ERROR;
        } else {
            targetFileName = classes + subject;
            System.out.println(targetFileName);
            try {
                inputStream = new FileInputStream(
                        new File("E:\\图片", "课程表.doc"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return SUCCESS;
        }
    }
    //文件名转换编码，防止中文乱码   
    public String getDownloadFileName() {
        String downloadFileName = targetFileName;
        try {
            // 使用ISO8859-1编码  
            downloadFileName = new String(downloadFileName.getBytes(),
                    "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return downloadFileName;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getClasses() {
        return classes;
    }
    public void setClasses(String classes) {
        this.classes = classes;
    }

    public void setTargetFileName(String targetFileName) {
        try {
            targetFileName = new String(targetFileName.getBytes("ISO8859-1"),
                    "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.targetFileName = targetFileName;
    }

    public String getTargetFileName() {
        return targetFileName;
    }
    public InputStream getInputStream() {
        return inputStream;
    }
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
