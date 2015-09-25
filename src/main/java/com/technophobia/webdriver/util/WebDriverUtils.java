package com.technophobia.webdriver.util;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ian on 27/07/15.
 */
public class WebDriverUtils {


    private static final Logger logger = LoggerFactory.getLogger(WebDriverUtils.class);

    /**
     * Checks that a list of WebElements only contains one (not empty and not
     * too many).
     *
     * @param msg a {@link java.lang.String} object.
     * @param matchingElems a {@link java.util.List} object.
     * @return a {@link org.openqa.selenium.WebElement} object.
     */
    public static WebElement checkForOneMatchingElement(final String msg, final List<WebElement> matchingElems) {
        WebElement rtn = null;
        if (matchingElems != null && matchingElems.size() > 1) {
            // ambiguous
            Assert.fail("Found too many elements that meet this criteria");
            // TODO - need some more debug here
        }

        else if (matchingElems != null) {
            rtn = matchingElems.get(0);
        }

        Assert.assertNotNull(msg, rtn);
        return rtn;
    }



}
