package com.github.cellularprivacy.ocidrestclient.dto;

/**
 * <latmin>,<lonmin>,<latmax>,<lonmax>
 *
 * @see <a href="http://wiki.opencellid.org/wiki/API#Getting_the_list_of_cells_in_a_specified_area">http://wiki.opencellid.org/wiki/API#Getting_the_list_of_cells_in_a_specified_area</a>
 */
public class BoundingBox {

    /**
     * Minimal bounding latitude
     */
    private double latmin;

    /**
     * Minimal bounding longitude
     */
    private double lonmin;

    /**
     * Maximal bounding latitude
     */
    private double latmax;

    /**
     * Maximal bounding longitude
     */
    private double lonmax;

    /**
     * @param latmin Minimal bounding latitude
     * @param lonmin Minimal bounding longitude
     * @param latmax Maximal bounding latitude
     * @param lonmax Maximal bounding longitude
     */
    public BoundingBox(double latmin, double lonmin, double latmax, double lonmax) {
        this.latmin = latmin;
        this.lonmin = lonmin;
        this.latmax = latmax;
        this.lonmax = lonmax;
    }

    public double getLatmin() {
        return latmin;
    }

    public void setLatmin(double latmin) {
        this.latmin = latmin;
    }

    public double getLonmin() {
        return lonmin;
    }

    public void setLonmin(double lonmin) {
        this.lonmin = lonmin;
    }

    public double getLatmax() {
        return latmax;
    }

    public void setLatmax(double latmax) {
        this.latmax = latmax;
    }

    public double getLonmax() {
        return lonmax;
    }

    public void setLonmax(double lonmax) {
        this.lonmax = lonmax;
    }

    @Override
    public String toString() {
        return String.format("%f,%f,%f,%f", latmin, lonmin, latmax, lonmax);
    }
}
