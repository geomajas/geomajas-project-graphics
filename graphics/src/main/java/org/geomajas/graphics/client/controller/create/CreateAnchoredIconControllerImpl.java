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

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.Draggable;
import org.geomajas.graphics.client.object.GIcon;
import org.geomajas.graphics.client.object.anchor.Anchored;
import org.geomajas.graphics.client.object.anchor.ResizableAnchorer;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer.Space;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.shape.MarkerShape;

import java.util.List;

/**
 * Controller that creates an anchored {@link GIcon}.
 * 
 * @author Jan De Moerloose
 * 
 */
public class CreateAnchoredIconControllerImpl extends CreateIconControllerImpl {
	
	public CreateAnchoredIconControllerImpl(GraphicsService graphicsService,
											int width, int height, List<String> hrefs) {
		super(graphicsService, width, height, hrefs);
		popup.setMarkerSectionVisible(true);
	}
	
	@Override
	public void createIconInContainer(String iconUrl, MarkerShape markerShape) {
		Coordinate screenIconPosition = transform(getClickPosition(), Space.USER, Space.SCREEN);
		Coordinate iconPosition = transform(new Coordinate(screenIconPosition.getX(), screenIconPosition.getY() - 40),
				Space.SCREEN, Space.USER);
		
		GIcon result = createIcon(iconPosition, iconUrl);
		result.removeRole(Anchored.TYPE);
		result.addRole(new ResizableAnchorer(getClickPosition(), markerShape));
		result.getRole(Draggable.TYPE).setPosition(iconPosition);
		addObject(result);
	}

}