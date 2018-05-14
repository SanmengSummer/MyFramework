package huaxia.myframework;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by summer on 2018/5/14.
 */

public interface MainModel extends MvpModel {
    Observable getData();
}
