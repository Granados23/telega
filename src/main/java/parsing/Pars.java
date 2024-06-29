package parsing;

import Utils.UtilsPars;
import options.Telegram_Bot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Pars implements Runnable {

    List<ParsElem> вывод = new ArrayList<>();
    List<ParsElem> вывод1 = new ArrayList<>();
    UtilsPars utilsPars = new UtilsPars();

    public void responsFull() throws IOException {
        Document document = Jsoup.connect("https://smart-lab.ru/q/shares/").get();
        Elements parsАкцйий = document.selectXpath("//td[@class='trades-table__name']");
        Elements parsЦена = document.selectXpath("//td[@class='trades-table__price']");
        for (int i = 0; i <= 150; i++) {
            вывод.add(new ParsElem(parsАкцйий.get(i).text(), Double.valueOf(parsЦена.get(i).text())));
        }
        вывод1 = вывод.stream().toList();
        вывод.clear();
    }

    public String responsPrice(String serch) {
        run();
        String результат = "";
        for (ParsElem values : вывод1) {
            if (values.getName().toUpperCase().startsWith(serch.toUpperCase())) {
                результат += values.getName() + " : " + values.getPrice() + " руб" + "\n";
            }
        }
        if (результат.equals("")) {

            return "Бумага не найдена";
        } else
            return результат;

    }

    public String parsMaxAndMinPrice(String text) {
        run();
        if (text.equalsIgnoreCase("/minPrice")) {
            вывод1 = вывод1.stream().sorted((x, y) -> Double.compare(x.getPrice(), y.getPrice())).toList();
        } else if (text.equalsIgnoreCase("/maxPrice")) {
            вывод1 = вывод1.stream().sorted((x, y) -> Double.compare(y.getPrice(), x.getPrice())).toList();
        }
        String viviod = utilsPars.filtr(вывод1);
        return viviod;
    }


    @Override
    public void run() {
        try {
            responsFull();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}