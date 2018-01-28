package cn.tzmedia.barrageserver.message.entity;

import cn.tzmedia.barrageserver.common.utils.UrlUtils;
import cn.tzmedia.barrageserver.server.model.HotCreatorTable;
import lombok.Data;

/**
 * Created by zero大神 on 2017/12/18.
 */
@Data
public class MsgItemHotItemCreator {
    private int hot;
    private String userRole;
    private String nickName;
    private String image;
    private String usertoken;
    private int levelRange;
    private int level;
    private int userId;
    private String id;
    private String artistId;

    public MsgItemHotItemCreator(HotCreatorTable hotCreatorTable){
        if(null == hotCreatorTable){
            return;
        }
        this.id = hotCreatorTable.getId();
        this.hot = hotCreatorTable.getHot();
        this.userRole = hotCreatorTable.getUser().getUserRole();
        this.nickName = hotCreatorTable.getUser().getNickName();
        if(null != hotCreatorTable.getUser().getImageList() && hotCreatorTable.getUser().getImageList().size() > 0){
            this.image = UrlUtils.getRealUrl(hotCreatorTable.getUser().getImageList().get(0));
        }
        this.usertoken = hotCreatorTable.getUsertoken();
        this.levelRange = hotCreatorTable.getUser().getLevelRange();
        this.level = hotCreatorTable.getUser().getLevel();
        this.userId = hotCreatorTable.getUser().getUserId();
        this.artistId = hotCreatorTable.getUser().getArtistId();
    }

}
