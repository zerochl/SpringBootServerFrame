package com.zero.barrageserver.dbserver.service;

import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.common.constant.ErrorConstant;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.servermsg.*;
import com.zero.barrageserver.common.utils.StringUtil;
import com.zero.barrageserver.message.entity.*;
import com.zero.barrageserver.dbserver.dao.ShowHistoryDao;
import com.zero.barrageserver.dbserver.model.ActivityTable;
import com.zero.barrageserver.dbserver.model.ShowHistoryTable;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.servermsg.BaseRequestRootMsgEntity;
import com.zero.barrageserver.common.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/18.
 */
@Service
@Log4j2
@AllArgsConstructor
public class ShowHistoryService {
    private ShowHistoryDao showHistoryDao;
    private NormalMsgService normalMsgService;
    private ArtistService artistService;
    private ActivityService activityService;
    private HotService hotService;

//    public void equipHistoryMsg(ResponseProgramMsg programMsg,String activityId){
//        if(null == programMsg || StringUtil.isEmpty(activityId)){
//            log.error("in equipHistoryMsg,param is null.");
//            return;
//        }
//        List<ShowHistoryTable> historyTableList = showHistoryDao.getAllHistoryByActivityId(activityId);
//        if(null == historyTableList || 0 == historyTableList.size()){
//            log.info("in equipHistoryMsg,no history.activityId:" + activityId);
//            return;
//        }
//        ArrayList<ResponseAwardMsg> historyAwardList = new ArrayList<>();
//        ArrayList<ResponseCommentMsg> historyCommentList = new ArrayList<>();
//        ArrayList<ResponseSongMsg> historySongList = new ArrayList<>();
//        ArrayList<ResponseLikeMsg> historyLikeList = new ArrayList<>();
//        ArrayList<ResponseVPMsg> historyVPList = new ArrayList<>();
//        for(ShowHistoryTable showHistoryTable:historyTableList){
//            log.info("value:" + showHistoryTable.getValue());
//            BaseRequestRootMsgEntity requestRootMsgEntity = new BaseRequestRootMsgEntity(showHistoryTable);
//            List<BaseResponseMsg> responseMsgList = normalMsgService.convertFromServerToClient(requestRootMsgEntity);
//            switch (showHistoryTable.getMsgType()){
//                case Constant.BARRAGE_TYPE_AWARD:
//                    addToList(responseMsgList,historyAwardList);
//                    break;
//                case Constant.BARRAGE_TYPE_COMMENT:
//                    addToList(responseMsgList,historyCommentList);
//                    break;
//                case Constant.BARRAGE_TYPE_LIKE:
//                    addToList(responseMsgList,historyLikeList);
//                    break;
//                case Constant.BARRAGE_TYPE_SONG:
//                    addToList(responseMsgList,historySongList);
//                    break;
//                case Constant.BARRAGE_TYPE_VP:
//                    addToList(responseMsgList,historyVPList);
//                    break;
//            }
//        }
//        programMsg.setHistoryCommentList(historyCommentList);
//        programMsg.setHistoryAwardList(historyAwardList);
//        programMsg.setHistoryLikeList(historyLikeList);
//        programMsg.setHistorySongList(historySongList);
//        programMsg.setHistoryVPList(historyVPList);
//    }

