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
package org.geomajas.graphics.client.controller.role;

import org.geomajas.graphics.client.controller.GraphicsController;
import org.geomajas.graphics.client.controller.GraphicsControllerFactory;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.updateable.AnchoredUpdateable;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Factory for the {@link org.geomajas.graphics.client.controller.role.AnchoredUpdateableController}.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class AnchoredUpdateableControllerFactory implements GraphicsControllerFactory {
	
	@Override
	public boolean supports(GraphicsObject object) {
		return object.hasRole(AnchoredUpdateable.TYPE);
	}

	@Override
	public GraphicsController createController(GraphicsService graphicsService, GraphicsObject object) {
		return new AnchoredUpdateableController(object, graphicsService);
	}

}
