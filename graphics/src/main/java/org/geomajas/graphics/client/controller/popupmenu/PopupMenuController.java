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
package org.geomajas.graphics.client.controller.popupmenu;

import org.geomajas.annotation.Api;
import org.geomajas.graphics.client.action.Action;
import org.geomajas.graphics.client.controller.GraphicsControllerWithVisibleElement;
import org.geomajas.graphics.client.event.GraphicsObjectContainerEvent;
import org.geomajas.graphics.client.event.GraphicsOperationEvent;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * Controller that shows a popup menu at the upper left corner of the
 * {@link org.geomajas.graphics.client.object.GraphicsObject}. The menu is created only once when the controller is
 * initalized.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public interface PopupMenuController extends GraphicsObjectContainerEvent.Handler, GraphicsOperationEvent.Handler,
		GraphicsControllerWithVisibleElement {

	/**
	 * MVP view part of {@link PopupMenuController}.
	 *
	 * @author Jan Venstermans
	 * @since 1.0.0
	 *
	 */
	@Api(allMethods = true)
	public interface View {

		/**
		 * Set the callback handler for the view.
		 * 
		 * @param handler
		 */
		void addHandler(Handler handler);

		/**
		 * Hide the view.
		 */
		void hide();

		/**
		 * Add a labeled action to the view.
		 * 
		 * @param label
		 * @param action
		 */
		void addAction(String label, Action action);

		/**
		 * Show the view at the specified position (screen coordinates).
		 * 
		 * @param clientX
		 * @param clientY
		 */
		void show(int clientX, int clientY);
	}

	/**
	 * MVP view handler part of {@link PopupMenuController}.
	 *
	 * @author Jan Venstermans
	 * @since 1.0.0
	 *
	 */
	@Api(allMethods = true)
	public interface Handler {

		/**
		 * View must call this method when the user selects an action.
		 * 
		 * @param action
		 */
		void onSelectAction(Action action);
	}

	/**
	 * MVP editor view part of {@link PopupMenuController}.
	 *
	 * @author Jan Venstermans
	 * @since 1.0.0
	 *
	 */
	@Api(allMethods = true)
	public interface EditorView extends IsWidget {

		/**
		 * Set the callback handler for the view.
		 * 
		 * @param handler
		 */
		void addHandler(EditorHandler editorHandler);

		/**
		 * Center the view on the page.
		 */
		void center();

		/**
		 * Hide the view.
		 */
		void hide();

	}

	/**
	 * MVP editor view Handler part of {@link PopupMenuController}.
	 *
	 * @author Jan Venstermans
	 * @since 1.0.0
	 *
	 */
	@Api(allMethods = true)
	public interface EditorHandler {

		/**
		 * Called when the user wants to commit changes and stop editing.
		 */
		void onOk();

		/**
		 * Called when the user wants to apply changes (for preview).
		 */
		void onApply();

		/**
		 * Called when the user wants to undo all changes. 
		 */
		void onUndo();

		/**
		 * Called when the user wants to undo all changes and stop editing. 
		 */
		void onCancel();
	}

}
