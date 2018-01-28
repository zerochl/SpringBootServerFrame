package cn.tzmedia.barrageserver.common.entity.activerequest;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */
@Data
public class DownloadImageEntity {
    private String name;
    private List<DownloadFileEntity> image;
    private String createDate;
    private String _id;
}
