package com.haso.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.haso.common.api.entity.ComHasoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 职位表对象
 *
 * <pre>
 *
 * Ver.   变更/障害No.    修改日期      作成/变更者       修改内容
 * ------ ------------- ------------ --------------- -----------------------------
 * 1.0    #13914         2020-05-13    hejunnan         初期作成
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JobView extends ComHasoEntity {

    private static final long serialVersionUID = 1L;
    /**id*/
    @TableId(type = IdType.AUTO)
    private java.lang.Integer id;
    /**no*/
    private java.lang.String no;
    /**伝票種類*/
    private java.lang.String denpyoShurui;
    /**開始時間*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.lang.String kaishiJikan;
    /**終了時間*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.lang.String shuryoJikan;
    /**状態*/
    private java.lang.String jotai;
    /**元データ*/
    private java.lang.String genFileId;
    /**出力ファイル*/
    private java.lang.String outputFileId;
    /**元データ*/
    private java.lang.String genFileName;
    /**出力ファイル*/
    private java.lang.String outputFileName;
    /**ログ*/
    private java.lang.String log;
    /**備考*/
    private java.lang.String biko;

    /**削除フラグ*/
    private java.lang.String sakujoflg;

    /**排他No*/
    private java.lang.Integer exclcnt;

    /**完全なパス*/
    private String fileFullpath;

    /**登録日時*/
    private Date torokudate;
    /**登録ユーザー*/
    private java.lang.String torokuusr;
    /**登録端末名*/
    private java.lang.String torokutrm;
    /**登録プログラム名のドメイン*/
    private java.lang.String torokuprgrm;

    /**更新ユーザー*/
    private java.lang.String kosinusr;
    /**更新端末名*/
    private java.lang.String kosintrm;
    /**更新プログラム名のドメイン*/
    private java.lang.String kosinprgrm;
    /**更新日時*/
    private Date kosindate;

    private java.lang.String denpyoShuruiDict;

    private java.lang.String jotaiDict;

    private java.lang.String outputType;
    /**稼働調査伝票種類*/
    private java.lang.String kadochosaShurui;
    /**稼働調査伝票種類*/
    private java.lang.String kadochosaShuruiDict;

    /**勝島向け収支報告一覧伝票種類*/
    private java.lang.String katsushimaShurui;
    /**勝島向け収支報告一覧伝票種類*/
    private java.lang.String katsushimaShuruiDict;

}
