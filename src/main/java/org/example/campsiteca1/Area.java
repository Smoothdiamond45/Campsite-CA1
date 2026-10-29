package org.example.campsiteca1;

public class Area {

    private final CustomLinkedList<Plot> plots = new CustomLinkedList<>();
    private String identifier;          // e.g. "Lake View Area 1"
    private String description;
    private Campsite campsite;     // the campsite this area belongs to (set by Campsite.addArea)

    public Area(String identifier, String description) {
        this.identifier = identifier;
        this.description = description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Campsite getCampsite() {
        return campsite;
    }

    void setCampsite(Campsite campsite) {
        this.campsite = campsite;
    }













    // ---------- Plots (each area owns its own list) ----------

    public CustomLinkedList<Plot> getPlots() {
        return plots;
    }

    public int getPlotCount() {
        return plots.size();
    }

  /*  public void addPlot(Plot plot) {
        plots.add(plot);
        plot.setArea(this);
    }

    /** Returns null if there is no plot with that identifier in this area.
    public Plot findPlot(String id) {
        return plots.find(p -> p.getIdentifier().equalsIgnoreCase(id));
    }

    public boolean removePlot(Plot plot) {
        boolean removed = plots.delete(plot);
        if (removed) plot.setArea(null);
        return removed;
    }*/

    /**
     * Shown in ComboBoxes, so two campsites can both have an "Area 1" without confusion.
     */
    @Override
    public String toString() {
        return campsite == null ? identifier : identifier + " (" + campsite.getName() + ")";
    }
}
