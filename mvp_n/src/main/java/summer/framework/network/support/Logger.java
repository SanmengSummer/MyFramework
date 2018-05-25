package summer.framework.network.support;


import summer.framework.utils.LogUtil;

/**
 * Summer
 * @date 2016/12/13.
 */
public class Logger implements LoggingInterceptor.Logger {

    @Override
    public void log(String message) {
        LogUtil.i("http : " + message);
    }
}
