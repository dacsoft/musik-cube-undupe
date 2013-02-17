package com.dacsoft.musikcube.undupe;

import java.util.Comparator;

public class DupeComparator implements Comparator<DupeDetail> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(DupeDetail o1, DupeDetail o2) {
        int diff = o1.artist.compareTo(o2.artist);
        if (diff != 0) {
            return diff;
        }
        diff = o1.title.compareTo(o2.title);
        if (diff != 0) {
            return diff;
        }
        diff = o1.album.compareTo(o2.album);
        if (diff != 0) {
            return diff;
        }


        return diff;
    }

}
