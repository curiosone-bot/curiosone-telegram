package com.github.bot.curiosone.telegram; /**
 * Created by michelerossi1 on 04/07/17.
 */

import libs.org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class Main
{
    public static void main(String[] args ) throws Exception
    {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try
        {
            com.github.bot.curiosone.app.chat.telegram.CuriosoneBots curiosoneBots = new com.github.bot.curiosone.app.chat.telegram.CuriosoneBots();
            curiosoneBots.botToken("337831514:AAFc4d4MCfAsDTvsaVCUEWzz2h2gtRCrl9I");
            botsApi.registerBot(curiosoneBots);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }
}
