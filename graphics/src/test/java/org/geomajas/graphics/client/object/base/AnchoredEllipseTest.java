package org.geomajas.graphics.client.object.base;

import junit.framework.Assert;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.GraphicsBaseTest;
import org.geomajas.graphics.client.render.shape.AnchoredEllipseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gwtmockito.GwtMockitoTestRunner;

@RunWith(GwtMockitoTestRunner.class)
public class AnchoredEllipseTest  extends GraphicsBaseTest {

	@Test
	public void testBounds() {
		for (double x = -0.5; x < 1; x += 0.5) {
			for (double y = -0.5; y < 1; y += 0.5) {
				AnchoredEllipseImpl c = new AnchoredEllipseImpl(20, 10, 10, 5, x, y);
				c.setScale(1, -1);
				c.setTranslation(0, 100);
				Assert.assertTrue(BboxService.equals(c.getUserBounds(), new Bbox(10 - 20 * x, 5 - 10 * y, 20, 10),
						0.00001));
				Assert.assertTrue(BboxService.equals(c.getBounds(), new Bbox(10 - 20 * x, 85 + 10 * y, 20, 10), 0.00001));
			}
		}
	}
}
