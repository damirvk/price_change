package damirvk.hr.pricetracker.parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import damirvk.hr.pricetracker.db.CarEntry;

/**
 * Created by SomeUser on 28.12.2016..
 */
public class ChangedData {

    private List<CarEntry> soldCars = new ArrayList<>();
    private List<CarEntry> priceDropped = new ArrayList<>();
    private List<CarEntry> dbEntries;

    public ChangedData(List<CarEntry> dbEntries) {
        this.dbEntries = dbEntries;
        try {
            for (CarEntry dbEntry : dbEntries) {
                int idStart = dbEntry.getUrl().indexOf("id=") + 3;
                String carId = dbEntry.getUrl().substring(idStart, idStart + 9);
                CarEntry freshEntry = new URLFinderAsync().execute(carId).get();
                if (!dbEntry.getStart_price().equals(freshEntry.getCurrentPrice())) {
                    priceDropped.add(freshEntry);
                } else if (freshEntry == null) {
                    soldCars.add(dbEntry);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public List<CarEntry> getSoldCars() {
        return soldCars;
    }

    public List<CarEntry> getPriceDropped() {
        return priceDropped;
    }
}
