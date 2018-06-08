package com.example.android.architecture.blueprints.todoapp.di;

import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskModule;
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskActivity;
import com.example.android.architecture.blueprints.todoapp.statistics.StatisticsActivity;
import com.example.android.architecture.blueprints.todoapp.statistics.StatisticsModule;
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailActivity;
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailPresenterModule;
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity;
import com.example.android.architecture.blueprints.todoapp.tasks.TasksModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */
@Module
public abstract class ActivityBindingModule {
    // 注意下面的这四个`Module`

    // TasksModule是给`TasksActivity`提供注入对象的,我想同理下面的。
    @ActivityScoped
    @ContributesAndroidInjector(modules = TasksModule.class)
    abstract TasksActivity tasksActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = AddEditTaskModule.class)
    abstract AddEditTaskActivity addEditTaskActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = StatisticsModule.class)
    abstract StatisticsActivity statisticsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = TaskDetailPresenterModule.class)
    abstract TaskDetailActivity taskDetailActivity();
}

/*
如果被@Provides标注的方法带有参数，dagger2会自动寻找本Module中其他返回值类型为参数的类型的且被@Provides标注的方法，
如果本Module中找不到就会去看这个类的构造参数是否被@Inject标注了
（所以一般情况下Module中方法的返回值都不能相同，当然也有办法使多个方法的返回值类型相同，
有需要的朋友请自行研究吧，本篇只讲解基础上手,@NAME @Qualifier）


在Module的构造函数带有参数且参数被使用的情况下，所生产的Component类就没有create()方法了
(理解:因为component用到的module有参数，不能自动生成，要程序员提供一个，所以Component不能自己creat默认的出来了)


presenter注入到view层:值得一提的是谷歌不推荐直接将presenter的构造参数添加注解，
更加推荐的是将presenter放到Module里进行管理，因为这样代码更加容易管理。
项目： 时间，进度，处理方式（沟通）
 */
