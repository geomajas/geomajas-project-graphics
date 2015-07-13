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
package org.geomajas.graphics.client.controller.rotate;

import org.geomajas.graphics.client.controller.RoleGraphicsControllerFactory;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.CoordinateBased;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Factory for the {@link RotateController}.
 * 
 * @author Jan De Moerloose
 * 
 */
public class RotateControllerFactory extends RoleGraphicsControllerFactory<CoordinateBased, RotateController> {
	
	public RotateControllerFactory() {
		super(CoordinateBased.TYPE);
	}

	@Override
	public RotateController createController(GraphicsService graphicsService, GraphicsObject object) {
		return new RotateController(object, graphicsService);
	}

}
