package cn.tzmedia.barrageserver.common.constant;

/**
 * Created by Administrator on 2017/11/28.
 */
public class CacheConstant {
    /************************Table*********************************/
    /***UserTable**/
    public final static String CACHE_TABLE_USER = "tab-user";
    /***User下IdTable**/
    public final static String CACHE_TABLE_USER_ID = CACHE_TABLE_USER + "-id";
    /***User下UserIdTable**/
    public final static String CACHE_TABLE_USER_USER_ID = CACHE_TABLE_USER + "-userId";

    /***ShopTable**/
    public final static String CACHE_TABLE_SHOP = "tab-shop";
    /***ShopNameTable**/
    public final static String CACHE_TABLE_SHOP_NAME = CACHE_TABLE_SHOP + "-name";
    /***ShopIdTable**/
    public final static String CACHE_TABLE_SHOP_ID = CACHE_TABLE_SHOP + "-shopId";
    /**ShopIdTable for activity*/
    public final static String CACHE_TABLE_SHOP_FOR_ACTIVITY_ID = CACHE_TABLE_SHOP + "-activity-shopId";
    /***ShopSetTable**/
    public final static String CACHE_TABLE_SHOP_SET = CACHE_TABLE_SHOP + "-shopSet";

    /**ActivityTable*/
    public final static String CACHE_TABLE_ACTIVITY = "tab-activity";
    /**ActivityTable根据ID存储*/
    public final static String CACHE_TABLE_ACTIVITY_ID = CACHE_TABLE_ACTIVITY + "-id";
    /**ActivityTable 今日演出*/
    public final static String CACHE_TABLE_ACTIVITY_SHOW_FOR_TODAY = CACHE_TABLE_ACTIVITY + "-show-today";

    /**AnimationTable*/
    public final static String CACHE_TABLE_ANIMATION = "tab-animation";
    /**Animation Award*/
    public final static String CACHE_ANIMATION_AWARD = "AwardList";

    /**BarrageConfigTable*/
    public static final String CACHE_TABLE_SHOP_BARRAGE_CONFIG = "tab-shop-barrage-config";

    /**BarrageMovieTable*/
    public static final String CACHE_TABLE_SHOP_BARRAGE_MOVIE = "tab-shop-barrage-movie";
    public static final String CACHE_KEY_ALL_SHOP_BARRAGE_MOVIE = "key-all-shop-barrage-movie";
    /************************Request**************************************/
    /***ten**/
    public final static String CACHE_REDIS_TEN_GROUP = "request-ten-group";

    /***********************DB write*********************************************/
    public final static String CAHCE_DB_WRITE_ASYNC = "dbwrite-async";
}
