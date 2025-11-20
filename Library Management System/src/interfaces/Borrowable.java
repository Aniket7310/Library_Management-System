package interfaces;

import exceptions.BookNotAvailableException;

public interface Borrowable {
    boolean isAvailable();
    void borrow() throws BookNotAvailableException;
    void giveBack();
}
