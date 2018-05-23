package summer.BaseFramework.api.support;


import summer.BaseFramework.utils.LogUtils;

/**
 * Summer
 * @date 2016/12/13.
 */
public class Logger implements LoggingInterceptor.Logger {

    @Override
    public void log(String message) {
        LogUtils.i("http : " + message);
    }
}
