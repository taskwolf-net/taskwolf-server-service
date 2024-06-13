package net.taskwolf.server.service.file;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "create")
public final class FileInjectionModule extends AbstractModule {
  @Provides
  @Singleton
  FileConfiguration provideFileConfiguration() throws Exception {
    return FileConfiguration.createAndLoad();
  }
}

