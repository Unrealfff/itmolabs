package org.unrealfff.data.unit;

import java.util.Comparator;

public class SortRouteByDistance implements Comparator<Route> {
    /**
     * comparator for reverse sort
     *
     * @param a
     * @param b
     * @return
     */
    public int compare(Route a, Route b) {
        if (a.getDistance() != null && b.getDistance() != null) {
            return Float.compare(a.getDistance(), b.getDistance());
        }
        else if(a.getDistance() != null) {
            return -1;
        }
        else if(b.getDistance() != null) {
            return 1;
        }
        else{
            return 0;
        }
    }
}