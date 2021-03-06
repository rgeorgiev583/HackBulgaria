package com.hackbulgaria.corejava.exam1.logger;

import org.junit.Test;

public class LoggerTestCase {
    
    @Test
    public void test() {
        Logger logger = new Logger(3); //set LEVEL to 3
        logger = new Logger();//I can also call 'new Logger()', in which case use a default LEVEL of 3

        logger.log(2, "Somewhat important message"); //gets logged as "2 => Somewhat important message"
        logger.log(3, "Less important message"); // also gets logged!
        logger.log(5, "Not important"); //this is less important than LEVEL, so it will **not be logged**.
        logger.log("Meh"); //overload without a LEVEL parameter, use 3 as default.

        logger.setLevel(2);
        logger.log("My message"); //does not get printed!
        
        Logger dateLogger = new DateLogger(); //I am not obliged to set a log level, by default your class should print everything in this case
        dateLogger.log("My message"); //this is an overload without a LEVEL parameter. In this situation, assume the LEVEL given to you is 3.
        //|22:14:01 14.06.2014| 3 => My message
    }

}
