package net.taskwolf.server.service.command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "create")
public final class ServiceCommand {
  private final String commandId;
  private final String command;

  public String execute() {
    return "";
  }
}
