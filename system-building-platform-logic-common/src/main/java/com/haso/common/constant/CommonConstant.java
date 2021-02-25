package com.haso.common.constant;

/**
 * @ClassName CommonConstant
 * @Description TODO
 * @Author haso
 * @Date 2020/02/29 15:27
 * @Version 1.0
 **/
public interface CommonConstant {

    /** sys_user 表 username 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_SYS_USER_USERNAME = "uniq_sys_user_username";
    /** sys_user 表 work_no 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_SYS_USER_WORK_NO = "uniq_sys_user_work_no";
    /** sys_user 表 phone 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_SYS_USER_PHONE = "uniq_sys_user_phone";
    /** sys_user 表 email 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_SYS_USER_EMAIL = "uniq_sys_user_email";
    /** sys_quartz_job 表 job_class_name 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_JOB_CLASS_NAME = "uniq_job_class_name";
    /** sys_position 表 code 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_CODE = "uniq_code";
    /** sys_role 表 code 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_SYS_ROLE_CODE = "uniq_sys_role_role_code";
    /** sys_depart 表 code 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_DEPART_ORG_CODE = "uniq_depart_org_code";

    //「エラー解決ステータス」が「未解決」
    public final static Integer SOLVE_STATUS_FLG_0 = 0;
    //「エラー解決ステータス」が「解決済み」
    public final static Integer SOLVE_STATUS_FLG_1 = 1;
    //「エラー解決ステータス」が「調査中」
    public final static Integer SOLVE_STATUS_FLG_2 = 2;

    /**
     * BATCH_COUNT
     */
    public static final Integer BATCH_COUNT = 10000;

    /**
     * 正常状态
     */
    public static final Integer STATUS_NORMAL = 0;

    /**
     * 禁用状态
     */
    public static final Integer STATUS_DISABLE = -1;

    /**
     * 删除标志
     */
    public static final Integer DEL_FLAG_1 = 1;

    /**
     * 未删除
     */
    public static final Integer DEL_FLAG_0 = 0;

    /**
     * 系统日志类型： 登录
     */
    public static final int LOG_TYPE_1 = 1;

    /**
     * 系统日志类型： 操作
     */
    public static final int LOG_TYPE_2 = 2;

    /**
     * 操作日志类型： 查询
     */
    public static final int OPERATE_TYPE_1 = 1;

    /**
     * 操作日志类型： 添加
     */
    public static final int OPERATE_TYPE_2 = 2;

    /**
     * 操作日志类型： 更新
     */
    public static final int OPERATE_TYPE_3 = 3;

    /**
     * 操作日志类型： 删除
     */
    public static final int OPERATE_TYPE_4 = 4;

    /**
     * 操作日志类型： 倒入
     */
    public static final int OPERATE_TYPE_5 = 5;

    /**
     * 操作日志类型： 导出
     */
    public static final int OPERATE_TYPE_6 = 6;


    /**
     * {@code 500 Server Error} (HTTP/1.0 - RFC 1945)
     */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /**
     * {@code 200 OK} (HTTP/1.0 - RFC 1945)
     */
    public static final Integer SC_OK_200 = 200;

    /**
     * 访问权限认证未通过 510
     */
    public static final Integer SC_JEECG_NO_AUTHZ = 510;

    /**
     * 登录用户Shiro权限缓存KEY前缀
     */
    public static String PREFIX_USER_SHIRO_CACHE = "shiro:cache:org.jeecg.modules.shiro.authc.ShiroRealm.authorizationCache:";
    /**
     * 登录用户Token令牌缓存KEY前缀
     */
    public static final String PREFIX_USER_TOKEN = "prefix_user_token_";
    /**
     * Token缓存时间：3600秒即一小时
     */
    public static final int TOKEN_EXPIRE_TIME = 3600;


    /**
     * 0：一级菜单
     */
    public static final Integer MENU_TYPE_0 = 0;
    /**
     * 1：子菜单
     */
    public static final Integer MENU_TYPE_1 = 1;
    /**
     * 2：按钮权限
     */
    public static final Integer MENU_TYPE_2 = 2;

    /**
     * 通告对象类型（USER:指定用户，ALL:全体用户）
     */
    public static final String MSG_TYPE_UESR = "USER";
    public static final String MSG_TYPE_ALL = "ALL";

    /**
     * 发布状态（0未发布，1已发布，2已撤销）
     */
    public static final String NO_SEND = "0";
    public static final String HAS_SEND = "1";
    public static final String HAS_CANCLE = "2";

    /**
     * 阅读状态（0未读，1已读）
     */
    public static final String HAS_READ_FLAG = "1";
    public static final String NO_READ_FLAG = "0";

    /**
     * 优先级（L低，M中，H高）
     */
    public static final String PRIORITY_L = "L";
    public static final String PRIORITY_M = "M";
    public static final String PRIORITY_H = "H";

    /**
     * 短信模板方式  0 .登录模板、1.注册模板、2.忘记密码模板
     */
    public static final String SMS_TPL_TYPE_0 = "0";
    public static final String SMS_TPL_TYPE_1 = "1";
    public static final String SMS_TPL_TYPE_2 = "2";

    /**
     * 状态(0无效1有效)
     */
    public static final String STATUS_0 = "0";
    public static final String STATUS_1 = "1";

    /**
     * 同步工作流引擎1同步0不同步
     */
    public static final String ACT_SYNC_0 = "0";
    public static final String ACT_SYNC_1 = "1";

    /**
     * 消息类型1:通知公告2:系统消息
     */
    public static final String MSG_CATEGORY_1 = "1";
    public static final String MSG_CATEGORY_2 = "2";

    /**
     * 是否配置菜单的数据权限 1是0否
     */
    public static final Integer RULE_FLAG_0 = 0;
    public static final Integer RULE_FLAG_1 = 1;

    /**
     * 是否用户已被冻结 1(解冻)正常 2冻结
     */
    public static final Integer USER_UNFREEZE = 1;
    public static final Integer USER_FREEZE = 2;

    /**
     * 字典翻译文本后缀
     */
    public static final String DICT_TEXT_SUFFIX = "_dictText";

    /**
     * 表单设计器主表类型
     */
    public static final Integer DESIGN_FORM_TYPE_MAIN = 1;

    /**
     * 表单设计器子表表类型
     */
    public static final Integer DESIGN_FORM_TYPE_SUB = 2;

    /**
     * 表单设计器URL授权通过
     */
    public static final Integer DESIGN_FORM_URL_STATUS_PASSED = 1;

    /**
     * 表单设计器URL授权未通过
     */
    public static final Integer DESIGN_FORM_URL_STATUS_NOT_PASSED = 2;

    /**
     * 表单设计器URL授权未通过
     */
    public static final String DESIGN_FORM_URL_TYPE_ADD = "add";
    /**
     * 表单设计器URL授权未通过
     */
    public static final String DESIGN_FORM_URL_TYPE_EDIT = "edit";
    /**
     * 表单设计器URL授权未通过
     */
    public static final String DESIGN_FORM_URL_TYPE_DETAIL = "detail";
    /**
     * 表单设计器URL授权未通过
     */
    public static final String DESIGN_FORM_URL_TYPE_VIEW = "view";

    /**
     * 员工身份 （1:普通员工  2:上级）
     */
    public static final Integer USER_IDENTITY_1 = 1;
    public static final Integer USER_IDENTITY_2 = 2;

    /**
     * 进口区分 （0:国内; 1:国外）
     */
    public static final Integer IMPORT_TYPE_0 = 0;
    public static final Integer IMPORT_TYPE_1 = 1;

    /**
     * 交货单发行区分 （0:不发行; 1:发行）
     */
    public static final Integer DELIVERY_TYPE_0 = 0;
    public static final Integer DELIVERY_TYPE_1 = 1;

    /**
     * 采购单发行区分 （0:不发行; 1:发行）
     */
    public static final Integer PURCHASE_TYPE_0 = 0;
    public static final Integer PURCHASE_TYPE_1 = 1;

    /**
     * 销售单位 1是0否
     */
    public static final Integer SALES_AVAILABLE_FLAG_0 = 0;
    public static final Integer SALES_AVAILABLE_FLAG_1 = 1;

    /**
     * 进货单位 1是0否
     */
    public static final Integer PURCHASE_AVAILABLE_FLAG_0 = 0;
    public static final Integer PURCHASE_AVAILABLE_FLAG_1 = 1;

    /**
     * 销售默认单位 0:不默认; 1:默认
     */
    public static final Integer SALES_DEFAULT_FLAG_0 = 0;
    public static final Integer SALES_DEFAULT_FLAG_1 = 1;

    /**
     * 进货默认单位 0:不默认; 1:默认;
     */
    public static final Integer PURCHASE_DEFAULT_FLAG_0 = 0;
    public static final Integer PURCHASE_DEFAULT_FLAG_1 = 1;

    /**
     * 状态(0无效1有效)
     */
    public static final Integer STATUS_FLG_0 = 0;
    public static final Integer STATUS_FLG_1 = 1;

