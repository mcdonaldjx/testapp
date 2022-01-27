package testapp;

/*
	@author mcdonaldjx
 // Lazy Singleton version
*/

public class ShapeFactory {
	private static ShapeFactory instance;
	
	private ShapeFactory(){
	}
	
	public static ShapeFactory getInstance(){
			if(instance == null){
				instance = new ShapeFactory();
			}
			return instance;
	}
	
	static Shape Create(String type, double [] input) { //Called by ShapeFactory.getInstance().Create() ?
        switch(type) {
            case "Ellipse":
                return new Ellipse(input);
            case "Rectangle":
                return new Rectangle(input);
            case "Triangle":
                return new Triangle(input);
            default:
                throw new IllegalArgumentException("Unsupported shape");
        }
    }
}
