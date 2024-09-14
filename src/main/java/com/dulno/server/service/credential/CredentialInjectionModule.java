package com.dulno.server.service.credential;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "create")
public final class CredentialInjectionModule extends AbstractModule {
  @Provides
  @Singleton
  CredentialConfiguration provideCredentialConfiguration() throws Exception {
    return CredentialConfiguration.createAndLoad();
  }
}
