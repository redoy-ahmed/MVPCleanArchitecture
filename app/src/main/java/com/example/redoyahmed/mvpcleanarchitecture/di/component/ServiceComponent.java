
package com.example.redoyahmed.mvpcleanarchitecture.di.component;

import com.example.redoyahmed.mvpcleanarchitecture.di.PerService;
import com.example.redoyahmed.mvpcleanarchitecture.di.module.ServiceModule;
import com.example.redoyahmed.mvpcleanarchitecture.service.SyncService;

import dagger.Component;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
    void inject(SyncService service);
}
