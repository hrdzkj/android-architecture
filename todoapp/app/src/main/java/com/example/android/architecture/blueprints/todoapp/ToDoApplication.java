package com.example.android.architecture.blueprints.todoapp;

import android.app.Application;
import android.support.annotation.VisibleForTesting;
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository;
import com.example.android.architecture.blueprints.todoapp.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import javax.inject.Inject;

/**
 * We create a custom {@link Application} class that extends  {@link DaggerApplication}.
 * We then override applicationInjector() which tells Dagger how to make our @Singleton Component
 * We never have to call `component.inject(this)` as {@link DaggerApplication} will do that for us.
 */
// 继承DaggerApplication并且实现applicationInjector方法，这是为了让Activity不用每个都去调用component的inject方法
public class ToDoApplication extends DaggerApplication {
    @Inject
    TasksRepository tasksRepository;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
 // 在AppComponent中将dagger2库里的AndroidInjectionModule注入到Application中，并将Application实现相应的接口
        return DaggerAppComponent.builder().application(this).build();
    }

    /**
     * Our Espresso tests need to be able to get an instance of the {@link TasksRepository}
     * so that we can delete all tasks before running each test
     */
    @VisibleForTesting
    public TasksRepository getTasksRepository() {
        return tasksRepository;
    }
}
