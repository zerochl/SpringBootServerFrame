package cn.tzmedia.barrageserver.dbserver.dao;

import cn.tzmedia.barrageserver.common.utils.StringUtil;
import cn.tzmedia.barrageserver.dbserver.model.BarrageDeviceTable;
import cn.tzmedia.barrageserver.dbserver.repository.BarrageDeviceRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zero大神 on 2017/12/19.
 */
@Component
@Log4j2
public class BarrageDeviceDao {
    @Autowired
    private BarrageDeviceRepository barrageDeviceRepository;
    public BarrageDeviceTable insert(BarrageDeviceTable barrageDeviceTable){
        if(null == barrageDeviceTable){
            return null;
        }
        return barrageDeviceRepository.insert(barrageDeviceTable);
    }

    public BarrageDeviceTable save(BarrageDeviceTable barrageDeviceTable){
        if(null == barrageDeviceTable){
            return null;
        }
        return barrageDeviceRepository.save(barrageDeviceTable);
    }

    public BarrageDeviceTable getBarrageDevice(String shopId,String sn){
        log.info("get from db");
        if(StringUtil.isEmpty(shopId) || StringUtil.isEmpty(sn)){
            return null;
        }
        return barrageDeviceRepository.findByShopIdAndSn(shopId,sn);
    }

}
