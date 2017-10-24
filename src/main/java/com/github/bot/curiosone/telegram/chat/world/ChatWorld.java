package com.github.bot.curiosone.telegram.chat.world;

import com.github.bot.curiosone.telegram.chat.helpers.TalkRequestResponse;

public class ChatWorld
{
  public static TalkRequestResponse lastBotMessage;

  public ChatWorld()
  {
      this.lastBotMessage = new TalkRequestResponse();
  }
}

