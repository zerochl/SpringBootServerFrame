package cn.tzmedia.barrageserver.common.constant;

/**
 * Created by Administrator on 2017/12/1.
 */
public class Constant {
    /**腾讯IM中的text消息类型*/
    public static final String TEN_MSG_TYPE_TEXT = "TIMTextElem";
//    /**群组ID前缀，与app区分开来*/
//    public static final String GROUP_PREFIX = "Bg" + ApiConstant.RUN_TYPE + "";
    /**群组类型*/
    public static final String GROUP_TYPE_BCHAT_ROOM = "ChatRoom";//AVChatRoom
    /**加群方式*/
    public static final String GROUP_JOIN_OPTION_FREE = "FreeAccess";

    /***********************************长连接发送到客户端的弹幕消息类型**************************************************/
    public static final String BARRAGE_TYPE_COMMENT = "COMMENT";
    public static final String BARRAGE_TYPE_LIKE = "LIKE";
    public static final String BARRAGE_TYPE_SONG = "SONG";
    public static final String BARRAGE_TYPE_AWARD = "AWARD";
    public static final String BARRAGE_TYPE_VP = "VP";
    public static final String BARRAGE_TYPE_PK_HOT = "HOT_PK";
    public static final String BARRAGE_TYPE_NORMAL_HOT = "HOTPOINT";
    public static final String BARRAGE_TYPE_SILKBAG = "USEKIT";
    public static final String BARRAGE_TYPE_IMAGE = "IMAGE";
    public static final String BARRAGE_TYPE_MOVIE = "VIDEO";
    public static final String BARRAGE_TYPE_IMAGE_WALL = "IMAGE_WALL";
    public static final String BARRAGE_TYPE_CONSUME_LIST = "CONSUME_LIST";//土豪榜

    public static final String BARRAGE_TYPE_HOTPOINTS = "HOTPOINTS";
    public static final String BARRAGE_TYPE_MULTI_HIT = "MULTI_HIT";

    public static final String BARRAGE_TYPE_PROGRAME = "PROGRAM";

    /***********************************数据库写入队列类型************************************************/
    public static final String DBWRITE_TYPE_BARRAGE = "BARRAGE";

    /***********************************数据库队列操作方式**************************************************************/
    public static final String DBWRITE_ACTION_INSERT = "insert";
    public static final String DBWRITE_ACTION_UPDATE = "update";
    public static final String DBWRITE_ACTION_DELETE = "delete";

    /**********************************演出与休息节消息类型*********************************************************/
    public static final String SHOW_AND_REST_TYPE_LIVE = "liveBroadcast";
    public static final String SHOW_AND_REST_TYPE_ARTIST = "customArtist";
    public static final String SHOW_AND_REST_TYPE_ARTIST_IMAGE = "artistImage";
    public static final String SHOW_AND_REST_TYPE_CUSTOM_IMAGE = "customImage";
    public static final String SHOW_AND_REST_TYPE_REMOTE_MOVIE = "remoteVideo";
    public static final String SHOW_AND_REST_TYPE_NEXT_ARTIST = "nextArtist";
    /**********************************单线程池类型*************************************************************/
    public static final String SINGLE_THREAD_EXECUTOR_TYPE_MESSAGE = "MessageProducer";
    public static final String SINGLE_THREAD_EXECUTOR_TYPE_DBWRITE = "DBWriteProducer";
    public static final String SINGLE_THREAD_EXECUTOR_TYPE_LOOPMSG = "LoopMsg";
    public static final String SINGLE_THREAD_EXECUTOR_TYPE_HOTMSG = "HotMsg";
    /**********************************线程池等级****************************************************************/
    public static final int QUEUE_LEVEL_ZERO = 0;
    public static final int QUEUE_LEVEL_ONE = 1;
    public static final int QUEUE_LEVEL_TWO = 2;
    /**********************************群组用户状态，在线不在线可推送******************************************************************/
    public static final String IM_MEMBER_STATUS_ONLINE = "Online";
    public static final String IM_MEMBER_STATUS_OFFLINE = "Offline";
    public static final String IM_MEMBER_STATUS_PUSHONLINE = "PushOnline";

    public static final int DB_WRITE_RETRY_MAX_COUNT = 3;
    public static final int IM_MSG_RETRY_MAX_COUNT = 3;
    /**热度用户最大值*/
    public static final int HOT_POINTS_USER_MAX_COUNT = 8;
    /**每个艺人获取演出图片时的最大值*/
    public static final int MAX_ARTIST_SHOW_IMAGE_SIZE = 3;
    /**演出节图片、视频等最大显示条数*/
    public static final int MAX_ACTIVITY_SHOW_SIZE = 20;
    /**休息节图片、视频等最大显示条数*/
    public static final int MAX_ACTIVITY_REST_SIZE = 15;//休息节如果都是艺人那么需要显示艺人信息，会比演出节更可怕

    /***********************************时间控制********************************/
    /**热度消息与土豪榜消息每发一个礼物都会收到，使用一定延时发送，此延时每次收到新消息不会重置*/
    public static final int NORMAL_DELAY_SEND = 2 * 1000;
    public static final int BARRAGE_SCROLL_TIME_NORMAL = 10;//second
    public static final int BARRAGE_SCROLL_TIME_VP = 12;//second
    /**右侧榜单显示时间*/
    public static final int CONSUME_CONTINUE_TIME = 10;//second
    /**上墙图片显示时间*/
    public static final int IMAGE_WALL_CONTINUE_TIME = 11;//second
    /**榜单显示时间*/
    public static final int SONG_CONTINUE_TIME = 12;//second
    /**榜单显示时间*/
    public static final int IMAGE_WALL_SWITCH_TIME = 5;//second
    /**榜单显示时间*/
    public static final int CONSUME_SWITCH_TIME = 60;//second
    /**全屏榜单持续显示时间*/
    public static final int CONSUME_BIG_SWITCH_TIME = 10;//second
}
