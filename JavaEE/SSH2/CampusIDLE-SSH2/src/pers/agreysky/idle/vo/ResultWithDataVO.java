package pers.agreysky.idle.vo;

public class ResultWithDataVO<T> {
    //������
    private Integer code;

    // ��ʾ��Ϣ
    private String msg;

    // ��������
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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
