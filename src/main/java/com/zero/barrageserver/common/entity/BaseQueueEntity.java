package com.zero.barrageserver.common.entity;

import com.zero.barrageserver.common.base.BaseClone;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zero大神 on 2017/12/20.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseQueueEntity extends BaseClone{
    public int weight;
}
