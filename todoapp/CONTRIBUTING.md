# How to become a contributor and submit your own code

## Contributor License Agreements

We'd love to accept your patches! Before we can take them, we 
have to jump a couple of legal hurdles.

### Before you contribute
Before we can use your code, you must sign the
[Google Individual Contributor License Agreement](https://cla.developers.google.com/about/google-individual)
(CLA), which you can do online. The CLA is necessary mainly because you own the
copyright to your changes, even after your contribution becomes part of our
codebase, so we need your permission to use and distribute your code. We also
need to be sure of various other things—for instance that you'll tell us if you
know that your code infringes on other people's patents. You don't have to sign
the CLA until after you've submitted your code for review and a member has
approved it, but you must do it before we can put your code into our codebase.
Before you start working on a larger contribution, you should get in touch with
us first through the issue tracker with your idea so that we can help out and
possibly guide you. Coordinating up front makes it much easier to avoid
frustration later on.

### Code reviews
All submissions, including submissions by project members, require review. We
use Github pull requests for this purpose.

### The small print
Contributions made by corporations are covered by a different agreement than
the one above, the
[Software Grant and Corporate Contributor License Agreement](https://cla.developers.google.com/about/google-corporate).


可以学习的地方：
ActivityUtils
DaggerAppCompatActivity




di包，这里面都是动态注入相关的类:有全局的ApplicationModule、AppComponent、ActivityBindingModule和自定义的Scope.

https://blog.csdn.net/Charon_Chui/article/details/80605900  todoapp
DaggerApplication?  DaggerAppComponent.builder().application(this).build()
这里面会将TasksRepositoryModule、ApplicationModule、ActivityBindingModule都进行绑定。是指
@Singleton
@Component(modules = {TasksRepositoryModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
还是指@Binds

/*
这是用于mock测试的一个类，里面的两个方法分别表示本地数据和远程数据，最终返回的都是TasksDataSource。
mock测试就是在测试过程中，对于某些不容易构造或者不容易获取的对象，用一个虚拟的对象来创建以便测试的测试方法。
这里对于数据对象直接在这里进行初始化，而不是在所有的用到该数据的地方new一遍。这也就体现了Dagger2的引入对测试
是一个极大的便利。 ---如何体现？
*/


Dagger2 提供全局对象引用的简易访问方式。声明了单例的实例都可以使用@inject进行访问。


一般的IOC框架都是通过反射来实现的,但Dagger2作为Android端的IOC框架,为了不影响性能,
它是通过apt动态生成代码来实现的.

AndroidInjectionModule的源码，AndroidInjectionModule是用来注入四大组件和fragment?

一般：MyAppModule提供全局单例功能，
// 从https://www.jianshu.com/p/6d3b1805f5e5 应该可以了解一些流程
// github https://github.com/xiaobailong24/DaggerAndroid