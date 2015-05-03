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

import org.geomajas.graphics.client.controller.GraphicsController;
import org.geomajas.graphics.client.controller.GraphicsControllerFactory;
import org.geomajas.graphics.client.event.GraphicsOperationEvent;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.operation.GraphicsOperation;

import com.google.web.bindery.event.shared.HandlerRegistration;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer;

/**
 * Service that acts as a registry for
 * {@link org.geomajas.graphics.client.controller.GraphicsControllerFactory}
 * factories and manages the operations on a
 * {@link org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer}.
 * 
 * @author Jan De Moerloose
 * 
 */
public interface GraphicsService {

	void execute(GraphicsOperation operation);

	void undo();

	void redo();

	GraphicsObjectContainer getObjectContainer();

	void setObjectContainer(GraphicsObjectContainer container);

	void setMetaControllerFactory(MetaControllerFactory controllerFactory);

	void registerControllerFactory(GraphicsControllerFactory controllerFactory);

	List<GraphicsControllerFactory> getControllerFactoryList();

	HandlerRegistration addGraphicsOperationHandler(GraphicsOperationEvent.Handler handler);
	
	GraphicsController getMetaController();

	void start();

	void stop();

	void update(GraphicsObject object);

	// BOOLEAN PROPERTIES

	boolean isUndoKeys();

	void setUndoKeys(boolean undoKeys);

	boolean isShowOriginalObjectWhileDragging();
	
	void setShowOriginalObjectWhileDragging(boolean showOriginalObjectWhileDragging);
	
	boolean isExternalizableLabeledOriginallyExternal();
	
	void setExternalizableLabeledOriginallyExternal(boolean externalizableLabeledOriginallyExternal);
}