    public BaseResponseEntity<ResponseProgramMsg> getPadHistoryMsgForRequest(String shopId){
        if(StringUtil.isEmpty(shopId)){
            return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
        ResponseProgramMsg programMsg = new ResponseProgramMsg();
        ActivityTable nowActivityTable = activityService.getNowActivity(shopId);
        if(null == nowActivityTable){
            //休息节，不返回节目数据
        }else{
            //演出节
            //返回历史数据
            List<BaseResponseMsg> historyMsg = getPadGroupHistoryMsg(nowActivityTable.getId());
            programMsg.setHistoryList(historyMsg);
            //节目数据
            MsgItemActivity msgItemActivity = new MsgItemActivity(nowActivityTable);
            msgItemActivity = artistService.equipActivity(nowActivityTable,msgItemActivity);
            programMsg.setActivity(msgItemActivity);
            //热度
            ResponseHotPointsMsg hotPointsMsg = hotService.getHotMessage(nowActivityTable.getId());
            programMsg.setHistoryHotMsg(hotPointsMsg);
        }
        return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OK, programMsg);
    }
    public List<BaseResponseMsg> getPadGroupHistoryMsg(String activityId){
        if(StringUtil.isEmpty(activityId)){
            return null;
        }
        return getGroupHistoryWithType(activityId,new ArrayList<String>(){{
            add(Constant.BARRAGE_TYPE_VP);
            add(Constant.BARRAGE_TYPE_AWARD);
            add(Constant.BARRAGE_TYPE_SONG);
        }});
    }
    /**
     * 给pad提供历史数据接口
     * 因为调用频繁，所以就不复用getHistoryMsg方法了
     * @param activityId
     * @return
     */
//    public List<BaseResponseMsg> getPadGroupHistoryMsg(String activityId){
//        if(StringUtil.isEmpty(activityId)){
//            return null;
//        }
//        long startTime = System.nanoTime();
//        List<ShowHistoryTable> historyTableList = showHistoryDao.getHistoryByType(activityId,new ArrayList<String>(){{
//            add(Constant.BARRAGE_TYPE_VP);
//            add(Constant.BARRAGE_TYPE_AWARD);
//            add(Constant.BARRAGE_TYPE_SONG);
//        }});
//        log.info("end db time:" + (System.nanoTime() - startTime) / 1000000);
//        if(null == historyTableList || 0 == historyTableList.size()){
//            log.error("in equipHistoryMsg,no history.activityId:" + activityId);
//            return null;
//        }
//        ArrayList<BaseResponseMsg> historyMsgList = new ArrayList<>();
//        for(ShowHistoryTable showHistoryTable:historyTableList){
////            log.info("value:" + showHistoryTable.getValue());
//            BaseRequestRootMsgEntity requestRootMsgEntity = new BaseRequestRootMsgEntity(showHistoryTable);
//            List<BaseResponseMsg> responseMsgList = normalMsgService.convertFromServerToClient(requestRootMsgEntity);
//            switch (showHistoryTable.getMsgType()){
//                case Constant.BARRAGE_TYPE_VP:
//                    //虚拟礼物需要做合并
//                    handleSameVP(responseMsgList,historyMsgList);
//                    break;
//                case Constant.BARRAGE_TYPE_AWARD:
//                    handleSameAward(responseMsgList,historyMsgList);
//                    break;
//                case Constant.BARRAGE_TYPE_SONG:
//                    if(null != responseMsgList){
//                        historyMsgList.addAll(responseMsgList);
//                    }
//                    break;
//            }
//        }
//        log.info("get history time:" + (System.nanoTime() - startTime) / 1000000);
//        return historyMsgList;
//    }

    /**
     * 使用聚合查询，查询所有有用的历史数据
     * @param activityId
     * @return
     */
    public List<BaseResponseMsg> getGroupHistoryMsg(String activityId){
        if(StringUtil.isEmpty(activityId)){
            return null;
        }
        return getGroupHistoryWithType(activityId,new ArrayList<String>(){{
            add(Constant.BARRAGE_TYPE_VP);
            add(Constant.BARRAGE_TYPE_AWARD);
            add(Constant.BARRAGE_TYPE_SONG);
            add(Constant.BARRAGE_TYPE_LIKE);
            add(Constant.BARRAGE_TYPE_COMMENT);
            add(Constant.BARRAGE_TYPE_IMAGE);
            add(Constant.BARRAGE_TYPE_MOVIE);
//            add(Constant.BARRAGE_TYPE_SILKBAG);
        }});
    }

