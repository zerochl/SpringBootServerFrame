package cn.tzmedia.barrageserver.dbserver.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/4.
 */
@Data
public class BarrageRoleEntity implements Serializable ,Cloneable{
    private double barrageAlpha;
    private List<ShowImageEntity> barrageShowImage;
    private List<RestImageEntity> barrageRestImage;
    @Field("live_enabled")
    private boolean liveEnable;
    @Field("live_url")
    private String liveUrl;
}
