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
import org.geomajas.geometry.Coordinate;

import com.google.gwt.event.dom.client.HumanInputEvent;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * A root area for rendering that is also a GWT widget.
 * 
 * @author Jan De Moerloose
 * @since 1.0.0
 *
 */
@Api(allMethods = true)
public interface RenderArea extends RenderContainer, IsWidget {

	/**
	 * Get the height in pixels of the area.
	 * 
	 * @return the height
	 */
	int getHeight();

	/**
	 * Get the width in pixels of the area.
	 * 
	 * @return the width
	 */
	int getWidth();

	/**
	 * Get the root container for rendering.
	 * 
	 * @return
	 */
	RenderContainer getRootContainer();

	/**
	 * Get the screen/image coordinate for this human input event.
	 * 
	 * @param event
	 * @return
	 */
	Coordinate getScreenCoordinate(HumanInputEvent<?> event);

	/**
	 * Transform coordinate from user to screen space and vice versa.
	 * 
	 * @param coordinate
	 * @param from
	 * @param to
	 * @return
	 */
	Coordinate transform(Coordinate coordinate, RenderSpace from, RenderSpace to);

	/**
	 * Set the translation part of the user-to-screen transformation. Y-axis points down for the default implementation.
	 * 
	 * @param deltaX distance in x direction
	 * @param deltaY distance in y direction
	 */
	void setTranslation(double deltaX, double deltaY);

	/**
	 * Set the scale part of the user-to-screen transformation. Y-scale is assumed to be negative for the default
	 * implementation.
	 * 
	 * @param scaleX scale factor in the x-direction
	 * @param scaleY scale factor in the y-direction
	 */
	void setScale(double scaleX, double scaleY);

	/**
	 * Get the current scale factor in the X-direction.
	 * 
	 * @return
	 */
	double getScaleX();

	/**
	 * Get the current scale factor in the Y-direction.
	 * 
	 * @return
	 */
	double getScaleY();

	/**
	 * Get the translation distance in the x-direction.
	 * 
	 * @return
	 */
	double getDeltaX();

	/**
	 * Get the translation distance in the y-direction.
	 * 
	 * @return
	 */
	double getDeltaY();

}
