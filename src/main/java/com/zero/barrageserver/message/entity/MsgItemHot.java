package com.zero.barrageserver.message.entity;

import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.dbserver.model.HotCreatorTable;
import com.zero.barrageserver.dbserver.model.HotTotalTable;
import com.zero.barrageserver.dbserver.model.HotCreatorTable;
import com.zero.barrageserver.dbserver.model.HotTotalTable;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/18.
 */
@Data
public class MsgItemHot implements Serializable{
    private String type;
    private List<MsgItemHotItem> hotItemList;
    public MsgItemHot(List<HotTotalTable> hotTotalTableList, List<HotCreatorTable> hotCreatorTableList){
        if(null == hotTotalTableList){
            return;
        }
        this.type = Constant.BARRAGE_TYPE_HOTPOINTS;
        this.hotItemList = new ArrayList<>();
        for(HotTotalTable hotTotalTable:hotTotalTableList){
            MsgItemHotItem msgItemHotItem = new MsgItemHotItem(hotTotalTable);
            for(HotCreatorTable hotCreatorTable:hotCreatorTableList){
                //如果属于普通节，teamCode都为0
                if(hotCreatorTable.getTeamCode() == msgItemHotItem.getTeamCode()){
                    msgItemHotItem.getHotCreatorList().add(new MsgItemHotItemCreator(hotCreatorTable));
                }
            }
            hotItemList.add(msgItemHotItem);
        }
    }
}
