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
package org.geomajas.graphics.client.controller.create.updateable;

import java.util.List;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.controller.create.base.CreateBaseIconController;
import org.geomajas.graphics.client.object.base.BaseIcon;
import org.geomajas.graphics.client.object.updateable.AnchoredIcon;
import org.geomajas.graphics.client.object.updateable.anchored.MarkerShape;
import org.geomajas.graphics.client.operation.AddOperation;
import org.geomajas.graphics.client.render.RenderSpace;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Controller that creates a {@link org.geomajas.graphics.client.object.base.BaseIcon}.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class CreateAnchoredIconController extends CreateBaseIconController {

	private MarkerShape markerShape;

	public CreateAnchoredIconController(GraphicsService graphicsService, int width, int height, List<String> hrefs) {
		super(graphicsService, width, height, hrefs);
		popup.setMarkerSectionVisible(true);
	}

	@Override
	public void createIconInContainer(String iconUrl, MarkerShape markerShape) {
		this.markerShape = markerShape;
		super.createIconInContainer(iconUrl, markerShape);
	}

	@Override
	protected void addObject(BaseIcon result) {
		Coordinate clickPosition = result.getUserPosition();
		Coordinate screenIconPosition = transform(clickPosition,
				RenderSpace.USER, RenderSpace.SCREEN);
		Coordinate iconPosition = transform(new Coordinate(screenIconPosition.getX(), screenIconPosition.getY() - 40),
				RenderSpace.SCREEN, RenderSpace.USER);
		AnchoredIcon anchoredIcon = new AnchoredIcon(iconPosition, (int) result.getBounds().getWidth(),
				(int) result.getBounds().getHeight(), result.getHref(), clickPosition, markerShape);
		execute(new AddOperation(anchoredIcon));
	}
}
