package com.zero.barrageserver.dbserver.service;

import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.common.manager.ProgramCountDownSendManager;
import com.zero.barrageserver.common.utils.BaseUtils;
import com.zero.barrageserver.common.utils.DateUtils;
import com.zero.barrageserver.common.utils.StringUtil;
import com.zero.barrageserver.message.MessageProducer;
import com.zero.barrageserver.message.entity.*;
import com.zero.barrageserver.dbserver.model.*;
import com.alibaba.fastjson.JSONObject;
import com.zero.barrageserver.common.utils.BaseUtils;
import com.zero.barrageserver.common.utils.DateUtils;
import com.zero.barrageserver.common.utils.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 节目信息
 * Created by zero大神 on 2017/12/13.
 */
@Service
@Log4j2
public class ProgramService {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private ShowHistoryService showHistoryService;
    @Autowired
    private MessageProducer messageProducer;
    @Autowired
    private ProgramCountDownSendManager programCountDownSendManager;
    @Autowired
    private HotService hotService;
    private HashMap<String,String> currentShowMap = new HashMap<>();

    public void sendProgramMsg(ActivityTable nowActivity,ActivityTable nextActivity,ShopTable shopTable,boolean needPulHistory){
//        ResponseProgramMsg programResponse = getProgramResponse(nowActivity,shopTable,needPulHistory);
        ResponseProgramMsg programResponse = getProgramResponse(nowActivity,nextActivity,shopTable,shouldNeedPullHistory(needPulHistory,shopTable.getId(),nowActivity));
        //因为腾讯IM大小限制，历史消息不能更随节目消息一起发送
        List<BaseResponseMsg> historyList = programResponse.getHistoryList();
        //因为腾讯IM大小限制，热度消息不能更随节目消息一起发送
        ResponseHotPointsMsg historyHotsMsg = programResponse.getHistoryHotMsg();
        //去除艺人图片信息，防止消息长度过长
        removeRedundancyMsg(programResponse);
        //IM 消息发送
        messageProducer.receiveMessage(new BaseResponseRootMsg(shopTable.getId(),null != nowActivity ? nowActivity.getId() : "",0, Constant.BARRAGE_TYPE_PROGRAME, JSONObject.toJSONString(programResponse),true));
        //发送历史消息,等待5秒钟，防止节目消息还没有送达
        messageProducer.receiveMessage(historyHotsMsg,shopTable.getId(),true,0);
        //发送历史消息,等待5秒钟，防止节目消息还没有送达
        messageProducer.receiveMessage(historyList,shopTable.getId(),true,0);
        //开始或者更新倒计时
        programCountDownSendManager.onSendProgramMsg(shopTable.getId(),nowActivity,nextActivity);
    }

    /**
     * 为节目信息移除冗余数据
     * @param programResponse
     */
    private void removeRedundancyMsg(ResponseProgramMsg programResponse){
        if(null == programResponse){
            return;
        }
        //因为腾讯IM大小限制，历史消息不能更随节目消息一起发送
        programResponse.setHistoryList(null);
        //因为腾讯IM大小限制，热度消息不能更随节目消息一起发送
        programResponse.setHistoryHotMsg(null);
        //艺人所属的图片信息没有存在必要了，可以删除
        if(null != programResponse.getActivity() && null != programResponse.getActivity().getArtistList()){
            for(MsgItemArtist artistEntity:programResponse.getActivity().getArtistList()){
                removeArtistRedundancyMsg(artistEntity);
            }
        }
        if(null != programResponse.getActivity() && null != programResponse.getActivity().getTeamList()){
            for(MsgTeamResponseEntity team:programResponse.getActivity().getTeamList()){
                for(MsgItemArtist artistEntity:team.getArtistList()){
                    removeArtistRedundancyMsg(artistEntity);
                }
            }
        }
    }

    private void removeArtistRedundancyMsg(MsgItemArtist artist){
        if(null == artist){
            return;
        }
        artist.setShowBarrageImage(null);
        artist.setExperience(null);
        artist.setSign(null);
        artist.setImageList(null);
    }

    /**
     * 如果参数主动要求需要拉取历史消息则立即返回，否则按照既定的逻辑，当前正在播放的节目仅仅修改时间是不会重新拉取历史消息的
     * @param needPullHistory
     * @return
     */
    private boolean shouldNeedPullHistory(boolean needPullHistory,String shopId,ActivityTable nowActivity){
        if(needPullHistory){
            currentShowMap.put(shopId,null == nowActivity ? "" : nowActivity.getId());
            return true;
        }
        if(nowActivity == null){
            //当前没有演出节，搞个屁
            //清除记录
            currentShowMap.remove(shopId);
            return false;
        }
        String recordActivityId = currentShowMap.get(shopId);
        if(nowActivity.getId().equals(recordActivityId)){
            //记录的与当前又要显示的是同一节，则不需要播放
            return false;
        }else{
            //不一样，需要重新拉取，此时需要重置记录
            currentShowMap.put(shopId,nowActivity.getId());
            return true;
        }
    }