    /**
     * 文件上传类型（本地：local，Minio：minio，阿里云：alioss）
     */
    public static final String UPLOAD_TYPE_LOCAL = "local";
    public static final String UPLOAD_TYPE_MINIO = "minio";
    public static final String UPLOAD_TYPE_OSS = "alioss";


    /**
     * 排他字段
     */
    public static final String COMMON_EXCLCNT = "exclcnt";

    /**
     * map参数名
     */
    public static final String COMMON_PARAM1 = "param1";

    /**
     * 状态字段
     */
    public static final String STATUSFLG_DICTTEXT = "statusFlg_dictText";
    public static final String STATUSFLG = "statusFlg";

    /**
     * 订单发货状态：0:未发货 1:部分发货 2:发货完成
     */
    public static final String ORDER_SHIPMENT_STATUS_UNSHIPPED = "0";
    public static final String ORDER_SHIPMENT_STATUS_PARTIAL_SHIPPED = "1";
    public static final String ORDER_SHIPMENT_STATUS_SHIPPED = "2";

    /**
     * 订单发货明细状态 1:已发货; 9:已取消
     */
    public static final String ORDER_SHIPMENT_DETAIL_STATUS_SHIPPED = "1";
    public static final String ORDER_SHIPMENT_DETAIL_STATUS_CANCELLED = "9";

    /**
     * 订单状态  1:已确认
     */
    public static final String ORDER_STATUS_CONFIRMED = "1";



    /**
     * 文件上传目录
     */
    public static final String FILE_UPLOAD_PATH = "denpyo";

    /**
     * 文件上传目录
     */
    public static final int BUFFER_SIZE = 1000;

    /**
     * JOB执行状态
     * 1:未実行
     * 2:実行中
     * 3:実行成功
     * 4:実行失敗
     */

    public static final String JOTAI_NOT_ERFORMED = "1";
    public static final String JOTAI_RUNNING = "2";
    public static final String JOTAI_SUCCESS = "3";
    public static final String JOTAI_FAILURE = "4";


    /**
     * 《保有台種類》
     * 1:999
     * 2:FP1
     * 3:iO!
     * 4:SDスマホ
     * 5:スマプリ
     * 6:決済端末【G7】
     * 7:宅配メイト用簡易PDT
     */
    public static final String DENPYOSHURUI_999 = "1";
    public static final String DENPYOSHURUI_FP1 = "2";
    public static final String DENPYOSHURUI_iO = "3";
    public static final String DENPYOSHURUI_SD_SMARTPHONE = "4";
    public static final String DENPYOSHURUI_SUMAPURI = "5";
    public static final String DENPYOSHURUI_PAYMENT_TERMINAL_G7 = "6";
    public static final String DENPYOSHURUI_SIMPLE_PDT= "7";

    /**
     * 《請求書種類》
     * 1:999
     * 2:FP1
     * 3:iO!
     * 4:SDスマホ
     * 5:スマプリ
     * 6:MDM明細
     * 7:999収支策定
     * 9:店頭・ｼﾝﾌﾟﾙ
     */
    public static final String INVOICE_999 = "1";
    public static final String INVOICE_FP1 = "2";
    public static final String INVOICE_iO = "3";
    public static final String INVOICE_SD_SMARTPHONE = "4";
    public static final String INVOICE_SUMAPURI = "5";
    public static final String INVOICE_MDMMEISAI = "6";
    public static final String INVOICE_TENTO = "9";

    /**
     * t_job_log content定义
     */
    public static final String JOBLOG_CONTENT_UNZIP_START = "zipファイルの解凍が開始します";
    public static final String JOBLOG_CONTENT_UNZIP_END = "zipファイルの解凍が完了しました";

    public static final String JOBLOG_CONTENT_READTBIHINCSV_START = "t_備品.csvの読み取りが開始します";
    public static final String JOBLOG_CONTENT_READTBIHINCSV_END = "t_備品.csvの読み取りが完了しました";
    public static final String JOBLOG_CONTENT_DELETETBIHIN_START = "t_備品表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETETBIHIN_END = "t_備品表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTTBIHIN_START = "t_備品表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTTBIHIN_END = "t_備品表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATETBIHIN_START = "t_備品表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATETBIHIN_END = "t_備品表の更新が完了しました";

    public static final String JOBLOG_CONTENT_READTRISUCSV_START = "t_リース.csvの読み取りが開始します";
    public static final String JOBLOG_CONTENT_READTRISUCSV_END = "t_リース.csvの読み取りが完了しました";
    public static final String JOBLOG_CONTENT_DELETETRISU_START = "t_リース表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETETRISU_END = "t_リースの削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTTRISU_START = "t_リース表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTTRISU_END = "t_リース表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATETRISU_START = "t_リース表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATETRISU_END = "t_リース表の更新が完了しました";

    public static final String JOBLOG_CONTENT_SELECTDATABASE_START = "データベースのクエリが開始します";
    public static final String JOBLOG_CONTENT_SELECTDATABASE_END = "データベースのクエリが完了しました";

    public static final String JOBLOG_CONTENT_OUTEXCEL_START = "Excelの出力が開始します";
    public static final String JOBLOG_CONTENT_OUTEXCEL_END = "Excelの出力が完了しました";

    public static final String JOBLOG_CONTENT_ZIP_START = "ファイルの圧縮が開始します";
    public static final String JOBLOG_CONTENT_ZIP_END = "ファイルの圧縮が完了しました";

    public static final String JOBLOG_CONTENT_INSERTTFILE_START = "t_file表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTTFILE_END = "t_file表の追加が完了しました";

    public static final String JOBLOG_CONTENT_UPDATEJOB_START = "t_job表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATEJOB_END = "t_job表の更新が完了しました";

    public static final String JOBLOG_CONTENT_READTKAMOTSUCSV_START = "t_貨物.csvの読み取りが開始します";
    public static final String JOBLOG_CONTENT_READTKAMOTSUCSV_END = "t_貨物.csvの読み取りが完了しました";
    public static final String JOBLOG_CONTENT_DELETETKAMOTSU_START = "t_貨物表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETETKAMOTSU_END = "t_貨物表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTTKAMOTSU_START = "t_貨物表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTTKAMOTSU_END = "t_貨物表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATETKAMOTSU_START = "t_貨物表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATETKAMOTSU_END = "t_貨物表の更新が完了しました";

    public static final String JOBLOG_CONTENT_READFUNSHITSUCSV_START = "t_全損紛失.csvの読み取りが開始します";
    public static final String JOBLOG_CONTENT_READFUNSHITSUCSV_END = "t_全損紛失.csvの読み取りが完了しました";
    public static final String JOBLOG_CONTENT_DELETEFUNSHITSU_START = "t_全損紛失表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETEFUNSHITSU_END = "t_全損紛失表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTFUNSHITSU_START = "t_全損紛失表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTFUNSHITSU_END = "t_全損紛失表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATEFUNSHITSU_START = "t_全損紛失表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATEFUNSHITSU_END = "t_全損紛失表の更新が完了しました";

    public static final String JOBLOG_CONTENT_READTTENWABANGOCSV_START = "T_電話番号.csvの読み取りが開始します";
    public static final String JOBLOG_CONTENT_READTTENWABANGOCSV_END = "T_電話番号.csvの読み取りが完了しました";
    public static final String JOBLOG_CONTENT_DELETETTENWABANGO_START = "T_電話番号表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETETTENWABANGO_END = "T_電話番号表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTTTENWABANGO_START = "T_電話番号表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTTTENWABANGO_END = "T_電話番号表の追加が完了しました";

    public static final String JOBLOG_CONTENT_READTSUMAHOSHUKKABICSV_START = "T_スマホ出荷日.csvの読み取りが開始します";
    public static final String JOBLOG_CONTENT_READTSUMAHOSHUKKABICSV_END = "T_スマホ出荷日.csvの読み取りが完了しました";
    public static final String JOBLOG_CONTENT_DELETETSUMAHOSHUKKABI_START = "T_スマホ出荷日表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETETSUMAHOSHUKKABI_END = "T_スマホ出荷日表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTTSUMAHOSHUKKABI_START = "T_スマホ出荷日表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTTSUMAHOSHUKKABI_END = "T_スマホ出荷日表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATETSUMAHOSHUKKABI_START = "T_スマホ出荷日表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATETSUMAHOSHUKKABI_END = "T_スマホ出荷日表の更新が完了しました";

    public static final String JOBLOG_CONTENT_READTJOGAIRISUTOCSV_START = "T_除外リスト.csvの読み取りが開始します";
    public static final String JOBLOG_CONTENT_READTJOGAIRISUTOCSV_END = "T_除外リスト.csvの読み取りが完了しました";
    public static final String JOBLOG_CONTENT_DELETETJOGAIRISUTO_START = "T_除外リスト表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETETJOGAIRISUTO_END = "T_除外リスト表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTTJOGAIRISUTO_START = "T_除外リスト表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTTJOGAIRISUTO_END = "T_除外リスト表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATETJOGAIRISUTO_START = "T_除外リスト表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATETJOGAIRISUTO_END = "T_除外リスト表の更新が完了しました";

