package com.dulno.server.service.connection;

import java.net.URI;

import com.dulno.server.service.command.ServiceCommand;
import com.dulno.server.service.file.ServiceFile;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

public final class ServiceConnection extends WebSocketClient {
  private static final String URL_FORMAT =
    "wss://api.dulno.com/device/connect/?token=%s&device=%s";

  public static ServiceConnection create(
    String token, String device
  ) throws Exception {
    return new ServiceConnection(new URI(String.format(URL_FORMAT, token, device)),
      token, device);
  }

  private final String token;
  private final String device;

  private ServiceConnection(
    URI address, String token, String device
  ) {
    super(address);
    this.token = token;
    this.device = device;
  }

  @Override
  public void onOpen(ServerHandshake handshakedata) {

  }

  @Override
  public void onMessage(String message) {
    var json = new JSONObject(message);
    var type = json.getString("type");
    try {
      processExecution(json, type);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void processExecution(JSONObject json, String type) throws Exception {
    if (type.equalsIgnoreCase("COMMAND")) {
      send(ServiceCommand.create(json.getString("commandId"),
        json.getString("command")).execute());
    } else if (type.equalsIgnoreCase("FILE_STORAGE")) {
      ServiceFile.create(json.getString("filePath"))
        .store(json.getString("storeId"));
    } else if (type.equalsIgnoreCase("FILE_INFO")) {
      ServiceFile.create(json.getString("filePath"))
        .info(json.getString("infoId"));
    } else if (type.equalsIgnoreCase("FILE_DELETE")) {
      ServiceFile.create(json.getString("filePath"))
        .delete(json.getString("deleteId"));
    }
  }

  @Override
  public void onClose(int code, String reason, boolean remote) {
    try {
      System.out.println("The connection to Dulno has been interrupted. " +
        "An attempt will be made to re-establish the connection in 10 seconds");
      Thread.sleep(10000);
      ServiceConnection.create(token, device).connect();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public void onError(Exception exception) {

  }
}