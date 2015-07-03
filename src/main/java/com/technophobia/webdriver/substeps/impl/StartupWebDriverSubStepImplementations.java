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
package com.technophobia.webdriver.substeps.impl;

import com.google.common.base.Supplier;
import com.technophobia.substeps.model.Scope;
import com.technophobia.substeps.model.SubSteps.Step;
import com.technophobia.substeps.model.SubSteps.StepImplementations;
import com.technophobia.substeps.runner.ExecutionContext;
import com.technophobia.webdriver.substeps.runner.DefaultExecutionSetupTearDown;
import com.technophobia.webdriver.substeps.runner.WebDriverFactory;
import com.technophobia.webdriver.util.WebDriverContext;

/**
 * <p>StartupWebDriverSubStepImplementations class.</p>
 *
 * @author ian
 * @version $Id: $Id
 */
@StepImplementations(requiredInitialisationClasses = DefaultExecutionSetupTearDown.class)
public class StartupWebDriverSubStepImplementations extends
        AbstractWebDriverSubStepImplementations {

    /**
     * <p>Constructor for StartupWebDriverSubStepImplementations.</p>
     */
    public StartupWebDriverSubStepImplementations() {
        super();
    }


    /**
     * <p>Constructor for StartupWebDriverSubStepImplementations.</p>
     *
     * @param webDriverContextSupplier a {@link com.google.common.base.Supplier} object.
     */
    public StartupWebDriverSubStepImplementations(
            final Supplier<WebDriverContext> webDriverContextSupplier) {
        super(webDriverContextSupplier);
    }


    /**
     * Starts a new web driver session
     *
     * @example Startup
     * @section Startup / Shutodwn
     */
    @Step("Startup")
    public void startup() {

        // WebDriverContext.initialise(Configuration.driverType());
    }


    /**
     * Shuts down the current web driver session
     *
     * @example Shutdown
     * @section Startup / Shutodwn
     */
    @Step("Shutdown")
    public void shutdown() {

        WebDriverFactory factory = (WebDriverFactory)ExecutionContext.get(Scope.SUITE, WebDriverFactory.WEB_DRIVER_FACTORY_KEY);

        factory.shutdownWebDriver(webDriverContext());
    }
}
