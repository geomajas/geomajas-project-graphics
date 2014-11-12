/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2014 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client.object.role;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.RoleInterface;
import org.geomajas.graphics.client.object.RoleType;
import org.geomajas.graphics.client.util.HasBounds;
import org.geomajas.graphics.client.util.HasPosition;

/**
 * Implemented by objects that can be dragged.
 * 
 * @author Jan De Moerloose
 * 
 */
public interface Draggable extends RoleInterface, HasBounds, HasPosition {
	
	RoleType<Draggable> TYPE = new RoleType<Draggable>("Draggable");
}
