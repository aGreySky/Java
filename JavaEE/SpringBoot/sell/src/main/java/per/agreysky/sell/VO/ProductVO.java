package per.agreysky.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.util.List;

/**
 * 商品（包含类目）
 */
@Data
public class ProductVO {

    @JsonProperty("name")//返回给前端是name
    private  String categoryName;

    @JsonProperty("type")//返回给前端是type
    private  Integer categoryType;

    @JsonProperty("foods")//返回给前端是foods
    private List<ProductInfoVO> productInfoVOList;
}
