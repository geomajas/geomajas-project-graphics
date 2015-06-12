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

/**
 * Interface for a border.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * @since 1.0.0
 * 
 */
@Api(allMethods = true)
public interface Bordered extends RoleInterface {

	/**
	 * The role type.
	 */
	RoleType<Bordered> TYPE = new RoleType<Bordered>("Bordered");

	/**
	 * Get the border stroke accessor.
	 * 
	 * @return
	 */
	Strokable getStrokable();

	/**
	 * Get the fill accessor.
	 * 
	 * @return
	 */
	Fillable getFillable();

}
