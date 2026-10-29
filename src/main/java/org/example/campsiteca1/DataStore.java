package org.example.campsiteca1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.ObjectInputFilter;
import java.util.Date;

public class DataStore {

    private static final DataStore INSTANCE = new DataStore();

    public static DataStore get() {
        return INSTANCE;
    }

    // Real
    private final CustomLinkedList<Campsite> campsites = new CustomLinkedList<>();
    private final CustomLinkedList<Area> areas = new CustomLinkedList<>();

    // Show
    private final ObservableList<Campsite> campsiteItems = FXCollections.observableArrayList();
    private final ObservableList<Area> areasItems = FXCollections.observableArrayList();

    private DataStore() {
    }
    //Campsite
    public void addCampsite(Campsite c) {
        campsites.add(c);
        campsiteItems.add(c);
    }

    public boolean removeCampsite(Campsite c) {
        campsiteItems.remove(c);
        return campsites.delete(c);
    }

    public CustomLinkedList<Campsite> getCampsites() {
        return campsites;
    }

    public ObservableList<Campsite> getCampsiteItems() {
        return campsiteItems;
    }


    //Area

    public void addArea(Area a) {
        areas.add(a);
        areasItems.add(a);
    }

    public boolean removeArea(Area a) {
        areasItems.remove(a);
        return areas.delete(a);
    }

    public CustomLinkedList<Area> getAreas() {
        return areas;
    }

    public ObservableList<Area> getAreasItems() {
        return areasItems;
    }

}
class Plot {
    String id;
    int capacity;
    String type;           // tent, caravan, motorhome...
    CustomLinkedList<Booking> bookings;
  //  boolean isAvailable(Date from, Date to);   // check overlaps
  //  void addBooking(Booking b);
   // void removeBooking(Booking b);
}


class Guest {
    String id, name, email, phone;
    CustomLinkedList<Booking> bookings;
}

class Booking {
    String id;
    Guest guest;
    Plot plot;
    Date start, end;       // or start date + number of nights
    int numPeople;
    ObjectInputFilter.Status status;         // enum is fine (not a collection)
}