    public static final String JOBLOG_CONTENT_READTKIKIRISUTOCSV_START = "T_機器リスト.csvの読み取りが開始します";
    public static final String JOBLOG_CONTENT_READTKIKIRISUTOCSV_END = "T_機器リスト.csvの読み取りが完了しました";
    public static final String JOBLOG_CONTENT_DELETETKIKIRISUTO_START = "T_機器リスト表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETETKIKIRISUTO_END = "T_機器リスト表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTTKIKIRISUTO_START = "T_機器リスト表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTTKIKIRISUTO_END = "T_機器リスト表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATETKIKIRISUTO_START = "T_機器リスト表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATETKIKIRISUTO_END = "T_機器リスト表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETEJOB_START = "t_job表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETEJOB_END = "t_job表の削除が完了しました";
    public static final String JOBLOG_CONTENT_DELETEJOB_FAIL = "t_job表の削除に失敗しました";


    public static final String JOBLOG_CONTENT_READSOFUTOBANKUCSV_START = "T_ソフトバンク.csvの読み取りが開始します";
    public static final String JOBLOG_CONTENT_READSOFUTOBANKUCSV_END = "T_ソフトバンク.csvの読み取りが完了しました";
    public static final String JOBLOG_CONTENT_DELETESOFUTOBANKU_START = "T_ソフトバンク表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETESOFUTOBANKU_END = "T_ソフトバンク表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTSOFUTOBANKU_START = "T_ソフトバンク表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTSOFUTOBANKU_END = "T_ソフトバンク表の追加が完了しました";

    public static final String JOBLOG_CONTENT_READTKATSUSHIMAKESSAIKADOTANMATSUCSV_START = "T_勝島決済稼働端末一覧.csvの読み取りが開始します";
    public static final String JOBLOG_CONTENT_READKATSUSHIMAKESSAIKADOTANMATSUCSV_END = "T_勝島決済稼働端末一覧.csvの読み取りが完了しました";
    public static final String JOBLOG_CONTENT_DELETEKATSUSHIMAKESSAIKADOTANMATSU_START = "T_勝島決済稼働端末一覧表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETETSUSHIMAKESSAIKADOTANMATSU_END = "T_勝島決済稼働端末一覧表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTTSUSHIMAKESSAIKADOTANMATSU_START = "T_勝島決済稼働端末一覧表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTTSUSHIMAKESSAIKADOTANMATSU_END = "T_勝島決済稼働端末一覧表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATETSUSHIMAKESSAIKADOTANMATSU_START = "T_勝島決済稼働端末一覧表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATETSUSHIMAKESSAIKADOTANMATSU_END = "T_勝島決済稼働端末一覧表の更新が完了しました";

    public static final String JOBLOG_CONTENT_READTKASHITSUKECSV_START = "T_貸付.csvの読み取りが開始します";
    public static final String JOBLOG_CONTENT_READTKASHITSUKECSV_END = "T_貸付.csvの読み取りが完了しました";
    public static final String JOBLOG_CONTENT_DELETETKASHITSUKE_START = "T_貸付表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETETKASHITSUKE_END = "T_貸付表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTTKASHITSUKE_START = "T_貸付表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTTKASHITSUKE_END = "T_貸付表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATETKASHITSUKE_START = "T_貸付表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATETKASHITSUKE_END = "T_貸付表の更新が完了しました";

    public static final String JOBLOG_CONTENT_READTTSIICARDKESSAICSV_START = "T_SIIカード決済.csvの読み取りが開始します";
    public static final String JOBLOG_CONTENT_READTSIICARDKESSAICSV_END = "T_SIIカード決済.csvの読み取りが完了しました";
    public static final String JOBLOG_CONTENT_DELETEKATSIICARDKESSAI_START = "T_SIIカード決済表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETETSIICARDKESSAI_END = "T_SIIカード決済表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTTSIICARDKESSAI_START = "T_SIIカード決済表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTTSIICARDKESSAI_END = "T_SIIカード決済表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATETSIICARDKESSAI_START = "T_SIIカード決済表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATETSIICARDKESSAI_END = "T_SIIカード決済表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETEWKESSAITANMATSUSAISHUINSATSUBI_START = "W_決済端末_最終印刷日表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETEWKESSAITANMATSUSAISHUINSATSUBI_END = "W_決済端末_最終印刷日表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTWKESSAITANMATSUSAISHUINSATSUBI_START = "W_決済端末_最終印刷日表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTWKESSAITANMATSUSAISHUINSATSUBI_END = "W_決済端末_最終印刷日表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATEWKESSAITANMATSUSAISHUINSATSUBI_START = "W_決済端末_最終印刷日表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATEWKESSAITANMATSUSAISHUINSATSUBI_END = "W_決済端末_最終印刷日表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETEWKESSAITANMATSUSAISHUPAIRINGHIDZUKE_START = "W_決済端末_最終ペアリング日付表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETEWKESSAITANMATSUSAISHUPAIRINGHIDZUKE_END = "W_決済端末_最終ペアリング日付表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTWKESSAITANMATSUSAISHUPAIRINGHIDZUKE_START = "W_決済端末_最終ペアリング日付表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTWKESSAITANMATSUSAISHUPAIRINGHIDZUKE_END = "W_決済端末_最終ペアリング日付表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATEWKESSAITANMATSUSAISHUPAIRINGHIDZUKE_START = "W_決済端末_最終ペアリング日付表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATEWKESSAITANMATSUSAISHUPAIRINGHIDZUKE_END = "W_決済端末_最終ペアリング日付表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETEWKESSAITANMATSUJOHO_START = "W_決済端末情報表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETEWKESSAITANMATSUJOHO_END = "W_決済端末情報表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTWKESSAITANMATSUJOHO_START = "W_決済端末情報表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTWKESSAITANMATSUJOHO_END = "W_決済端末情報表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATEWKESSAITANMATSUJOHO_START = "W_決済端末情報表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATEWKESSAITANMATSUJOHO_END = "W_決済端末情報表の更新が完了しました";


    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_BIHIN_START = "t_請求書_999_備品表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_BIHIN_END = "t_請求書_999_備品表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_BIHIN_START = "t_請求書_999_備品表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_BIHIN_END = "t_請求書_999_備品表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_BIHIN_START = "t_請求書_999_備品表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_BIHIN_END = "t_請求書_999_備品表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_BIHIN_KOTEI_START = "t_請求書_999_備品_固定表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_BIHIN_KOTEI_END = "t_請求書_999_備品_固定表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_BIHIN_KOTEI_START = "t_請求書_999_備品_固定表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_BIHIN_KOTEI_END = "t_請求書_999_備品_固定表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_BIHIN_KOTEI_START = "t_請求書_999_備品_固定表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_BIHIN_KOTEI_END = "t_請求書_999_備品_固定表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_DONYU_JOKYO_START = "t_請求書_９９９導入状況表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_DONYU_JOKYO_END = "t_請求書_９９９導入状況表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_DONYU_JOKYO_START = "t_請求書_９９９導入状況表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_DONYU_JOKYO_END = "t_請求書_９９９導入状況表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_DONYU_JOKYO_START = "t_請求書_９９９導入状況表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_DONYU_JOKYO_END = "t_請求書_９９９導入状況表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_HENKYAKU_SUMI_PDT_START = "t_請求書_999_返却済PDT表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_HENKYAKU_SUMI_PDT_END = "t_請求書_999_返却済PDT表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_HENKYAKU_SUMI_PDT_START = "t_請求書_999_返却済PDT表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_HENKYAKU_SUMI_PDT_END = "t_請求書_999_返却済PDT表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_HENKYAKU_SUMI_PDT_START = "t_請求書_999_返却済PDT表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_HENKYAKU_SUMI_PDT_END = "t_請求書_999_返却済PDT表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_KAISEN_KAIYAKU_IYAKUKIN_START = "t_請求書_999_回線解約違約金表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_KAISEN_KAIYAKU_IYAKUKIN_END = "t_請求書_999_回線解約違約金表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_KAISEN_KAIYAKU_IYAKUKIN_START = "t_請求書_999_回線解約違約金表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_KAISEN_KAIYAKU_IYAKUKIN_END = "t_請求書_999_回線解約違約金表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_KAISEN_KAIYAKU_IYAKUKIN_START = "t_請求書_999_回線解約違約金表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_KAISEN_KAIYAKU_IYAKUKIN_END = "t_請求書_999_回線解約違約金表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_KIKI_PVT_START = "t_請求書_999_機器PVT(VBA)表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_KIKI_PVT_END = "t_請求書_999_機器PVT(VBA)表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_KIKI_PVT_START = "t_請求書_999_機器PVT(VBA)表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_KIKI_PVT_END = "t_請求書_999_機器PVT(VBA)表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_KIKI_PVT_START = "t_請求書_999_機器PVT(VBA)表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_KIKI_PVT_END = "t_請求書_999_機器PVT(VBA)表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_MASTER_START = "t_請求書_999_マスタ表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_MASTER_END = "t_請求書_999_マスタ表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_MASTER_START = "t_請求書_999_マスタ表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_MASTER_END = "t_請求書_999_マスタ表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_MASTER_START = "t_請求書_999_マスタ表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_MASTER_END = "t_請求書_999_マスタ表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_SHITEN_MASTER_START = "t_請求書_999_支店マスタ表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_SHITEN_MASTER_END = "t_請求書_999_支店マスタ表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_SHITEN_MASTER_START = "t_請求書_999_支店マスタ表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_SHITEN_MASTER_END = "t_請求書_999_支店マスタ表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_SHITEN_MASTER_START = "t_請求書_999_支店マスタ表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_SHITEN_MASTER_END = "t_請求書_999_支店マスタの表更新が完了しました";


    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_ZENSON_FUNSHITSU_START = " t_請求書_999_紛失全損表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_ZENSON_FUNSHITSU_END = " t_請求書_999_紛失全損表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_ZENSON_FUNSHITSU_START = " t_請求書_999_紛失全損表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_ZENSON_FUNSHITSU_END = " t_請求書_999_紛失全損表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_ZENSON_FUNSHITSU_START = " t_請求書_999_紛失全損表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_999_ZENSON_FUNSHITSU_END = " t_請求書_999_紛失全損表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_FP1_BIHIN_START = "t_請求書_fp1_備品表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_FP1_BIHIN_END = "t_請求書_fp1_備品表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_FP1_BIHIN_START = "t_請求書_fp1_備品表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_FP1_BIHIN_END = "t_請求書_fp1_備品表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_FP1_BIHIN_START = "t_請求書_fp1_備品表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_FP1_BIHIN_END = "t_請求書_fp1_備品表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_FP1_BIHIN_KOTEI_START = "t_請求書_fp1_備品_固定表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_FP1_BIHIN_KOTEI_END = "t_請求書_fp1_備品_固定表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_FP1_BIHIN_KOTEI_START = "t_請求書_fp1_備品_固定表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_FP1_BIHIN_KOTEI_END = "t_請求書_fp1_備品_固定表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_FP1_BIHIN_KOTEI_START = "t_請求書_fp1_備品_固定表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_FP1_BIHIN_KOTEI_END = "t_請求書_fp1_備品_固定表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_FP1_DONYU_JOKYO_START = "t_請求書_FP1導入状況表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_FP1_DONYU_JOKYO_END = "t_請求書_FP1導入状況表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_FP1_DONYU_JOKYO_START = "t_請求書_FP1導入状況表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_FP1_DONYU_JOKYO_END = "t_請求書_FP1導入状況表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_FP1_DONYU_JOKYO_START = "t_請求書_FP1導入状況表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_FP1_DONYU_JOKYO_END = "t_請求書_FP1導入状況表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_FP1_MOTO_DATA_START = "t_請求書_FP1_元データ表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_FP1_MOTO_DATA_END = "t_請求書_FP1_元データ表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_FP1_MOTO_DATA_START = "t_請求書_FP1_元データ表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_FP1_MOTO_DATA_END = "t_請求書_FP1_元データ表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_FP1_MOTO_DATA_START = "t_請求書_FP1_元データ表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_FP1_MOTO_DATA_END = "t_請求書_FP1_元データ表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_FP1_SEISAN_CODE_START = "t_請求書_FP1_精算コード表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_FP1_SEISAN_CODE_END = "t_請求書_FP1_精算コード表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_FP1_SEISAN_CODE_START = "t_請求書_FP1_精算コード表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_FP1_SEISAN_CODE_END = "t_請求書_FP1_精算コード表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_FP1_SEISAN_CODE_START = "t_請求書_FP1_精算コード表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_FP1_SEISAN_CODE_END = "t_請求書_FP1_精算コード表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_FP1_ZENSON_FUNSHITSU_ICHIRAN_START = "t_請求書_FP1_全損・紛失一覧表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_FP1_ZENSON_FUNSHITSU_ICHIRAN_END = "t_請求書_FP1_全損・紛失一覧表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_FP1_ZENSON_FUNSHITSU_ICHIRAN_START = "t_請求書_FP1_全損・紛失一覧表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_FP1_ZENSON_FUNSHITSU_ICHIRAN_END = "t_請求書_FP1_全損・紛失一覧表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_FP1_ZENSON_FUNSHITSU_ICHIRAN_START = "t_請求書_FP1_全損・紛失一覧表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_FP1_ZENSON_FUNSHITSU_ICHIRAN_END = "t_請求書_FP1_全損・紛失一覧表の更新が完了しました";

