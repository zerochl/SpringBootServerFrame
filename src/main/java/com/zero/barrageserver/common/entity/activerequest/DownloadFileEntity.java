package com.zero.barrageserver.common.entity.activerequest;

import lombok.Data;

/**
 * Created by Administrator on 2017/11/30.
 */
@Data
public class DownloadFileEntity {
    private String fileUrl;
    private String MD5Str;
}
