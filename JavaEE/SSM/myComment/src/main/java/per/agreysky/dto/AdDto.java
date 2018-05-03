package per.agreysky.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import per.agreysky.bean.Ad;

@JsonInclude(Include.NON_NULL)
@Data
public class AdDto extends Ad {
    private String img;

    private MultipartFile imgFile;

}
