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
package org.geomajas.graphics.client.util;

/**
 * Corner and mid-side positions of a bounding box. Left/right and upper/lower refer to the user perspective, the
 * actual coordinates will depend on the direction of x and y-axis.
 * 
 * @author Jan De Moerloose
 * 
 */
public enum BboxPosition {
	/**
	 * Lower left corner.
	 */
	CORNER_LL, 
	/**
	 * Lower right corner.
	 */
	CORNER_LR,
	/**
	 * Upper left corner.
	 */
	CORNER_UL,
	/**
	 * Upper right corner.
	 */
	CORNER_UR,
	/**
	 * Lower middle.
	 */
	MIDDLE_LOW,
	/**
	 * Upper middle.
	 */
	MIDDLE_UP,
	/**
	 * Left middle.
	 */
	MIDDLE_LEFT,
	/**
	 * Right middle.
	 */
	MIDDLE_RIGHT;
}
