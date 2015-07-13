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
package org.geomajas.graphics.client.action;

import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.controller.rotate.RotateController;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.CoordinateBased;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.resource.GraphicsResource;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Action that activates {@link RotateController}.
 * 
 * @author Jan De Moerloose
 *
 */
public class RotateAction extends AbstractAction implements MouseDownHandler {

	private RotateController rotateController;

	private Draggable object;

	private HandlerRegistration registration;

	@Override
	public void execute(GraphicsObject object) {
		this.object = object.getRole(Draggable.TYPE);
		rotateController = new RotateController(object, getService());
		rotateController.setActive(true);
		getService().getMetaController().setActive(false);
		registration = getService().getObjectContainer().addMouseDownHandler(this);
	}

	@Override
	public boolean supports(GraphicsObject object) {
		return object.hasRole(CoordinateBased.TYPE) && object.hasRole(Draggable.TYPE);
	}

	@Override
	protected String getDefaultLabel() {
		return GraphicsResource.MESSAGES.actionLabelRotate();
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		if (BboxService.contains(object.getUserBounds(), getService().getObjectContainer().getUserCoordinate(event))) {
			rotateController.onMouseDown(event);
		} else {
			if (registration != null) {
				rotateController.setActive(false);
				getService().getMetaController().setActive(true);
				registration.removeHandler();
				registration = null;
			}
		}
	}
}
