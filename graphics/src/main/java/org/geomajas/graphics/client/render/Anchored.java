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

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.util.HasPosition;

/**
 * Implemented by objects of (typically) fixed screen size for which the user position corresponds to a specified
 * relative point in screen space. The screen space point is determined by adding (anchorX * width, anchorY * height) to
 * the center in user space. This means that (anchorX, anchorY) = (0, 0) corresponds to anchoring the center of the
 * object to the user position, while (anchorX, anchorY) = (0.5, 0.5) corresponds to anchoring the top-right corner of
 * the object to the user position.
 * 
 * @author Jan De Moerloose
 *
 */
public interface Anchored extends HasPosition {

	/**
	 * Get the anchor of the object.
	 * 
	 * @return
	 */
	Coordinate getAnchor();

	/**
	 * Set the anchor of the object.
	 * 
	 * @param anchor
	 */
	void setAnchor(Coordinate anchor);
}
