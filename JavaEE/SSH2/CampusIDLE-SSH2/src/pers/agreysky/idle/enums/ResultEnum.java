package pers.agreysky.idle.enums;

public enum ResultEnum {

    USER_NOT_EXIST(1, "�û�������"), ACTION_ERROR(2, "��������"), FRIEND_EXISTED(3,
            "������Ϣ�Ѿ�������"), FILE_NOT_EXIST(4, "�ļ�������"), FRIEND_NOT_EXIST(5,
                    "������Ϣ������"), HELP_NOT_EXIST(6, "������Ϣ������"), HELP_EXISTED(7,
                            "������Ϣ�Ѿ�������"), ITEM_NOT_EXIST(8,
                                    "������Ϣ������"), ITEM_EXIST(9,
                                            "������Ϣ�Ѿ�������"), JOB_EXIST(10,
                                                    "��ְ��Ϣ�Ѿ�������"), TYPE_NOT_EXIST(
                                                            11,
                                                            "�������Ͳ�����"), CONDITION_NOT_NULL(
                                                                    12,
                                                                    "������������Ϊ��"), PHONE_NOT_EXIST(
                                                                            13,
                                                                            "�ֻ��Ų�����"), LOGIN_USEREMAIL_FAIL(
                                                                                    14,
                                                                                    "��¼ʧ�ܣ������˺������벻ƥ��"), LOGIN_PHONE_FAIL(
                                                                                            15,
                                                                                            "��¼ʧ�ܣ��ֻ��˺������벻ƥ��"), LOGIN_USERNAME_FAIL(
                                                                                                    16,
                                                                                                    "��¼ʧ�ܣ��û��������벻ƥ��"), NON_ACTIVATION(
                                                                                                            17,
                                                                                                            "�˺�δ������ܵ�¼...��鿴ע�������˺��е��ʼ����м���"), USERNAME_EXIST(
                                                                                                                    18,
                                                                                                                    "�û����Ѵ���"),;
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
