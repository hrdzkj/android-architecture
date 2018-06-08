package com.example.android.architecture.blueprints.todoapp.di;

import android.app.Application;
import com.example.android.architecture.blueprints.todoapp.ToDoApplication;
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository;
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepositoryModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

/**
 * This is a Dagger component. Refer to {@link ToDoApplication} for the list of Dagger components
 * used in this application.
 * <p>
 * Even though Dagger allows annotating a {@link Component} as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in {@link
 * ToDoApplication}.
 * //{@link AndroidSupportInjectionModule}
 * // is the module from Dagger.Android that helps with the generation
 * // and location of subcomponents.
 *
 * AndroidSupportInjectionModule帮我们将安卓中四大组件以及Fragment进行绑定
 *
 * 注入AndroidInjectionModule，以确保Android的类(Activity、Fragment、Service、BroadcastReceiver及ContentProvider等)
 * 可以绑定。一般把AndroidInjectionModule放在ApplicationCoponent中，其他的Component依赖Application即可。
 * AndroidInjection: Dagger.Android 注入的核心类，主要封装了一些静态方法用来为四大组件和 Fragment 进行注入.
 *
 * modules=/include 相当于融合方法到一个module里
 * AndroidSupportInjectionModule(AndroidInjectionModule)
 *
 * AndroidInjectionModule主要提供Dagger.Android组件包，它应该被包含在注入Application的Component注入器的modules 中。
 *
 * 将我们需要注入的对象的类的构造参数使用@Inject标注，告诉dagger2它可以实例化这个类；这样就即使module里面没有提供有
 * 这个类的@provider，也可以生成这个类来给container注入。
 *
 * 将dagger2库里的AndroidInjectionModule注入到Application中
 */
@Singleton
@Component(modules = {TasksRepositoryModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<ToDoApplication> {

    // 提供TaskRepository的注入对象
    TasksRepository getTasksRepository();

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.

    // @Builder不仅可以用来标识接口也可以标识静态的抽象类，使用这注解的时候有五条规则
    // https://blog.csdn.net/lan568240761/article/details/78976505
    @Component.Builder
    interface Builder {

        // BindsInstance用这个注解标识的方法，必须有个参数；而且这个参数实例会被直接放入Component，以提供依赖
        // 使用场景：AppComponent使用的时候才传入一些配置 Component实例化的时候使用builder模式传入了我们需要传入的值
        // 如果我们希望在使用Module的时候才传入一些配置，直接使用Module的构造参数传入即可
        // 总结：那个component实例化需要参数，那个component就需要采用build模式
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}

/*
https://www.jianshu.com/p/22c397354997
一.最简单不带Module的Inject方式，即：直接给依赖对象的构造函数添加@Inject标注
二.使用module:分普通+module才参数构造函数+module依赖另外module
三 component依赖component
3.1)dependence实现方式:
1、父Component中要显式的写出需要暴露可提供给子Component的依赖；
2、子Component在注解中使用dependencies=来连接父Component；
3、注意子Component实例化方式。

3.2)subComponent实现方式:
1、先定义子Component，使用@Subcomponent标注（不可同时再使用@Component）；
2、父Component中定义获得子Component的方法；
3、注意子Component实例化方式

3.3)子Component构建时传入参数的实现方式：子Component构建时传入参数的话就需要
在子Component中使用@Subcomponent.Builder注解（接口或抽象类），具体实现步骤：
1、在子Component，定义一个接口或抽象类（通常定义为xxBuilder），使用@Subcomponent.Builder标注：
  编写返回值为xxBuilder，方法的参数为需要传入参数的Module；编写返回值为当前子Component的无参方法；
2、父Component中定义获得子Component.Builder的方法

四.Scope作用域——单例


看看用https://blog.csdn.net/qq_17766199/article/details/73030696 理解AndroidInjector
*/

