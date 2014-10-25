/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2014 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client.controller.create;

import org.geomajas.graphics.client.object.GRectangle;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Controller that creates a {@link GRectangle}.
 * 
 * @author Jan De Moerloose
 * 
 */

public class CreateRectangleController extends CreateBoundedObjectController<GRectangle> {

	public CreateRectangleController(GraphicsService graphicsService) {
		super(graphicsService);
	}

	@Override
	protected GRectangle createObjectWithoutBounds() {
		return new GRectangle(0, 0, 0, 0);
	}
}