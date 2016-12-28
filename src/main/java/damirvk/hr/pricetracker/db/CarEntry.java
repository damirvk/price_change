package damirvk.hr.pricetracker.db;

/**
 * Created by SomeUser on 27.12.2016..
 */
public class CarEntry {

    private int iid;
    private String url;
    private String start_price;
    private String currentPrice;
    private String shortTitle;

    public CarEntry() {
    }

    public CarEntry(String url, String start_price, String currentPrice, String shortTitle) {
        this.url = url;
        this.start_price = start_price;
        this.currentPrice = currentPrice;
        this.shortTitle = shortTitle;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStart_price() {
        return start_price;
    }

    public void setStart_price(String start_price) {
        this.start_price = start_price;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    @Override
    public String toString() {
        return "CarEntry{" +
                "iid=" + iid +
                ", url='" + url + '\'' +
                ", start_price='" + start_price + '\'' +
                ", currentPrice='" + currentPrice + '\'' +
                '}';
    }
}