    // スマプリ
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SUMAPURI_DONYU_JOKYO_START = "t_請求書_スマプリ導入状況表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SUMAPURI_DONYU_JOKYO_END = "t_請求書_スマプリ導入状況表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SUMAPURI_DONYU_JOKYO_START = "t_請求書_スマプリ導入状況表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SUMAPURI_DONYU_JOKYO_END = "t_請求書_スマプリ導入状況表の追加が完了しました";
//    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_SUMAPURI_DONYU_JOKYO_START = "t_請求書_スマプリ導入状況表の更新が開始します";
//    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_SUMAPURI_DONYU_JOKYO_END = "t_請求書_スマプリ導入状況表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SUMAPURI_BIHIN_START = "t_請求書_スマプリ_備品表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SUMAPURI_BIHIN_END = "t_請求書_スマプリ_備品表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SUMAPURI_BIHIN_START = "t_請求書_スマプリ_備品表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SUMAPURI_BIHIN_END = "t_請求書_スマプリ_備品表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SUMAPURI_MASTER_START = "t_請求書_スマプリ_マスタ表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SUMAPURI_MASTER_END = "t_請求書_スマプリ_マスタの削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SUMAPURI_MASTER_START = "t_請求書_スマプリ_マスタ表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SUMAPURI_MASTER_END = "t_請求書_スマプリ_マスタの追加が完了しました";

    //io
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_IO_START = "t_請求書_iO!表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_IO_END = " t_請求書_iO!表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_IO_START = " t_請求書_iO!表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_IO_END = " t_請求書_iO!表の追加が完了しました";

    // 初期分
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_SHOKIBUN_START = "t_請求書_初期分表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_SHOKIBUN_END = " t_請求書_初期分表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_SHOKIBUN_START = " t_請求書_初期分表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_SHOKIBUN_END = " t_請求書_初期分表の追加が完了しました";

    //MDM
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_CUSTOM_REPORT_START = "t_請求書_【MDM】ｶｽﾀﾑﾚﾎﾟｰﾄ表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_CUSTOM_REPORT_END = " t_請求書_【MDM】ｶｽﾀﾑﾚﾎﾟｰﾄ表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_CUSTOM_REPORT_START = " t_請求書_【MDM】ｶｽﾀﾑﾚﾎﾟｰﾄ表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_CUSTOM_REPORT_END = " t_請求書_【MDM】ｶｽﾀﾑﾚﾎﾟｰﾄ表の追加が完了しました";

    //MDMT_請求書_解約分再出荷
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_KAIYAKU_BUN_SAI_SHUKKA_START = "t_請求書_解約分再出荷表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_KAIYAKU_BUN_SAI_SHUKKA_END = " t_請求書_解約分再出荷表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_KAIYAKU_BUN_SAI_SHUKKA_START = " t_請求書_解約分再出荷表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_KAIYAKU_BUN_SAI_SHUKKA_END = " t_請求書_解約分再出荷表の追加が完了しました";

    //MDM
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_EIGYOTEN_START = "t_請求書_【MDM】営業所別集計表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_EIGYOTEN_END = " t_請求書_【MDM】営業所別集計表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_EIGYOTEN_START = " t_請求書_【MDM】営業所別集計表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_EIGYOTEN_END = " t_請求書_【MDM】営業所別集計表の追加が完了しました";

    //MDM
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_SHITEN_START = "t_請求書_【MDM】支店別集計表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_SHITEN_END = " t_請求書_【MDM】支店別集計表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_SHITEN_START = " t_請求書_【MDM】支店別集計表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_SHITEN_END = " t_請求書_【MDM】支店別集計表の追加が完了しました";


    //MDMT_
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_TENTO_SIMPLE_CUSTOM_REPORT_START = "t_請求書_【店頭・ｼﾝﾌﾟﾙ】ｶｽﾀﾑﾚﾎﾟｰﾄ表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_TENTO_SIMPLE_CUSTOM_REPORT_END = " t_請求書_【店頭・ｼﾝﾌﾟﾙ】ｶｽﾀﾑﾚﾎﾟｰﾄ表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_TENTO_SIMPLE_CUSTOM_REPORT_START = " t_請求書_【店頭・ｼﾝﾌﾟﾙ】ｶｽﾀﾑﾚﾎﾟｰﾄ表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_TENTO_SIMPLE_CUSTOM_REPORT_END = " t_請求書_【店頭・ｼﾝﾌﾟﾙ】ｶｽﾀﾑﾚﾎﾟｰﾄ表の追加が完了しました";

