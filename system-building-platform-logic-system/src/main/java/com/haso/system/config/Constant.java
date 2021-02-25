package com.haso.system.config;

import java.util.HashMap;
import java.util.Map;

public class Constant {

	public final static String OTHER = "その他";

	public final static int REDISCACHEMANAGER_EXPIRE_TIMEOOUT = 200000;

	//契約情報履歴----->日程希望リスト(都道府県Map)
	public static final Map<String, String> CountryMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("北海道","北海道");
			put("青森県","東北");
			put("岩手県","東北");
			put("宮城県","東北");
			put("秋田県","東北");
			put("山形県","東北");
			put("福島県","東北");
			put("新潟県","関東");
			put("茨城県","関東");
			put("栃木県","関東");
			put("群馬県","関東");
			put("埼玉県","関東");
			put("千葉県","関東");
			put("東京都","関東");
			put("神奈川県","関東");
			put("山梨県","関東");
			put("富山県","中部");
			put("石川県","中部");
			put("福井県","中部");
			put("岐阜県","中部");
			put("愛知県","中部");
			put("滋賀県","関西");
			put("京都府","関西");
			put("大阪府","関西");
			put("兵庫県","関西");
			put("奈良県","関西");
			put("和歌山県","関西");
			put("徳島県","関西");
			put("鳥取県","中国");
			put("島根県","中国");
			put("岡山県","中国");
			put("広島県","中国");
			put("山口県","中国");
			put("香川県","中国");
			put("愛媛県","中国");
			put("高知県","中国");
			put("福岡県","九州");
			put("佐賀県","九州");
			put("長崎県","九州");
			put("熊本県","九州");
			put("大分県","九州");
			put("宮崎県","九州");
			put("鹿児島県","九州");
			put("沖縄県","九州");
		}
	};



	//「エラー解決ステータス」が「0」
	public final static Integer STATUS_FLG_0 = 0;
	//「エラー解決ステータス」が「1」
	public final static Integer STATUS_FLG_1 = 1;
	//「エラー解決ステータス」が「2」
	public final static Integer STATUS_FLG_2 = 2;
    //「エラー解決ステータス」が「未解決」
    public final static String STATUS_FLG_4 = "未解決";
    //「エラー解決ステータス」が「解決済み」
    public final static String STATUS_FLG_5 = "解決済み";
    //「エラー解決ステータス」が「調査中」
    public final static String STATUS_FLG_6 = "調査中";
}
