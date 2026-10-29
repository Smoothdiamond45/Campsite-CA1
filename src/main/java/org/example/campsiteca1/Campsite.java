package org.example.campsiteca1;

public class Campsite {

    public String name;
    public String address;
    public String phone;
    public int stars;
    private CustomLinkedList<String> facilities;
    private final CustomLinkedList<Area> areas = new CustomLinkedList<>();

    public CustomLinkedList<Area> getAreas() { return areas; }

    public void addArea(Area area) {
        areas.add(area);
        area.setCampsite(this);   // so Area.toString() can show the campsite name
    }

    public Campsite(String name, String address, String phone, int stars, CustomLinkedList<String> facilities) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.stars = stars;
        this.facilities = facilities;
    }

    public String getName() {return name;}
    public String getAddress() {return address;}
    public String getPhone() {return phone;}
    public int getStars() {return stars;}
    public CustomLinkedList<String> getFacilities() {return facilities;}

     
    public String getFacilitiesText() {
        StringBuilder sb = new StringBuilder();
        for (String f : facilities) {          // works because your list is Iterable
            if (sb.length() > 0) sb.append(", ");
            sb.append(f);
        }
        return sb.toString();
    }
    @Override
    public String toString() {
        return null;
    }


}
