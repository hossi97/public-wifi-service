// dao: db랑 연결하는
// controller: 요청을 받고 반환 / service : 요청 받은 것을 갖고 데이터 조작
// dto: 계층간의 데이터 전달을 위해 사용한다. entity 에서 필요한 것들만 반환하기 위해 사용한다.
// vo: db 컬럼 이름들을 기준으로 변수를 만든다. 컬럼 말고도 클래스를 사용해서 데이터를 만들 수 있다.

package com.ho.dao;

import com.ho.dto.WifiDto;
import com.ho.repository.WifiRepository;
import com.ho.service.WifiService;
import com.ho.vo.WifiVo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WifiDao {
    WifiRepository wifiService = WifiService.getWifiService();

    public void insertWifi(List<WifiDto> list) throws SQLException {
        String sql = "insert into wifi(x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm,\n" +
                "                 x_swifi_adres1, x_swifi_adres2, x_swifi_instl_floor,\n" +
                "                 x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se,\n" +
                "                 x_swifi_cmcwr, x_swifi_cnstc_year, x_swifi_inout_door,\n" +
                "                 x_swifi_remars3, lat, lnt, work_dttm)\n" +
                "   values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        wifiService.insertWifiInfos(sql, list);

    } //17804개

    public void deleteAll() throws SQLException {
        String sql = "truncate table wifi";

        wifiService.deleteAllWifiInfos(sql);

    }

    // 내근처 와이파이 조회
    public List<WifiVo> selectSearchWifiList(Double lat, Double lnt) {
        String sql = "select * " +
                ", format((6371 * acos(cos(radians(" + lat + ")) * cos(radians(lat)) * cos(radians(lnt) - radians(" + lnt + ")) " +
                "+ sin(radians(" + lat + ")) * sin(radians(lat)))), 4) as distance " +
                " from wifi " +
                " order by distance , X_SWIFI_MGR_NO" +
                " limit 20";

        List<WifiVo> list = new ArrayList<>();
        wifiService.getWifiList(sql, list);

        return list;
    }
}