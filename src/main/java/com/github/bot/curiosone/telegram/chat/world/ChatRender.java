package com.github.bot.curiosone.app.chat.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class ChatRender {

  private OrthographicCamera cam;
  private com.github.bot.curiosone.app.chat.world.ChatWorld world;
  private Stage stage;
  private SpriteBatch batch;
  private StretchViewport viewp;

  public ChatRender(ChatWorld world) {
    this.world = world;
    this.cam = new OrthographicCamera();       // Setting camera
    cam.setToOrtho(false, 480, 800);
    this.batch = new SpriteBatch();
    batch.setProjectionMatrix(cam.combined);
    viewp = new StretchViewport(480, 800, cam);
    world.getSendButton().setWorld(world);
    world.getInserimento().setWorld(world);
    this.stage = new Stage(viewp, batch);
    buildStage();
  }

  public void render() {
    //Gdx.app.log("ChatRender", "render");
    Gdx.gl.glClearColor(0, 0, 0, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.draw();
  }

  private void buildStage() {
    stage.addActor(world.getBg());
    stage.addActor(world.getGameButton());
    stage.addActor(world.getMenuButton());
    stage.addActor(world.getSendButton());
    stage.addActor(world.getInserimento());
    stage.addActor(world.getScrollPane());
    Gdx.input.setInputProcessor(stage);
  }

  public Stage getStage() {
    return stage;
  }

  public StretchViewport getView() {
    return viewp;
  }

  public void dispose() {
    stage.dispose();
  }
}
