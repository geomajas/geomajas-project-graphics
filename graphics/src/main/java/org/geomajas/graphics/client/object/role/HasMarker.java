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
import org.geomajas.graphics.client.object.RoleInterface;
import org.geomajas.graphics.client.object.RoleType;
import org.geomajas.graphics.client.render.Marker;

/**
 * Role for objects that are connected to a marker by a maker line.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * @since 1.0.0
 * 
 */
@Api(allMethods = true)
public interface HasMarker extends RoleInterface {

	/**
	 * The role type.
	 */
	RoleType<HasMarker> TYPE = new RoleType<HasMarker>("HasMarker");

	/* anchor line */

	/**
	 * Get the marker line stroke accessor.
	 * 
	 * @return
	 */
	Strokable getMarkerLineStrokable();

	/**
	 * Get the marker.
	 * 
	 * @return
	 */
	Marker getMarker();

}
