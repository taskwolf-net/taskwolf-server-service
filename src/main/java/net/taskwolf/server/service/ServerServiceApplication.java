package net.taskwolf.server.service;

import com.google.inject.Guice;

public class ServerServiceApplication {
  public static void main(String[] args) throws Exception {
    var injector = Guice.createInjector(ServerServiceInjectionModule.create());
  }
}
