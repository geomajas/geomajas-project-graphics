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
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Factory for {@link org.geomajas.graphics.client.controller.GraphicsController} instances.
 * 
 * @param <C> type of graphicsController that will be created
 * @author Jan De Moerloose
 * @since 1.0.0
 */
@Api(allMethods = true)
public interface GraphicsControllerFactory<C extends GraphicsController> {

	/**
	 * Does the factory support this object ?
	 * 
	 * @param object
	 * @return true if supported, false otherwise
	 */
	boolean supports(GraphicsObject object);

	/**
	 * Create a {@link org.geomajas.graphics.client.controller.GraphicsController} for this object and service.
	 * 
	 * @param graphicsService
	 * @param object
	 * @return the controller for this object
	 */
	C createController(GraphicsService graphicsService, GraphicsObject object);
}
