package per.agreysky.sell.exception;

import per.agreysky.sell.enums.ResultEnum;

public class SellException extends RuntimeException {

    public Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code=resultEnum.getCode();
    }

    public SellException( Integer code , String message) {
        super(message);
        this.code = code;
    }
}
