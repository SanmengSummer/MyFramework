package summer.myframework.activity;

import summer.myframework.network.retrofit.RetrofitUtils;
import mvp.cn.common.util.LogUtil;
import rx.Observable;

/**
 * Created by Summer on 2018/5/14.
 * MainModel的处理层，一般就是请求网络数据的初级处理，获取Retrofit的Observable；
 */

public class MainModelImpl implements MainModel {
    @Override
    public Observable getData() {
        LogUtil.i("MainModel请求数据");
        Observable observer = RetrofitUtils.getInstance().queryParamInfo();
        return observer ;
    }
}
