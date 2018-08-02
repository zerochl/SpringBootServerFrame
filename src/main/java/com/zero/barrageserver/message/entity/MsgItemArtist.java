package com.zero.barrageserver.message.entity;

import com.zero.barrageserver.common.utils.UrlUtils;
import com.zero.barrageserver.dbserver.model.ArtistTable;
import com.zero.barrageserver.common.utils.UrlUtils;
import com.zero.barrageserver.dbserver.model.ArtistTable;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */
@Data
public class MsgItemArtist {
    private String id;
    private List<String> showBarrageImage;
    private List<String> imageList;
    private String introduce;
    private String experience;
    private String name;
    private String sign;
    private List<String> tag;
    private String headImage;
    public MsgItemArtist(ArtistTable artistTable){
        if(null == artistTable){
            return;
        }
        this.id = artistTable.getId();
        this.showBarrageImage = UrlUtils.getRealUrl(artistTable.getShowBarrageImage());
//        this.imageList = UrlUtils.getRealUrl(artistTable.getImageList());
        this.introduce = artistTable.getIntroduce();
        this.experience = artistTable.getExperience();
        this.name = artistTable.getName();
        this.sign = artistTable.getSign();
        this.tag = artistTable.getTag();
        if(null != artistTable.getImageList() && artistTable.getImageList().size() > 0){
            this.headImage = UrlUtils.getRealUrl(artistTable.getImageList().get(0));
        }
    }
}
