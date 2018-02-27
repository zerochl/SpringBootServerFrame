package cn.tzmedia.barrageserver.message.entity;

import cn.tzmedia.barrageserver.dbserver.model.HotTotalTable;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/18.
 */
@Data
public class MsgItemHotItem implements Serializable{
    private long total;
    private int teamCode;
    private List<MsgItemHotItemCreator> hotCreatorList;

    public MsgItemHotItem(HotTotalTable hotTotalTable){
        if(null == hotTotalTable){
            return;
        }
        this.total = hotTotalTable.getTotal();
        this.teamCode = hotTotalTable.getTeamCode();
        this.hotCreatorList = new ArrayList<>();
    }
}
