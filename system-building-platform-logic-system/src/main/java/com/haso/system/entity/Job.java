package com.haso.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.haso.common.aspect.annotation.Dict;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: JOB一览
 * @Author: jeecg-boot
 * @Date:  2019-01-02
 * @Version: V1.0
 */
@Data
@TableName("t_job")
public class Job implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**id*/
	@TableId(type = IdType.AUTO)
	private java.lang.Integer id;
	/**no*/
	private java.lang.String no;
	/**伝票種類*/
	@Dict(dicCode = "denpyoShurui")
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
	@Dict(dicCode = "jotai")
	private java.lang.String jotai;
	/**元データ*/
	private java.lang.String genFileId;
	/**出力ファイル*/
	private java.lang.String outputFileId;
	/**ログ*/
	private java.lang.String log;
	/**備考*/
	private java.lang.String biko;

	/**削除フラグ*/
	private java.lang.String sakujoflg;

	/**排他No*/
	private java.lang.Integer exclcnt;

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


	/**params1*/
	private java.lang.String params1;

	/**params2*/
	private java.lang.String params2;

	/**params3*/
	private java.lang.String params3;

	/**params4*/
	private java.lang.String params4;

	/**params5*/
	private java.lang.String params5;

	/**月度*/
	private java.lang.String outputDate;

	/**月度*/
	private java.lang.String outputType;

}
