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

import org.geomajas.graphics.client.util.HasFill;
import org.geomajas.graphics.client.util.HasStroke;

/**
 * A basic circle.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public interface BaseCircle extends HasStroke, HasFill, IsRenderable {

	/**
	 * Set the x-coordinate of the center in user space.
	 * 
	 * @param x
	 */
	void setUserX(double x);

	/**
	 * Set the y-coordinate of the center in user space.
	 * 
	 * @param y
	 */
	void setUserY(double y);

	/**
	 * Get the x-coordinate of the center in user space.
	 * 
	 * @return
	 */
	double getUserX();

	/**
	 * Get the y-coordinate of the center in user space.
	 * 
	 * @return
	 */
	double getUserY();

	/**
	 * Get the radius in user space.
	 * 
	 * @return
	 */
	double getUserRadius();

	/**
	 * Set the radius in user space.
	 * 
	 * @param v
	 */
	void setUserRadius(double userRadius);

	/**
	 * Get the x-coordinate of the center in screen space.
	 * 
	 * @return
	 */
	int getX();

	/**
	 * Get the y-coordinate of the center in screen space.
	 * 
	 * @return
	 */
	int getY();

	/**
	 * Get the radius in screen space.
	 * 
	 * @return
	 */
	int getRadius();

}