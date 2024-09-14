package com.dulno.server.service;

import com.google.inject.Guice;
import com.dulno.server.service.connection.ServiceConnection;
import com.dulno.server.service.credential.CredentialConfiguration;

public class ServerServiceApplication {
  public static void main(String[] args) throws Exception {
    var injector = Guice.createInjector(ServerServiceInjectionModule.create());
    var credentialConfiguration = injector.getInstance(CredentialConfiguration.class);
    if (credentialConfiguration.token() == null ||
      credentialConfiguration.device() == null
    ) {
      System.out.println("No device credentials found. You probably haven't " +
        "registered yet. Enter \"dulno login\" to make up for this.");
      return;
    }
    var connection = ServiceConnection.create(credentialConfiguration.token(),
      credentialConfiguration.device());
    connection.connect();
    System.out.println("The Dulno service has been successfully launched");
    Thread.currentThread().join();
  }
}
