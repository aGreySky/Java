package pers.agreysky.idle.exception;

import pers.agreysky.idle.enums.ResultEnum;

public class IdleException extends RuntimeException {

    public Integer code;

    public IdleException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public IdleException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
