package com.technophobia.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by ian on 31/08/15.
 */

public abstract class BaseBy extends By {

    private static final Logger logger = LoggerFactory.getLogger(BaseBy.class);

    @Override
    public final List<WebElement> findElements(final SearchContext context) {

        List<WebElement> matchingElems = null;
        try {
            matchingElems = findElementsBy(context);
        }
        catch (StaleElementReferenceException e){
            logger.debug("StaleElementReferenceException looking for elements");
        }

        // returning non null will prevent any wait from waiting..

        return matchingElems;
    }

    public abstract List<WebElement> findElementsBy(final SearchContext context);
}
