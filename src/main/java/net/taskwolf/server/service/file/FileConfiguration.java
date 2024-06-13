package net.taskwolf.server.service.file;

import lombok.Getter;
import lombok.experimental.Accessors;
import net.taskwolf.server.service.configuration.Configuration;
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

  private FileConfiguration(String path) {
    super(path);
  }

  @Override
  protected void deserialize(JSONObject json) {

  }
}