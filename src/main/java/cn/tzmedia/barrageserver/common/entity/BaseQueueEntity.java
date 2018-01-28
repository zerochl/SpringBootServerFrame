package cn.tzmedia.barrageserver.common.entity;

import cn.tzmedia.barrageserver.common.base.BaseClone;
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
