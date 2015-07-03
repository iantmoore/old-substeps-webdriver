/*
 *	Copyright Technophobia Ltd 2012
 *
 *   This file is part of Substeps.
 *
 *    Substeps is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    Substeps is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with Substeps.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.technophobia.webdriver.util;

import com.google.common.base.Function;
import com.technophobia.webdriver.substeps.runner.Condition;
import com.technophobia.webdriver.substeps.runner.WebdriverSubstepsPropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>ElementLocators class.</p>
 *
 * @author ian
 * @version $Id: $Id
 */
public class ElementLocators {

    private static final Logger LOG = LoggerFactory.getLogger(ElementLocators.class);

    private final static int timeout = 10;
    final static long polltime = 1000;


    /**
     * <p>waitForElement.</p>
     *
     * @param by a {@link org.openqa.selenium.By} object.
     * @param webDriver a {@link org.openqa.selenium.WebDriver} object.
     * @return a {@link org.openqa.selenium.WebElement} object.
     */
    public static WebElement waitForElement(final By by, WebDriver webDriver) {
        return waitForElement(by, WebdriverSubstepsPropertiesConfiguration.INSTANCE.defaultTimeout(), webDriver);
    }


    /**
     * <p>waitForElement.</p>
     *
     * @param by a {@link org.openqa.selenium.By} object.
     * @param timeOutSeconds a long.
     * @param webDriver a {@link org.openqa.selenium.WebDriver} object.
     * @return a {@link org.openqa.selenium.WebElement} object.
     */
    public static WebElement waitForElement(final By by, final long timeOutSeconds, WebDriver webDriver) {
        final WebDriverWait wait = new WebDriverWait(webDriver, timeOutSeconds);
        final Function<WebDriver, WebElement> condition2 = new Function<WebDriver, WebElement>() {
            public WebElement apply(final WebDriver driver) {
                return driver.findElement(by);
            }
        };

        // Implementations should wait until the condition evaluates to a value
        // that is neither null nor false.
        return waitUntil(wait, condition2, webDriver);
    }


    /**
     * <p>waitUntil.</p>
     *
     * @param wait a {@link org.openqa.selenium.support.ui.WebDriverWait} object.
     * @param condition a {@link com.google.common.base.Function} object.
     * @param webDriver a {@link org.openqa.selenium.WebDriver} object.
     * @return a {@link org.openqa.selenium.WebElement} object.
     */
    public static WebElement waitUntil(final WebDriverWait wait, final Function<WebDriver, WebElement> condition,
                                       WebDriver webDriver) {
        WebElement elem = null;
        try {
            elem = wait.until(condition);
        } catch (final TimeoutException e) {
            LOG.debug("timed out page src:\n" + webDriver.getPageSource());
        }
        return elem;
    }


    /**
     * <p>waitForCondition.</p>
     *
     * @param condition a {@link com.technophobia.webdriver.substeps.runner.Condition} object.
     * @param webDriver a {@link org.openqa.selenium.WebDriver} object.
     * @return a boolean.
     */
    public static boolean waitForCondition(final Condition condition, WebDriver webDriver) {

        int count = 0;

        while (!condition.conditionMet()) {

            count++;

            try {
                Thread.sleep(polltime);
            } catch (final InterruptedException ignore) {
                // Do Nothing
            }

            if (count == timeout) {
                return false;
            }
        }

        return true;
    }
}
