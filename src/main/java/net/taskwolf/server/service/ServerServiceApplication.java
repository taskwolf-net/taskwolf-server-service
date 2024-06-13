package net.taskwolf.server.service;

import com.google.inject.Guice;
import net.taskwolf.server.service.connection.ServiceConnection;

public class ServerServiceApplication {
  public static void main(String[] args) throws Exception {
    var injector = Guice.createInjector(ServerServiceInjectionModule.create());
    var connection = ServiceConnection.create("", "");
  }
}
