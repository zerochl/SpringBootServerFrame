package com.zero.barrageserver.message.entity;

import com.zero.barrageserver.dbserver.model.ArtistTable;
import com.zero.barrageserver.dbserver.model.TeamEntity;
import com.zero.barrageserver.dbserver.model.ArtistTable;
import com.zero.barrageserver.dbserver.model.TeamEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */
@Data
public class MsgTeamResponseEntity {
    private List<MsgItemArtist> artistList;
    private int teamCode;
    private String teamName;
    public MsgTeamResponseEntity(List<ArtistTable> artistTableList, TeamEntity teamEntity){
        if(null == teamEntity || null == artistTableList || artistTableList.size() == 0){
            return;
        }
        artistList = new ArrayList<>();
        for(ArtistTable artistTable:artistTableList){
            MsgItemArtist msgItemArtist = new MsgItemArtist(artistTable);
            artistList.add(msgItemArtist);
        }
        this.teamCode = teamEntity.getTeamCode();
        this.teamName = teamEntity.getTeamName();
    }
}
