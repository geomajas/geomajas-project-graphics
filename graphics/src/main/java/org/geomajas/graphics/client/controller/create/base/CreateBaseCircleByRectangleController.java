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
package org.geomajas.graphics.client.controller.create.base;

import org.geomajas.graphics.client.controller.create.CreateObjectByRectangleController;
import org.geomajas.graphics.client.object.base.BaseCircleObject;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Controller that creates a {@link org.geomajas.graphics.client.object.base.BaseCircle}.
 *
 * @author Jan Venstermans
 *
 */
public class CreateBaseCircleByRectangleController extends CreateObjectByRectangleController<BaseCircleObject> {

	public CreateBaseCircleByRectangleController(GraphicsService graphicsService) {
		super(graphicsService);
	}

	@Override
	protected BaseCircleObject createObjectWithoutBounds() {
		BaseCircleObject ellipse = new BaseCircleObject(0, 0, 0);
		return ellipse;
	}
}