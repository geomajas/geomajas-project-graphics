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

import org.geomajas.geometry.Coordinate;

/**
 * Interface with getters and setters of position attributes.
 * 
 * @author Jan Venstermans
 * 
 */
public interface HasPosition {

	/**
	 * Get a representative point of the object in user space. Usually this point is the center of the object. For
	 * anchored objects (fixed size), the user position is the position in user space that corresponds to the anchor
	 * location in screen space, i.e the natural user location of the object. For path-based objects, this is the first
	 * point of the object. Consult the javadoc of the object for the exact interpretation.
	 * 
	 * @return
	 */
	Coordinate getUserPosition();

	/**
	 * Set a representative point of the object in user space. Usually this point is the center of the object. For
	 * anchored objects (fixed size), the user position is the position in user space that corresponds to the anchor
	 * location in screen space, i.e the natural user location of the object. For path-based objects, this is the first
	 * point of the object. Consult the javadoc of the object for the exact interpretation.
	 * 
	 * @param position
	 */
	void setUserPosition(Coordinate position);
}