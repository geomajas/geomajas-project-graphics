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
package org.geomajas.project.graphics.example.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.action.BringToFrontAction;
import org.geomajas.graphics.client.action.DeleteAction;
import org.geomajas.graphics.client.action.DuplicateAction;
import org.geomajas.graphics.client.controller.create.base.CreateBaseCircleByRadiusController;
import org.geomajas.graphics.client.controller.create.base.CreateBaseCircleByRectangleController;
import org.geomajas.graphics.client.controller.create.base.CreateBaseEllipseController;
import org.geomajas.graphics.client.controller.create.base.CreateBaseIconController;
import org.geomajas.graphics.client.controller.create.base.CreateBaseImageController;
import org.geomajas.graphics.client.controller.create.base.CreateBasePathController;
import org.geomajas.graphics.client.controller.create.base.CreateBasePathLineController;
import org.geomajas.graphics.client.controller.create.base.CreateBaseRectangleController;
import org.geomajas.graphics.client.controller.create.base.CreateBaseTextController;
import org.geomajas.graphics.client.controller.create.updateable.CreateAnchoredBorderedTextController;
import org.geomajas.graphics.client.controller.create.updateable.CreateAnchoredIconController;
import org.geomajas.graphics.client.controller.create.updateable.CreateBorderedTextController;
import org.geomajas.graphics.client.controller.create.updateable.CreateLabeledEllipseController;
import org.geomajas.graphics.client.controller.create.updateable.CreateLabeledImageController;
import org.geomajas.graphics.client.controller.create.updateable.CreateLabeledPathController;
import org.geomajas.graphics.client.controller.create.updateable.CreateLabeledPathLineController;
import org.geomajas.graphics.client.controller.create.updateable.CreateLabeledRectangleController;
import org.geomajas.graphics.client.controller.delete.DeleteControllerFactory;
import org.geomajas.graphics.client.controller.drag.DragControllerFactory;
import org.geomajas.graphics.client.controller.popupmenu.PopupMenuControllerFactory;
import org.geomajas.graphics.client.controller.resize.ResizeControllerFactory;
import org.geomajas.graphics.client.controller.role.AnchoredDragControllerFactory;
import org.geomajas.graphics.client.controller.role.LabelControllerFactory;
import org.geomajas.graphics.client.editor.AnchoredEditor;
import org.geomajas.graphics.client.editor.LabeledEditor;
import org.geomajas.graphics.client.editor.StrokeFillEditor;
import org.geomajas.graphics.client.editor.TextableEditor;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.widget.createcontrollergroup.CreateButtonGroupWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * Example application.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class Example implements EntryPoint {

	private static final Binder UIBINDER = GWT.create(Binder.class);

	/**
	 * UI binder.
	 *
	 */
	interface Binder extends UiBinder<DockLayoutPanel, Example> {

	}

	@UiField
	protected DockLayoutPanel dockLayoutPanel;

	@UiField
	protected FlowPanel westFlowPanel;

	@UiField
	protected CaptionPanel captionPanelBaseCreateButtons;

	@UiField
	protected CaptionPanel captionPanelCombinedCreateButtons;

	@UiField
	protected CaptionPanel captionPanelGeneralOptions;

	@UiField
	protected CheckBox checkShowDrag;

	@UiField
	protected ToggleButton navigationControllerToggleButton;

	@UiField
	protected ScrollPanel westScrollPanel;

	private CreateButtonGroupWidget createBaseButtonGroupWidget;

	private CreateButtonGroupWidget createUpdateableGroupButtonGroupWidget;

	private NavigationController navigationController;

	private GraphicsService graphicsService;

	private List<String> urls = new ArrayList<String>(Arrays.asList(GWT.getModuleBaseURL() + "image/slider.gif",
			GWT.getModuleBaseURL() + "image/cloud.png", GWT.getModuleBaseURL() + "image/sun.jpg"));

	private List<String> url = new ArrayList<String>(Arrays.asList(urls.get(0)));

	private ExampleGraphicsObjectContainer graphicsObjectContainer;

	@Override
	public void onModuleLoad() {
		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void onUncaughtException(Throwable e) {
				GWT.log("failed", e);
			}
		});
		SimpleEventBus eventBus = new SimpleEventBus();

		graphicsObjectContainer = new ExampleGraphicsObjectContainer(eventBus);

		// graphics service configuration
		graphicsService = Graphics.createGraphicsService(eventBus);
		graphicsService.setUndoKeys(true);
		graphicsService.setShowOriginalObjectWhileDragging(true);
		graphicsService.setObjectContainer(graphicsObjectContainer);
		registerControllerFactories();

		// popupFactory
		registerPopupFactoryActionsAndEditiors();

		// create widget and fill
		createBaseButtonGroupWidget = new CreateButtonGroupWidget(graphicsService);
		registerBaseCreateControllersToWidget(createBaseButtonGroupWidget);
		createUpdateableGroupButtonGroupWidget = new CreateButtonGroupWidget(graphicsService);
		registerUpdateableGroupCreateControllersToWidget(createUpdateableGroupButtonGroupWidget);
		navigationController = new NavigationController(graphicsService, graphicsObjectContainer.getRenderArea());

		// layout
		UIBINDER.createAndBindUi(this);
		fillWestFlowPanel();
		dockLayoutPanel.add(graphicsObjectContainer);
		RootLayoutPanel.get().add(dockLayoutPanel);

		graphicsService.start();
	}

	@UiHandler("navigationControllerToggleButton")
	public void onNavigationButtonClicked(ClickEvent e) {
		navigationController.setActive(!navigationController.isActive());
		if (navigationController.isActive()) {
			graphicsService.stop();
		} else {
			graphicsService.start();
		}
	}

	// -----------------------------------------------------------------------------
	// FUNCTIONALITY
	// -----------------------------------------------------------------------------

	private void registerControllerFactories() {
		graphicsService.registerControllerFactory(new ResizeControllerFactory());
		graphicsService.registerControllerFactory(new DragControllerFactory());
		graphicsService.registerControllerFactory(new DeleteControllerFactory());
		graphicsService.registerControllerFactory(new LabelControllerFactory());
		graphicsService.registerControllerFactory(new AnchoredDragControllerFactory());
	}

	private void registerPopupFactoryActionsAndEditiors() {
		PopupMenuControllerFactory popupFactory = new PopupMenuControllerFactory();
		// register actions
		popupFactory.registerAction(new DeleteAction());
		popupFactory.registerEditor(new TextableEditor());
		popupFactory.registerEditor(new LabeledEditor());
		popupFactory.registerEditor(new StrokeFillEditor());
		popupFactory.registerAction(new BringToFrontAction());
		popupFactory.registerEditor(new AnchoredEditor());
		popupFactory.registerAction(new DuplicateAction());
		// register popupfactory to graphicsService
		graphicsService.registerControllerFactory(popupFactory);
	}

	private void registerBaseCreateControllersToWidget(CreateButtonGroupWidget createButtonGroupWidget) {
		createButtonGroupWidget.addCreateController(new CreateBaseTextController(graphicsService), "Base Text");
		createButtonGroupWidget.addCreateController(new CreateBaseRectangleController(graphicsService),
				"Base Rectangle");
		createButtonGroupWidget.addCreateController(new CreateBaseCircleByRectangleController(graphicsService),
				"Base Circle (by rectangle)");
		CreateBaseCircleByRadiusController createBaseCircleByRadiusController = new CreateBaseCircleByRadiusController(
				graphicsService);
		createBaseCircleByRadiusController.getPathStrokable().setStrokeColor("red");
		createBaseCircleByRadiusController.getCircleFillable().setFillColor("red");
		createButtonGroupWidget.addCreateController(createBaseCircleByRadiusController, "Base Circle (by radius)");
		createButtonGroupWidget.addCreateController(new CreateBaseEllipseController(graphicsService), "Base Ellipse");
		createButtonGroupWidget.addCreateController(new CreateBaseIconController(graphicsService, 16, 16, url),
				"Base Icon");
		createButtonGroupWidget
				.addCreateController(new CreateBaseImageController(graphicsService, 200, 235,
						"http://tuxpaint.org/stamps/stamps/animals/birds/cartoon/tux.png", true),
						"Base Image (preserve ratio)");
		createButtonGroupWidget.addCreateController(new CreateBaseImageController(graphicsService, 200, 235,
				"http://tuxpaint.org/stamps/stamps/animals/birds/cartoon/tux.png", false),
				"Base Image (not preserve ratio)");
		createButtonGroupWidget
				.addCreateController(new CreateBasePathController(graphicsService, true), "Base Polygon");
		createButtonGroupWidget.addCreateController(new CreateBasePathLineController(graphicsService), "Base Line");
	}

	private void registerUpdateableGroupCreateControllersToWidget(CreateButtonGroupWidget createButtonGroupWidget) {
		createButtonGroupWidget.addCreateController(new CreateLabeledRectangleController(graphicsService),
				"Labeled Rectangle");
		createButtonGroupWidget.addCreateController(new CreateLabeledEllipseController(graphicsService),
				"Labeled Ellipse");
		createButtonGroupWidget.addCreateController(new CreateLabeledImageController(graphicsService, 200, 235,
				"http://tuxpaint.org/stamps/stamps/animals/birds/cartoon/tux.png"), "Labeled Image");
		createButtonGroupWidget.addCreateController(new CreateLabeledPathController(graphicsService, true),
				"Labeled Polygon");
		createButtonGroupWidget.addCreateController(new CreateLabeledPathLineController(graphicsService),
				"Labeled Line");
		createButtonGroupWidget.addCreateController(new CreateBorderedTextController(graphicsService), "Bordered Text");
		createButtonGroupWidget.addCreateController(new CreateAnchoredIconController(graphicsService, 16, 16, null),
				"Anchored Icon");
		createButtonGroupWidget.addCreateController(new CreateAnchoredBorderedTextController(graphicsService),
				"Anchored Bordered Text");
	}

	// -----------------------------------------------------------------------------
	// LAYOUT
	// -----------------------------------------------------------------------------

	private void fillWestFlowPanel() {
		checkShowDrag.setValue(graphicsService.isShowOriginalObjectWhileDragging());
		checkShowDrag.setValue(graphicsService.isShowOriginalObjectWhileDragging());
		checkShowDrag.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				graphicsService.setShowOriginalObjectWhileDragging(checkShowDrag.getValue());
			}

		});

		captionPanelBaseCreateButtons.setContentWidget(createBaseButtonGroupWidget.asWidget());
		createBaseButtonGroupWidget.asWidget().setStyleName("graphicsExample-leftPanel-createButtonsPanel");

		captionPanelCombinedCreateButtons.setContentWidget(createUpdateableGroupButtonGroupWidget.asWidget());
		createUpdateableGroupButtonGroupWidget.asWidget().setStyleName("graphicsExample-leftPanel-createButtonsPanel");

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent resizeEvent) {
				updateWestSectionToWindowHeight();
			}
		});
		updateWestSectionToWindowHeight();
	}

	private void updateWestSectionToWindowHeight() {
		westScrollPanel.setHeight(Window.getClientHeight() + "px");
	}
}
