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

package org.geomajas.graphics.client.object.updateable.hasmarker;

import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.render.Marker;

/**
 * Enumeration of standard Marker Shapes.
 * 
 * @author Jan Venstermans
 * 
 */
public enum MarkerShape {

	/**
	 * a square, side 8 pixels.
	 */
	SQUARE("Square"),

	/**
	 * a circle, diameter 8 pixels.
	 */
	CIRCLE("Circle"),

	/**
	 * a cross: diagonals of a 8x8 box.
	 */
	CROSS("Cross");

	private String title;

	/**
	 * Constructor.
	 * 
	 * @param title
	 */
	private MarkerShape(String title) {
		this.title = title;
	}

	public Marker getMarkerShape() {
		switch (this) {
			case SQUARE:
				return Graphics.getRenderElementFactory().createRectangleMarker(0, 0, 8, 8, 0, 0);
			case CIRCLE:
				return Graphics.getRenderElementFactory().createCircleMarker(0, 0, 4, 0, 0);
			case CROSS:
				return Graphics.getRenderElementFactory().createCrossMarker(0, 0, 8);
		}
		return null;
	}

	/**
	 * Create a marker shape that is centered around a given coordinate posX, posY and whose shape is is a box with
	 * given size.
	 * 
	 * @param posX
	 * @param posY
	 * @param size
	 * @return
	 */
	public Marker getMarkerShape(double posX, double posY, double size) {
		switch (this) {
			case SQUARE:
				return Graphics.getRenderElementFactory().createRectangleMarker(posX, posX, size, size, (int) size / 2,
						(int) size / 2);
			case CIRCLE:
				return Graphics.getRenderElementFactory().createCircleMarker(posX, posY, size / 2, 0, 0);
			case CROSS:
				return Graphics.getRenderElementFactory().createCrossMarker(posX, posY, (int) size);
		}
		return null;
	}

	public String getTitle() {
		return title;
	}

}
