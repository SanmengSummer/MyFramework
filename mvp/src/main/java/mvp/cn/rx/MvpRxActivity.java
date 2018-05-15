
package mvp.cn.rx;

import android.os.Bundle;

import mvp.cn.common.base.MvpActivity;
import mvp.cn.common.base.MvpView;


public abstract class MvpRxActivity<M extends MvpModel, V extends MvpView, P extends MvpRxPresenter<M, V>>
        extends MvpActivity<V, P> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MvpRxPresenter p = getPresenter();
        if (p != null) {
            p.setModel(createModel());
        }
    }

    public abstract M createModel();
}
