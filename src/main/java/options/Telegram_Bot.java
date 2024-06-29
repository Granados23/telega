package options;

import Utils.UtilsPars;
import comands.RequestStart;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import parsing.Pars;

public class Telegram_Bot extends TelegramLongPollingBot {
    Pars pars = new Pars();
    RequestStart start = new RequestStart();
    UtilsPars utilsPars = new UtilsPars();
    User user = new User();

    @Override
    public String getBotToken() {
        return "6686287188:AAFPSSpegYH5TWuw8chNOjelzJp_-0noDhk";
    }

    @Override
    public String getBotUsername() {
        return "ALL_Chat_AI_bot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        String chatid = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();
        int iduser = update.getUpdateId();
        System.out.println("ID_users: " + iduser + "\n Chat_id: " + chatid + "\n text: " + text);


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatid);


        if (text.equals("/start")) {
            sendMessage.setText(start.request());
        }

        if (text.equalsIgnoreCase("/maxPrice") || text.equalsIgnoreCase("/minPrice")) {
            sendMessage.setText(pars.parsMaxAndMinPrice(text));
        }
        if (text.equals(text) && !text.equalsIgnoreCase("/maxPrice") && !text.equalsIgnoreCase("/start") && !text.equalsIgnoreCase("/minPrice")) {
            sendMessage.setText(pars.responsPrice(text));
        }


        try {
            this.execute(sendMessage);

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

}

