package com.lishi.recruitment.constant;

/**
 * @author LiShi
 * date: 2021/1/9 18:04
 * description: 业务常量
 */
public class Constant {

    private Constant() {
    }

    /**
     * 应聘者身份
     */
    public static final int IDENTITY_CANDIDATE = 0;

    /**
     * 公司身份
     */
    public static final int IDENTITY_COMPANY = 1;

    /**
     * 管理员身份
     */
    public static final int IDENTITY_ADMIN = 2;

    /**
     * 招聘进度: 等待
     */
    public static final int PROGRESS_WAIT = 0;

    /**
     * 招聘进度: 接受
     */
    public static final int PROGRESS_ACCEPT = 1;

    /**
     * 招聘进度: 拒绝
     */
    public static final int PROGRESS_REFUSE = 2;
}