    private ResponseProgramMsg getProgramResponse(ActivityTable nowActivity,ActivityTable nextActivity,ShopTable shopTable,boolean needPullHistory){
        ResponseProgramMsg programResponse = null;
        if(nowActivity == null){
            //休息节
            programResponse = new ResponseProgramMsg();
            setRestImage(shopTable,programResponse,nextActivity);
            //检查并获取需要立即显示的项
            programResponse.setBarrageRestInfoList(checkAndGetImmediateRestInfo(programResponse.getBarrageRestInfoList()));
            //超出最大条则随机获取
            programResponse.setBarrageRestInfoList(BaseUtils.getRandomMaxList(programResponse.getBarrageRestInfoList(),Constant.MAX_ACTIVITY_REST_SIZE,true));
        }else{
            //演出节
            programResponse = new ResponseProgramMsg();
            //节目信息
            MsgItemActivity msgItemActivity = new MsgItemActivity(nowActivity);
            msgItemActivity = artistService.equipActivity(nowActivity,msgItemActivity);
            programResponse.setActivity(msgItemActivity);
            //演出信息
            programResponse.setBarrageShowInfoList(getShowInfo(shopTable,programResponse.getActivity()));
            //热度
//            MsgItemHot msgItemHot = hotService.getHotListForShow(nowActivity.getId());
//            programResponse.setHistoryHots(msgItemHot);
            ResponseHotPointsMsg hotPointsMsg = hotService.getHotMessage(nowActivity.getId());
            programResponse.setHistoryHotMsg(hotPointsMsg);
            //如果需要拉取history，则处理
            if(needPullHistory){
                pullHistory(nowActivity,programResponse);
            }
        }
        //设置nexttime
        if(null != nextActivity){
            programResponse.setNextTime(DateUtils.getFormatDate(nextActivity.getStartTime()));
        }
        return programResponse;
    }

    private void pullHistory(ActivityTable nowActivity,ResponseProgramMsg programResponse){
        //历史消息
        //赞赏、评论、礼物、点赞、点歌
//            showHistoryService.equipHistoryMsg(programResponse,nowActivity.getId());
        //此类消息需要单独发送，否则消息体过大
        programResponse.setHistoryList(showHistoryService.getGroupHistoryMsg(nowActivity.getId()));

        //MultiHit
    }

    /**
     * 设置休息节循环
     * @param shopTable
     * @param programResponse
     * @param nextActivity
     */
    private void setRestImage(ShopTable shopTable,ResponseProgramMsg programResponse,ActivityTable nextActivity){
        if(null == shopTable || null == shopTable.getBarrageRole() || null == shopTable.getBarrageRole().getBarrageRestImage() || null == programResponse){
            return;
        }
        List<MsgItemBarrageRestInfo> barrageRestInfoList = new ArrayList<>();
        programResponse.setBarrageRestInfoList(barrageRestInfoList);
        MsgItemBarrageRestInfo msgItemBarrageRestInfo;
        for(RestImageEntity restImageEntity:shopTable.getBarrageRole().getBarrageRestImage()){
            if(restImageEntity.getType().equals(Constant.SHOW_AND_REST_TYPE_NEXT_ARTIST)){
                //如果是下节艺人，需要特殊处理，如果下节艺人多则会产生多条记录
                if(null == nextActivity || null == nextActivity.getArtistIdList() || nextActivity.getArtistIdList().size() == 0){
                    continue;
                }
                List<ArtistTable> artistTableList = artistService.getAllArtistFromActivity(nextActivity);
                //循环添加下节所有艺人消息
                for(ArtistTable artistTable:artistTableList){
                    msgItemBarrageRestInfo = new MsgItemBarrageRestInfo(restImageEntity,artistTable);
                    barrageRestInfoList.add(msgItemBarrageRestInfo);
                }
                continue;
            }
            msgItemBarrageRestInfo = new MsgItemBarrageRestInfo(restImageEntity);
            barrageRestInfoList.add(msgItemBarrageRestInfo);
        }
    }

    /**
     * 直播，当节演出艺人图片信息不不止存在数据库中，需要自己构造
     * @param shopTable
     * @param msgItemActivity
     * @return
     */
    private List<MsgItemBarrageShowInfo> getShowInfo(ShopTable shopTable,MsgItemActivity msgItemActivity){
        if(null == shopTable){
            return null;
        }
        List<MsgItemBarrageShowInfo> showInfoList = new ArrayList<>();
        //直播
        if(null != shopTable.getBarrageRole() && shopTable.getBarrageRole().isLiveEnable() && !StringUtil.isEmpty(shopTable.getBarrageRole().getLiveUrl())){
            MsgItemBarrageShowInfo liveShowInfo = new MsgItemBarrageShowInfo(Constant.SHOW_AND_REST_TYPE_LIVE,shopTable.getBarrageRole().getLiveUrl());
            showInfoList.add(liveShowInfo);
            return showInfoList;
        }
        for(ShowImageEntity showImageEntity:shopTable.getBarrageRole().getBarrageShowImage()){
            if(showImageEntity.getType().equals(Constant.SHOW_AND_REST_TYPE_ARTIST_IMAGE)){
                //当节艺人图片
                showInfoList.addAll(getImageListFromActivity(msgItemActivity,showImageEntity.getInterval(),showImageEntity.isImmediateShow()));
            }else{
                //视频与自定义
                MsgItemBarrageShowInfo showInfo = new MsgItemBarrageShowInfo(showImageEntity);
                showInfoList.add(showInfo);
            }
        }

        //检查并获取需要立即显示的项
        showInfoList = checkAndGetImmediateShowInfo(showInfoList);
        //超出最大条则随机获取
        showInfoList = BaseUtils.getRandomMaxList(showInfoList,Constant.MAX_ACTIVITY_SHOW_SIZE,true);
        return showInfoList;
    }

