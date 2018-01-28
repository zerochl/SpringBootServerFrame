package cn.tzmedia.barrageserver.message.base;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zero大神 on 2017/12/12.
 */
public class CustomLinkedBlockingQueue<T> extends LinkedBlockingQueue<T> {
    private int weight;
    public CustomLinkedBlockingQueue(int weight){
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
