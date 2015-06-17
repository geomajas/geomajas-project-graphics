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
package org.geomajas.graphics.client.render;

import org.geomajas.annotation.Api;
import org.geomajas.graphics.client.service.HasAllMouseAndClickHandlers;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Implemented by all graphical objects. Rendering happens by adding the object to a {@link RenderContainer}.
 *
 * @author Jan De Moerloose
 * @since 1.0.0
 * 
 */
@Api(allMethods = true)
public interface Renderable extends HasAllMouseAndClickHandlers {

	/**
	 * Get the parent container.
	 * 
	 * @return the container or null if the object is not rendered or it is the root container.
	 */
	RenderContainer getParent();

	/**
	 * Detaches the object from its parent.
	 */
	void removeFromParent();

	/**
	 * Set the cursor when hovering this object.
	 * 
	 * @param css css name of the cursor
	 */
	void setCursor(String css);

	/**
	 * Let this object capture all mouse events.
	 */
	void capture();

	/**
	 * Release capturing of all events to this object.
	 */
	void releaseCapture();

	/**
	 * Set the opacity of the object.
	 * 
	 * @param opacity
	 */
	void setOpacity(double opacity);

	/**
	 * Set the visibility of the object.
	 * 
	 * @param visible
	 */
	void setVisible(boolean visible);

	/**
	 * Determine if the object is the source of this event.
	 * 
	 * @param event
	 * @return true if the object is the source.
	 */
	boolean isSourceOf(GwtEvent<?> event);
}
