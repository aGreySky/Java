package pers.agreysky.idle.vo;

/**
 * Http请求返回的最外层对象
 */

public class ResultVO {
    //错误码
    private Integer code;

    // 提示信息
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
