package per.agreysky.org.action;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import per.agreysky.org.service.PhotoService;
import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Student;

public class PhotoAction extends ActionSupport {
    private Book book;
    private Student student;
    private PhotoService photoService;
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    //��ȡͼƬ
    public String getImage() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        String ISBN = book.getISBN();
        Book b = photoService.selectBook(ISBN);
        byte[] photo = b.getPhoto();
        response.setContentType("image/jpeg");
        ServletOutputStream os = response.getOutputStream();
        if (photo != null && photo.length != 0) {
            for (int i = 0; i < photo.length; i++) {
                os.write(photo[i]);
            }
            os.flush();
        }
        return NONE;
    }
    //��ȡ�û���Ƭ
    public String getStuImage() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        //        System.out.println(student.getReaderId());
        Student stu = photoService.selectByReaderId(student.getReaderId());
        byte[] photo = stu.getPhoto();
        response.setContentType("image/jpeg");
        ServletOutputStream os = response.getOutputStream();
        if (photo != null && photo.length != 0) {
            for (int i = 0; i < photo.length; i++) {
                os.write(photo[i]);
            }
            os.flush();
        }
        return NONE;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public PhotoService getPhotoService() {
        return photoService;
    }
    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

}
