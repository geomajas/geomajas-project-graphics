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
package org.geomajas.project.graphics.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.web.bindery.event.shared.SimpleEventBus;
import org.geomajas.graphics.client.action.BringToFrontAction;
import org.geomajas.graphics.client.action.DeleteAction;
import org.geomajas.graphics.client.controller.create.CreateAnchoredIconControllerImpl;
import org.geomajas.graphics.client.controller.create.CreateAnchoredTextController;
import org.geomajas.graphics.client.controller.create.CreateIconControllerImpl;
import org.geomajas.graphics.client.controller.create.CreateTextController;
import org.geomajas.graphics.client.controller.create.base.CreateBaseCircleController;
import org.geomajas.graphics.client.controller.create.base.CreateBaseEllipseController;
import org.geomajas.graphics.client.controller.create.base.CreateBaseIconController;
import org.geomajas.graphics.client.controller.create.base.CreateBaseImageController;
import org.geomajas.graphics.client.controller.create.base.CreateBasePathController;
import org.geomajas.graphics.client.controller.create.base.CreateBasePathLineController;
import org.geomajas.graphics.client.controller.create.base.CreateBaseRectangleController;
import org.geomajas.graphics.client.controller.create.base.CreateBaseTextController;
import org.geomajas.graphics.client.controller.create.updateable.CreateLabeledEllipseController;
import org.geomajas.graphics.client.controller.create.updateable.CreateLabeledImageController;
import org.geomajas.graphics.client.controller.create.updateable.CreateLabeledPathController;
import org.geomajas.graphics.client.controller.create.updateable.CreateLabeledPathLineController;
import org.geomajas.graphics.client.controller.create.updateable.CreateLabeledRectangleController;
import org.geomajas.graphics.client.controller.delete.DeleteControllerFactory;
import org.geomajas.graphics.client.controller.drag.DragControllerFactory;
import org.geomajas.graphics.client.controller.popupmenu.PopupMenuControllerFactory;
import org.geomajas.graphics.client.controller.resize.ResizeControllerFactory;
import org.geomajas.graphics.client.controller.role.AnchorControllerFactory;
import org.geomajas.graphics.client.controller.role.LabelControllerFactory;
import org.geomajas.graphics.client.editor.AnchorStyleEditor;
import org.geomajas.graphics.client.editor.LabelEditor;
import org.geomajas.graphics.client.editor.LabeledUpdateableEditor;
import org.geomajas.graphics.client.editor.StrokeFillEditor;
import org.geomajas.graphics.client.editor.TextableEditor;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.service.GraphicsServiceImpl;
import org.geomajas.graphics.client.widget.createcontrollergroup.CreateButtonGroupWidget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Example application.
 * 
 * @author Jan De Moerloose
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
	protected CaptionPanel captionPanelCreateButtons;

	@UiField
	protected CaptionPanel captionPanelCogPosition;

	@UiField
	protected CaptionPanel captionPanelGeneralOptions;

	@UiField
	protected TextBox textBoxCogXOffset;

	@UiField
	protected TextBox textBoxCogYOffset;

	@UiField
	protected CheckBox checkShowDrag;

	@UiField
	protected ToggleButton navigationControllerToggleButton;

	@UiField
	protected ScrollPanel westScrollPanel;

	private CreateButtonGroupWidget createBaseButtonGroupWidget;
	private CreateButtonGroupWidget createUpdateableGroupButtonGroupWidget;
	private CreateButtonGroupWidget createButtonGroupWidget;

	/* some controllers that have extra functions */

	private CreateIconControllerImpl createIconController;

	private CreateAnchoredIconControllerImpl createAnchoredIconController;

	private NavigationController navigationController;
	
	private GraphicsService graphicsService;
	
	private VerticalPanel iconChoicePanel;

	private CheckBox checkExternalLabel;
	
	private List<String> urls = new ArrayList<String>(Arrays.asList(GWT.getModuleBaseURL() + "image/slider.gif",
			GWT.getModuleBaseURL() + "image/cloud.png",
			GWT.getModuleBaseURL() + "image/sun.jpg"));
	
	private List<String> url = new ArrayList<String>(Arrays.asList(urls.get(0)));
	
	private PopupMenuControllerFactory popupFactory;

	private ExampleGraphicsObjectContainer graphicsObjectContainer;

	@Override
	public void onModuleLoad() {
		SimpleEventBus eventBus = new SimpleEventBus();
		graphicsObjectContainer = new ExampleGraphicsObjectContainer(eventBus);
		graphicsService = new GraphicsServiceImpl(eventBus, true);
		graphicsService.setShowOriginalObjectWhileDragging(true);
		graphicsService.setObjectContainer(graphicsObjectContainer);
		
		//functionalities
		popupFactory = new PopupMenuControllerFactory();
		registerControllerFactories();
		registerPopupFactoryActionsAndEditiors();

		//create widget and fill
		createButtonGroupWidget = new CreateButtonGroupWidget(graphicsService);
		registerCreateControllersToWidget(createButtonGroupWidget);
		createBaseButtonGroupWidget = new CreateButtonGroupWidget(graphicsService);
		registerBaseCreateControllersToWidget(createBaseButtonGroupWidget);
		createUpdateableGroupButtonGroupWidget = new CreateButtonGroupWidget(graphicsService);
		registerUpdateableGroupCreateControllersToWidget(createUpdateableGroupButtonGroupWidget);
		navigationController = new NavigationController(graphicsService, graphicsObjectContainer.getRootContainer());

		//layout
		UIBINDER.createAndBindUi(this);
		fillWestFlowPanel();
		dockLayoutPanel.add(graphicsObjectContainer);
		RootLayoutPanel.get().add(dockLayoutPanel);

		graphicsService.start();
	}

	@UiHandler("navigationControllerToggleButton")
	public void onNavigationButtonClicked(ClickEvent e) {
		navigationController.setActive(!navigationController.isActive());
	}

	//-----------------------------------------------------------------------------
	// FUNCTIONALITY
	//-----------------------------------------------------------------------------

	private void registerControllerFactories() {
		graphicsService.registerControllerFactory(new ResizeControllerFactory());
		graphicsService.registerControllerFactory(new DragControllerFactory());
		graphicsService.registerControllerFactory(new DeleteControllerFactory());
		graphicsService.registerControllerFactory(new LabelControllerFactory());
		graphicsService.registerControllerFactory(new AnchorControllerFactory());
		graphicsService.registerControllerFactory(popupFactory);

		// TODO: re-asses unsupported controller
//		graphicsService.registerControllerFactory(new ExternalizableLabeledControllerFactory());
	}
	
	private void registerPopupFactoryActionsAndEditiors() {
		popupFactory.registerAction(new DeleteAction());
		popupFactory.registerEditor(new TextableEditor());
		popupFactory.registerEditor(new LabelEditor());
		popupFactory.registerEditor(new LabeledUpdateableEditor());
		popupFactory.registerEditor(new StrokeFillEditor());
		popupFactory.registerAction(new BringToFrontAction());
		popupFactory.registerEditor(new AnchorStyleEditor());

		// TODO: re-asses unsupported editors/action
//		popupFactory.registerAction(new DuplicateAction());
//		popupFactory.registerEditor(new ExternalLabelEditor());
//		popupFactory.registerAction(new AddTextAsAnchorAction());
//		popupFactory.registerAction(new ToggleLabelAction());
//		popupFactory.registerEditor(new TemplateLabelEditor());
	}

	private void registerBaseCreateControllersToWidget(CreateButtonGroupWidget createButtonGroupWidget) {
		createButtonGroupWidget.addCreateController(new CreateBaseTextController(graphicsService), "Base Text");
		createButtonGroupWidget.addCreateController(
				new CreateBaseRectangleController(graphicsService), "Base Rectangle");
		createButtonGroupWidget.addCreateController(new CreateBaseCircleController(graphicsService), "Base Circle");
		createButtonGroupWidget.addCreateController(new CreateBaseEllipseController(graphicsService), "Base Ellipse");
		createButtonGroupWidget.addCreateController(
				new CreateBaseIconController(graphicsService, 16, 16, url), "Base Icon");
		createButtonGroupWidget.addCreateController(new CreateBaseImageController(graphicsService, 200, 235,
				"http://tuxpaint.org/stamps/stamps/animals/birds/cartoon/tux.png"), "Base Image");
		createButtonGroupWidget.addCreateController(
				new CreateBasePathController(graphicsService, true), "Base Polygon");
		createButtonGroupWidget.addCreateController(new CreateBasePathLineController(graphicsService), "Base Line");
	}

	private void registerUpdateableGroupCreateControllersToWidget(CreateButtonGroupWidget createButtonGroupWidget) {
		createButtonGroupWidget.addCreateController(new CreateLabeledRectangleController(graphicsService),
				"Labeled Rectangle");
		createButtonGroupWidget.addCreateController(new CreateLabeledEllipseController(graphicsService),
				"Labeled Ellipse");
		createButtonGroupWidget.addCreateController(new CreateLabeledImageController(graphicsService, 200, 235,
						"http://tuxpaint.org/stamps/stamps/animals/birds/cartoon/tux.png"),
				"Labeled Image");
		createButtonGroupWidget.addCreateController(new CreateLabeledPathController(graphicsService, true),
				"Labeled Polygon");
		createButtonGroupWidget.addCreateController(new CreateLabeledPathLineController(graphicsService),
				"Labeled Line");
	}

	private void registerCreateControllersToWidget(CreateButtonGroupWidget createButtonGroupWidget) {
		createButtonGroupWidget.addCreateController(new CreateTextController(graphicsService), "Text");
		createButtonGroupWidget.addCreateController(new CreateAnchoredTextController(graphicsService), "Anchored Text");
//		createButtonGroupWidget.addCreateController(new CreateRectangleController(graphicsService), "Rectangle");
//		createButtonGroupWidget.addCreateController(new CreateEllipseController(graphicsService), "Ellipse");
//		createButtonGroupWidget.addCreateController(new CreateImageController(graphicsService, 200, 235,
//				"http://tuxpaint.org/stamps/stamps/animals/birds/cartoon/tux.png"), "Image");
//		createButtonGroupWidget.addCreateController(new CreatePathController(graphicsService, false), "Line");
//		createButtonGroupWidget.addCreateController(new CreatePathController(graphicsService, true), "Polygon");

//		createIconController = new CreateIconControllerImpl(graphicsService, 16, 16, url);
//		createButtonGroupWidget.addCreateController(createIconController, "Icon");

		createAnchoredIconController
				= new CreateAnchoredIconControllerImpl(graphicsService, 16,	16, null);
		createAnchoredIconController.setChoiceListImageSize(32);
		createButtonGroupWidget.addCreateController(createAnchoredIconController, "Anchored Icon");

		//TODO: re-asses create controllers
		/*createButtonGroupWidget.addCreateController(new CreateTextAreaHtmlController(graphicsService), "Textarea");
		createButtonGroupWidget.addCreateController(new CreateLineWithTemplateLabeledController(graphicsService),
				"Line With Templ Labeled"); */
	}

	//-----------------------------------------------------------------------------
	// LAYOUT
	//-----------------------------------------------------------------------------

	private void fillWestFlowPanel() {
		String tooltipText = "x and y offset from left top position. 0-0 position inside object. " +
				"Positive offset values means: more outside the object. Offset step is size of the cog.";
		textBoxCogXOffset.setText(popupFactory.getOffsetX() + "");
		textBoxCogXOffset.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				try {
					popupFactory.setOffsetX(Double.parseDouble(textBoxCogXOffset.getText()));
				} catch (Exception ex) {
					//don't do anything
				}
			}
		});
		textBoxCogXOffset.setTitle(tooltipText);
		textBoxCogYOffset.setText(popupFactory.getOffsetY() + "");
		textBoxCogYOffset.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				try {
					popupFactory.setOffsetY(Double.parseDouble(textBoxCogYOffset.getText()));
				} catch (Exception ex) {
					//don't do anything
				}
			}
		});
		textBoxCogYOffset.setTitle(tooltipText);

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

		captionPanelCreateButtons.setContentWidget(createButtonGroupWidget.asWidget());
		createButtonGroupWidget.asWidget().setStyleName("graphicsExample-leftPanel-createButtonsPanel");

		// TODO: review icon panel
		//createIconChoicePanel(createIconController, createAnchoredIconController);
		//westFlowPanel.add(iconChoicePanel);

		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent resizeEvent) {
				updateWestSectionToWindowHeight();
			}
		});
		updateWestSectionToWindowHeight();
	}

	private void createIconChoicePanel(final CreateIconControllerImpl createIconController,
			final CreateAnchoredIconControllerImpl createAnchoredIconController) {
		iconChoicePanel = new VerticalPanel();
		RadioButton rb0 = new RadioButton("myRadioGroup", "No icon: sets default");
		rb0.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createIconController.setHrefs(null);
				createAnchoredIconController.setHrefs(null);
			}
		});
		RadioButton rb1 = new RadioButton("myRadioGroup", "1 icon");
		rb1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createIconController.setHrefs(url);
				createAnchoredIconController.setHrefs(url);
			}
		});
		RadioButton rb2 = new RadioButton("myRadioGroup", "multiple icons");
		rb2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createIconController.setHrefs(urls);
				createAnchoredIconController.setHrefs(urls);
			}
		});
		iconChoicePanel.add(new Label("Change nr of icons in icon choice list:"));
		iconChoicePanel.add(rb0);
		iconChoicePanel.add(rb1);
		iconChoicePanel.add(rb2);
		iconChoicePanel.setVisible(false);
		rb1.setValue(true);
	}

	private void updateWestSectionToWindowHeight() {
		westScrollPanel.setHeight(Window.getClientHeight() + "px");
	}
}
