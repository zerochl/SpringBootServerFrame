package cn.tzmedia.barrageserver.common.iface;

/**
 * Created by zero大神 on 2017/10/11.
 */
public interface IThreadCallBack {
    public abstract Object doAsyncTask(
            int type,
            Object serializable);

    public abstract void doReturnAction(
            int type,
            Object result,
            Object serializable);
}
