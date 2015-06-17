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
package org.geomajas.graphics.client.object.updateable.bordered;

import org.geomajas.geometry.Bbox;
import org.geomajas.graphics.client.render.shape.AnchoredRectangleImpl;
import org.geomajas.graphics.client.util.GraphicsUtil;

/**
 * A non-scaling rectangle that is anchored to its world space location on a specific pixel or anchor location (useful
 * for location markers).
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class MarginAnchoredRectangleImpl extends AnchoredRectangleImpl {

	private int margin;

	/**
	 * Creates an rectangle at the specified world location with a specified size and anchor point. E.g., if
	 * (anchorX,anchorY)=(width/2, height/2), the center of the rectangle will be positioned at the world location.
	 *
	 * @param userX x-location in world coordinates
	 * @param userY y-location in world coordinates
	 * @param userWidth width in pixels
	 * @param userHeight height in pixels
	 * @param anchorX x-location of the anchor point (rectangle-relative)
	 * @param anchorY y-location of the anchor point (rectangle-relative)
	 */
	public MarginAnchoredRectangleImpl(double userX, double userY, double userWidth, double userHeight, int anchorX,
			int anchorY, int margin) {
		super(userX, userY, userWidth, userHeight, anchorX, anchorY);
		this.margin = margin;
		setRoundedCorners(margin);
	}

	@Override
	protected void drawTransformed() {
		// calculate the screen bounds and take the lower-left corner
		Bbox b = GraphicsUtil.transform(getUserBounds(), getScaleX(), getScaleY(), getDeltaX(), getDeltaY());
		getImpl().setX(getElement(), (int) b.getX() - margin, isAttached());
		getImpl().setY(getElement(), (int) b.getY() - margin, isAttached());
		setWidth((int) getUserWidth() + 2 * margin);
		setHeight((int) getUserHeight() + 2 * margin);
	}

}