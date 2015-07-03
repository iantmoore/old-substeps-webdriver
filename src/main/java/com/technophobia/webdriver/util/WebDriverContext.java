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

import com.technophobia.webdriver.substeps.runner.Condition;
import com.technophobia.webdriver.substeps.runner.DriverType;
import com.technophobia.webdriver.substeps.runner.WebdriverSubstepsPropertiesConfiguration;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * A container used to hold the webdriver instance and the current element used
 * by step implementations.
 *
 * @author imoore
 * @version $Id: $Id
 */
public class WebDriverContext {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverContext.class);

    /** Constant <code>EXECUTION_CONTEXT_KEY="_webdriver_context_key"</code> */
    public static final String EXECUTION_CONTEXT_KEY = "_webdriver_context_key";

    private final DriverType driverType;
    private final WebDriver webDriver;

    private WebElement currentElement = null;
    private boolean failed = false;

    // TODO - this should be put in the execution context....
    private Map<String, WebElement> elementStash = null;


    /**
     * <p>Constructor for WebDriverContext.</p>
     *
     * @param driverType a {@link com.technophobia.webdriver.substeps.runner.DriverType} object.
     * @param webDriver a {@link org.openqa.selenium.WebDriver} object.
     */
    public WebDriverContext(final DriverType driverType, final WebDriver webDriver) {
        this.driverType = driverType;
        this.webDriver = webDriver;
    }


    /**
     * <p>Getter for the field <code>currentElement</code>.</p>
     *
     * @return a {@link org.openqa.selenium.WebElement} object.
     */
    public WebElement getCurrentElement() {
        Assert.assertNotNull("expecting current element not to be null", this.currentElement);
        return this.currentElement;
    }


    /**
     * <p>Setter for the field <code>currentElement</code>.</p>
     *
     * @param currentElement a {@link org.openqa.selenium.WebElement} object.
     */
    public void setCurrentElement(final WebElement currentElement) {
        this.currentElement = currentElement;
    }


    /**
     * <p>Getter for the field <code>webDriver</code>.</p>
     *
     * @return a {@link org.openqa.selenium.WebDriver} object.
     */
    public WebDriver getWebDriver() {
        return this.webDriver;
    }

    /**
     * <p>Getter for the field <code>driverType</code>.</p>
     *
     * @return a {@link com.technophobia.webdriver.substeps.runner.DriverType} object.
     */
    public DriverType getDriverType() {
        return this.driverType;
    }

    /**
     * <p>hasFailed.</p>
     *
     * @return a boolean.
     */
    public boolean hasFailed() {
        return this.failed;
    }


    /**
     * <p>Setter for the field <code>failed</code>.</p>
     */
    public void setFailed() {
        this.failed = true;
    }


    /**
     * <p>waitForElement.</p>
     *
     * @param by a {@link org.openqa.selenium.By} object.
     * @return a {@link org.openqa.selenium.WebElement} object.
     */
    public WebElement waitForElement(final By by) {
        return ElementLocators.waitForElement(by, WebdriverSubstepsPropertiesConfiguration.INSTANCE.defaultTimeout(), this.webDriver);
    }


    /**
     * <p>waitForElement.</p>
     *
     * @param by a {@link org.openqa.selenium.By} object.
     * @param timeOutSeconds a long.
     * @return a {@link org.openqa.selenium.WebElement} object.
     */
    public WebElement waitForElement(final By by, final long timeOutSeconds) {
        return ElementLocators.waitForElement(by, timeOutSeconds, this.webDriver);
    }


    /**
     * <p>waitForCondition.</p>
     *
     * @param condition a {@link com.technophobia.webdriver.substeps.runner.Condition} object.
     * @return a boolean.
     */
    public boolean waitForCondition(final Condition condition) {
        return ElementLocators.waitForCondition(condition, this.webDriver);
    }


    /**
     * <p>stashElement.</p>
     *
     * @param key a {@link java.lang.String} object.
     * @param element a {@link org.openqa.selenium.WebElement} object.
     */
    public void stashElement(final String key, final WebElement element) {

        if (this.elementStash == null) {
            this.elementStash = new HashMap<String, WebElement>();
        }

        if (this.elementStash.containsKey(key)) {

            // do we care ?
            logger.debug("replacing existing object in stash using key: " + key);
        }

        this.elementStash.put(key, element);
    }


    /**
     * <p>getElementFromStash.</p>
     *
     * @param key a {@link java.lang.String} object.
     * @return a {@link org.openqa.selenium.WebElement} object.
     */
    public WebElement getElementFromStash(final String key) {

        final WebElement elem = this.elementStash != null ? this.elementStash.get(key) : null;

        Assert.assertNotNull("Attempt to retrieve a null element from the stash with key: " + key, elem);

        return elem;
    }
}
