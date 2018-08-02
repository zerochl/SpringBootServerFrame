package com.zero.barrageserver.dbserver.service;

import com.zero.barrageserver.message.entity.MsgItemActivity;
import com.zero.barrageserver.message.entity.MsgTeamResponseEntity;
import com.zero.barrageserver.dbserver.dao.ArtistDao;
import com.zero.barrageserver.dbserver.model.ActivityTable;
import com.zero.barrageserver.dbserver.model.ArtistTable;
import com.zero.barrageserver.dbserver.model.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/15.
 */
@Service
public class ArtistService {
    @Autowired
    private ArtistDao artistDao;

    public void clearArtistCache(){

    }

    /**
     * 降节目中与艺人相关的消息装载到节目消息中,用于演出节，休息节不需要这些
     * @param nowActivity
     * @param msgItemActivity
     * @return
     */
    public MsgItemActivity equipActivity(ActivityTable nowActivity,MsgItemActivity msgItemActivity){
        if(null == msgItemActivity || null == nowActivity){
            return msgItemActivity;
        }
        msgItemActivity.setArtistListByArtistTable(artistDao.getArtistListByIdList(nowActivity.getArtistIdList()));
        if(nowActivity.getTeamList() != null && nowActivity.getTeamList().size() > 0){
            List<MsgTeamResponseEntity> teamResponseEntityList = new ArrayList<>();
            for(TeamEntity teamEntity:nowActivity.getTeamList()){
                List<ArtistTable> artistTableList = artistDao.getArtistListByIdList(teamEntity.getArtistIdList());
                MsgTeamResponseEntity msgTeamResponseEntity = new MsgTeamResponseEntity(artistTableList,teamEntity);
                teamResponseEntityList.add(msgTeamResponseEntity);
            }
            msgItemActivity.setTeamList(teamResponseEntityList);
        }
        return msgItemActivity;
    }

    /**
     * 获取节目中所有艺人信息
     * @param activityTable
     * @return
     */
    public List<ArtistTable> getAllArtistFromActivity(ActivityTable activityTable){
        if(null == activityTable){
            return null;
        }
        List<ArtistTable> artistTableList = new ArrayList<>();
        List<ArtistTable> normalArtistList = artistDao.getArtistListByIdList(activityTable.getArtistIdList());
        if(null != normalArtistList){
            artistTableList.addAll(normalArtistList);
        }
        if(activityTable.getTeamList() != null && activityTable.getTeamList().size() > 0){
            for(TeamEntity teamEntity:activityTable.getTeamList()){
                List<ArtistTable> pkArtistList = artistDao.getArtistListByIdList(teamEntity.getArtistIdList());
                if(null != pkArtistList){
                    artistTableList.addAll(pkArtistList);
                }
            }
        }
        return artistTableList;
    }
}
