package com.dulno.server.service;

import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;
import com.dulno.server.service.command.CommandInjectionModule;
import com.dulno.server.service.credential.CredentialInjectionModule;
import com.dulno.server.service.file.FileInjectionModule;

@RequiredArgsConstructor(staticName = "create")
public final class ServerServiceInjectionModule extends AbstractModule {
  @Override
  protected void configure() {
    install(CredentialInjectionModule.create());
    install(CommandInjectionModule.create());
    install(FileInjectionModule.create());
  }
}
