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
package com.technophobia.webdriver.substeps.runner;

import java.io.File;
import java.net.URL;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.technophobia.substeps.model.Configuration;

/**
 * <p>WebdriverSubstepsPropertiesConfiguration class.</p>
 *
 * @author ian
 * @version $Id: $Id
 */
public enum WebdriverSubstepsPropertiesConfiguration implements WebdriverSubstepsConfiguration {

    INSTANCE; // uninstantiable

    private final Logger LOG = LoggerFactory.getLogger(WebdriverSubstepsPropertiesConfiguration.class);

    private final Class<? extends WebDriverFactory> webdriverFactoryClass;

    private final String baseUrl;
    private final String driverLocale;
    private final boolean reuseWebdriver;
    private final String htmlUnitProxyHost;
    private final boolean shutdownWebdriver;
    private final boolean htmlunitDisableJs;
    private final Integer htmlUnitProxyPort;
    private final DefaultDriverType driverType;
    private final boolean visualWebdriverCloseOnFail;

    private long defaultWebDriverTimeoutSecs = 10; //TODO: why's this one special?

    private WebdriverSubstepsPropertiesConfiguration() {

        final URL defaultURL = getClass().getResource("/default-webdriver-substeps.properties");

        Assert.assertNotNull(defaultURL);

        Configuration.INSTANCE.addDefaultProperties(defaultURL, "default-webdriver");

        baseUrl = determineBaseURL(Configuration.INSTANCE.getString("base.url"));

        driverType = DefaultDriverType.valueOf(Configuration.INSTANCE.getString("driver.type").toUpperCase());

        driverLocale = Configuration.INSTANCE.getString("webdriver.locale");

        shutdownWebdriver = Configuration.INSTANCE.getBoolean("webdriver.shutdown");
        visualWebdriverCloseOnFail = Configuration.INSTANCE.getBoolean("visual.webdriver.close.on.fail");

        reuseWebdriver = Configuration.INSTANCE.getBoolean("webdriver.reuse");

        defaultWebDriverTimeoutSecs = Configuration.INSTANCE.getInt("default.webdriver.timeout.secs");

        htmlunitDisableJs = Configuration.INSTANCE.getBoolean("htmlunit.disable.javascript");

        htmlUnitProxyHost = Configuration.INSTANCE.getString("htmlunit.proxy.host");
        htmlUnitProxyPort = Configuration.INSTANCE.getInt("htmlunit.proxy.port");

        try {
            webdriverFactoryClass = Class.forName(Configuration.INSTANCE.getString("webdriver.factory.class")).asSubclass(WebDriverFactory.class);
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("'webdriver.factory.class' is invalid.", ex);
        }

        LOG.info("Using properties:\n" + Configuration.INSTANCE.getConfigurationInfo());
    }


    /**
     * <p>baseURL.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String baseURL() {
        return baseUrl;
    }


    /**
     * <p>driverType.</p>
     *
     * @return a {@link com.technophobia.webdriver.substeps.runner.DefaultDriverType} object.
     */
    public DefaultDriverType driverType() {
        return driverType;
    }


    /**
     * <p>driverLocale.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String driverLocale() {
        return driverLocale;
    }


    /**
     * <p>shutDownWebdriver.</p>
     *
     * @return a boolean.
     */
    public boolean shutDownWebdriver() {
        return shutdownWebdriver;
    }


    /**
     * <p>isJavascriptDisabledWithHTMLUnit.</p>
     *
     * @return a boolean.
     */
    public boolean isJavascriptDisabledWithHTMLUnit() {
        return htmlunitDisableJs;
    }


    /**
     * <p>closeVisualWebDriveronFail.</p>
     *
     * @return a boolean.
     */
    public boolean closeVisualWebDriveronFail() {
        return visualWebdriverCloseOnFail;
    }


    /**
     * <p>reuseWebDriver.</p>
     *
     * @return a boolean.
     */
    public boolean reuseWebDriver() {
        return reuseWebdriver;
    }


    /**
     * <p>defaultTimeout.</p>
     *
     * @return a long.
     */
    public long defaultTimeout() {
        return defaultWebDriverTimeoutSecs;
    }


    /**
     * <p>Getter for the field <code>htmlUnitProxyHost</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getHtmlUnitProxyHost() {
        return htmlUnitProxyHost;
    }


    /**
     * <p>Getter for the field <code>htmlUnitProxyPort</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getHtmlUnitProxyPort() {
        return htmlUnitProxyPort;
    }

    /**
     * <p>getWebDriverFactoryClass.</p>
     *
     * @return a {@link java.lang.Class} object.
     */
    public Class<? extends WebDriverFactory> getWebDriverFactoryClass() {
        return webdriverFactoryClass;
    }

    private String determineBaseURL(final String baseUrlProperty) {

        final String resolvedBaseUrl;
        final String property = removeTrailingSlash(baseUrlProperty);

        if (!property.startsWith("http") && !property.startsWith("file://")) {

            resolvedBaseUrl = removeTrailingSlash(new File(property).toURI()
                    .toString());
        } else {
            resolvedBaseUrl = property;
        }

        return resolvedBaseUrl;
    }


    private String removeTrailingSlash(final String string) {
        if (string.endsWith("/")) {
            return string.substring(0, string.length() - 1);
        }
        return string;
    }
}
