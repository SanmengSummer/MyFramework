package summer.myframework.activity;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by Summer on 2018/5/14.
 * MainActivity的数据变化请求层，一般通过implement由MainModelImpl获取Observable，
 * 通过依赖由MainPresenter处理
 */

public interface MainModel extends MvpModel{
    Observable getData();
}