    // sd
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_SD_START = "t_請求書_Sdスマホ表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_SD_END = " t_請求書_Sdスマホ表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_SD_START = " t_請求書_Sdスマホ表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_SD_END = " t_請求書_Sdスマホ表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_IOCSV_START = "t_請求書_Sdスマホio_for_sd表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_IOCSV_END = " t_請求書_Sdスマホio_for_sd表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_IOCSV_START = " t_請求書_Sdスマホio_for_sd表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_IOCSV_END = " t_請求書_Sdスマホio_for_sd表の追加が完了しました";


    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_KAIYAKU_RUISEKI_IO_START = "t_請求書_解約累積iO表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_KAIYAKU_RUISEKI_IO_END = " t_請求書_解約累積iO表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_KAIYAKU_RUISEKI_IO_START = " t_請求書_解約累積iO表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_KAIYAKU_RUISEKI_IO_END = " t_請求書_解約累積iO表の追加が完了しました";



    // simple
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_SIMPLE_START = "t_請求書_SdスマホSimple表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_SIMPLE_END = " t_請求書_SdスマホSimple表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_SIMPLE_START = " t_請求書_SdスマホSimple表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_SIMPLE_END = " t_請求書_SdスマホSimple表の追加が完了しました";

    // tento
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_TENTO_START = "t_請求書_Sdスマホtento表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_TENTO_END = " t_請求書_Sdスマホtento表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_TENTO_START = " t_請求書_Sdスマホtento表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_TENTO_END = " t_請求書_Sdスマホtento表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_SHURI_START = "t_請求書_Sdスマホshuri表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_SHURI_END = " t_請求書_Sdスマホshuri表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_SHURI_START = " t_請求書_Sdスマホshuri表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_SHURI_END = " t_請求書_Sdスマホshuri表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_SHOKI28190_START = "t_請求書_初期28190表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_SHOKI28190_END = " t_請求書_初期28190表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_SHOKI28190_START = " t_請求書_初期28190表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_SHOKI28190_END = " t_請求書_初期28190表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_SHOKI28000_START = "t_請求書_初期28000表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_SHOKI28000_END = " t_請求書_初期28000表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_SHOKI28000_START = " t_請求書_初期28000表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_SHOKI28000_END = " t_請求書_初期28000表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_T_KIKI_RUISEKI_SHINKI_START = "t_請求書_機器一覧(総計)の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_T_KIKI_RUISEKI_SHINKI_END = " t_請求書_機器一覧(総計)の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_T_KIKI_RUISEKI_SHINKI_START = " t_請求書_機器一覧(総計)の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_T_KIKI_RUISEKI_SHINKI_END = " t_請求書_機器一覧(総計)の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_T_KIKI_KONGETSU_SHINKI_START = "t_請求書_機器一覧(今月新規)の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_T_KIKI_KONGETSU_SHINKI_END = " t_請求書_機器一覧(今月新規)の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_T_KIKI_KONGETSU_SHINKI_START = " t_請求書_機器一覧(今月新規)の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_T_KIKI_KONGETSU_SHINKI_END = " t_請求書_機器一覧(今月新規)の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_T_KIKI_SHOKI_28190_START = "t_請求書_機器一覧(初期28190)の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_T_KIKI_SHOKI_28190_END = " t_請求書_機器一覧(初期28190)の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_T_KIKI_SHOKI_28190_START = " t_請求書_機器一覧(初期28190)の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_T_KIKI_SHOKI_28190_END = " t_請求書_機器一覧(初期28190)の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_T_KIKI_SHOKI_28000_START = "t_請求書_機器一覧(初期28000)の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_T_KIKI_SHOKI_28000_END = " t_請求書_機器一覧(初期28000)の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_T_KIKI_SHOKI_28000_START = " t_請求書_機器一覧(初期28000)の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_T_KIKI_SHOKI_28000_END = " t_請求書_機器一覧(初期28000)の追加が完了しました";

    // io
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_PVT_START = "t_請求書_PVT表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_PVT_END = " t_請求書_PVT表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_PVT_START = " t_請求書_PVT表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_PVT_END = " t_請求書_PVT表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_IO_PVT_START = " t_請求書_PVT表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_SEIKYUSHO_IO_PVT_END = " t_請求書_PVT表の更新が完了しました";


    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_DAISU_START = "t_請求書_台数貼付用の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_DAISU_END = " t_請求書_台数貼付用の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_DAISU_START = " t_請求書_台数貼付用の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_DAISU_END = " t_請求書_台数貼付用の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_EIGYOJOSEISANKODOMASUTA_START = "t_請求書_営業所-精算コードマスタの削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_EIGYOJOSEISANKODOMASUTA_END = " t_請求書_営業所-精算コードマスタの削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_EIGYOJOSEISANKODOMASUTA_START = " t_請求書_営業所-精算コードマスタの追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_EIGYOJOSEISANKODOMASUTA_END = " t_請求書_営業所-精算コードマスタの追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_HONSHAMATOME_START = "t_請求書_本社まとめの削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_HONSHAMATOME_END = " t_請求書_本社まとめの削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_HONSHAMATOME_START = " t_請求書_本社まとめの追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_HONSHAMATOME_END = " t_請求書_本社まとめの追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_KIKIICHIRANKONGETSUSHINKI_START = "t_請求書_機器一覧（今月新規）の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_KIKIICHIRANKONGETSUSHINKI_END = " t_請求書_機器一覧（今月新規）の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_KIKIICHIRANKONGETSUSHINKI_START = " t_請求書_機器一覧（今月新規）の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_KIKIICHIRANKONGETSUSHINKI_END = " t_請求書_機器一覧（今月新規）の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_KIKIICHIRANSHINKIRUISEKI_START = "t_請求書_機器一覧（新規累積）の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_KIKIICHIRANSHINKIRUISEKI_END = " t_請求書_機器一覧（新規累積）の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_KIKIICHIRANSHINKIRUISEKI_START = " t_請求書_機器一覧（新規累積）の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_KIKIICHIRANSHINKIRUISEKI_END = " t_請求書_機器一覧（新規累積）の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_KIKIICHIRANSHOKI15000_START = "t_請求書_機器一覧（初期15000）の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_KIKIICHIRANSHOKI15000_END = " t_請求書_機器一覧（初期15000）の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_KIKIICHIRANSHOKI15000_START = " t_請求書_機器一覧（初期15000）の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_KIKIICHIRANSHOKI15000_END = " t_請求書_機器一覧（初期15000）の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_KIKIICHIRANZENDAI_START = "t_請求書_機器一覧（全台）の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_IO_KIKIICHIRANZENDAI_END = " t_請求書_機器一覧（全台）の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_KIKIICHIRANZENDAI_START = " t_請求書_機器一覧（全台）の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_IO_KIKIICHIRANZENDAI_END = " t_請求書_機器一覧（全台）の追加が完了しました";


    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_SHUSHI_SAKUTEI_START = "T_999収支策定表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_999_SHUSHI_SAKUTEI_END = "T_999収支策定表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_SHUSHI_SAKUTEI_START = "T_999収支策定表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_999_SHUSHI_SAKUTEI_END = "T_999収支策定表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_LAST_MONTH_SHITEN_START = "T_MDM支店別前月度表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_LAST_MONTH_SHITEN_END = "T_MDM支店別前月度表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_LAST_MONTH_SHITEN_START = "T_MDM支店別前月度表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_LAST_MONTH_SHITEN_END = "T_MDM支店別前月度表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_LAST_MONTH_EIGYOTEN_START = "T_MDM営業所別前月度表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MDM_LAST_MONTH_EIGYOTEN_END = "T_MDM営業所別前月度表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_LAST_MONTH_EIGYOTEN_START = "T_MDM営業所別前月度表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MDM_LAST_MONTH_EIGYOTEN_END = "T_MDM営業所別前月度表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_MOTODATA28000_START = "t_請求書_SD元データ28000表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_MOTODATA28000_END = " t_請求書_SD元データ28000表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_MOTODATA28000_START = " t_請求書_SD元データ28000表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_MOTODATA28000_END = " t_請求書_SD元データ28000表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_MOTODATA28190_START = "t_請求書_SD元データ28190表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_MOTODATA28190_END = " t_請求書_SD元データ28190表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_MOTODATA28190_START = " t_請求書_SD元データ28190表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_MOTODATA28190_END = " t_請求書_SD元データ28190表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_SHINKI_DONYU_LIST_START = "t_請求書_SD元データ新規導入一覧表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_SHINKI_DONYU_LIST_END = " t_請求書_SD元データ新規導入一覧表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_SHINKI_DONYU_LIST_START = " t_請求書_SD元データ新規導入一覧表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_SHINKI_DONYU_LIST_END = " t_請求書_SD元データ新規導入一覧表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_KAISHU_LIST_START = "t_請求書_SD元データ回収一覧表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SDSUMAHO_KAISHU_LIST_END = " t_請求書_SD元データ回収一覧表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_KAISHU_LIST_START = " t_請求書_SD元データ回収一覧表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SDSUMAHO_KAISHU_LIST_END = " t_請求書_SD元データ回収一覧表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_TENTO_START = "t_請求書_店頭表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_TENTO_END = " t_請求書_店頭表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_TENTO_START = " t_請求書_店頭表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_TENTO_END = " t_請求書_店頭表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SHINPURU_START = "t_請求書_シンプル表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SHINPURU_END = " t_請求書_シンプル表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SHINPURU_START = " t_請求書_シンプル表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SHINPURU_END = " t_請求書_シンプル表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_TENTO_LIST_START = "t_請求書_店頭受取スマホ_新規導入一覧表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_TENTO_LIST_END = " t_請求書_店頭受取スマホ_新規導入一覧表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_TENTO_LIST_START = " t_請求書_店頭受取スマホ_新規導入一覧表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_TENTO_LIST_END = " t_請求書_店頭受取スマホ_新規導入一覧表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SHINPURU_LIST_START = "t_請求書_シンプルスマホ_新規導入一覧表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SHINPURU_LIST_END = " t_請求書_シンプルスマホ_新規導入一覧表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SHINPURU_LIST_START = " t_請求書_シンプルスマホ_新規導入一覧表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SHINPURU_LIST_END = " t_請求書_シンプルスマホ_新規導入一覧表の追加が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SUMAPURI_DAISU_START = "T_スマプリ台数一覧表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_SUMAPURI_DAISU_END = "T_スマプリ台数一覧表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SUMAPURI_DAISU_START = "T_スマプリ台数一覧表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_SUMAPURI_DAISU_END = "T_スマプリ台数一覧表の追加が完了しました";
    /**
     * 各支社名字定义
     */
    public static final String KYUSHU_SHITEN = "九州支店";
    public static final String MINAMIKYUSHU_SHITEN = "南九州支店";
    public static final String SHIKOKU_SHITEN = "四国支店";
    public static final String CHUGOKU_SHITEN = "中国支店";
    public static final String KANSAI_SHITEN = "関西支店";
    public static final String KYOTO_SHITEN = "京都支店";
    public static final String HOKURIKU_SHITEN = "北陸支店";
    public static final String CHUKYO_SHITEN = "中京支店";
    public static final String SHINETSU_SHITEN = "信越支店";
    public static final String TOKAI_SHITEN = "東海支店";
    public static final String KANTO_SHITEN = "関東支店";
    public static final String KANAGAWA_SHITEN = "神奈川支店";
    public static final String NISHIKANTO_SHITEN = "西関東支店";
    public static final String KITAKANTO_SHITEN = "北関東支店";
    public static final String HIGASHIKANTO_SHITEN = "東関東支店";
    public static final String MINAMI_TOHOKU_SHITEN = "南東北支店";
    public static final String KITA_TOHOKU_SHITEN = "北東北支店";
    public static final String HOKKAIDO_SHITEN = "北海道支店";
    public static final String HONSHA = "本社";
    public static final String SGS = "SGS";
    public static final String SGMV = "SGMV";
    public static final String IO = "3";
    public static final String IOSGE = "3.1";

