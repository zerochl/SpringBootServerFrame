package cn.tzmedia.barrageserver.common.entity.servermsg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zero大神 on 2017/12/14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RequestSongMsg extends BaseRequestMsgEntity{
    @JSONField(name = "songname")
    private String songName;
    @JSONField(name = "songimage")
    private String songImage;
    private int left;
    @JSONField(name = "artistid")
    private String artistId;
    @JSONField(name = "artistname")
    private String artistName;
}
