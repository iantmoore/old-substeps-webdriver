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

/**
 * <p>WebDriverSubstepsException class.</p>
 *
 * @author imoore
 * @version $Id: $Id
 */
public class WebDriverSubstepsException extends RuntimeException {

    /**
     * <p>Constructor for WebDriverSubstepsException.</p>
     */
    public WebDriverSubstepsException() {
        super();
    }


    /**
     * <p>Constructor for WebDriverSubstepsException.</p>
     *
     * @param msg a {@link java.lang.String} object.
     */
    public WebDriverSubstepsException(final String msg) {
        super(msg);

    }
}
