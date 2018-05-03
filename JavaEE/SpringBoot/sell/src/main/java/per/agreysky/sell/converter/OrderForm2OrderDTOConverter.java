package per.agreysky.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import per.agreysky.sell.dataobject.OrderDetail;
import per.agreysky.sell.dto.OrderDTO;
import per.agreysky.sell.enums.ResultEnum;
import per.agreysky.sell.exception.SellException;
import per.agreysky.sell.form.OrderForm;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2OrderDTOConverter {

    public   static OrderDTO cenvert(OrderForm orderForm){
        Gson gson =new Gson();

        OrderDTO orderDTO =new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList=new ArrayList<>();
        try {
            orderDetailList =  gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        }catch (Exception e){
            log.error("[对象转换]错误，Strig={}",orderForm.getItems());
            throw  new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return  orderDTO;
    }
}
