package com.haso.sysp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.haso.common.api.vo.Result;
import com.haso.sysp.model.BlcarrierDTO;
import com.haso.sysp.model.DairitenDTO;
import com.haso.sysp.model.HakoutenDTO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 適正台数一覧
 * @Author: Gong Lingxiao
 * @Date: 2020-12-28
 * @Version:V1.0
 */
@RestController
@RequestMapping("/dacs/dairitenMaintenance")
@Slf4j
@Api(tags = "代理店メンテナンス")
public class DairitenMaintenanceController {

    /**
     * 代理店メンテナンス検索
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<IPage<DairitenDTO>> list(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                                                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        Result<IPage<DairitenDTO>> result = new Result<IPage<DairitenDTO>>();
        List<DairitenDTO> records = new ArrayList<DairitenDTO>();
        DairitenDTO dto1 = new DairitenDTO();
        dto1.setBlcarriercd("JAL");
        dto1.setHakoutencd("0199");
        dto1.setAirportcd("FUK");
        dto1.setAirportnm("（ 福岡空港 ）");
        dto1.setKanritencd("703000");
        dto1.setKanritennm("（ 九州航空 ）");
        dto1.setDairitencd("SGX30");
        //dto1.setDairitennm("（ 福岡／ＢＳＡ ）");
        dto1.setDairitennm("福岡／ＢＳＡ");
        dto1.setTesuryoritsu("5 %");
        dto1.setHinmkcd("38");
        dto1.setHinmknm("品目名");
        DairitenDTO dto2 = new DairitenDTO();
        dto2.setBlcarriercd("JAL");
        dto2.setHakoutencd("0199");
        dto2.setAirportcd("FUK");
        dto2.setAirportnm("（ 福岡空港 ）");
        dto2.setKanritencd("703000");
        dto2.setKanritennm("（ 九州航空 ）");
        dto2.setDairitencd("SGX31");
        //dto2.setDairitennm("（ 福岡／佐川急便 ）");
        dto2.setDairitennm("福岡／佐川急便");
        dto2.setTesuryoritsu("5 %");
        dto2.setHinmkcd("3S");
        dto2.setHinmknm("品目名");
        DairitenDTO dto3 = new DairitenDTO();
        dto3.setBlcarriercd("JAL");
        dto3.setHakoutencd("0199");
        dto3.setAirportcd("FUK");
        dto3.setAirportnm("（ 福岡空港 ）");
        dto3.setKanritencd("703000");
        dto3.setKanritennm("（ 九州航空 ）");
        dto3.setDairitencd("SGX30");
        //dto3.setDairitennm("（ 福岡／ＢＳＡ ）");
        dto3.setDairitennm("福岡／ＢＳＡ");
        dto3.setTesuryoritsu("5 %");
        dto3.setHinmkcd("3T");
        dto3.setHinmknm("品目名");
        DairitenDTO dto4 = new DairitenDTO();
        dto4.setBlcarriercd("JAL");
        dto4.setHakoutencd("0199");
        dto4.setAirportcd("FUK");
        dto4.setAirportnm("（ 福岡空港 ）");
        dto4.setKanritencd("703000");
        dto4.setKanritennm("（ 九州航空 ）");
        dto4.setDairitencd("SGX29");
        //dto4.setDairitennm("（ 福岡 ）");
        dto4.setDairitennm("福岡");
        dto4.setTesuryoritsu("5 %");
        dto4.setHinmkcd("ALL");
        dto4.setHinmknm("品目名");

        DairitenDTO dto5 = new DairitenDTO();
        dto5.setBlcarriercd("JAL");
        dto5.setHakoutencd("0199");
        dto5.setAirportcd("KKJ");
        dto5.setAirportnm("（ 福岡空港 ）");
        dto5.setKanritencd("703000");
        dto5.setKanritennm("（ 九州航空 ）");
        dto5.setDairitencd("SGX42");
        //dto5.setDairitennm("（ 北九州 ）");
        dto5.setDairitennm("北九州");
        dto5.setTesuryoritsu("5 %");
        dto5.setHinmkcd("ALL");
        dto5.setHinmknm("品目名");
        DairitenDTO dto6 = new DairitenDTO();
        dto6.setBlcarriercd("JAL");
        dto6.setHakoutencd("0299");
        dto6.setAirportcd("KMJ");
        dto6.setAirportnm("（ 熊本空港 ）");
        dto6.setKanritencd("703000");
        dto6.setKanritennm("（ 九州航空 ）");
        dto6.setDairitencd("SGX33");
        //dto6.setDairitennm("（ 熊本 ）");
        dto6.setDairitennm("熊本");
        dto6.setTesuryoritsu("5 %");
        dto6.setHinmkcd("ALL");
        dto6.setHinmknm("品目名");
        DairitenDTO dto7 = new DairitenDTO();
        dto7.setBlcarriercd("JAL");
        dto7.setHakoutencd("0399");
        dto7.setAirportcd("NGS");
        dto7.setAirportnm("（ 長崎空港営業所 ）");
        dto7.setKanritencd("703031");
        dto7.setKanritennm("（ 九州航空 ）");
        dto7.setDairitencd("SGX36");
        //dto7.setDairitennm("（ 長崎 ）");
        dto7.setDairitennm("長崎");
        dto7.setTesuryoritsu("5 %");
        dto7.setHinmkcd("ALL");
        dto7.setHinmknm("品目名");
        DairitenDTO dto8 = new DairitenDTO();
        dto8.setBlcarriercd("JAL");
        dto8.setHakoutencd("0419");
        dto8.setAirportcd("OKA");
        dto8.setAirportnm("（ 那覇空港 ）");
        dto8.setKanritencd("703023");
        dto8.setKanritennm("（ 沖縄空港営業所 ）");
        dto8.setDairitencd("SGX39");
        //dto8.setDairitennm("（ 沖縄／ＢＳＡ ）");
        dto8.setDairitennm("沖縄／ＢＳＡ");
        dto8.setTesuryoritsu("5 %");
        dto8.setHinmkcd("38");
        dto8.setHinmknm("品目名");
        DairitenDTO dto9 = new DairitenDTO();
        dto9.setBlcarriercd("JAL");
        dto9.setHakoutencd("0419");
        dto9.setAirportcd("OKA");
        dto9.setAirportnm("（ 那覇空港 ）");
        dto9.setKanritencd("703023");
        dto9.setKanritennm("（ 沖縄空港営業所 ）");
        dto9.setDairitencd("SGX39");
        //dto9.setDairitennm("（ 沖縄／ＢＳＡ ）");
        dto9.setDairitennm("沖縄／ＢＳＡ");
        dto9.setTesuryoritsu("5 %");
        dto9.setHinmkcd("3T");
        dto9.setHinmknm("品目名");
        DairitenDTO dto10 = new DairitenDTO();
        dto10.setBlcarriercd("JAL");
        dto10.setHakoutencd("0419");
        dto10.setAirportcd("OKA");
        dto10.setAirportnm("（ 那覇空港 ）");
        dto10.setKanritencd("703023");
        dto10.setKanritennm("（ 沖縄空港営業所 ）");
        dto10.setDairitencd("SGX40");
        //dto10.setDairitennm("（ 沖縄 ）");
        dto10.setDairitennm("沖縄");
        dto10.setTesuryoritsu("5 %");
        dto10.setHinmkcd("ALL");
        dto10.setHinmknm("品目名");

        DairitenDTO dto11 = new DairitenDTO();
        dto11.setBlcarriercd("JAL");
        dto11.setHakoutencd("0479");
        dto11.setAirportcd("ASJ");
        dto11.setAirportnm("（ 奄美大島空港 ）");
        dto11.setKanritencd("703023");
        dto11.setKanritennm("（ 沖縄空港営業所 ）");
        dto11.setDairitencd("SGX40");
        //dto11.setDairitennm("（ 奄美大島 ）");
        dto11.setDairitennm("奄美大島");
        dto11.setTesuryoritsu("5 %");
        dto11.setHinmkcd("ALL");
        dto11.setHinmknm("品目名");

        DairitenDTO dto12 = new DairitenDTO();
        dto12.setBlcarriercd("JAL");
        dto12.setHakoutencd("0499");
        dto12.setAirportcd("OKA");
        dto12.setAirportnm("（ 鹿児島空港 ）");
        dto12.setKanritencd("703023");
        dto12.setKanritennm("（ 沖縄空港営業所 ）");
        dto12.setDairitencd("SGX40");
        //dto12.setDairitennm("（ 鹿児島 ）");
        dto12.setDairitennm("鹿児島");
        dto12.setTesuryoritsu("5 %");
        dto12.setHinmkcd("ALL");
        dto12.setHinmknm("品目名");

        DairitenDTO dto13 = new DairitenDTO();
        dto13.setBlcarriercd("JAL");
        dto13.setHakoutencd("0599");
        dto13.setAirportcd("OKA");
        dto13.setAirportnm("（ 宮崎空港 ）");
        dto13.setKanritencd("703023");
        dto13.setKanritennm("（ 沖縄空港営業所 ）");
        dto13.setDairitencd("SGX40");
        //dto13.setDairitennm("（ 宮崎 ）");
        dto13.setDairitennm("宮崎");
        dto13.setTesuryoritsu("5 %");
        dto13.setHinmkcd("ALL");
        dto13.setHinmknm("品目名");

        DairitenDTO dto14 = new DairitenDTO();
        dto14.setBlcarriercd("JAL");
        dto14.setHakoutencd("0599");
        dto14.setAirportcd("OKA");
        dto14.setAirportnm("（ 宮崎空港 ）");
        dto14.setKanritencd("703023");
        dto14.setKanritennm("（ 沖縄空港営業所 ）");
        dto14.setDairitencd("SGX40");
        //dto13.setDairitennm("（ 宮崎 ）");
        dto14.setDairitennm("宮崎");
        dto14.setTesuryoritsu("5 %");
        dto14.setHinmkcd("ALL");
        dto14.setHinmknm("品目名");

        DairitenDTO dto15 = new DairitenDTO();
        dto15.setBlcarriercd("JAL");
        dto15.setHakoutencd("0599");
        dto15.setAirportcd("OKA");
        dto15.setAirportnm("（ 宮崎空港 ）");
        dto15.setKanritencd("703023");
        dto15.setKanritennm("（ 沖縄空港営業所 ）");
        dto15.setDairitencd("SGX40");
        //dto13.setDairitennm("（ 宮崎 ）");
        dto15.setDairitennm("宮崎");
        dto15.setTesuryoritsu("5 %");
        dto15.setHinmkcd("ALL");
        dto15.setHinmknm("品目名");

        DairitenDTO dto16 = new DairitenDTO();
        dto16.setBlcarriercd("JAL");
        dto16.setHakoutencd("0599");
        dto16.setAirportcd("OKA");
        dto16.setAirportnm("（ 宮崎空港 ）");
        dto16.setKanritencd("703023");
        dto16.setKanritennm("（ 沖縄空港営業所 ）");
        dto16.setDairitencd("SGX40");
        //dto13.setDairitennm("（ 宮崎 ）");
        dto16.setDairitennm("宮崎");
        dto16.setTesuryoritsu("5 %");
        dto16.setHinmkcd("ALL");
        dto16.setHinmknm("品目名");
        DairitenDTO dto17 = new DairitenDTO();
        dto17.setBlcarriercd("JAL");
        dto17.setHakoutencd("0599");
        dto17.setAirportcd("OKA");
        dto17.setAirportnm("（ 宮崎空港 ）");
        dto17.setKanritencd("703023");
        dto17.setKanritennm("（ 沖縄空港営業所 ）");
        dto17.setDairitencd("SGX40");
        //dto13.setDairitennm("（ 宮崎 ）");
        dto17.setDairitennm("宮崎");
        dto17.setTesuryoritsu("5 %");
        dto17.setHinmkcd("ALL");
        dto17.setHinmknm("品目名");
        DairitenDTO dto18 = new DairitenDTO();
        dto18.setBlcarriercd("JAL");
        dto18.setHakoutencd("0599");
        dto18.setAirportcd("OKA");
        dto18.setAirportnm("（ 宮崎空港 ）");
        dto18.setKanritencd("703023");
        dto18.setKanritennm("（ 沖縄空港営業所 ）");
        dto18.setDairitencd("SGX40");
        //dto13.setDairitennm("（ 宮崎 ）");
        dto18.setDairitennm("宮崎");
        dto18.setTesuryoritsu("5 %");
        dto18.setHinmkcd("ALL");
        dto18.setHinmknm("品目名");
        DairitenDTO dto19 = new DairitenDTO();
        dto19.setBlcarriercd("JAL");
        dto19.setHakoutencd("0599");
        dto19.setAirportcd("OKA");
        dto19.setAirportnm("（ 宮崎空港 ）");
        dto19.setKanritencd("703023");
        dto19.setKanritennm("（ 沖縄空港営業所 ）");
        dto19.setDairitencd("SGX40");
        //dto13.setDairitennm("（ 宮崎 ）");
        dto19.setDairitennm("宮崎");
        dto19.setTesuryoritsu("5 %");
        dto19.setHinmkcd("ALL");
        dto19.setHinmknm("品目名");
        DairitenDTO dto20 = new DairitenDTO();
        dto20.setBlcarriercd("JAL");
        dto20.setHakoutencd("0599");
        dto20.setAirportcd("OKA");
        dto20.setAirportnm("（ 宮崎空港 ）");
        dto20.setKanritencd("703023");
        dto20.setKanritennm("（ 沖縄空港営業所 ）");
        dto20.setDairitencd("SGX40");
        //dto13.setDairitennm("（ 宮崎 ）");
        dto20.setDairitennm("宮崎");
        dto20.setTesuryoritsu("5 %");
        dto20.setHinmkcd("ALL");
        dto20.setHinmknm("品目名");
        DairitenDTO dto21 = new DairitenDTO();
        dto21.setBlcarriercd("JAL");
        dto21.setHakoutencd("0599");
        dto21.setAirportcd("OKA");
        dto21.setAirportnm("（ 宮崎空港 ）");
        dto21.setKanritencd("703023");
        dto21.setKanritennm("（ 沖縄空港営業所 ）");
        dto21.setDairitencd("SGX40");
        //dto13.setDairitennm("（ 宮崎 ）");
        dto21.setDairitennm("宮崎");
        dto21.setTesuryoritsu("5 %");
        dto21.setHinmkcd("ALL");
        dto21.setHinmknm("品目名");

        DairitenDTO dto22 = new DairitenDTO();
        dto22.setBlcarriercd("JAL");
        dto22.setHakoutencd("0599");
        dto22.setAirportcd("OKA");
        dto22.setAirportnm("（ 宮崎空港 ）");
        dto22.setKanritencd("703023");
        dto22.setKanritennm("（ 沖縄空港営業所 ）");
        dto22.setDairitencd("SGX40");
        //dto13.setDairitennm("（ 宮崎 ）");
        dto22.setDairitennm("宮崎");
        dto22.setTesuryoritsu("5 %");
        dto22.setHinmkcd("ALL");
        dto22.setHinmknm("品目名");



        records.add(dto1);
        records.add(dto2);
        records.add(dto3);
        records.add(dto4);
        records.add(dto5);
        records.add(dto6);
        records.add(dto7);
        records.add(dto8);
        records.add(dto9);
        records.add(dto10);
        records.add(dto11);
        records.add(dto12);
        records.add(dto13);
        records.add(dto14);
        records.add(dto15);
        records.add(dto16);
        records.add(dto17);
        records.add(dto18);
        records.add(dto19);
        records.add(dto20);
        records.add(dto21);
        records.add(dto22);
        IPage<DairitenDTO> dairitenList = new Page<DairitenDTO>();
        dairitenList.setCurrent(1);
        dairitenList.setRecords(records);
        dairitenList.setPages(1);
        dairitenList.setSize(10);
        dairitenList.setTotal(records.size());
        result.setSuccess(true);
        result.setResult(dairitenList);
        return result;
    }

    /**
     * BLキャリア検索
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/blcarrierSearch", method = RequestMethod.GET)
    public Result<IPage<BlcarrierDTO>> blcarrierSearch(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                                                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Result<IPage<BlcarrierDTO>> result = new Result<IPage<BlcarrierDTO>>();
        List<BlcarrierDTO> records = new ArrayList<BlcarrierDTO>();
        BlcarrierDTO dto1 = new BlcarrierDTO();
        dto1.setBlcarriercd("0001");
        dto1.setBlcarriernm("JAL");
        BlcarrierDTO dto2 = new BlcarrierDTO();
        dto2.setBlcarriercd("0002");
        dto2.setBlcarriernm("ANA");
        BlcarrierDTO dto3 = new BlcarrierDTO();
        dto3.setBlcarriercd("0002");
        dto3.setBlcarriernm("ANA");
        BlcarrierDTO dto4 = new BlcarrierDTO();
        dto4.setBlcarriercd("0002");
        dto4.setBlcarriernm("ANA");
        BlcarrierDTO dto5 = new BlcarrierDTO();
        dto5.setBlcarriercd("0002");
        dto5.setBlcarriernm("ANA");
        BlcarrierDTO dto6 = new BlcarrierDTO();
        dto6.setBlcarriercd("0002");
        dto6.setBlcarriernm("ANA");
        BlcarrierDTO dto7 = new BlcarrierDTO();
        BlcarrierDTO dto8 = new BlcarrierDTO();
        BlcarrierDTO dto9 = new BlcarrierDTO();
        BlcarrierDTO dto10 = new BlcarrierDTO();

        records.add(dto1);
        records.add(dto2);
        records.add(dto3);
        records.add(dto4);
        records.add(dto5);
        records.add(dto6);
//        records.add(dto7);
//        records.add(dto8);
//        records.add(dto9);
//        records.add(dto10);
        IPage<BlcarrierDTO> blcarrierList = new Page<BlcarrierDTO>();
        blcarrierList.setCurrent(1);
        blcarrierList.setRecords(records);
        blcarrierList.setPages(1);
        blcarrierList.setSize(10);
        blcarrierList.setTotal(records.size());
        result.setSuccess(true);
        result.setResult(blcarrierList);
        return result;
    }

    /**
     * 発行店検索
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/hakoutenSearch", method = RequestMethod.GET)
    public Result<IPage<HakoutenDTO>> hakoutenSearch(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                                                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Result<IPage<HakoutenDTO>> result = new Result<IPage<HakoutenDTO>>();
        List<HakoutenDTO> records = new ArrayList<HakoutenDTO>();
        HakoutenDTO dto1 = new HakoutenDTO();
        dto1.setHakoutencd("0199");
        dto1.setHakoutennm("福岡空港");
        HakoutenDTO dto2 = new HakoutenDTO();
        HakoutenDTO dto3 = new HakoutenDTO();
        HakoutenDTO dto4 = new HakoutenDTO();
        HakoutenDTO dto5 = new HakoutenDTO();
        HakoutenDTO dto6 = new HakoutenDTO();
        HakoutenDTO dto7 = new HakoutenDTO();
        HakoutenDTO dto8 = new HakoutenDTO();
        HakoutenDTO dto9 = new HakoutenDTO();
        HakoutenDTO dto10 = new HakoutenDTO();

        records.add(dto1);
//        records.add(dto2);
//        records.add(dto3);
//        records.add(dto4);
//        records.add(dto5);
//        records.add(dto6);
//        records.add(dto7);
//        records.add(dto8);
//        records.add(dto9);
//        records.add(dto10);
        IPage<HakoutenDTO> hakoutenList = new Page<HakoutenDTO>();
        hakoutenList.setCurrent(1);
        hakoutenList.setRecords(records);
        hakoutenList.setPages(1);
        hakoutenList.setSize(10);
        hakoutenList.setTotal(records.size());
        result.setSuccess(true);
        result.setResult(hakoutenList);
        return result;
    }
}
