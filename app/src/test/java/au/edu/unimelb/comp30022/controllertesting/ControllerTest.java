package au.edu.unimelb.comp30022.controllertesting;

import android.location.Location;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    //@InjectMocks annotation is used to create and inject the mock object
    @InjectMocks
    Controller controller;

    //@Mock annotation is used to create the mock object to be injected
    @Mock
    PostageRateCalculator postageRateCalculator;

    @Mock
    PostcodeValidator postCodeValidator;

    @Mock
    AddressTools addressTools;

    @Mock
    Location location;

    @Test
    public void testCostLabel() {
        Mockito.when(postageRateCalculator.computeCost(
                Mockito.any(Location.class),
                Mockito.any(Location.class)))
                .thenReturn(5);
        Mockito.when(postCodeValidator.isValid(Mockito.any(String.class)))
                .thenReturn(true);
        Mockito.when(addressTools.locationFromAddress(Mockito.any(Address.class)))
                .thenReturn(location);

        controller = new Controller(addressTools, postCodeValidator, postageRateCalculator);

        controller.calculateButtonPressed();

        //test the cost label
        Assert.assertEquals(controller.costLabel.getText(), "$5");
    }

    @Before
    public void setUp() throws Exception {
        UI.addWidget("SOURCE_POST_CODE", new EditText());
        UI.addWidget("DESTINATION_POST_CODE", new EditText());
        UI.addWidget("COST_LABEL", new TextView());
    }

    @After
    public void tearDown() throws Exception {
        // don't need to do anything
    }
}