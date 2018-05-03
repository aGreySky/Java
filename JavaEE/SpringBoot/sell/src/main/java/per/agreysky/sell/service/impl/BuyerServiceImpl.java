package per.agreysky.sell.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.agreysky.sell.dto.OrderDTO;
import per.agreysky.sell.enums.ResultEnum;
import per.agreysky.sell.exception.SellException;
import per.agreysky.sell.service.BuyerService;
import per.agreysky.sell.service.OrderService;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid,String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDTO cancel(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid,orderId);
        if(orderDTO == null){
            log.error("[取消订单]查不到订单，orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid,String orderId){
        OrderDTO orderDTO =orderService.findOne(orderId);
        if (orderDTO == null){
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("[查询订单]订单的openid不一致。openid={},orderId={}",openid,orderId);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
