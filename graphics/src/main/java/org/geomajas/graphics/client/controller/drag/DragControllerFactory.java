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
package org.geomajas.graphics.client.controller.drag;

import org.geomajas.graphics.client.controller.GraphicsController;
import org.geomajas.graphics.client.controller.GraphicsControllerFactory;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Factory for the {@link org.geomajas.graphics.client.controller.resize.ResizeController}.
 * 
 * @author Jan De Moerloose
 * 
 */
public class DragControllerFactory implements GraphicsControllerFactory {
	
	private boolean dragOnActivate;
	
	public DragControllerFactory() {
		this(true);
	}

	public DragControllerFactory(boolean dragOnActivate) {
		this.dragOnActivate = dragOnActivate;
	}

	@Override
	public boolean supports(GraphicsObject object) {
		return object.hasRole(Draggable.TYPE) && !object.hasRole(Resizable.TYPE);
	}

	@Override
	public GraphicsController createController(GraphicsService graphicsService, GraphicsObject object) {
		return new DragController(object, graphicsService, dragOnActivate);
	}

}
