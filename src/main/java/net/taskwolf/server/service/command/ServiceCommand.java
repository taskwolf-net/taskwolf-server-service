package net.taskwolf.server.service.command;

import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RequiredArgsConstructor(staticName = "create")
public final class ServiceCommand {
  private final String commandId;
  private final String command;

  public String execute() throws Exception {
    if (!CommandConfiguration.createAndLoad().enabled()) {
      return "Command Response " + commandId + " '' '' -1";
    }
    try {
      var process = Runtime.getRuntime().exec(command);
      var inputReader = new BufferedReader(new InputStreamReader(
        process.getInputStream()));
      var errorReader = new BufferedReader(new
        InputStreamReader(process.getErrorStream()));
      var temporary = "";
      var input = new StringBuilder();
      while ((temporary = inputReader.readLine()) != null) {
        input.append(temporary);
      }
      var error = new StringBuilder();
      while ((temporary = errorReader.readLine()) != null) {
        error.append(temporary);
      }
      process.onExit().join();
      return "Command Response " + commandId + " '" + input + "' '" + error + "' " +
        process.exitValue();
    } catch (Exception exception) {
      return "Command Response " + commandId + " '' '" + exception.getMessage() +
        "' " + -1;
    }
  }
}
