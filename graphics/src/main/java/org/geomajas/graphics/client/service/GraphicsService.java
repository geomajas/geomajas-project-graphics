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
package org.geomajas.graphics.client.service;

import java.util.List;

import org.geomajas.annotation.Api;
import org.geomajas.graphics.client.controller.GraphicsController;
import org.geomajas.graphics.client.controller.GraphicsControllerFactory;
import org.geomajas.graphics.client.event.GraphicsOperationEvent;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.operation.GraphicsOperation;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer;

import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Service that acts as a registry for {@link org.geomajas.graphics.client.controller.GraphicsControllerFactory}
 * factories and manages the operations on a
 * {@link org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer}.
 * 
 * @author Jan De Moerloose
 * @since 1.0.0
 * 
 */
@Api(allMethods = true)
public interface GraphicsService {

	/**
	 * Execute a graphics operation.
	 * 
	 * @param operation
	 */
	void execute(GraphicsOperation operation);

	/**
	 * Undo the last operation.
	 */
	void undo();

	/**
	 * Redo the last undone operation.
	 */
	void redo();

	/**
	 * Get the container of graphical objects.
	 * 
	 * @return
	 */
	GraphicsObjectContainer getObjectContainer();

	/**
	 * Set the container of graphical objects.
	 * 
	 * @param container
	 */
	void setObjectContainer(GraphicsObjectContainer container);

	/**
	 * Set the factory for the meta controller.
	 * 
	 * @param controllerFactory
	 */
	void setMetaControllerFactory(MetaControllerFactory controllerFactory);

	/**
	 * Register a controller factory. Controllers are made on the fly for each object.
	 * 
	 * @param controllerFactory
	 */
	void registerControllerFactory(GraphicsControllerFactory controllerFactory);

	/**
	 * Get the list of registered controller factories.
	 * 
	 * @return
	 */
	List<GraphicsControllerFactory> getControllerFactoryList();

	/**
	 * Add an event handler for graphical operations.
	 * 
	 * @param handler
	 * @return
	 */
	HandlerRegistration addGraphicsOperationHandler(GraphicsOperationEvent.Handler handler);

	/**
	 * Get the current meta controller.
	 * 
	 * @return
	 */
	GraphicsController getMetaController();

	/**
	 * Start the service.
	 */
	void start();

	/**
	 * Stop the service.
	 */
	void stop();

	/**
	 * Notify the container that an object is updated.
	 * 
	 * @param object
	 */
	void update(GraphicsObject object);

	// BOOLEAN PROPERTIES

	/**
	 * Is the undo via key Ctrl-Z active ?
	 * 
	 * @return
	 */
	boolean isUndoKeys();

	/**
	 * Activate the undo via key Ctrl-Z.
	 * 
	 * @param undoKeys
	 */
	void setUndoKeys(boolean undoKeys);

	/**
	 * Should the original object be shown while dragging.
	 * 
	 * @return
	 */
	boolean isShowOriginalObjectWhileDragging();

	/**
	 * Set the original object visible while dragging.
	 * 
	 * @param showOriginalObjectWhileDragging
	 */
	void setShowOriginalObjectWhileDragging(boolean showOriginalObjectWhileDragging);

}