    /**
     * 根据消息类型获取聚合之后的消息
     * @param activityId
     * @param msgType
     * @return
     */
    private List<BaseResponseMsg> getGroupHistoryWithType(String activityId,ArrayList<String> msgType){
        if(StringUtil.isEmpty(activityId) || null == msgType || msgType.size() == 0){
            return null;
        }
        List<ShowHistoryTable> historyTableList = showHistoryDao.getGroupHistoryByType(activityId,msgType);
        if(null == historyTableList || 0 == historyTableList.size()){
            log.error("in equipHistoryMsg,no history.activityId:" + activityId);
            return null;
        }
        ArrayList<BaseResponseMsg> historyMsgList = new ArrayList<>();
        for(ShowHistoryTable showHistoryTable:historyTableList){
//            log.info("value:" + showHistoryTable.getValue());
            //包装成服务端发送过来的IM消息的格式，保证可以复用convert方法
            BaseRequestRootMsgEntity requestRootMsgEntity = new BaseRequestRootMsgEntity(showHistoryTable);
            //执行convert，此处需要注意，所有字段都是从数据库取来，如果服务端消息字段做了映射此处可能会有问题
            List<BaseResponseMsg> responseMsgList = normalMsgService.convertFromServerToClient(requestRootMsgEntity);
            if(null != responseMsgList){
                //把聚合得到的count，price设置到value中
                int index = 0;
                for(BaseResponseMsg responseMsg:responseMsgList){
                    switch (showHistoryTable.getMsgType()){
                        case Constant.BARRAGE_TYPE_VP:
                            break;
                        case Constant.BARRAGE_TYPE_AWARD:
                            //只有赞赏需要知道总金额，直接复用price字段即可
                            ((ResponseAwardMsg)responseMsg).setPrice(showHistoryTable.getTotalPrice());
                            break;
                        case Constant.BARRAGE_TYPE_SONG:
                            break;
                    }
                    responseMsg.setCount(showHistoryTable.getTotalCount());
                    //如果是一条记录产生的多条休息，ID是一样的，此处使用index进行区分
                    responseMsg.setId(showHistoryTable.getId() + (0 == index ? "" : index));
                    index++;
                }
                historyMsgList.addAll(responseMsgList);
            }
        }
        return historyMsgList;
    }

//    public List<BaseResponseMsg> getGroupHistoryMsg(String activityId){
//        if(StringUtil.isEmpty(activityId)){
//            return null;
//        }
//        List<ShowHistoryTable> historyTableList = showHistoryDao.getAllHistoryByActivityId(activityId);
//        if(null == historyTableList || 0 == historyTableList.size()){
//            log.error("in equipHistoryMsg,no history.activityId:" + activityId);
//            return null;
//        }
//        ArrayList<BaseResponseMsg> historyMsgList = new ArrayList<>();
//        for(ShowHistoryTable showHistoryTable:historyTableList){
////            log.info("value:" + showHistoryTable.getValue());
//            BaseRequestRootMsgEntity requestRootMsgEntity = new BaseRequestRootMsgEntity(showHistoryTable);
//            List<BaseResponseMsg> responseMsgList = normalMsgService.convertFromServerToClient(requestRootMsgEntity);
//            switch (showHistoryTable.getMsgType()){
//                case Constant.BARRAGE_TYPE_VP:
//                    //虚拟礼物需要做合并
//                    handleSameVP(responseMsgList,historyMsgList);
//                    break;
//                case Constant.BARRAGE_TYPE_AWARD:
//                    handleSameAward(responseMsgList,historyMsgList);
//                    break;
//                case Constant.BARRAGE_TYPE_LIKE:
//                    handleSameLike(responseMsgList,historyMsgList);
//                    break;
//                default:
//                    if(null != responseMsgList){
//                        historyMsgList.addAll(responseMsgList);
//                    }
//                    break;
//            }
//        }
//        return historyMsgList;
//    }

    private void handleSameVP(List<BaseResponseMsg> responseMsgList,ArrayList<BaseResponseMsg> historyMsgList){
        for(BaseResponseMsg responseMsg:responseMsgList){
            boolean hasContains = false;
            for(BaseResponseMsg historyMsg:historyMsgList){
                if(historyMsg instanceof ResponseVPMsg
                        && ((ResponseVPMsg)historyMsg).getVpId().equals(((ResponseVPMsg)responseMsg).getVpId())
                        && historyMsg.getUsertoken().equals(responseMsg.getUsertoken())){
                    historyMsg.setCount(responseMsg.getCount() > historyMsg.getCount() ? responseMsg.getCount() : historyMsg.getCount());
                    hasContains = true;
                    break;
                }
            }
            if(!hasContains){
                historyMsgList.add(responseMsg);
            }
        }
    }

    private void handleSameAward(List<BaseResponseMsg> responseMsgList,ArrayList<BaseResponseMsg> historyMsgList){
        for(BaseResponseMsg responseMsg:responseMsgList){
            boolean hasContains = false;
            for(BaseResponseMsg historyMsg:historyMsgList){
                if(historyMsg instanceof ResponseAwardMsg
                        && historyMsg.getUsertoken().equals(responseMsg.getUsertoken())){
                    //此处不需要处理金额，只需要知道有人送出了赞赏，如果需要知道金额，需要修改此处
                    hasContains = true;
                    ((ResponseAwardMsg) historyMsg).setPrice(((ResponseAwardMsg) historyMsg).getPrice() + ((ResponseAwardMsg) responseMsg).getPrice());
                    break;
                }
            }
            if(!hasContains){
                historyMsgList.add(responseMsg);
            }
        }
    }

    private void handleSameLike(List<BaseResponseMsg> responseMsgList,ArrayList<BaseResponseMsg> historyMsgList){
        for(BaseResponseMsg responseMsg:responseMsgList){
            boolean hasContains = false;
            for(BaseResponseMsg historyMsg:historyMsgList){
                if(historyMsg instanceof ResponseLikeMsg
                        && historyMsg.getUsertoken().equals(responseMsg.getUsertoken())){
                    hasContains = true;
                    break;
                }
            }
            if(!hasContains){
                historyMsgList.add(responseMsg);
            }
        }
    }

    private <T extends BaseResponseMsg>void addToList(List<BaseResponseMsg> responseMsgList,List<T> container){
        if(null == responseMsgList || responseMsgList.size() == 0 || null == container){
            return;
        }
        for(BaseResponseMsg baseResponseMsg:responseMsgList){
            container.add((T)baseResponseMsg);
        }
    }
}
