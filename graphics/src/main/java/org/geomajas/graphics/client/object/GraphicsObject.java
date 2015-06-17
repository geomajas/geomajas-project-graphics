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
package org.geomajas.graphics.client.object;

import org.geomajas.annotation.Api;
import org.geomajas.graphics.client.render.IsRenderable;
import org.geomajas.graphics.client.object.role.IsCloneable;

/**
 * Common interface for all graphics objects. All objects can react to mouse events through their {@link IsRenderable}
 * representation. A graphics object can support any number of roles.
 * 
 * @author Jan De Moerloose
 * @since 1.0.0
 * 
 */
@Api(allMethods = true)
public interface GraphicsObject extends IsRenderable, IsCloneable {

	/**
	 * Check if object has this role.
	 * 
	 * @param type
	 * @return
	 */
	boolean hasRole(RoleType<?> type);

	/**
	 * Get the role of this type.
	 * 
	 * @param type
	 * @return the role or null (if object has no such role)
	 */
	<T extends RoleInterface> T getRole(RoleType<T> type);

}
