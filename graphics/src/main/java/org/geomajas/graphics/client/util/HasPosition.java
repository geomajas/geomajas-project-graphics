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
	 * Get the minX/minY corner of the object in user space. This is different from userX/userY = center for
	 * circles/ellipses, but ensures consistency when positioning overlapping objects (e.g. circle/rectangle) !
	 * 
	 * @return
	 */
	Coordinate getUserPosition();

	/**
	 * Set the minX/minY corner of the object in user space. This is different from userX/userY = center for
	 * circles/ellipses, but ensures consistency when positioning overlapping objects (e.g. circle/rectangle) !
	 * 
	 * @param position
	 */
	void setUserPosition(Coordinate position);
}