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
package org.geomajas.graphics.client.widget.createcontrollergroup;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.geomajas.graphics.client.controller.MetaController;
import org.geomajas.graphics.client.controller.create.CreateController;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer;
import org.geomajas.graphics.client.widget.createcontrollergroup.CreateControllerGroupPresenter.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;

import com.google.gwtmockito.GwtMockitoTestRunner;

@RunWith(GwtMockitoTestRunner.class)
public class CreateControllerGroupPresenterImplTest {

	@Mock(answer = Answers.CALLS_REAL_METHODS)
	private CreateController<?> c1;

	@Mock(answer = Answers.CALLS_REAL_METHODS)
	private CreateController<?> c2;

	@Mock
	private View view;
	
	@Mock
	private GraphicsService graphicsService;
	
	@Mock
	private GraphicsObjectContainer graphicsObjectContainer;
	
	@Mock
	private MetaController metaController;
	
	@Before
	public void setup() {
		when(graphicsService.getObjectContainer()).thenReturn(graphicsObjectContainer);
		when(graphicsService.getMetaController()).thenReturn(metaController);
	}

	@Test
	public void testViewSetHandler() throws Exception {
		CreateControllerGroupPresenterImpl p = new CreateControllerGroupPresenterImpl(graphicsService, view);
		p.addCreateController(c1, "label1");
		p.addCreateController(c2, "label2");
		verify(graphicsObjectContainer).addGraphicsObjectContainerHandler(p);
		verify(view).setHandler(p);
		verify(view).addCreateController(c1, "label1");
		verify(view).addCreateController(c2, "label2");
	}


	@Test
	public void testOnActivateTrue() throws Exception {
		CreateControllerGroupPresenterImpl p = new CreateControllerGroupPresenterImpl(graphicsService, view);
		p.addCreateController(c1, "label1");
		p.addCreateController(c2, "label2");
		p.onActivateController(c1, true);

		verify(c1).setActive(true);
		verify(c2).setActive(false);
		verify(c2, never()).setActive(true);
		verify(metaController).setActive(false);
	}

	@Test
	public void testOnActivateWhileOtherActive() throws Exception {
		// add controller to presenter
		CreateControllerGroupPresenterImpl p = new CreateControllerGroupPresenterImpl(graphicsService, view);
		p.addCreateController(c1, "label1");
		p.addCreateController(c2, "label2");
		p.onActivateController(c1, true);
		reset(c1);
		reset(c2);
		reset(metaController);

		p.onActivateController(c2, true);

		verify(c2).setActive(true);
		verify(c1).setActive(false);
		verify(c1, never()).setActive(true);
		verify(metaController).setActive(false);
	}

	@Test
	public void testOnDeActivateAfterActive() throws Exception {
		// add controller to presenter
		CreateControllerGroupPresenterImpl p = new CreateControllerGroupPresenterImpl(graphicsService, view);
		p.addCreateController(c1, "label1");
		p.addCreateController(c2, "label2");
		p.onActivateController(c1, true);
		reset(c1);
		reset(c2);
		reset(metaController);

		p.onActivateController(c1, false);

		verify(c1).setActive(false);
		verify(c1, never()).setActive(true);
		verify(c2).setActive(false);
		verify(c2, never()).setActive(true);
		verify(metaController).setActive(true);
	}
}