    /**
     * 各支社文件名定义
     */
    public static final String KYUSHU_SHITEN_FILENAME = "【0289九州】";
    public static final String MINAMIKYUSHU_SHITEN_FILENAME = "【0389南九州】";
    public static final String SHIKOKU_SHITEN_FILENAME = "【1999四国】";
    public static final String CHUGOKU_SHITEN_FILENAME = "【2099中国】";
    public static final String KANSAI_SHITEN_FILENAME = "【3289関西】";
    public static final String KYOTO_SHITEN_FILENAME = "【4099京都】";
    public static final String HOKURIKU_SHITEN_FILENAME = "【5099北陸】";
    public static final String CHUKYO_SHITEN_FILENAME = "【6189中京】";
    public static final String SHINETSU_SHITEN_FILENAME = "【5799信越】";
    public static final String TOKAI_SHITEN_FILENAME = "【6699東海】";
    public static final String KANTO_SHITEN_FILENAME = "【7297関東】";
    public static final String KANAGAWA_SHITEN_FILENAME = "【7392神奈川】";
    public static final String NISHIKANTO_SHITEN_FILENAME = "【7397西関東】";
    public static final String KITAKANTO_SHITEN_FILENAME = "【7697北関東】";
    public static final String HIGASHIKANTO_SHITEN_FILENAME = "【7797東関東】";
    public static final String MINAMI_TOHOKU_SHITEN_FILENAME = "【8099南東北】";
    public static final String KITA_TOHOKU_SHITEN_FILENAME = "【8689北東北】";
    public static final String HOKKAIDO_SHITEN_FILENAME = "【9189北海道】";

    /**
     * 上传的csv文件名
     */
    public static final String T_BIHIN_CSVNAME = "T_備品.csv";
    public static final String T_RISU_CSVNAME = "T_リース.csv";
    public static final String T_KAMOTSU_CSVNAME = "T_貨物.csv";
    public static final String T_ZENSON_FUNSHITSU_CSVNAME = "T_全損紛失.csv";
    public static final String T_JOGAI_RISUTO_CSVNAME = "T_除外リスト.csv";
    public static final String T_KIKI_RISUTO_CSVNAME = "T_機器リスト.csv";
    public static final String T_TENWABANGO_CSVNAME = "T_電話番号.csv";
    public static final String T_SUMAHO_SHUKKABI_CSVNAME = "T_スマホ出荷日.csv";
    public static final String T_SOFUTOBANKU_CSVNAME = "T_ソフトバンク.csv";
    public static final String T_KATSUSHIMA_KESSAI_KADO_TANMATSU_CSVNAME = "T_勝島決済稼働端末一覧.csv";
    public static final String T_KASHITSUKE_CSVNAME = "T_貸付.csv";
    public static final String T_SII_CARD_KESSAI_CSVNAME = "T_SIIカード決済.csv";
    public static final String T_FP1_CSVNAME = "FP1.csv";
    public static final String T_SD_SUMAHO_CSVNAME = "SDスマホ.csv";
    public static final String T_ZENKEN_CSVNAME = "全件.csv";

    // TODO
    public static final String T_SEIKYUSHO_999_BIHIN_CSVNAME = "T_請求書_999_備品.csv";
    public static final String T_SEIKYUSHO_999_BIHIN_KOTEI_CSVNAME = "T_請求書_999_備品_固定.csv";
    public static final String T_SEIKYUSHO_999_DONYU_JOKYO_CSVNAME = "T_請求書_999_導入状況.csv";
    public static final String T_SEIKYUSHO_999_HENKYAKU_SUMI_PDT_CSVNAME = "T_請求書_999_返却済PDT.csv";
    public static final String T_SEIKYUSHO_999_KAISEN_KAIYAKU_IYAKUKIN_CSVNAME = "T_請求書_999_回線解約違約金.csv";
    public static final String T_SEIKYUSHO_999_KIKI_PVT_CSVNAME = "T_請求書_999_機器PVT.csv";
    public static final String T_SEIKYUSHO_999_MASTER_CSVNAME = "T_請求書_999_マスタ.csv";
    public static final String T_SEIKYUSHO_999_SHITEN_MASTER_CSVNAME = "T_請求書_999_支店マスタ.csv";
    public static final String T_SEIKYUSHO_999_ZENSON_FUNSHITSU_CSVNAME = "T_請求書_999_紛失全損.csv";


    // TODO
    public static final String T_SEIKYUSHO_FP1_BIHIN_CSVNAME = "T_請求書_fp1_備品.csv";
    public static final String T_SEIKYUSHO_FP1_BIHIN_KOTEI_CSVNAME = "T_請求書_fp1_備品_固定.csv";
    public static final String T_SEIKYUSHO_FP1_DONYU_JOKYO_CSVNAME = "T_請求書_fp1_導入状況.csv";
    public static final String T_SEIKYUSHO_FP1_MOTO_DATA_CSVNAME = "T_請求書_fp1_元データ.csv";
    public static final String T_SEIKYUSHO_FP1_SEISAN_CODE_CSVNAME = "T_請求書_fp1_精算コード.csv";
    public static final String T_SEIKYUSHO_FP1_ZENSON_FUNSHITSU_ICHIRAN_CSVNAME = "T_請求書_fp1_全損紛失一覧.csv";

    // TODO
    public static final String T_SEIKYUSHO_SUMAPURI_DONYUJOKYO_CSVNAME = "T_請求書_sumapuri_導入状況一覧.csv";
    public static final String T_SEIKYUSHO_SUMAPURI_ZENSONFUNSHITSU_CSVNAME = "T_請求書_sumapuri_紛失全損一覧.csv";
    public static final String T_SEIKYUSHO_SUMAPURI_BIHIN_CSVNAME = "T_請求書_sumapuri_備品.csv";
    public static final String T_SEIKYUSHO_SUMAPURI_MOTO_DATA_CSVNAME = "T_請求書_sumapuri_元データ.csv";
    public static final String T_SEIKYUSHO_SUMAPURI_MASTER_CSVNAME = "T_請求書_sumapuri_マスタ.csv";
    public static final String T_SEIKYUSHO_SUMAPURI_SHITEN_MASTER_CSVNAME = "T_請求書_sumapuri_支店マスタ.csv";



