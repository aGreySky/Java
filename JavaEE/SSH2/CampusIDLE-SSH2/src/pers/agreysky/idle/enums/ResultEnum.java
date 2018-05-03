package pers.agreysky.idle.enums;

public enum ResultEnum {

    USER_NOT_EXIST(1, "用户不存在"), ACTION_ERROR(2, "操作错误"), FRIEND_EXISTED(3,
            "交友信息已经发布过"), FILE_NOT_EXIST(4, "文件不存在"), FRIEND_NOT_EXIST(5,
                    "交友信息不存在"), HELP_NOT_EXIST(6, "帮助信息不存在"), HELP_EXISTED(7,
                            "帮助信息已经发布过"), ITEM_NOT_EXIST(8,
                                    "闲置信息不存在"), ITEM_EXIST(9,
                                            "闲置信息已经发布过"), JOB_EXIST(10,
                                                    "兼职信息已经发布过"), TYPE_NOT_EXIST(
                                                            11,
                                                            "搜索类型不存在"), CONDITION_NOT_NULL(
                                                                    12,
                                                                    "搜索条件不能为空"), PHONE_NOT_EXIST(
                                                                            13,
                                                                            "手机号不存在"), LOGIN_USEREMAIL_FAIL(
                                                                                    14,
                                                                                    "登录失败，邮箱账号与密码不匹配"), LOGIN_PHONE_FAIL(
                                                                                            15,
                                                                                            "登录失败，手机账号与密码不匹配"), LOGIN_USERNAME_FAIL(
                                                                                                    16,
                                                                                                    "登录失败，用户名与密码不匹配"), NON_ACTIVATION(
                                                                                                            17,
                                                                                                            "账号未激活，不能登录...请查看注册邮箱账号中的邮件进行激活"), USERNAME_EXIST(
                                                                                                                    18,
                                                                                                                    "用户名已存在"),;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
