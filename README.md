First,this is a simple Android framework of Mvp. It is based on a Mvp framework of Hannes Dorfmann.

#使用说明：（mvp）
本框架是Mvp+Retrofit2+RxJava2的简单框架，里面的逻辑很简单，有兴趣的可以简单看看。

可compile 'summer:mvp-framework:1.1.1-b0'；
也可下载Library（mvp）直接依赖，推荐下载依赖，方便改动。

0.创建自己的Application类extend SoftApplication；

    0.0 另有有方法可直接调用:
    getOSVersion()得到系统的版本号
    getAppVersionCode()得到应用的版本号VersionCode
    getAppVersionName()得到应用的版本号VersionName
    quit()退出应用

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

5.本框架已包含ButterKnife8.4.0版本，如果需要使用，
    5.1请在Module的build.gradle文件中的dependencies中添加
        annotationProcessor 'com.jakewharton:butterknife-compiler:8.4.0';
    5.2请在项目的build.gradle文件中的dependencies中添加
        classpath 'com.jakewharton:butterknife-gradle-plugin:8.4.0'

6.另外本框架还有一些Util可供使用：
DateUtil,DensityUtil,LogUtil,NetworkUtil,StringUtil,ToastUtil,
VerifyCheck,ACache,AppUtil,DeviceUtil,FileUtil,FormatUtil,
IMEUtil,JavaLicense,RxUtil,ScreenUtil,SharePreferencesUtil,
具体使用方法可以可以直接查看相关类中的方法介绍。

优点：这是个mvp的框架，所以有效的对各类层进行解耦，符合高内聚低耦合的编程思想；使用简单，易理解；
缺点：这种框架有些老旧简单，很多技术也很老旧，不够简洁。

由于本框架结构简单，并没有太多复杂或装逼的逻辑结构，所以在这里就不在详细赘述，有兴趣的同道可以简单研究一下构成逻辑，可帮助你更好的了解Mvp+Retrofit2+RxJava2并作出出色的牛逼框架；
另外有部分对Retrofit2和RxJava2不太了解的同学可以自学一下这两个的基础，否则可能不能灵活使用本框架。
有问题反馈或着某些高大上的建议，可直接联系QQ619716973或在我的github上留言https://github.com/SanmengSummer/MyFramework 。


#使用说明：（mvp_n）
本框架是Mvp+Retrofit2+RxJava2+dagger2的简单框架，和上面的框架完全完全不同，具有更高的解耦性，不过在使用前建议了解关于注解的相关知识（具体可参考dagger2的学习）。

0.本框架还有一些Util可供使用：
  DateUtil,DensityUtil,LogUtil,NetworkUtil,StringUtil,ToastUtil,
  VerifyCheck,ACache,AppUtil,DeviceUtil,FileUtil,FormatUtil,
  IMEUtil,JavaLicense,RxUtil,ScreenUtil,SharePreferencesUtil,
  具体使用方法可以可以直接查看相关类中的方法介绍。
  
1.framework文件夹中的licenses有javaLicense可以帮助实现简单文档注释，使用前需要更改部分代码；

2.具体使用方法可参见Demo；