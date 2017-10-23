package com.github.bot.curiosone.telegram.chat.world;

import java.io.IOException;

import com.github.bot.curiosone.telegram.chat.helpers.TalkRequestResponse;

public class MainTest
{
    public static void main(String[] args) throws IOException
    {
        ServerConnection serverConnection = new ServerConnection();
        TalkRequestResponse talkRequestResponse = new TalkRequestResponse("how are you?", "", "");
        System.out.println(serverConnection.getAnswer(talkRequestResponse));
    }
}
