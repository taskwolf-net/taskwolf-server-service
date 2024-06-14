package net.taskwolf.server.service.file;

import lombok.RequiredArgsConstructor;
import net.taskwolf.server.service.credential.CredentialConfiguration;
import net.taskwolf.server.service.request.TaskwolfRequest;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.util.Base64;
import java.util.Map;

@RequiredArgsConstructor(staticName = "create")
public final class ServiceFile {
  private final String filePath;

  private static final String FILE_STORE_URL =
    "https://api.taskwolf.net/v1/device/file/storage/response/";

  public void store(String storeId) throws Exception {
    var credentials = CredentialConfiguration.createAndLoad();
    var requestBody = Map.of("device", credentials.device(), "storage", storeId);
    var response = TaskwolfRequest.create(FILE_STORE_URL, "POST",
      new JSONObject(requestBody)).sendAuthorized(credentials.token());
    var content = new JSONObject(response.body()).getString("content");
    var file = new File(filePath);
    if (filePath.charAt(filePath.length() - 1) == '/') {
      file.mkdirs();
      return;
    }
    file.getParentFile().mkdirs();
    file.createNewFile();
    FileUtils.writeByteArrayToFile(file, Base64.getDecoder().decode(content));
  }

  private static final String FILE_INFO_URL =
    "https://api.taskwolf.net/v1/device/file/info/response/";

  public void info(String infoId) throws Exception {
    var file = new File(filePath);
    var content = FileUtils.readFileToByteArray(file);
    var credentials = CredentialConfiguration.createAndLoad();
    var requestBody = Map.of("device", credentials.device(), "info", infoId,
      "content", Base64.getEncoder().encodeToString(content));
    TaskwolfRequest.create(FILE_INFO_URL, "POST",
      new JSONObject(requestBody)).sendAuthorized(credentials.token());
  }

  private static final String FILE_DELETE_URL =
    "https://api.taskwolf.net/v1/device/file/delete/response/";

  public void delete(String deleteId) throws Exception {
    var credentials = CredentialConfiguration.createAndLoad();
    var requestBody = Map.of("device", credentials.device(), "delete", deleteId);
    TaskwolfRequest.create(FILE_DELETE_URL, "POST",
      new JSONObject(requestBody)).sendAuthorized(credentials.token());
    var file = new File(filePath);
    file.delete();
  }
}
