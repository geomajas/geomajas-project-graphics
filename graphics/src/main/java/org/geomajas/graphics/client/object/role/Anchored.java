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
import org.geomajas.graphics.client.object.updateable.anchored.MarkerShape;
import org.geomajas.graphics.client.object.updateable.anchored.TwoPointsLine;

/**
 * Role for anchor.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * @since 1.0.0
 * 
 */
@Api(allMethods = true)
public interface Anchored extends RoleInterface {

	/**
	 * The role type.
	 */
	RoleType<Anchored> TYPE = new RoleType<Anchored>("Anchored");

	/* anchor line */

	/**
	 * Get the anchor line stroke accessor.
	 * 
	 * @return
	 */
	Strokable getAnchorLineStrokable();

	/**
	 * Set the anchor line dash style.
	 * 
	 * @param dashStyle
	 */
	void setAnchorLineDashStyle(TwoPointsLine.LineDashStyle dashStyle);

	/* anchor marker shape */

	/**
	 * Get the anchor marker shape stroke accessor.
	 * 
	 * @return
	 */
	Strokable getAnchorMarkerShapeStrokable();

	/**
	 * Get the anchor marker shape fill accessor.
	 * 
	 * @return
	 */
	Fillable getAnchorMarkerShapeFillable();

	/**
	 * Set the anchor position.
	 * 
	 * @param position
	 */
	void setAnchorPosition(Coordinate position);

	/**
	 * Get the anchor position.
	 * 
	 * @return
	 */
	Coordinate getAnchorPosition();

	/**
	 * Set the visibility of the anchor.
	 * 
	 * @param visible
	 */
	void setAnchorVisible(boolean visible);

	/**
	 * Get the marker shape.
	 * 
	 * @return
	 */
	MarkerShape getMarkerShape();
}
