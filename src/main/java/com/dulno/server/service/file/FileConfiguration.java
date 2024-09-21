package com.dulno.server.service.file;

import com.dulno.server.service.configuration.Configuration;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.json.JSONObject;

@Getter
@Accessors(fluent = true)
public final class FileConfiguration extends Configuration {
  private static final String CONFIGURATION_PATH = "file/file.json";

  public static FileConfiguration createAndLoad() throws Exception {
    var configuration = new FileConfiguration(CONFIGURATION_PATH);
    if (!configuration.exists()) {
      return configuration;
    }
    configuration.load();
    return configuration;
  }

  public static FileConfiguration createAndStore(boolean enabled) throws Exception {
    var configuration = new FileConfiguration(CONFIGURATION_PATH, enabled);
    if (!configuration.exists()) {
      configuration.create();
    }
    configuration.save();
    return configuration;
  }

  private boolean enabled = true;

  private FileConfiguration(String path) {
    super(path);
  }

  private FileConfiguration(String path, boolean enabled) {
    super(path);
    this.enabled = enabled;
  }

  @Override
  protected void deserialize(JSONObject json) {
    enabled = json.getBoolean("enabled");
  }
}