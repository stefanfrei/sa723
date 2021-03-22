package org.schlibbuz.sa723.web.components.factory;

import java.util.Date;
import java.util.TimerTask;

public class FileSystemPoll extends TimerTask {
    

    FileSystemPoll() {}

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " fs polled @"+ new Date());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
