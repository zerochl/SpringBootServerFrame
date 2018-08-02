package com.zero.barrageserver.common.constant;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by zero大神 on 2018/1/16.
 */
@Component
//@ConfigurationProperties(locations = {"classpath:config/myProps.yml"},prefix = "myProps")
@ConfigurationProperties(prefix = "app")
@Setter
@Getter
public class ConfigConstant {
    private String buildType;
}
