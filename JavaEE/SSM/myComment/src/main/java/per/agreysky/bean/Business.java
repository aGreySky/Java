package per.agreysky.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
@Data
@JsonInclude(Include.NON_NULL)
public class Business extends BaseBean {
    private Long id;
    private String title;
    private String subTitle;
    private Double price;
    private String distance;
    private Integer mumber;
    private String desc;
    private String city;
    private String category;
    private int star;
    private String img;

}
