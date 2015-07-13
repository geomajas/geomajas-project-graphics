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
package org.geomajas.graphics.client.controller.rotate;

import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.controller.UpdateHandlerGraphicsController;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.service.GraphicsService;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;

/**
 * {@link org.geomajas.graphics.client.controller.UpdateHandlerGraphicsControllerWithVisibleElement} that handles
 * resizing (through anchor points).
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class RotateController extends UpdateHandlerGraphicsController implements MouseDownHandler {

	/**
	 * Object under control.
	 */
	private Draggable object;

	/**
	 * Handler to drag the object.
	 */
	private RotateDragHandler dragHandler;

	public RotateController(GraphicsObject object, GraphicsService service) {
		super(service, object);
		this.object = object.getRole(Draggable.TYPE);
	}

	/**
	 * This {@link MouseDownHandler} handler method is called from the
	 * {@link org.geomajas.graphics.client.controller.DefaultMetaController}.
	 *
	 * @param event
	 */
	@Override
	public void onMouseDown(MouseDownEvent event) {
		if (isActive()) {
			if (BboxService.contains(object.getUserBounds(), getUserCoordinate(event))) {
				dragHandler.onMouseDown(event);
				event.stopPropagation();
			}
		}
	}

	@Override
	protected void init() {
		setHandlerGroup(getService().getObjectContainer().createContainer());
		// create the drag handler and attach it
		dragHandler = new RotateDragHandler(getObject(), getService(), this);
		getHandlerGroup().add(dragHandler.getInvisbleMaskGraphicsObject().getRenderable());
		// update positions
		updateHandlers();
		// add the group
		getContainer().add(getHandlerGroup());
	}

	@Override
	public void updateHandlers() {
		if (dragHandler != null) {
			dragHandler.update();
		}
	}
}
