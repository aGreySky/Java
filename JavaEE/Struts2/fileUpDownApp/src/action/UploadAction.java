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
    private String iconFileName;//ͷ���ļ���
    private String iconContentType;//ͷ���ļ�����
    private String resumeFileName;//�����ļ���
    private String resumeContentType;//�����ļ�����

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
            request.put("error", "ͷ���С���ܳ���50kb!");
            return ERROR;
        } else if (code == 2) {
            request.put("error", "ͷ��ֻ��Ϊgif���ͻ�jpeg���ͣ�");
            return ERROR;
        } else if (code == 3) {
            request.put("error", "ͷ����Ϊ�գ�");
            return ERROR;
        } else if (code == 0) {
            code = fileUpLoad(resume, 2 * 1024 * 1024,
                    new String[]{"application/msword"}, resumeFileName,
                    resumeContentType);
            if (code == 1) {
                request.put("error", "������С���ܳ���50kb!");
                return ERROR;
            } else if (code == 2) {
                request.put("error", "����ֻ��Ϊdoc���ͣ�");
                return ERROR;
            } else if (code == 3) {
                request.put("error", "��������Ϊ�գ�");
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
        request.put("error", "δ֪�����������ϴ���");
        return ERROR;
    }
    /**
     * @param code
     * 0:�ϴ��ɹ�
     * 1:�ļ���С����fileMaxLength
     * 2:�ļ����Ͳ�����Ҫ��
     * 3:�ļ�Ϊ��
     * @param file���ļ�
     * @param fileMaxLength���ļ�����ڴ�
     * @param designatedFileContentType[]���ļ����������
     * @param fileName���ļ���
     * @param fileContentType���ļ�����
     * @return
     * @throws IOException 
     */
    public int fileUpLoad(File file, long fileMaxLength,
            String[] designatedFileContentType, String fileName,
            String fileContentType) throws IOException {
        int code = 0;
        //�ж��ļ��Ƿ��ϴ�
        if (file == null && fileName == null) {
            code = 3;
            return code;
        }
        //�ж��ļ������Ƿ����Ҫ��
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
        // 1.��ȡ�ϴ����ļ�·��  
        File filePath = new File(ServletActionContext.getServletContext()
                .getRealPath("/iconAndResume"));
        // 2.�ж�Ŀ¼�Ƿ���ڣ���������ڴ����ļ���  
        if (!filePath.exists()) {
            filePath.mkdir();// ����Ŀ¼  
        }
        System.out.println(filePath);
        // 3.�����ļ�����������Ϊ������ָ��·��  
        FileInputStream is = new FileInputStream(file);
        // ��ȡ�ϴ��ļ��Ĵ�С(��λ���ֽ�),��������С������
        int length = is.available();// ��ȡ�ϴ��ļ����ֽڴ�С  
        if (length >= fileMaxLength) {
            code = 1;
            is.close();
            return code;
        }

        // 4.���ж��ļ��Ƿ���ڣ���������ɾ����Ȼ���ȡ�ļ������������������ĵ�ַ�Ѽ�����  
        File oldFile = new File(filePath, fileName);
        if (oldFile.exists()) {
            oldFile.delete();
        }
        FileOutputStream out = new FileOutputStream(
                oldFile);
        try {
            // 5.���Խ�  
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) > 0) {
                // ��buffer��Ķ�����������0��ʼ��ÿ��дlen��ô����д���������  
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
