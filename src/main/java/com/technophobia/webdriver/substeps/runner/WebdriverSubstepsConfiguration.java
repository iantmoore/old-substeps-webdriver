package com.technophobia.webdriver.substeps.runner;

/**
 * User: dmoss
 * Date: 20/05/13
 * Time: 21:58
 *
 * @author ian
 * @version $Id: $Id
 */
public interface WebdriverSubstepsConfiguration {
    /**
     * <p>baseURL.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String baseURL();

    /**
     * <p>driverType.</p>
     *
     * @return a {@link com.technophobia.webdriver.substeps.runner.DefaultDriverType} object.
     */
    DefaultDriverType driverType();

    /**
     * <p>driverLocale.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String driverLocale();

    /**
     * <p>shutDownWebdriver.</p>
     *
     * @return a boolean.
     */
    boolean shutDownWebdriver();

    /**
     * <p>isJavascriptDisabledWithHTMLUnit.</p>
     *
     * @return a boolean.
     */
    boolean isJavascriptDisabledWithHTMLUnit();

    /**
     * <p>closeVisualWebDriveronFail.</p>
     *
     * @return a boolean.
     */
    boolean closeVisualWebDriveronFail();

    /**
     * <p>reuseWebDriver.</p>
     *
     * @return a boolean.
     */
    boolean reuseWebDriver();

    /**
     * <p>defaultTimeout.</p>
     *
     * @return a long.
     */
    long defaultTimeout();

    /**
     * <p>getHtmlUnitProxyHost.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getHtmlUnitProxyHost();

    /**
     * <p>getHtmlUnitProxyPort.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getHtmlUnitProxyPort();

    /**
     * <p>getWebDriverFactoryClass.</p>
     *
     * @return a {@link java.lang.Class} object.
     */
    Class<? extends WebDriverFactory> getWebDriverFactoryClass();
}
