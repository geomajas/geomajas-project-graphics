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
package org.geomajas.graphics.client.object.updateable.labeled;

import org.geomajas.graphics.client.object.RoleInterface;
import org.geomajas.graphics.client.object.RoleType;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.object.updateable.Updateable;


/**
 * Interface for a label.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public interface LabeledUpdateable extends Updateable, RoleInterface {
	
	RoleType<LabeledUpdateable> TYPE = new RoleType<LabeledUpdateable>("LabeledUpdateable");

	Textable getTextable();

}
