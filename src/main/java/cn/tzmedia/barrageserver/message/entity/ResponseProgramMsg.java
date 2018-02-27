package cn.tzmedia.barrageserver.message.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */
@Data
public class ResponseProgramMsg {
    private String nextTime;
    private MsgItemActivity activity;
    private MsgItemHot historyHots;
    private ResponseHotPointsMsg historyHotMsg;
    private List<ResponseCommentMsg> historyCommentList;
    private List<ResponseAwardMsg> historyAwardList;
    private List<ResponseSongMsg> historySongList;
    private List<ResponseLikeMsg> historyLikeList;
    private List<ResponseVPMsg> historyVPList;
    private List<MsgItemBarrageRestInfo> barrageRestInfoList;
    private List<MsgItemBarrageShowInfo> barrageShowInfoList;
    private List<BaseResponseMsg> historyList;

//    public ResponseProgramMsg(ShopTable shopTable,boolean isRest){
//        if(isRest){
//            setRestImage(shopTable);
//        }
//    }

    public ResponseProgramMsg(){
    }

//    private void setRestImage(ShopTable shopTable){
//        if(null == shopTable || null == shopTable.getBarrageRole() || null == shopTable.getBarrageRole().getBarrageRestImage()){
//            return;
//        }
//        barrageRestInfoList = new ArrayList<>();
//        MsgItemBarrageRestInfo msgItemBarrageRestInfo;
//        for(RestImageEntity restImageEntity:shopTable.getBarrageRole().getBarrageRestImage()){
//            msgItemBarrageRestInfo = new MsgItemBarrageRestInfo(restImageEntity);
//            barrageRestInfoList.add(msgItemBarrageRestInfo);
//        }
//    }

//    private void setShowImage(ShopTable shopTable){
//        if(null == shopTable || null == shopTable.getBarrageRole() || null == shopTable.getBarrageRole().getBarrageShowImage()){
//            return;
//        }
//        barrageShowInfoList = new ArrayList<>();
//        MsgItemBarrageShowInfo msgItemBarrageShowInfo;
//        for(ShowImageEntity restImageEntity:shopTable.getBarrageRole().getBarrageShowImage()){
//            msgItemBarrageShowInfo = new MsgItemBarrageShowInfo(restImageEntity);
//            barrageShowInfoList.add(msgItemBarrageShowInfo);
//        }
//    }

}
