package me.percydan.borderremover.config;

public interface OptionAccess {
    int getGenOffset();
    boolean getEnableFarlands();
    boolean getShiftFarlands();
    void setGenOffset(int value);
    void setEnableFarlands(boolean value);
    void setShiftFarlands(boolean value);
}
