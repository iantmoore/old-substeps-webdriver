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

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.technophobia.webdriver.util.WebDriverContext;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * <p>DefaultWebDriverFactory class.</p>
 *
 * @author ian
 * @version $Id: $Id
 */
public class DefaultWebDriverFactory implements WebDriverFactory {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultWebDriverFactory.class);

    private final WebdriverSubstepsConfiguration configuration;

    /**
     * <p>Constructor for DefaultWebDriverFactory.</p>
     */
    public DefaultWebDriverFactory() {
        this(WebdriverSubstepsPropertiesConfiguration.INSTANCE);
    }

    /**
     * <p>Constructor for DefaultWebDriverFactory.</p>
     *
     * @param configuration a {@link com.technophobia.webdriver.substeps.runner.WebdriverSubstepsConfiguration} object.
     */
    public DefaultWebDriverFactory(WebdriverSubstepsConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * <p>createWebDriver.</p>
     *
     * @return a {@link org.openqa.selenium.WebDriver} object.
     */
    public WebDriver createWebDriver() {

        final WebDriver webDriver;

        switch (configuration.driverType()) {
            case FIREFOX: {
                webDriver = new FirefoxDriver();
                break;
            }
            case HTMLUNIT: {
                final HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX_3_6);
                htmlUnitDriver.setJavascriptEnabled(!configuration.isJavascriptDisabledWithHTMLUnit());

                // Run via a proxy - HTML unit only for timebeing
                final String proxyHost = configuration.getHtmlUnitProxyHost();
                if (!StringUtils.isEmpty(proxyHost)) {
                    final int proxyPort = configuration.getHtmlUnitProxyPort();
                    htmlUnitDriver.setProxy(proxyHost, proxyPort);
                }

                setDriverLocale(htmlUnitDriver);

                webDriver = htmlUnitDriver;
                break;

            }
            case CHROME: {

                webDriver = new ChromeDriver();
                break;

            }
            case IE: {

                // apparently this is required to get around some IE security
                // restriction.

                final DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
                ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

                LOG.warn("Using IE Webdriver with IGNORING SECURITY DOMAIN");

                webDriver = new InternetExplorerDriver(ieCapabilities);
                break;
            }
            default: {
                throw new IllegalArgumentException("unknown driver type");
            }
        }

        return webDriver;

    }

    /**
     * <p>driverType.</p>
     *
     * @return a {@link com.technophobia.webdriver.substeps.runner.DriverType} object.
     */
    public DriverType driverType() {
        return configuration.driverType();
    }

    /** {@inheritDoc} */
    public void shutdownWebDriver(WebDriverContext webDriverContext) {

        LOG.debug("Shutting WebDriver down");
        WebDriver webDriver = webDriverContext.getWebDriver();
        if (webDriver != null) {
            webDriver.manage().deleteAllCookies();
            webDriver.quit();
        }
    }

    /** {@inheritDoc} */
    public void resetWebDriver(WebDriverContext webDriverContext) {

        LOG.debug("Resetting WebDriver");
        WebDriver webDriver = webDriverContext.getWebDriver();

        // this was the default behaviour previously in Webdriver context

        if(webDriver != null) {
            webDriver.manage().deleteAllCookies();
        }

    }

    /**
     * By default the HtmlUnit driver is set to en-us. This can cause problems
     * with formatters.
     */
    private void setDriverLocale(final WebDriver driver) {

        try {
            final Field field = driver.getClass().getDeclaredField("webClient");
            if (field != null) {
                final boolean original = field.isAccessible();
                field.setAccessible(true);

                final WebClient webClient = (WebClient) field.get(driver);
                if (webClient != null) {
                    webClient.addRequestHeader("Accept-Language", "en-gb");
                }
                field.setAccessible(original);
            } else {
                Assert.fail("Failed to get webclient field to set accept language");
            }
        } catch (final IllegalAccessException ex) {

            LOG.warn(ex.getMessage());

        } catch (final SecurityException e) {

            LOG.warn(e.getMessage());
        } catch (final NoSuchFieldException e) {

            LOG.warn(e.getMessage());
        }
    }

}
