package model;

public interface Mapable    {
    default int getBoxColor(int x, int y) {
        return 0;
    }
}
