
package mvp.cn.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import mvp.cn.common.base.MvpFragment;
import mvp.cn.common.base.MvpView;


public abstract class MvpRxFragment<M extends MvpModel, V extends MvpView, P extends MvpRxPresenter<M, V>>
        extends MvpFragment<V, P> {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MvpRxPresenter p = getPresenter();
        if (p != null) {
            p.setModel(createModel());
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public abstract M createModel();
}
