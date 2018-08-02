package com.zero.barrageserver.message.entity;

import com.zero.barrageserver.common.constant.Constant;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zero大神 on 2017/12/19.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseMultiHitMsg extends BaseResponseMsg{
    private int comboVPCount;
    private String comboImage;

    public ResponseMultiHitMsg(ResponseVPMsg responseVPMsg){
        this.activityId = responseVPMsg.getActivityId();
        this.comboVPCount = responseVPMsg.getComboVPCount();
        this.comboImage = responseVPMsg.getVpImage();

        this.setType(Constant.BARRAGE_TYPE_MULTI_HIT);
        setUser(responseVPMsg);
    }
}
