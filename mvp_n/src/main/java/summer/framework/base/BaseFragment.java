package summer.framework.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import summer.framework.application.BaseApplication;
import summer.framework.component.AppComponent;
import summer.framework.utils.LogUtil;

public abstract class BaseFragment extends Fragment {
    protected View parentView;
    protected FragmentActivity activity;
    protected LayoutInflater inflater;
    protected Context mContext;

    public abstract
    @LayoutRes
    int getLayoutResId(Bundle savedInstanceState);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = getSupportActivity();
        mContext = activity;
        if (parentView == null) {
            LogUtil.i(getClass().getName() + "初始化");
            int contentViewRes = getLayoutResId(savedInstanceState);
            if (contentViewRes == -1) {
                LogUtil.i("未设置LayoutRes");
                return null;
            }
            parentView = inflater.inflate(contentViewRes, container, false);
        } else {
            LogUtil.i(getClass().getName() + "再次加载,无需初始化");
        }
        this.inflater = inflater;
        return parentView;
    }

    protected abstract void initView(View v);

    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupActivityComponent(BaseApplication.getInstance().getAppComponent());
        if (view != null) initView(view);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public FragmentActivity getSupportActivity() {
        return super.getActivity();
    }

    public Context getApplicationContext() {
        return this.activity == null ? (getActivity() == null ? null : getActivity()
                .getApplicationContext()) : this.activity.getApplicationContext();
    }

    protected LayoutInflater getLayoutInflater() {
        return inflater;
    }

    protected View getParentView() {
        return parentView;
    }

    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }
}