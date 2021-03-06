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
package org.geomajas.graphics.client.controller.role;

import org.geomajas.graphics.client.controller.GraphicsController;
import org.geomajas.graphics.client.controller.GraphicsControllerFactory;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.HasMarker;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Factory for the {@link AnchoredDragController}.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class AnchoredDragControllerFactory implements GraphicsControllerFactory {
	
	@Override
	public boolean supports(GraphicsObject object) {
		return object.hasRole(HasMarker.TYPE);
	}

	@Override
	public GraphicsController createController(GraphicsService graphicsService, GraphicsObject object) {
		return new AnchoredDragController(object, graphicsService);
	}

}
