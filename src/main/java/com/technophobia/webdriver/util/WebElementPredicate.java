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

import org.openqa.selenium.WebElement;

import com.google.common.base.Predicate;

/**
 * <p>WebElementPredicate interface.</p>
 *
 * @author ian
 * @version $Id: $Id
 */
public interface WebElementPredicate extends Predicate<WebElement> {
    /**
     * <p>getTagname.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getTagname();


    /**
     * <p>getDescription.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getDescription();

}
