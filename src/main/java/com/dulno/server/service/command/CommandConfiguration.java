package com.dulno.server.service.command;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.dulno.server.service.configuration.Configuration;
import org.json.JSONObject;

@Getter
@Accessors(fluent = true)
public final class CommandConfiguration extends Configuration {
  private static final String CONFIGURATION_PATH = "command/command.json";

  public static CommandConfiguration createAndLoad() throws Exception {
    var configuration = new CommandConfiguration(CONFIGURATION_PATH);
    if (!configuration.exists()) {
      return configuration;
    }
    configuration.load();
    return configuration;
  }

  public static CommandConfiguration createAndStore(boolean enabled) throws Exception {
    var configuration = new CommandConfiguration(CONFIGURATION_PATH, enabled);
    if (!configuration.exists()) {
      configuration.create();
    }
    configuration.save();
    return configuration;
  }

  private boolean enabled = true;

  private CommandConfiguration(String path) {
    super(path);
  }

  private CommandConfiguration(String path, boolean enabled) {
    super(path);
    this.enabled = enabled;
  }

  @Override
  protected void deserialize(JSONObject json) {
    enabled = json.getBoolean("enabled");
  }
}
