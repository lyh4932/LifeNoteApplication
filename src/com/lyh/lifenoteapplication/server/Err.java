package com.lyh.lifenoteapplication.server;

public class Err {

    /** 网络连接超时 */
    public static final int HTTP_CONNECTION_TIMEOUT = -100;

    /** Socket连接超时 */
    public static final int HTTP_SOCKET_TIMEOUT = -101;

    /** 网络协议错误 */
    public static final int HTTP_PROTOCOL_ERR = -102;

    /** 其他网络错误 */
    public static final int HTTP_OTHER_ERR = -200;

    /** 成功 */
    public static final int OK = 0;

    /** 成功 */
    public static final String OK_S = "ERROR_OK";

    /** 系统错误 */
    public static final int SYSTEM_ERR = 1;

    /** 参数输入错误 */
    public static final int PARAM_ERR = 2;

    /** 用户不存在 */
    public static final int USER_NOT_EXIST = 101;

    /** 用户密码错误 */
    public static final int USER_PASSWORD_ERR = 102;

    /** 用户已存在 */
    public static final int USER_ID_ALREADY_EXIST = 103;

    /** 注册失败 */
    public static final int USER_ID_REGISTER_FAILED = 104;

    /** 激活失败 */
    public static final int USER_ID_ACTIVATE_FAILED = 105;

    /** 登录失败 */
    public static final int USER_LOGIN_FAILED = 106;

    /** 用户名不存在 */
    public static final int USER_ID_NOT_EXIST = 107;

    /** 密码错误 */
    public static final int PASSWORD_ERR = 108;

    /** 登出失败 */
    public static final int USER_LOGOUT_FAILED = 109;

    /** 会话过期 */
    public static final int SESSION_VOID = 21019;

    /** token验证错误 */
    public static final int SESSION_VALIDATE_ERROR = 21018;

    /** 会话过期 */
    public static final String SESSION_VOID_S = "ERROR_SESSION_VOID";

    /** 无访问权限 */
    public static final int AUTHORITY_LIMIT = 111;

    /** 手机号长度错误 */
    public static final int USER_ID_PHONE_LENGTH = 112;

    /** 电子邮件地址长度不能超过50 */
    public static final int USER_ID_EMAIL_LENGTH = 113;

    /** MAC地址长度错误 */
    public static final int DEVICE_MAC_LENGTH = 114;

    /** 设备已绑定 */
    public static final int DEVICE_ALREADY_BIND = 115;

    /** 设备型号不存在 */
    public static final int DEVICE_MODEL_NOT_EXIST = 116;

    /** 城市信息不存在 */
    public static final int CITY_NOT_EXIST = 117;

    /** 设备名称不能为空 */
    public static final int DEVICE_NAME_NULL = 118;

    /** 设备名称不能超过256 */
    public static final int DEVICE_NAME_LENGTH = 119;

    /** 协议编号不能为空 */
    public static final int DEVICE_PROTOCOL_NULL = 120;

    /** 传入的国家ID不存在 */
    public static final int COUNTRY_ID_NOT_EXIST = 121;

    /** 设备不存在 */
    public static final int DEVICE_NOT_EXIST = 122;

    /** 资源创建错误 */
    public static final int RESOURCE_CREATE_ERR = 201;

    /** 资源不存在 */
    public static final int RESOURCE_NOT_EXIST = 202;

    /** 其他错误 */
    public static final int OTHER_ERR = 444;

    // ////////////////////////////全局错误码//////////////////////////////////
    /** 网络异常 */
    public static final int ERR_MESSAGE_NETWORK_ANOMALY = 00010;

    /** 网络超时 */
    public static final int ERR_MESSAGE_NETWORK_TIMEOUT = 00011;
}
