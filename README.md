First,this is a simple Android framework of Mvp. It is based on a Mvp framework of Hannes Dorfmann.

#使用说明：
本框架是Mvp+Retrofit2+RxJava2的简单框架，里面的逻辑很简单，有兴趣的可以简单看看。
1.搭建网络框架，可直接参照retrofit文件夹里的RetrofitUtils搭建

2.创建Activity可继承BaseActivity<M, V, P>implements V 
如：
public class MainActivity extends BaseActivity<MainModel, MainView, MainPresenter> implements MainView 
具体Demo可参照app 里的activity

3.创建Fragment可继承BaseFragment<M, V, P>implements V 
如：
public class HomeFragment extends BaseFragment<HomeFragmentModel, HomeFragmentView, HomeFragmentPresenter> implements HomeFragmentView
具体Demo可参照app 里的fragment

4.另外可以在自制控件时直接使用MvpFrameLayout,MvpLinerLayout,MvpRelativeLayout可达到解耦的效果，使用方法和BaseFragment相同。

由于本框架结构简单，并没有太多复杂或装逼的逻辑结构，所以在这里就不在详细赘述，有兴趣的同道可以简单研究一下构成逻辑，可帮助你更好的了解Mvp+Retrofit2+RxJava2并作出出色的牛逼框架；
另外有部分对Retrofit2和RxJava2不太了解的同学可以自学一下这两个的基础，否则可能不能灵活使用本框架。
有问题反馈或着某些高大上的建议，可直接联系QQ619716973或在我的github上留言https://github.com/SanmengSummer/MyFramework 。