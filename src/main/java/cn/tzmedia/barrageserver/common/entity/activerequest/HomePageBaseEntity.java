package cn.tzmedia.barrageserver.common.entity.activerequest;

import lombok.Data;

/**
 * Created by Administrator on 2017/11/30.
 */
@Data
public class HomePageBaseEntity {
    private int homePageItemType;
    private String value;
    private String homePageItemId;
    private boolean isFirstShopOrActivity = false;
}
