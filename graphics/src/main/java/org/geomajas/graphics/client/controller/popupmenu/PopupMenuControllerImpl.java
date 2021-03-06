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
package org.geomajas.graphics.client.controller.popupmenu;

import java.util.ArrayList;
import java.util.List;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.action.Action;
import org.geomajas.graphics.client.controller.AbstractInterruptibleGraphicsController;
import org.geomajas.graphics.client.event.GraphicsObjectContainerEvent;
import org.geomajas.graphics.client.event.GraphicsObjectContainerEvent.ActionType;
import org.geomajas.graphics.client.event.GraphicsOperationEvent;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.RenderSpace;
import org.geomajas.graphics.client.render.shape.AnchoredImageImpl;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.util.BboxPosition;
import org.geomajas.graphics.client.util.GraphicsUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;

/**
 * Controller that shows a popup menu at the upper left corner of the {@link GraphicsObject}. The menu is created only
 * once when the controller is initalized.
 * 
 * @author Jan De Moerloose
 * 
 */
public class PopupMenuControllerImpl extends AbstractInterruptibleGraphicsController implements PopupMenuController,
		PopupMenuController.Handler {

	public static final int IMG_DIST = 10;

	private View menu;

	private String iconUrl;

	/**
	 * Our own container.
	 */
	private RenderContainer container;

	private PropertyHandler handler;

	private List<Action> actions;

	public PopupMenuControllerImpl(List<Action> actions, GraphicsObject object, GraphicsService service, //
			String iconUrl) {
		super(service, object);
		this.iconUrl = iconUrl;

		// only register actions that are compatible with the object
		this.actions = new ArrayList<Action>();
		for (Action action : actions) {
			if (action.supports(object)) {
				this.actions.add(action);
			}
		}

		container = addContainer();
		// listen to changes to our object
		service.getObjectContainer().addGraphicsObjectContainerHandler(this);
		service.getObjectContainer().addGraphicsOperationEventHandler(this);
	}

	@Override
	public void setActive(boolean active) {
		if (active != isActive()) {
			super.setActive(active);
			if (isActive()) {
				if (handler == null) {
					// create and (implicitly) activate the handler group
					init();
				} else {
					// the group may be detached, update and reattach !
					handler.update();
					handler.addToContainer(container);
				}
				if (menu == null) {
					menu = Graphics.getViewManager().createPopupMenuView();
					menu.addHandler(this);
					for (Action action : actions) {
						menu.addAction(action.getLabel(), action);
					}
				}
				bringContainerToFront(container);
			} else {
				// just remove the handler
				if (handler != null) {
					handler.removeFromContainer();
				}
				if (menu != null) {
					menu.hide();
				}
			}
		}
	}

	private void init() {
		// create the handler and attach it
		handler = new PropertyHandler();
		handler.update();
		// add the handler
		handler.addToContainer(container);
	}

	@Override
	public void onAction(GraphicsObjectContainerEvent event) {
		if (event.getObject() == getObject()) {
			if (event.getActionType() == ActionType.UPDATE) {
				// must re-initialize as this object has changed (mask)
				container.clear();
				if (isActive()) {
					init();
				}
			} else {
				// handled by meta controller
			}
		}
	}

	public AnchoredImageImpl getPropertyImage() {
		return handler.getPropertyImage();
	}

	public void setPropertyImage(AnchoredImageImpl propertyImage) {
		handler.setPropertyImage(propertyImage);
	}

	@Override
	public void onSelectAction(Action action) {
		action.execute(getObject());
		menu.hide();
	}

	/**
	 * 
	 */
	class PropertyHandler implements MouseDownHandler {

		private AnchoredImageImpl propertyImage;

		public PropertyHandler() {
			propertyImage = new AnchoredImageImpl(0, 0, 16, 16, iconUrl != null ? iconUrl : GWT.getModuleBaseURL()
					+ "image/cogContrast.png", 1.125, -1.125);
			propertyImage.setFixedSize(true);
			propertyImage.addMouseDownHandler(this);
		}

		public void removeFromContainer() {
			container.remove(propertyImage);
		}

		public void update() {
			BboxPosition bboxPos = transform(BboxPosition.CORNER_UL, RenderSpace.SCREEN, RenderSpace.USER);
			Coordinate pos = transform(new Coordinate(IMG_DIST, IMG_DIST), RenderSpace.SCREEN, RenderSpace.USER);
			if (getObject().hasRole(Resizable.TYPE)) {
				pos = GraphicsUtil.getPosition(getObject().getRole(Resizable.TYPE).getUserBounds(), bboxPos);
			} else if (getObject().hasRole(Draggable.TYPE)) {
				pos = GraphicsUtil.getPosition(getObject().getRole(Draggable.TYPE).getUserBounds(), bboxPos);
			}
			propertyImage.setUserX(pos.getX());
			propertyImage.setUserY(pos.getY());
		}

		public void addToContainer(RenderContainer container) {
			container.add(propertyImage);
		}

		@Override
		public void onMouseDown(MouseDownEvent event) {
			menu.show(event.getClientX(), event.getClientY());
		}

		public void onClick(ClickEvent event) {
		}

		public AnchoredImageImpl getPropertyImage() {
			return propertyImage;
		}

		public void setPropertyImage(AnchoredImageImpl propertyImage) {
			this.propertyImage = propertyImage;
		}

	}

	@Override
	public void setControllerElementsVisible(boolean visible) {
		if (handler == null) {
			// create and (implicitly) activate the handler group
			init();
		}
		handler.getPropertyImage().setVisible(visible);
	}

	@Override
	public void onOperation(GraphicsOperationEvent event) {
		if (event.getOperation().getObject() == getObject() && handler != null) {
			handler.update();
		}
	}

}