    public static final String T_SEIKYUSHO_SDSUMAHO_SDCSV ="T_請求書_sd.csv";
    public static final String T_SEIKYUSHO_SDSUMAHO_SIMPLE ="T_請求書_シンプル.csv";
    public static final String T_SEIKYUSHO_SDSUMAHO_TENTO ="T_請求書_店頭.csv";
    public static final String T_SEIKYUSHO_SDSUMAHO_SHURI ="T_請求書_SDスマホ修理明細.csv";
    public static final String T_SEIKYUSHO_SDSUMAHO_SIMPLE_SHURI ="T_請求書_シンプル修理明細.csv";
    public static final String T_SEIKYUSHO_SDSUMAHO_SHOKI28190 ="T_請求書_初期28190.csv";
    public static final String T_SEIKYUSHO_SDSUMAHO_SHOKI28000 ="T_請求書_初期28000.csv";
    public static final String T_SEIKYUSHO_SDSUMAHO_KIKI_ICHIRAN_SOKEI ="T_請求書_sdsumaho_機器一覧(総計).csv";
    public static final String T_SEIKYUSHO_SDSUMAHO_KIKI_ICHIRAN_RUISEKI_SHINKI ="T_請求書_sdsumaho_機器一覧(累積新規).csv";
    public static final String T_SEIKYUSHO_SDSUMAHO_KIKI_ICHIRAN_KONGETSU_SHINKI ="T_請求書_sdsumaho_機器一覧(今月新規).csv";


    public static final String T_SEIKYUSHO_SDSUMAHO_MOTODATA28000 ="T_請求書_SD元データ28000件.csv";
    public static final String T_SEIKYUSHO_SDSUMAHO_MOTODATA28190 ="T_請求書_SD元データ28190件.csv";
    public static final String T_SEIKYUSHO_SDSUMAHO_MOTODATASHINKI_DONYU_LIST ="T_請求書_SD元データ新規導入一覧.csv";
    public static final String T_SEIKYUSHO_SDSUMAHO_MOTODATAKAISHU_LIST ="T_請求書_SD元データ回収一覧.csv";
    public static final String T_SEIKYUSHO_SDSUMAHO_MOTODATAIOCSV_LIST ="T_請求書_SD元データiO.csv";

    public static final String T_SEIKYUSHO_SHINPURU ="T_請求書_ｼﾝﾌﾟﾙ.csv";
    public static final String T_SEIKYUSHO_TENTO ="T_請求書_店頭.csv";
    public static final String T_SEIKYUSHO_SHINPURULIST ="T_請求書_シンプルスマホ_新規導入一覧.csv";
    public static final String T_SEIKYUSHO_TENTOLIST ="T_請求書_店頭受取スマホ_新規導入一覧.csv";

    public static final String T_SEIKYUSHO_MDM_SDCSV ="T_請求書_sd.csv";
    public static final String T_SEIKYUSHO_MDM_IOCSV ="T_請求書_io.csv";
    public static final String T_SEIKYUSHO_MDM_SHOKIBUN ="T_請求書_初期分判定.csv";
    public static final String T_SEIKYUSHO_MDM_CUSTOM_REPORT ="T_請求書_MDM_ｶｽﾀﾑﾚﾎﾟｰﾄ.csv";
    public static final String T_SEIKYUSHO_MDM_TENTO_SIMPLE_CUSTOM_REPORT ="T_請求書_店頭_ｼﾝﾌﾟﾙ_ｶｽﾀﾑﾚﾎﾟｰﾄ.csv";
    public static final String T_SEIKYUSHO_MDM_KAIYAKU_RUISEKI_IO ="T_請求書_解約累積iO!.csv";
    public static final String T_SEIKYUSHO_MDM_KAIYAKU_BUN_SAI_SHUKKA ="T_請求書_解約分再出荷.csv";
    public static final String T_SEIKYUSHO_MDM_SHITEN_BETSU_SHUKEI ="T_請求書_MDM_支店別集計.csv";
    public static final String T_SEIKYUSHO_MDM_EIGYOTEN_BETSU_SHUKEI ="T_請求書_MDM_営業所別集計.csv";


    public static final String OSU = "大洲";
    public static final String T_SEIKYUSHO_SDSUMAHO_KIKI_ICHIRAN_SHOKI_28190 = "t_seikyusho_sdsumaho_kiki_ichiran_shoki_28190.csv";
    public static final String T_SEIKYUSHO_SDSUMAHO_KIKI_ICHIRAN_SHOKI_28000 = "t_seikyusho_sdsumaho_kiki_ichiran_shoki_28000.csv";
    public static final String T_SEIKYUSHO_SDSUMAHO_IOCSV ="iO!.csv";

    // iO csvname
    public static final String T_SEIKYUSHO_IO_DAISU_CSVNAME = "T_請求書_iO_台数貼付用.csv";
    public static final String T_SEIKYUSHO_IO_EIGYOJO_SEISAN_KODOMASUTA_CSVNAME = "T_請求書_iO_営業所_精算コードマスタ.csv";
    public static final String T_SEIKYUSHO_IO_HONSHA_MATOME_CSVNAME = "T_請求書_iO_本社まとめ.csv";
    public static final String T_SEIKYUSHO_IO_PVT_F_CSVNAME = "T_請求書_iO_PVT_F.csv";
    public static final String T_SEIKYUSHO_IO_PVT_J_CSVNAME = "T_請求書_iO_PVT_J.csv";
    public static final String T_SEIKYUSHO_IO_KAIYAKU_RUISEKI_CSVNAME = "T_請求書_iO_解約累積.csv";
    public static final String T_SEIKYUSHO_IO_KIKI_ICHIRAN_KONGETSU_SHINKI_CSVNAME = "T_請求書_iO_機器一覧_今月新規.csv";
    public static final String T_SEIKYUSHO_IO_KIKI_ICHIRAN_KONGETSU_SHINKI_F_CSVNAME = "T_請求書_iO_機器一覧_今月新規_F.csv";
    public static final String T_SEIKYUSHO_IO_KIKI_ICHIRAN_SHINKI_RUISEKI_CSVNAME = "T_請求書_iO_機器一覧_新規累積.csv";
    public static final String T_SEIKYUSHO_IO_KIKI_ICHIRAN_SHOKI_15000_CSVNAME = "T_請求書_iO_機器一覧_初期15000.csv";
    public static final String T_SEIKYUSHO_IO_KIKI_ICHIRAN_ZEN_DAI_CSVNAME = "T_請求書_iO_機器一覧_全台.csv";
    public static final String T_SEIKYUSHO_IO_KATSUSHIMA_SHURI_TAIO_CSVNAME = "T_請求書_iO_勝島修理対応.csv";

    /**
     * 保有台上传的元数据文件名
     */
    public static final String T_SEIKYUSHO_MOTO_EXCELNAME = "こんかいのリスト.xlsx";
    public static final String T_SEIKYUSHO_MOTO_PDTNAME = "PDT最終利用履歴";
    public static final String ALL = "all";
    public static final String CSV_999 = "999.csv";
    public static final String CSV_IO = "iO.csv";
    public static final String CSV_SUMAPURI = "スマプリ.csv";
    public static final String CSV_G7 = "G7.csv";
    public static final String CSV_G7_MEISAI = "G7請求明細";


    /**
     * 模板文件名字段
     **/
    public static final String TEMPLATE_FILE_NAME = "template_file_name";
    public static final String T_SEIKYUSHO_TEMPLATE_FILE_NAME = "t_seikyusho_template_file_name";
    public static final String T_KATSUSHIMA_TEMPLATE_FILE_NAME = "t_katsushima_template_file_name";
    public static final String TEMPLATE_MOTO_FILE_NAME = "template_moto_file_name";

    /**
     * job type
     **/
    public static final String OUTPUT_TYPE_DENPYOSYORI = "1";
    public static final String OUTPUT_TYPE_SEIYUSHO = "2";
    public static final String OUTPUT_TYPE_SUITABLEUNITS = "3";
    public static final String OUTPUT_TYPE_INVENTORY = "4";
    public static final String OUTPUT_TYPE_OPERATION = "6";
    public static final String OUTPUT_TYPE_KATSUSHIMA= "7";
    /**
     * 《SD、iO!稼働調査種類》
     * 1:iO
     * 2:SD
     **/
    public static final String KADOCHOSA_iO = "1";
    public static final String KADOCHOSA_SD_SMARTPHONE = "2";

    /**
     * 《勝島向け収支報告一覧種類》
     * 1:999
     * 2:FP1
     * 3:iO!
     * 4:SDスマホ
     * 5:スマプリ
     */
    public static final String KATSUSHIMA_999 = "1";
    public static final String KATSUSHIMA_FP1 = "2";
    public static final String KATSUSHIMA_iO = "3";
    public static final String KATSUSHIMA_SD_SMARTPHONE = "4";
    public static final String KATSUSHIMA_SUMAPURI = "5";
    public static final String KATSUSHIMA_SUMAPURI_DAISU = "6";

    public static final String TANAOROSHI_SHURI_JOKYO_HOKOKU_TEMPLATE_FILE_NAME = "tanaoroshi_shuri_jokyo_hokoku_template_file_name";
    public static final String T_TEKISEI_DAISU_TEMPLATE_FILE_NAME = "t_tekisei_daisu_template_file_name";

