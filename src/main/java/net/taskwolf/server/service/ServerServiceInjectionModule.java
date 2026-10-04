package net.taskwolf.server.service;

import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;
import net.taskwolf.server.service.command.CommandInjectionModule;
import net.taskwolf.server.service.credential.CredentialInjectionModule;
import net.taskwolf.server.service.file.FileInjectionModule;

@RequiredArgsConstructor(staticName = "create")
public final class ServerServiceInjectionModule extends AbstractModule {
  @Override
  protected void configure() {
    install(CredentialInjectionModule.create());
    install(CommandInjectionModule.create());
    install(FileInjectionModule.create());
  }
}
