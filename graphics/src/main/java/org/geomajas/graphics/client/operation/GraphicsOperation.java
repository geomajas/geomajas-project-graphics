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
package org.geomajas.graphics.client.operation;

import org.geomajas.annotation.Api;
import org.geomajas.annotation.UserImplemented;
import org.geomajas.graphics.client.object.GraphicsObject;

/**
 * Undoable operation on a graphics object.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * @since 1.0.0
 * 
 */
@Api(allMethods = true)
@UserImplemented
public interface GraphicsOperation {

	/**
	 * Called by the service when the operation is executed.
	 */
	void execute();

	/**
	 * Called by the service when the operation should be undone.
	 */
	void undo();

	GraphicsObject getObject();

	/**
	 * Get the type of operation.
	 * 
	 * @return
	 */
	Type getType();

	/**
	 * Different types of Graphics Operation.
	 * 
	 * @author Jan Venstermans
	 * 
	 */
	public enum Type {
		UPDATE, // update an object
		ADD, // add an object
		REMOVE // remove an object
	}
}
