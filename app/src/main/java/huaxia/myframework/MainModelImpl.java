package huaxia.myframework;

import huaxia.myframework.network.retrofit.RetrofitUtils;
import mvp.cn.common.util.LogUtil;
import rx.Observable;

/**
 * Created by summer on 2018/5/14.
 */

public class MainModelImpl implements MainModel {
    @Override
    public Observable getData() {
        LogUtil.log("MainModel请求数据");
        Observable observer = RetrofitUtils.getInstance().queryParamInfo();
        return observer ;
    }
}
