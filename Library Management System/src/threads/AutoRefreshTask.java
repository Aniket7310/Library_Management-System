package threads;

import ui.LibraryGUI;

public class AutoRefreshTask implements Runnable {

    private final LibraryGUI gui;

    public AutoRefreshTask(LibraryGUI gui) {
        this.gui = gui;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(4000); // 4 seconds
                gui.loadBooksInTable();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
