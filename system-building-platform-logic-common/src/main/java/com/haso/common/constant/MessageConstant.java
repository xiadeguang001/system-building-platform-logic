package com.haso.common.constant;

/**
 * 报错、提示信息 定数定义类。
 *
 * <pre>
 *
 * Ver.   变更/障害No.    修改日期      作成/变更者       修改内容
 * ------ ------------- ------------ --------------- -----------------------------
 * 1.0    #13914        2020-05-10   Fengbo.Ge       初期作成
 * </pre>
 */
public interface MessageConstant {

    /**
     * 保存成功
     */
    public static final String SAVE_SUCCESS = "0001";

    /**
     * 编辑成功
     */
    public static final String EDIT_SUCCESS = "0002";

    /**
     * 删除成功
     */
    public static final String DELETE_SUCCESS = "0003";

    /**
     * 操作成功
     */
    public static final String OPRATE_SUCCESS = "0004";

    /**
     * 操作失败
     */
    public static final String SAVE_FAIL = "0005";

    /**
     * 删除失败
     */
    public static final String DELETE_FAIL = "0006";

    /**
     * 文件导入成功
     */
    public static final String FILE_IMPORT_SUCCESS = "0007";

    /**
     * 文件导入失败
     */
    public static final String FILE_IMPORT__FAIL = "0008";

    /**
     * 字典参数格式不正确
     */
    public static final String DICT_PARAMETER_NOT_INCORRECT = "0009";

    /**
     * 参数不识别
     */
    public static final String PARAMETER_NOT_INCORRECT = "0010";

    /**
     * 此{0}数据不存在
     */
    public static final String DATA_NOT_FIND = "0011";


    /**
     * {0}格式不正确
     */
    public static final String DATA_FORMAT_NOT_FIND = "0012";


    /**
     * 该值可用
     */
    public static final String DATA_VALID = "0013";


    /**
     * 该值不可用
     */
    public static final String DATA_INVALID = "0014";


    /**
     * 文件导入成功，但有错误。
     */
    public static final String FILE_IMPORT_WARN = "0015";

    /**
     * 字段长度不正，忽略导入
     */
    public static final String DATA_LENGTH_ERROR = "0016";


    /**
     * 单位编码只能输入英数字，忽略导入
     */
    public static final String DATA_CHECK = "0017";
}

