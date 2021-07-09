package me.percydan.borderremover.config;

public interface OptionAccess {
    int getGenOffset();

    void setGenOffset(int value);

    boolean getEnableFarlands();

    void setEnableFarlands(boolean value);

    String getXZScale();

    void setXZScale(String value);

    String getYScale();

    void setYScale(String value);
}
