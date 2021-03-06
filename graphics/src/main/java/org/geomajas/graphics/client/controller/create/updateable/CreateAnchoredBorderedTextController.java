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

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.controller.create.base.CreateBaseTextController;
import org.geomajas.graphics.client.object.base.BaseTextObject;
import org.geomajas.graphics.client.object.updateable.AnchoredBorderedText;
import org.geomajas.graphics.client.object.updateable.hasmarker.MarkerShape;
import org.geomajas.graphics.client.operation.AddOperation;
import org.geomajas.graphics.client.render.RenderSpace;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Controller that creates a {@link AnchoredBorderedText}.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class CreateAnchoredBorderedTextController extends CreateBaseTextController {

	private MarkerShape markerShape = MarkerShape.SQUARE;

	public CreateAnchoredBorderedTextController(GraphicsService graphicsService) {
		super(graphicsService);
	}

	public void setMarkerShape(MarkerShape markerShape) {
		this.markerShape = markerShape;
	}

	@Override
	protected void addObject(BaseTextObject result) {
		if (result == null) {
			execute(null);
			return;
		}
		Coordinate clickPosition = result.getUserPosition();
		Coordinate screenIconPosition = transform(clickPosition,
				RenderSpace.USER, RenderSpace.SCREEN);
		Coordinate iconPosition = transform(new Coordinate(screenIconPosition.getX(), screenIconPosition.getY() - 40),
				RenderSpace.SCREEN, RenderSpace.USER);
		AnchoredBorderedText anchoredBorderedText =
				new AnchoredBorderedText(iconPosition, result.getText(), 10, clickPosition, markerShape);
		execute(new AddOperation(anchoredBorderedText));
	}
}
