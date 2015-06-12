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
package org.geomajas.graphics.client;

import org.geomajas.annotation.Api;
import org.geomajas.graphics.client.controller.popupmenu.PopupMenuController;
import org.geomajas.graphics.client.editor.Editor;
import org.geomajas.graphics.client.widget.createcontrollergroup.CreateControllerGroupPresenter;

/**
 * Interface for specific view elements.
 *
 * @author Jan Venstermans
 * @since 1.0.0
 *
 */
@Api(allMethods = true)
public interface GraphicsViewManager {

	/**
	 * Create the view of the popup menu (shown when clicking the cog).
	 * 
	 * @return the view
	 */
	PopupMenuController.View createPopupMenuView();

	/**
	 * 
	 * Create the editor for editing a particular role of an object (e.g Stroked, Filled).
	 * 
	 * @param object
	 * @return the view
	 */
	PopupMenuController.EditorView createPopupMenuEditorView(Editor object);

	/**
	 * Create the toolbar view for creating objects.
	 * 
	 * @return the view
	 */
	CreateControllerGroupPresenter.View createCreateControllerGroupView();
}
