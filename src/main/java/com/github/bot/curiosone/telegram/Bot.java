package com.github.bot.curiosone.telegram;

import com.github.bot.curiosone.app.chat.helpers.TalkRequestResponse;
import com.github.bot.curiosone.app.chat.world.ServerConnection;
import org.telegram.telegrambots.api.methods.send.SendMessage;



public class Bot
{
    private Long id;
    public SendMessage message;
    private Long generalId;
    private boolean status = true;
    private String name = "I don't know";
    private String age = "I don't know";
    private final String MY_NAME = "my name is";
    private final String I_M = "i'm";
    private String scope;

    public Bot(Long id, Long generalId, SendMessage message, String name)
    {
        this.id = id;
        this.generalId = generalId;
        this.message = message;
        this.name = name;
    }


    public void send(String testo) throws Exception
    {
        String text_to_send = textToSend(testo);
        message.setChatId(id);
        message.setText(text_to_send);
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == null || !(o instanceof Bot)) return false;
        Bot b = (Bot)o;
        return id.equals(b.id) && generalId.equals(b.generalId);
    }

    @Override
    public int hashCode()
    { return id.hashCode() + generalId.hashCode(); }



    private String textToSend(String text)  throws Exception//messaggio da inviare
    {
        if(status)
        {
            switch (text)
            {
                case "wake up": status = true; return "Hi, " + name;
                case "shut up": status = false; return "Ok, if you want to wake me up you'll have to type \"hi curiosone\"";
                case "/start": return "Hi, my name is Curiosone. I like to learn anything. Let's start right away, how old are you?";
                case "what is my name?": return name;
                case "what's my name?": return name;
                case "how old am i?" : return age;
                case "hi curiosone": status = true; return "Hi, " + name;
            }
            if(text.startsWith(MY_NAME))
            {
                int index = text.indexOf(MY_NAME);
                if (index != -1)
                {
                    name = text.substring(index+MY_NAME.length()+1);
                    return "Nice to meet you, "+ text.substring(index+MY_NAME.length()+1) + " \uD83D\uDC4B";
                }
            }
            if(text.startsWith(I_M))
            {
                String s = text.substring(text.indexOf(I_M)+I_M.length()+1);
                if(!Character.isDigit(s.charAt(0)))
                    age = text.substring(text.indexOf(I_M)+I_M.length()+1);
                return "👌";
            }
            if(text.startsWith("/support"))
            {
                id = 65762325L;
                return text.substring(text.indexOf("/support") + "/support".length());
            }
            ServerConnection serverConnection = new ServerConnection();
            TalkRequestResponse talkRequestResponse = new TalkRequestResponse(text, scope, "");
            scope = talkRequestResponse.getScope();
            return serverConnection.getAnswer(talkRequestResponse);
        }
        return null;
    }
}