    // 05.適正台数
    public static final String T_SUITABLE_UNITS_TANAOROSHI_SHURI_JOKYO_HOKOKU_P = "T_棚卸_修理状況報告_P.csv";
    public static final String T_TEKISEI_DAISU = "T_適正台数関連.csv";
    public static final String T_TEKISEI_DAISU_TEN_MASTER = "T_店情報.csv";
    public static final String T_TEKISEI_DAISU_JUCHU = "T_受注T.csv";
    public static final String T_TEKISEI_DAISU_KOSUSU_JISSEKI = "T_コース数実績.csv";
    public static final String T_TEKISEI_DAISU_TANAOROSHI = "T_棚卸.csv";
    // 適正台数
    public static final String JOBLOG_CONTENT_DELETE_TANAOROSHI_SHURI_JOKYO_HOKOKU_P_START = "T_棚卸_修理状況報告_P表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETE_TANAOROSHI_SHURI_JOKYO_HOKOKU_P_END = "T_棚卸_修理状況報告_P表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERT_TANAOROSHI_SHURI_JOKYO_HOKOKU_P_START = "T_棚卸_修理状況報告_P表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERT_TANAOROSHI_SHURI_JOKYO_HOKOKU_P_END = "T_棚卸_修理状況報告_P表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATE_TANAOROSHI_SHURI_JOKYO_HOKOKU_P_START = "T_棚卸_修理状況報告_P表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATE_TANAOROSHI_SHURI_JOKYO_HOKOKU_P_END = "T_棚卸_修理状況報告_P表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETE_T_TEKISEI_DAISU_TEN_MASTER_START = "T_店情報表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETE_T_TEKISEI_DAISU_TEN_MASTER_END = "T_店情報表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERT_T_TEKISEI_DAISU_TEN_MASTER_START = "T_店情報表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERT_T_TEKISEI_DAISU_TEN_MASTER_END = "T_店情報表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATE_T_TEKISEI_DAISU_TEN_MASTER_START = "T_店情報表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATE_T_TEKISEI_DAISU_TEN_MASTER_END = "T_店情報表の更新が完了しました";


    public static final String JOBLOG_CONTENT_DELETET_T_TEKISEI_DAISU_JUCHU_START = "T_受注T表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_T_TEKISEI_DAISU_JUCHU_END = "T_受注T表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_T_TEKISEI_DAISU_JUCHU_START = "T_受注T表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_T_TEKISEI_DAISU_JUCHU_END = "T_受注T表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_T_TEKISEI_DAISU_JUCHU_START = "T_受注T表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_T_TEKISEI_DAISU_JUCHU_END = "T_受注T表の更新が完了しました";


    public static final String JOBLOG_CONTENT_DELETE_T_TEKISEI_DAISU_TANAOROSHI_START = "T_棚卸表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETE_T_TEKISEI_DAISU_TANAOROSHI_END = "T_棚卸表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERT_T_TEKISEI_DAISU_TANAOROSHI_START = "T_棚卸表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERT_T_TEKISEI_DAISU_TANAOROSHI_END = "T_棚卸表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATE_T_TEKISEI_DAISU_TANAOROSHI_START = "T_棚卸表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATE_T_TEKISEI_DAISU_TANAOROSHI_END = "T_棚卸表の更新が完了しました";



    public static final String JOBLOG_CONTENT_DELETET_T_TEKISEI_DAISU_KOSUSU_JISSEKI_START = "T_コース数実績表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_T_TEKISEI_DAISU_KOSUSU_JISSEKI_END = "T_コース数実績表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_T_TEKISEI_DAISU_KOSUSU_JISSEKI_START = "T_コース数実績表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_T_TEKISEI_DAISU_KOSUSU_JISSEKI_END = "T_コース数実績表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_T_TEKISEI_DAISU_KOSUSU_JISSEKI_START = "T_コース数実績表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_T_TEKISEI_DAISU_KOSUSU_JISSEKI_END = "T_コース数実績表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETET_T_TEKISEI_DAISU_START = "T_適正台数関連表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_T_TEKISEI_DAISU_END = "T_適正台数関連表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_T_TEKISEI_DAISU_START = "T_適正台数関連表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_T_TEKISEI_DAISU_END = "T_適正台数関連表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATET_T_TEKISEI_DAISU_START = "T_適正台数関連表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATET_T_TEKISEI_DAISU_END = "T_適正台数関連表の更新が完了しました";


    // iOスマホ元データ
    // 模板名
    public static final String T_IO_SHINKI_DONYU_ICHIRAN = "【ｉＯ！】新規導入一覧.csv";
    public static final String T_IO_CSVNAME = "iO!.csv";
    public static final String T_IO_KAIYAKU_ICHIRAN = "ｉＯ！解約一覧.csv";
    public static final String T_SD_SHINKI_DONYU_ICHIRAN = "【SDスマホ】新規導入一覧.csv";

    public static final String JOBLOG_CONTENT_DELETE_T_IO_SHINKI_DONYU_ICHIRAN_START = "T_【ｉＯ！】新規導入一覧表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETE_T_IO_SHINKI_DONYU_ICHIRAN_END = "T_【ｉＯ！】新規導入一覧表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERT_T_IO_SHINKI_DONYU_ICHIRAN_START = "T_【ｉＯ！】新規導入一覧表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERT_T_IO_SHINKI_DONYU_ICHIRAN_END = "T_【ｉＯ！】新規導入一覧表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATE_T_IO_SHINKI_DONYU_ICHIRAN_START = "T_【ｉＯ！】新規導入一覧表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATE_T_IO_SHINKI_DONYU_ICHIRAN_END = "T_【ｉＯ！】新規導入一覧表の更新が完了しました";



    public static final String JOBLOG_CONTENT_DELETE_T_IO_KAIYAKU_ICHIRAN_START = "T_ｉＯ！解約一覧表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETE_T_IO_KAIYAKU_ICHIRAN_END = "T_ｉＯ！解約一覧表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERT_T_IO_KAIYAKU_ICHIRAN_START = "T_ｉＯ！解約一覧表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERT_T_IO_KAIYAKU_ICHIRAN_END = "T_ｉＯ！解約一覧表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATE_T_IO_KAIYAKU_ICHIRAN_START = "T_ｉＯ！解約一覧表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATE_T_IO_KAIYAKU_ICHIRAN_END = "T_ｉＯ！解約一覧表の更新が完了しました";

    public static final String JOBLOG_CONTENT_DELETE_T_SD_SHINKI_DONYU_ICHIRAN_START = "T_【SDスマホ】新規導入一覧表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETE_T_SD_SHINKI_DONYU_ICHIRAN_END = "T_【SDスマホ】新規導入一覧表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERT_T_SD_SHINKI_DONYU_ICHIRAN_START = "T_【SDスマホ】新規導入一覧表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERT_T_SD_SHINKI_DONYU_ICHIRAN_END = "T_【SDスマホ】新規導入一覧表の追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATE_T_SD_SHINKI_DONYU_ICHIRAN_START = "T_【SDスマホ】新規導入一覧表の更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATE_T_SD_SHINKI_DONYU_ICHIRAN_END = "T_【SDスマホ】新規導入一覧表の更新が完了しました";

    //io
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MOTO_IO_START = "t_iO!表の削除が開始します";
    public static final String JOBLOG_CONTENT_DELETET_SEIKYUSHO_MOTO_IO_END = " t_iO!表の削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MOTO_IO_START = " t_iO!表の追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTT_SEIKYUSHO_MOTO_IO_END = " t_iO!表の追加が完了しました";

    // 01.保有台数一覧 FP1 元データ
    public static final String T_KAMOTSU_MOTO_CSVNAME = "PDT最終利用履歴";
    public static final String T_ZENSON_FUNSHITSU_MOTO_FP1_CSVNAME = "FP1解約一覧.xlsx";

    public static final String JOBLOG_CONTENT_DELETETKAMOTSU_MOTO_START = "t_貨物表_元データの削除が開始します";
    public static final String JOBLOG_CONTENT_DELETETKAMOTSU_MOTO_END = "t_貨物表_元データの削除が完了しました";
    public static final String JOBLOG_CONTENT_INSERTTKAMOTSU_MOTO_START = "t_貨物表_元データの追加が開始します";
    public static final String JOBLOG_CONTENT_INSERTTKAMOTSU_MOTO_END = "t_貨物表_元データの追加が完了しました";
    public static final String JOBLOG_CONTENT_UPDATETKAMOTSU_MOTO_START = "t_貨物表_元データの更新が開始します";
    public static final String JOBLOG_CONTENT_UPDATETKAMOTSU_MOTO_END = "t_貨物表_元データの更新が完了しました";

    // 01.保有台数一覧 SD 元データ
    public static final String T_SD_SHINKI_DONYU_ICHIRAN_MOTO_CSVNAME = "【SDスマホ】新規導入一覧";
    public static final String T_SD_KADO_DAISU_KEISAN_YO_MOTO_CSVNAME = "稼働台数計算用";
    public static final String T_TERMINFO_IC_MOTO_CSVNAME = "TERMINFO_IC";
    public static final String T_ALL_MOTO_CSVNAME = "all_";
}

