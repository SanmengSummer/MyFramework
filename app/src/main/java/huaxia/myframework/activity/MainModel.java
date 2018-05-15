package huaxia.myframework.activity;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by Summer on 2018/5/14.
 */

public interface MainModel extends MvpModel {
    Observable getData();
}
