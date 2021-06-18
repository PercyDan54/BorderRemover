package me.percydan.borderremover.config;

public interface OptionAccess {
    int getGenOffset();

    void setGenOffset(int value);

    boolean getEnableFarlands();

    void setEnableFarlands(boolean value);

    boolean getShiftFarlands();

    void setShiftFarlands(boolean value);
}
