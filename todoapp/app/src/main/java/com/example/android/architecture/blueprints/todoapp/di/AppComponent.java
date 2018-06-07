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
 *
 * modules=/include 相当于融合方法到一个module里
 * AndroidSupportInjectionModule(AndroidInjectionModule)
 *
 * AndroidInjectionModule主要提供Dagger.Android组件包，它应该被包含在注入Application的Component注入器的modules 中。
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
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
