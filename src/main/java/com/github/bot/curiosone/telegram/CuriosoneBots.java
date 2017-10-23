package com.github.bot.curiosone.telegram;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class CuriosoneBots extends TelegramLongPollingBot
{
    private String name;
    private String receivedText;
    private String token;
    private List<Bot> botSet = new ArrayList<Bot>();
    public SendMessage message = new SendMessage();



    @Override
    public void onUpdateReceived(Update update) throws Exception//quello che fa quando arriva il messaggio
    {
        if(update.hasMessage() && update.getMessage().hasText()) //controllo se l'input è testo
        {
            Long senderId = update.getMessage().getChatId(); //variabile dell'ID dell'utente
            Long generalId = update.getMessage().getFrom().getId().longValue();
            name = update.getMessage().getFrom().getFirstName();
            receivedText = update.getMessage().getText().toLowerCase(); // variabile per il testo ricevuto dall'utente

            generatesBot(senderId, generalId);
        }
    }

    public void generatesBot(Long senderId, Long general) throws Exception
    {

        Bot bot = new Bot(senderId, general, message, name);
        if (!botSet.contains(bot)) {
            botSet.add(bot);
            bot.send(receivedText);
            try {
                sendMessage(bot.message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            int i = botSet.indexOf(bot);
            Bot b = botSet.get(i);
            b.send(receivedText);
            try {
                sendMessage(b.message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    public String getReceivedText()
    {
        return receivedText;
    }

    @Override
    public String getBotUsername()
    {
        return "Il Curiosone"; //nome del bot
    }
    @Override
    public String getBotToken()
    {
        return token; //token
    }
    public void botToken(String token)
    {
        this.token = token;
    }
}
