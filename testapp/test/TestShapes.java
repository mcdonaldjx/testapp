
import org.junit.*;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

/**
 * @author JaredM
 */
public class TestShapes {
	private Triangle myTri;
	private Ellipse myEllp;
	private ShapeFactory sf;
	private Random rand = new Random();
	double[] input = new double[3];
	
    @Test
    @DisplayName("Isosceles Test")
    public void testIsosceles() {
    	double a = 4;
    	double b = 4;
    	double c = 5;
    	input = new double[]{a,b,c};
    	myTri = new Triangle(input);
    	try {
    		assertTrue(myTri.isIsoscelesTriangle());
    	}
    	catch(AssertionError ae) {
    		System.out.println("I caught an assertion error with a = "+a+", b = "+b+", c = "+c+" in Isosceles Test");
    	}
    }
    @Test
    @DisplayName("Scalene Test")
    public void testScalene() {
    	double a = 3;
    	double b = 2;
    	double c = 4;
    	input = new double[]{a,b,c};
    	myTri = new Triangle(input);
    	try {
    		assertTrue(myTri.isScaleneTriangle());
    	}
    	catch(AssertionError ae) {
    		System.out.println("I caught an assertion error with a = "+a+", b = "+b+", c = "+c+" in Scalene Test");
    	}
    }
    @Test
    @DisplayName("Acute Angled Test")
    public void checkAcuteAngled() {
    	double a = 7;
    	double b = 8;
    	double c = 9;
    	input = new double[]{a,b,c};
    	myTri = new Triangle(input);
    	try {
    		assertEquals(true,myTri.isAcuteAngledTriangle());
    	}
    	catch(AssertionError ae) {
    		System.out.println("I caught an assertion error with a = "+a+", b = "+b+", c = "+c+" in Acute Angled Test");
    	}
    }
    @Test
    @DisplayName("Obtuse Angled Test")
    public void checkObtuseAngled() {
    	double a = rand.nextInt(5-2-1)+1;
    	double b = a+1;
    	double c = b;
    	input = new double[]{a,b,c};
    	myTri = new Triangle(input);
    	try {
    		assertEquals(true,myTri.isObtuseAngledTriangle());
    	}
    	catch(AssertionError ae) {
    		System.out.println("I caught an assertion error with a = "+a+", b = "+b+", c = "+c+" in Obtuse Angled Test");
    	}
    }
    
    //Ellipse Tests
    
    @Test
    @DisplayName("Is Circle Test")
    public void isCircle() {
    	input = new double[] {rand.nextInt(20-10-1)+1, rand.nextInt(20-10-1)+1};
    	myEllp = new Ellipse(input);
    	assertFalse(myEllp.isCircle());
    }
    @Test
    @DisplayName("Checking Major Axis Test")
    public void checkMajorAxis() {
    	input = new double[] {rand.nextInt(20-10-1)+1, rand.nextInt(20-10-1)+1};
    	myEllp = new Ellipse(input);
    	assertTrue(myEllp.isMajorAxisLarger());
    }
    @Test
    @DisplayName("Checking Minor Axis Test")
    public void checkMinorAxis() {
    	input = new double[] {rand.nextInt(20-10-1)+1, rand.nextInt(20-10-1)+1};
    	myEllp = new Ellipse(input);
    	assertFalse(myEllp.isMinorAxisLarger());
    }
    
    @Test
    @DisplayName("Comparing Area")
    public void sameAreas() {
    	input = new double[] {rand.nextInt(20-10-1)+1, rand.nextInt(20-10-1)+1};
    	myEllp = new Ellipse(input);
    	double[] secInput = new double[] {rand.nextInt(20-10-1)+1, rand.nextInt(20-10-1)+1};
    	Ellipse secEllp = new Ellipse(secInput);
    	assertEquals(myEllp.getArea(), secEllp.getArea(), 0);
    }
    
    //ShapeFactory Tests
    
    @Test
    @DisplayName("Checking Invalid Type")
    public void tryInvalidShape() {
    	assertThrows(IllegalArgumentException.class, ShapeFactory.Create("Hexagon", new double[]{10,20,30,10,20,20}));
    }

	//Rectangle Tests
	@Test
	@DisplayName("Check Rectangle Sides")
	public void checkRectangleSides(){
		Rectangle myRectangle = new Rectangle(new double[] {1,2});
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, new Rectangle(new double[] {1,2,3}));
		assertThat(e).hadMessageThat().contains("number");
	}

		@Test
	@DisplayName("Check Rectangle Lengths")
	public void checkRectangleSides(){
		Rectangle myRectangle = new Rectangle(new double[] {1,0});
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, new Rectangle(new double[] {1,0}));
		assertThat(e).hadMessageThat().contains("values");
	}
}
