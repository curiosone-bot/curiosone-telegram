 /**
 * Created by michelerossi1 on 04/07/17.
 */

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.github.bot.curiosone.telegram.CuriosoneBots;


public class MainTest
{
    public static void main(String[] args ) throws Exception
    {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try
        {
            CuriosoneBots curiosoneBots = new CuriosoneBots();
            curiosoneBots.botToken("337831514:AAFc4d4MCfAsDTvsaVCUEWzz2h2gtRCrl9I");
            botsApi.registerBot(curiosoneBots);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }
}
