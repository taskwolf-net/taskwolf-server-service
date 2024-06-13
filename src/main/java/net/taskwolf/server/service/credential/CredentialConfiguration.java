package net.taskwolf.server.service.credential;

import lombok.Getter;
import lombok.experimental.Accessors;
import net.taskwolf.server.service.configuration.Configuration;
import org.json.JSONObject;

@Getter
@Accessors(fluent = true)
public final class CredentialConfiguration extends Configuration {
  private static final String CONFIGURATION_PATH = "credential/credential.json";

  public static CredentialConfiguration createAndLoad() throws Exception {
    var configuration = new CredentialConfiguration(CONFIGURATION_PATH);
    if (!configuration.exists()) {
      return configuration;
    }
    configuration.load();
    return configuration;
  }

  private String token;
  private String device;

  private CredentialConfiguration(String path) {
    super(path);
  }

  @Override
  protected void deserialize(JSONObject json) {
    token = json.getString("token");
    device = json.getString("device");
  }
}