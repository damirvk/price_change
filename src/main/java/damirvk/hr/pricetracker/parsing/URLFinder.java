package damirvk.hr.pricetracker.parsing;

import android.provider.MediaStore;

import com.annimon.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import damirvk.hr.pricetracker.db.CarEntry;

/**
 * Created by SomeUser on 27.12.2016..
 */
public class URLFinder {


    public CarEntry getDataFromUrl(String carId) {
        try {
            String fullURL = "http://suchen.mobile.de/fahrzeuge/details.html?id=" + carId;
            Document doc = Jsoup
                    .connect(fullURL)
                    .get();
            Elements links = doc.select(".rbt-prime-price");
            if (links.size() == 0) {
                return null;
            } else {
                String mainTitle = doc.select("#rbt-ad-title").text();
                String currentPrice = doc.select(".rbt-prime-price").text();
                CarEntry carEntry = new CarEntry();
                carEntry.setUrl(fullURL);
                carEntry.setCurrentPrice(currentPrice);
                carEntry.setStart_price(currentPrice);
                carEntry.setShortTitle(mainTitle);
                return carEntry;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
