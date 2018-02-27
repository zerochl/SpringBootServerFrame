package cn.tzmedia.barrageserver.common.entity.response;

import cn.tzmedia.barrageserver.dbserver.model.RestImageEntity;
import cn.tzmedia.barrageserver.dbserver.model.ShopTable;
import cn.tzmedia.barrageserver.dbserver.model.ShowImageEntity;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */
@Data
public class ShopResponseEntity {
    private String id;
    private String name;
    private String city;
    private double maskAlpha;
    //兼容客户端字段名写错问题
    private double masklpha;
    private List<ShowImageEntity> showImageList;
    private List<RestImageEntity> restImageList;
    public ShopResponseEntity(ShopTable shopTable){
        if(null == shopTable){
            return;
        }
        this.id = shopTable.getId();
        if(null != shopTable.getBarrageRole()){
            this.maskAlpha = shopTable.getBarrageRole().getBarrageAlpha();
            this.masklpha = shopTable.getBarrageRole().getBarrageAlpha();
            this.restImageList = shopTable.getBarrageRole().getBarrageRestImage();
            this.showImageList = shopTable.getBarrageRole().getBarrageShowImage();
        }
        this.name = shopTable.getName();
        this.city = shopTable.getCity();
    }
}
