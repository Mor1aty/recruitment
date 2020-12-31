package com.lishi.recruitment.wrap;


import com.lishi.recruitment.constant.CustomConstant;

/**
 * @author LiShi
 * date: 2020/12/31 14:19
 * description: 返回方法封装
 */
public class WrapMapper {
    private WrapMapper() {

    }

    /**
     * 成功封装
     *
     * @param msg String
     * @param data E
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> ok(int code, String msg, E data) {
        return new Wrapper<E>(code, msg, data);
    }

    /**
     * 成功封装
     *
     * @param msg String
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> ok(int code, String msg) {
        return new Wrapper<E>(code, msg, null);
    }

    /**
     * 成功封装
     *
     * @param msg String
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> okExec(String msg) {
        return new Wrapper<E>(CustomConstant.Wrap.CODE_EXEC_SUCCESS, msg, null);
    }

    /**
     * 成功封装
     *
     * @param msg String
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> okExec(String msg, E data) {
        return new Wrapper<E>(CustomConstant.Wrap.CODE_EXEC_SUCCESS, msg, data);
    }

    /**
     * 成功封装
     *
     * @param msg String
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> okObtain(String msg, E data) {
        return new Wrapper<E>(CustomConstant.Wrap.CODE_OBTAIN_SUCCESS, msg, data);
    }


    /**
     * 错误封装
     *
     * @param msg String
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> error(int code, String msg) {
        return new Wrapper<E>(code, msg, null);
    }

    /**
     * 错误封装
     *
     * @param msg String
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> errorExec(String msg) {
        return new Wrapper<E>(CustomConstant.Wrap.CODE_EXEC_FAILURE, msg, null);
    }

    /**
     * 错误封装
     *
     * @param msg String
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> errorObtain(String msg) {
        return new Wrapper<E>(CustomConstant.Wrap.CODE_OBTAIN_FAILURE, msg, null);
    }
}
