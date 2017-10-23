package com.github.bot.curiosone.app.chat.world;

import com.github.bot.curiosone.app.chat.helpers.TalkRequestResponse;

import java.io.IOException;

public class MainTest
{
    public static void main(String[] args) throws IOException
    {
        ServerConnection serverConnection = new ServerConnection();
        TalkRequestResponse talkRequestResponse = new TalkRequestResponse("how are you?", "", "");
        System.out.println(serverConnection.getAnswer(talkRequestResponse));
    }
}
