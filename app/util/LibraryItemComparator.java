package util;

import model.LibraryItem;

import java.util.Comparator;

public class LibraryItemComparator implements Comparator<LibraryItem> {

    @Override
    public int compare(LibraryItem o1, LibraryItem o2) {
        return o1.getFine() > o2.getFine() ? 1 : 0;
    }
}
