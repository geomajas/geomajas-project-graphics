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
package org.geomajas.graphics.client.service;

import org.geomajas.annotation.Api;
import org.geomajas.graphics.client.controller.MetaController;

/**
 * Factory for the meta-controller. This is a singleton controller that handles activation/deactivation of individual
 * controllers. 
 * 
 * @author Jan De Moerloose
 * @since 1.0.0
 * 
 */
@Api(allMethods = true)
public interface MetaControllerFactory {

	MetaController createController(GraphicsService graphicsService);
}