    /**
     * 检查并获取需要立即显示的项
     * @return
     */
    private List<MsgItemBarrageShowInfo> checkAndGetImmediateShowInfo(List<MsgItemBarrageShowInfo> showInfoList){
        if(null == showInfoList){
            return showInfoList;
        }
        ArrayList<MsgItemBarrageShowInfo> immediateShowList = new ArrayList<>();
        for(MsgItemBarrageShowInfo showInfo:showInfoList){
            if(showInfo.isImmediateShow()){
                immediateShowList.add(showInfo);
            }
        }
        if(immediateShowList.size() == 0){
            return showInfoList;
        }
        return immediateShowList;
    }

    /**
     * 检查并获取需要立即显示的项
     * @return
     */
    private List<MsgItemBarrageRestInfo> checkAndGetImmediateRestInfo(List<MsgItemBarrageRestInfo> restInfoList){
        if(null == restInfoList){
            return restInfoList;
        }
        ArrayList<MsgItemBarrageRestInfo> immediateRestList = new ArrayList<>();
        for(MsgItemBarrageRestInfo restInfo:restInfoList){
            if(restInfo.isImmediateShow()){
                immediateRestList.add(restInfo);
            }
        }
        if(immediateRestList.size() == 0){
            return restInfoList;
        }
        return immediateRestList;
    }

    /**
     * 获取当节演出艺人图片
     * @param msgItemActivity
     * @param interval
     * @return
     */
    private List<MsgItemBarrageShowInfo> getImageListFromActivity(MsgItemActivity msgItemActivity,int interval,boolean immediateShow){
        ArrayList<MsgItemBarrageShowInfo> showInfoList = new ArrayList<>();
        if(null == msgItemActivity){
            return showInfoList;
        }
        showInfoList.addAll(getImageListFromArtistList(msgItemActivity.getArtistList(),interval,Constant.MAX_ARTIST_SHOW_IMAGE_SIZE,immediateShow));
        if(null != msgItemActivity.getTeamList()){
            for(MsgTeamResponseEntity teamResponseEntity:msgItemActivity.getTeamList()){
                showInfoList.addAll(getImageListFromArtistList(teamResponseEntity.getArtistList(),interval,Constant.MAX_ARTIST_SHOW_IMAGE_SIZE,immediateShow));
            }
        }
        return showInfoList;
    }

    private List<MsgItemBarrageShowInfo> getImageListFromArtistList(List<MsgItemArtist> artistList,int interval,int maxCount,boolean immediateShow){
        ArrayList<MsgItemBarrageShowInfo> showInfoList = new ArrayList<>();
        if(null == artistList || artistList.size() == 0){
            return showInfoList;
        }
        for(MsgItemArtist msgItemArtist:artistList){
            if(null != msgItemArtist.getShowBarrageImage()){
                //最外层做了最大数目限制，所以此处就不需要了
//                ArrayList<Integer> randomList = getRandomIndex(msgItemArtist.getShowBarrageImage().size(),maxCount);
//                for(Integer randomIndex:randomList){
//                    MsgItemBarrageShowInfo imageShowInfo = new MsgItemBarrageShowInfo(Constant.SHOW_AND_REST_TYPE_CUSTOM_IMAGE,
//                            msgItemArtist.getShowBarrageImage().get(randomIndex),interval);
//                    showInfoList.add(imageShowInfo);
//                }
                for(String artistImage:msgItemArtist.getShowBarrageImage()){
                    MsgItemBarrageShowInfo imageShowInfo = new MsgItemBarrageShowInfo(Constant.SHOW_AND_REST_TYPE_CUSTOM_IMAGE,artistImage,interval,immediateShow);
                    showInfoList.add(imageShowInfo);
                }
            }
        }
        return showInfoList;
    }

//    private Random random = new Random();
//
//    private ArrayList<Integer> getRandomIndex(int size,int count){
//        ArrayList<Integer> randomList = new ArrayList<>();
//        if(0 == size){
//            return randomList;
//        }
//        int randomIndex = random.nextInt(size);
//        for(int i = 0;i < count;i++){
//            randomList.add((randomIndex + i) % size);
//        }
//        return randomList;
//    }

}
