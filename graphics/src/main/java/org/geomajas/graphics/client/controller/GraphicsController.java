/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2015 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client.controller;

import org.geomajas.annotation.Api;

/**
 * Controller for graphics objects.
 * 
 * @author Jan De Moerloose
 * @since 1.0.0
 * 
 */
@Api(allMethods = true)
public interface GraphicsController {

	/**
	 * Activate the controller. Typically renders the GUI handlers.
	 * 
	 * @param active
	 */
	void setActive(boolean active);

	/**
	 * Is the controller active ?
	 * 
	 * @return
	 */
	boolean isActive();

	/**
	 * Destroy any GUI resources associated with this controller.
	 */
	void destroy();
}
