package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {
    private String username;
    private String subject;
    private String classes;
    private File icon;
    private File resume;
    private String iconFileName;//头像文件名
    private String iconContentType;//头像文件类型
    private String resumeFileName;//简历文件名
    private String resumeContentType;//简历文件类型

    @Override
    public String execute() throws IOException {
        System.out.println(icon);
        System.out.println(iconFileName + resumeFileName);
        Map request = (Map) ActionContext.getContext().get("request");
        int code = 0;
        code = fileUpLoad(icon, 50 * 1024,
                new String[]{"image/jpeg", "image/gif"}, iconFileName,
                iconContentType);
        if (code == 1) {
            request.put("error", "头像大小不能超过50kb!");
            return ERROR;
        } else if (code == 2) {
            request.put("error", "头像只能为gif类型或jpeg类型！");
            return ERROR;
        } else if (code == 3) {
            request.put("error", "头像不能为空！");
            return ERROR;
        } else if (code == 0) {
            code = fileUpLoad(resume, 2 * 1024 * 1024,
                    new String[]{"application/msword"}, resumeFileName,
                    resumeContentType);
            if (code == 1) {
                request.put("error", "简历大小不能超过50kb!");
                return ERROR;
            } else if (code == 2) {
                request.put("error", "简历只能为doc类型！");
                return ERROR;
            } else if (code == 3) {
                request.put("error", "简历不能为空！");
                return ERROR;
            } else if (code == 0) {
                File filePath = new File(ServletActionContext
                        .getServletContext().getRealPath("iconAndResume"));
                String iconUrl = filePath + "\\" + iconFileName;
                String resumeUrl = filePath + "\\" + resumeFileName;
                request.put("iconUrl", iconUrl);
                request.put("resumeUrl", resumeUrl);
                return SUCCESS;
            }
        }
        System.out.println(code);
        request.put("error", "未知错误，请重新上传！");
        return ERROR;
    }
    /**
     * @param code
     * 0:上传成功
     * 1:文件大小超出fileMaxLength
     * 2:文件类型不符合要求
     * 3:文件为空
     * @param file：文件
     * @param fileMaxLength：文件最大内存
     * @param designatedFileContentType[]：文件允许的类型
     * @param fileName：文件名
     * @param fileContentType：文件类型
     * @return
     * @throws IOException 
     */
    public int fileUpLoad(File file, long fileMaxLength,
            String[] designatedFileContentType, String fileName,
            String fileContentType) throws IOException {
        int code = 0;
        //判断文件是否上传
        if (file == null && fileName == null) {
            code = 3;
            return code;
        }
        //判断文件类型是否符合要求
        for (int i = 0; i < designatedFileContentType.length; i++) {
            System.out.println(fileContentType);
            if (fileContentType.equals(designatedFileContentType[i])) {
                break;
            }
            if (i == designatedFileContentType.length - 1) {
                code = 2;
                return code;
            }
        }
        // 1.获取上传的文件路径  
        File filePath = new File(ServletActionContext.getServletContext()
                .getRealPath("/iconAndResume"));
        // 2.判断目录是否存在，如果不存在创建文件夹  
        if (!filePath.exists()) {
            filePath.mkdir();// 创建目录  
        }
        System.out.println(filePath);
        // 3.声明文件的输入流，为输入流指定路径  
        FileInputStream is = new FileInputStream(file);
        // 获取上传文件的大小(单位是字节),做数据狭小的限制
        int length = is.available();// 获取上传文件的字节大小  
        if (length >= fileMaxLength) {
            code = 1;
            is.close();
            return code;
        }

        // 4.先判断文件是否存在，存在则先删除。然后获取文件输出流，声明输出流的地址已及名称  
        File oldFile = new File(filePath, fileName);
        if (oldFile.exists()) {
            oldFile.delete();
        }
        FileOutputStream out = new FileOutputStream(
                oldFile);
        try {
            // 5.流对接  
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) > 0) {
                // 将buffer里的二进制数，从0开始，每次写len这么长，写入输出流中  
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            is.close();
            out.close();
        }
        return code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public File getIcon() {
        return icon;
    }

    public void setIcon(File icon) {
        this.icon = icon;
    }

    public File getResume() {
        return resume;
    }

    public void setResume(File resume) {
        this.resume = resume;
    }

    public String getIconFileName() {
        return iconFileName;
    }

    public void setIconFileName(String iconFileName) {
        this.iconFileName = iconFileName;
    }

    public String getIconContentType() {
        return iconContentType;
    }

    public void setIconContentType(String iconContentType) {
        this.iconContentType = iconContentType;
    }

    public String getResumeFileName() {
        return resumeFileName;
    }

    public void setResumeFileName(String resumeFileName) {
        this.resumeFileName = resumeFileName;
    }

    public String getResumeContentType() {
        return resumeContentType;
    }

    public void setResumeContentType(String resumeContentType) {
        this.resumeContentType = resumeContentType;
    }

}
