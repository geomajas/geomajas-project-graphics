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
package org.geomajas.graphics.client.object.role;

import org.geomajas.annotation.Api;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.RoleInterface;
import org.geomajas.graphics.client.object.RoleType;

/**
 * Implemented by graphical objects that can be represented an array of coordinates.
 * 
 * @author Jan De Moerloose
 * @since 1.0.0
 * 
 */
@Api(allMethods = true)
public interface CoordinateBased extends RoleInterface {

	/**
	 * The role type.
	 */
	RoleType<CoordinateBased> TYPE = new RoleType<CoordinateBased>("CoordinateBased");

	/**
	 * Set the coordinates.
	 * 
	 * @param coordinates
	 */
	void setCoordinates(Coordinate[] coordinates);

	/**
	 * Get the coordinates.
	 * 
	 * @return
	 */
	Coordinate[] getCoordinates();

	/**
	 * Add a coordinate at the end of the array.
	 * 
	 * @param coordinate
	 */
	void addCoordinate(Coordinate coordinate);

	/**
	 * Get the last coordinate.
	 * 
	 * @return
	 */
	Coordinate getLastCoordinate();

	/**
	 * Get the number of coordinates.
	 * 
	 * @return
	 */
	int getCoordinateCount();

	/**
	 * Move the coordinate at the specified index.
	 * 
	 * @param coordinate
	 * @param index
	 */
	void moveCoordinate(Coordinate coordinate, int index);
}
