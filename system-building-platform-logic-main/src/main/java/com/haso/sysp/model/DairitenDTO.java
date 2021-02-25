package com.haso.sysp.model;

import lombok.Data;

@Data
public class DairitenDTO {

    /** ＢＬキャリアコード */
    private String blcarriercd;

    /** 発行店コード */
    private String hakoutencd;

    /** 発空港コード */
    private String airportcd;

    /** 発空港名 */
    private String airportnm;

    /** 管理店コード */
    private String kanritencd;

    /** 管理店名 */
    private String kanritennm;

    /** 代理店コード */
    private String dairitencd;

    /** 代理店名 */
    private String dairitennm;

    /** 代理店手数料率 */
    private String tesuryoritsu;

    /** 品目コード */
    private String hinmkcd;

    /** 品目名 */
    private String hinmknm;

}
