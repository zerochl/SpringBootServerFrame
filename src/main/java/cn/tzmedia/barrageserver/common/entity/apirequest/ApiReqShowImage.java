package cn.tzmedia.barrageserver.common.entity.apirequest;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by zero大神 on 2018/1/10.
 */
@Data
public class ApiReqShowImage implements Serializable{
    private String type;
    private String image;
    private int interval;
    private int play_count;
    private String video_url;
    private boolean immediateShow;
}
