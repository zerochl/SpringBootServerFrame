package com.zero.barrageserver.message.entity;

import com.zero.barrageserver.dbserver.model.ActivityTable;
import com.zero.barrageserver.dbserver.model.ArtistTable;
import com.zero.barrageserver.dbserver.model.ActivityTable;
import com.zero.barrageserver.dbserver.model.ArtistTable;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */
@Data
public class MsgItemActivity implements Serializable{
    private String id;
    private List<MsgItemArtist> artistList;
    private List<MsgItemArtist> band;
    private String shopId;
    private String shopName;
    private int type;
    private String pkType;
    private List<MsgTeamResponseEntity> teamList;

    public MsgItemActivity(ActivityTable activityTable){
        if(null == activityTable){
            return;
        }
        this.id = activityTable.getId();
        this.shopId = activityTable.getShopId();
        this.type = activityTable.getType();
        this.pkType = activityTable.getPkType();
    }

    public void setArtistListByArtistTable(List<ArtistTable> artistTableList){
        if(null == artistTableList || 0 == artistTableList.size()){
            return;
        }
        artistList = new ArrayList<>();
        for(ArtistTable artistTable:artistTableList){
            MsgItemArtist msgItemArtist = new MsgItemArtist(artistTable);
            artistList.add(msgItemArtist);
        }
    }

}
