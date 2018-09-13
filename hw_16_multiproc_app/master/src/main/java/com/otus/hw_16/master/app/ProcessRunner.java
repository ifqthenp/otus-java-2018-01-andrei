package com.otus.hw_16.master.app;

import java.io.IOException;

public interface ProcessRunner {

    void start(String command) throws IOException;

    void stop();

    String getOutput();

}
