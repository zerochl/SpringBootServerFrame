package cn.tzmedia.barrageserver.dbserver.model;

import cn.tzmedia.barrageserver.common.entity.apirequest.ApiReqBarrageDevice;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by zero大神 on 2017/12/19.
 */
@Document(collection = "barrage.device")
@Data
public class BarrageDeviceTable implements Serializable{
    @Field("_id")
    @Id
    private String id;
    private String sn;
    private String ip;
    private String version;
    @Field("shop_id")
    private String shopId;

    public BarrageDeviceTable(){}

    public BarrageDeviceTable(ApiReqBarrageDevice barrageDevice){
        reSetData(barrageDevice);
    }

    public void reSetData(ApiReqBarrageDevice barrageDevice){
        this.sn = barrageDevice.getSn();
        this.ip = barrageDevice.getIp();
        this.version = barrageDevice.getVersion();
        this.shopId = barrageDevice.getShopId();
    }
}
