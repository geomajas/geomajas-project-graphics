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

import org.geomajas.graphics.client.controller.create.CreateController;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.updateable.LabeledImage;
import org.geomajas.graphics.client.operation.AddOperation;
import org.geomajas.graphics.client.service.GraphicsService;

import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Controller that creates a {@link LabeledImage}.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class CreateLabeledImageController extends CreateController<LabeledImage> implements MouseUpHandler {

	private HandlerRegistration registration;

	private String href;

	private int width;

	private int height;

	public CreateLabeledImageController(GraphicsService graphicsService, int width, int height, String href) {
		super(graphicsService);
		setHref(href);
		setHeight(height);
		setWidth(width);
	}

	@Override
	public void setActive(boolean active) {
		super.setActive(active);
		if (active) {
			registration = getObjectContainer().addMouseUpHandler(this);
		} else {
			if (registration != null) {
				registration.removeHandler();
				registration = null;
			}
		}
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		LabeledImage result = new LabeledImage(0, 0, width, height, href, "Image");
		result.getRole(Draggable.TYPE).setUserPosition(getUserCoordinate(event));
		execute(new AddOperation(result));
	}

}
