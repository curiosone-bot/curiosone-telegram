package com.github.bot.curiosone.telegram.chat.world;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;
import com.github.bot.curiosone.telegram.chat.helpers.TalkRequestResponse;

import okhttp3.*;


public class ServerConnection{

  public static final String BASE_URL = "https://curiosone-bot.herokuapp.com";
  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  private OkHttpClient client;
  private ChatWorld world;
  private static final List<String> playArr = Arrays.asList("error with server , please retry later",
                                                            "error with server comunication, please check your connection and retry ",
                                                            "Please let's talk about something different than me."
                                                            );

  public ServerConnection() {
    client = new OkHttpClient().newBuilder()
      .connectTimeout(10,TimeUnit.SECONDS)
      .writeTimeout(10, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .build();

  }

  public String getAnswer(TalkRequestResponse message) throws IOException, UnknownHostException {
    Json json = new Json();
    String url = BASE_URL + "/talk";
    RequestBody body = RequestBody.create(JSON, message.json());
    Request request = new Request.Builder()
      .url(url)
      .post(body)
      .build();
    try (Response response = this.client.newCall(request).execute()) {
      String data = response.body().string();
      world.lastBotMessage = json.fromJson(TalkRequestResponse.class, data);

    } catch (SerializationException e) {
      world.lastBotMessage.setMessage("error with server , please retry later");
    }

    return world.lastBotMessage.getMessage();
  }

  public String getScope(TalkRequestResponse message) throws IOException, UnknownHostException {
    Json json = new Json();
    String url = BASE_URL + "/talk";
    RequestBody body = RequestBody.create(JSON, message.json());
    Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();
    try (Response response = this.client.newCall(request).execute()) {
      String data = response.body().string();
      world.lastBotMessage = json.fromJson(TalkRequestResponse.class, data);

    } catch (SerializationException e) {
      world.lastBotMessage.setMessage("error with server , please retry later");
    }

    return world.lastBotMessage.getScope();
  }

  public void setWorld(ChatWorld world) {
    this.world = world;
  }
}
