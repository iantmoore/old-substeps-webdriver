/*
 * Copyright Technophobia Ltd 2013
 *
 *     This file is part of Substeps.
 *
 *     Substeps is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version
 *
 *     Substeps is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with Substeps.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.technophobia.webdriver.substeps.runner;


import com.technophobia.webdriver.util.WebDriverContext;
import org.openqa.selenium.WebDriver;

/**
 * <p>WebDriverFactory interface.</p>
 *
 * @author ian
 * @version $Id: $Id
 */
public interface WebDriverFactory {

    /** Constant <code>WEB_DRIVER_FACTORY_KEY="webDriverFactory"</code> */
    static final String WEB_DRIVER_FACTORY_KEY = "webDriverFactory";

    /**
     * <p>createWebDriver.</p>
     *
     * @return a {@link org.openqa.selenium.WebDriver} object.
     */
    WebDriver createWebDriver();

    /**
     * <p>driverType.</p>
     *
     * @return a {@link com.technophobia.webdriver.substeps.runner.DriverType} object.
     */
    DriverType driverType();

    /**
     * <p>shutdownWebDriver.</p>
     *
     * @param webDriverContext a {@link com.technophobia.webdriver.util.WebDriverContext} object.
     */
    void shutdownWebDriver(WebDriverContext webDriverContext);

    /**
     * <p>resetWebDriver.</p>
     *
     * @param webDriverContext a {@link com.technophobia.webdriver.util.WebDriverContext} object.
     */
    boolean resetWebDriver(WebDriverContext webDriverContext);
}
