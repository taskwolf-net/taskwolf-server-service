package net.taskwolf.server.service.command;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "create")
public final class CommandInjectionModule extends AbstractModule {
  @Provides
  @Singleton
  CommandConfiguration provideCommandConfiguration() throws Exception {
    return CommandConfiguration.createAndLoad();
  }
}
