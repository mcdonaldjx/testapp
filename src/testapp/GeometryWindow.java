package testapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.awt.event.ItemEvent;
import javax.swing.JFormattedTextField;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

public class GeometryWindow {

	private JFrame frmGeometry;
	private CardLayout inputCards = new CardLayout(0, 0);
	private CardLayout outputCards= new CardLayout(0, 0);
	private CardLayout shapeAInputCards = new CardLayout(0, 0);
	private CardLayout shapeBInputCards = new CardLayout(0, 0);
	public ShapeFactory shapeCreator = new ShapeFactory();
	private int calcMode, shapeAMode, shapeBMode = -1;
	private DecimalFormat doubleFormat = new DecimalFormat("###,####.##");
	private NumberFormatter doubleFormatter = new NumberFormatter(doubleFormat);
	
	private double calcCircum, calcArea, shapeACircum, shapeAArea, shapeBCircum, shapeBArea;
	
	private Shape shapeA = null;
	private Shape shapeB = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeometryWindow window = new GeometryWindow();
					window.frmGeometry.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GeometryWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGeometry = new JFrame();
		frmGeometry.setTitle("Geometry");
		frmGeometry.setBounds(100, 100, 599, 530);
		frmGeometry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGeometry.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 585, 500);
		frmGeometry.getContentPane().add(tabbedPane);
		
		JPanel calculatePanel = new JPanel();
		calculatePanel.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Calculate", null, calculatePanel, "Calculate a shape!");
		calculatePanel.setLayout(null);
		
		String[] shapes = {"Ellipse", "Rectangle","Triangle"};
		
		JLabel shapeLabel = new JLabel("Shape:");
		shapeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeLabel.setForeground(Color.WHITE);
		shapeLabel.setBounds(183, 11, 46, 22);
		calculatePanel.add(shapeLabel);
		
		
		
		JLabel shapePicture = new JLabel("");
		shapePicture.setBounds(250, 44, 75, 75);
		calculatePanel.add(shapePicture);
		
		JPanel outputPanel = new JPanel();
		outputPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		outputPanel.setForeground(Color.WHITE);
		outputPanel.setBackground(Color.DARK_GRAY);
		outputPanel.setBounds(36, 226, 498, 175);
		calculatePanel.add(outputPanel);
		outputPanel.setLayout(outputCards);
		
		JPanel blankOutput = new JPanel();
		blankOutput.setBackground(Color.DARK_GRAY);
		outputPanel.add(blankOutput, "name_1121216275611900");
		outputCards.addLayoutComponent(blankOutput,"blankOutput");
		
		
		JPanel ellipseOutput = new JPanel();
		ellipseOutput.setBackground(Color.DARK_GRAY);
		outputPanel.add(ellipseOutput, "name_1121116907526300");
		ellipseOutput.setLayout(null);
		outputCards.addLayoutComponent(ellipseOutput,"ellipseOutput");
		
		JLabel circumLabel = new JLabel("Circumference:");
		circumLabel.setForeground(Color.WHITE);
		circumLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		circumLabel.setBounds(42, 11, 98, 20);
		ellipseOutput.add(circumLabel);
		
		JLabel ellipCircumLabel = new JLabel("New label");
		ellipCircumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ellipCircumLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ellipCircumLabel.setForeground(Color.WHITE);
		ellipCircumLabel.setBounds(150, 15, 103, 14);
		ellipseOutput.add(ellipCircumLabel);
		
		JLabel areaLabel = new JLabel("Area:");
		areaLabel.setForeground(Color.WHITE);
		areaLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		areaLabel.setBounds(295, 11, 45, 20);
		ellipseOutput.add(areaLabel);
		
		JLabel ellipAreaLabel = new JLabel("New label");
		ellipAreaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ellipAreaLabel.setForeground(Color.WHITE);
		ellipAreaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ellipAreaLabel.setBounds(350, 15, 103, 14);
		ellipseOutput.add(ellipAreaLabel);
		
		JLabel isCircleLabel = new JLabel("is Circle?");
		isCircleLabel.setForeground(Color.WHITE);
		isCircleLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		isCircleLabel.setBounds(192, 76, 65, 20);
		ellipseOutput.add(isCircleLabel);
		
		JLabel circumLabel_1_1 = new JLabel("is Major Axis Larger?");
		circumLabel_1_1.setForeground(Color.WHITE);
		circumLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		circumLabel_1_1.setBounds(32, 142, 134, 20);
		ellipseOutput.add(circumLabel_1_1);
		
		JLabel isCircleResult = new JLabel("X");
		isCircleResult.setForeground(Color.WHITE);
		isCircleResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isCircleResult.setBounds(266, 79, 34, 15);
		ellipseOutput.add(isCircleResult);
		
		JLabel isMajorAxisLargerLabel = new JLabel("X");
		isMajorAxisLargerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		isMajorAxisLargerLabel.setForeground(Color.WHITE);
		isMajorAxisLargerLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isMajorAxisLargerLabel.setBounds(198, 145, 34, 15);
		ellipseOutput.add(isMajorAxisLargerLabel);
		
		JLabel circumLabel_1_1_1 = new JLabel("is Minor Axis Larger?");
		circumLabel_1_1_1.setForeground(Color.WHITE);
		circumLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		circumLabel_1_1_1.setBounds(264, 142, 134, 20);
		ellipseOutput.add(circumLabel_1_1_1);
		
		JLabel isMinorAxisLargerLabel = new JLabel("X");
		isMinorAxisLargerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		isMinorAxisLargerLabel.setForeground(Color.WHITE);
		isMinorAxisLargerLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isMinorAxisLargerLabel.setBounds(430, 145, 34, 15);
		ellipseOutput.add(isMinorAxisLargerLabel);
		
		JPanel rectangleOutput = new JPanel();
		rectangleOutput.setBackground(Color.DARK_GRAY);
		outputPanel.add(rectangleOutput, "name_1121123959239700");
		outputCards.addLayoutComponent(rectangleOutput,"rectangleOutput");
		rectangleOutput.setLayout(null);
		
		JLabel circumLabel_1 = new JLabel("Circumference:");
		circumLabel_1.setBounds(53, 11, 90, 15);
		circumLabel_1.setForeground(Color.WHITE);
		circumLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		rectangleOutput.add(circumLabel_1);
		
		JLabel rectCircumLabel = new JLabel("");
		rectCircumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rectCircumLabel.setBounds(196, 11, 53, 15);
		rectCircumLabel.setForeground(Color.WHITE);
		rectCircumLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rectangleOutput.add(rectCircumLabel);
		
		JLabel areaLabel_1 = new JLabel("Area:");
		areaLabel_1.setBounds(302, 11, 32, 15);
		areaLabel_1.setForeground(Color.WHITE);
		areaLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		rectangleOutput.add(areaLabel_1);
		
		JLabel rectAreaLabel = new JLabel("");
		rectAreaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rectAreaLabel.setBounds(387, 11, 53, 15);
		rectAreaLabel.setForeground(Color.WHITE);
		rectAreaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rectangleOutput.add(rectAreaLabel);
		
		JLabel isSquareLabel = new JLabel("is Square?");
		isSquareLabel.setBounds(196, 79, 78, 15);
		isSquareLabel.setForeground(Color.WHITE);
		isSquareLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		rectangleOutput.add(isSquareLabel);
		
		JLabel isSquareResult = new JLabel("X");
		isSquareResult.setHorizontalAlignment(SwingConstants.CENTER);
		isSquareResult.setBounds(282, 79, 34, 15);
		isSquareResult.setForeground(Color.WHITE);
		isSquareResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rectangleOutput.add(isSquareResult);
		
		JPanel triangleOutput = new JPanel();
		triangleOutput.setBackground(Color.DARK_GRAY);
		outputPanel.add(triangleOutput, "name_1121140164680199");
		outputCards.addLayoutComponent(triangleOutput,"triangleOutput");
		triangleOutput.setLayout(null);
		
		JLabel circumLabel_2 = new JLabel("Circumference:");
		circumLabel_2.setForeground(Color.WHITE);
		circumLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		circumLabel_2.setBounds(42, 0, 98, 20);
		triangleOutput.add(circumLabel_2);
		
		JLabel triCircum = new JLabel("New label");
		triCircum.setHorizontalAlignment(SwingConstants.CENTER);
		triCircum.setForeground(Color.WHITE);
		triCircum.setFont(new Font("Tahoma", Font.PLAIN, 12));
		triCircum.setBounds(182, 3, 71, 14);
		triangleOutput.add(triCircum);
		
		JLabel areaLabel_2 = new JLabel("Area:");
		areaLabel_2.setForeground(Color.WHITE);
		areaLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		areaLabel_2.setBounds(295, 0, 45, 20);
		triangleOutput.add(areaLabel_2);
		
		JLabel triArea = new JLabel("New label");
		triArea.setHorizontalAlignment(SwingConstants.CENTER);
		triArea.setForeground(Color.WHITE);
		triArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		triArea.setBounds(382, 3, 71, 14);
		triangleOutput.add(triArea);
		
		JLabel isEqui = new JLabel("is Equilateral?");
		isEqui.setForeground(Color.WHITE);
		isEqui.setFont(new Font("Tahoma", Font.BOLD, 12));
		isEqui.setBounds(6, 72, 90, 15);
		triangleOutput.add(isEqui);
		
		JLabel isEquiResult = new JLabel("X");
		isEquiResult.setHorizontalAlignment(SwingConstants.CENTER);
		isEquiResult.setForeground(Color.WHITE);
		isEquiResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isEquiResult.setBounds(102, 72, 34, 15);
		triangleOutput.add(isEquiResult);
		
		JLabel isRight = new JLabel("is Right?");
		isRight.setForeground(Color.WHITE);
		isRight.setFont(new Font("Tahoma", Font.BOLD, 12));
		isRight.setBounds(142, 72, 60, 15);
		triangleOutput.add(isRight);
		
		JLabel isRightResult = new JLabel("X");
		isRightResult.setHorizontalAlignment(SwingConstants.CENTER);
		isRightResult.setForeground(Color.WHITE);
		isRightResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isRightResult.setBounds(208, 72, 34, 15);
		triangleOutput.add(isRightResult);
		
		JLabel lblIsObtuseAngled = new JLabel("is Obtuse Angled?");
		lblIsObtuseAngled.setForeground(Color.WHITE);
		lblIsObtuseAngled.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIsObtuseAngled.setBounds(41, 127, 116, 15);
		triangleOutput.add(lblIsObtuseAngled);
		
		JLabel isObtuseAngResult = new JLabel("X");
		isObtuseAngResult.setHorizontalAlignment(SwingConstants.CENTER);
		isObtuseAngResult.setForeground(Color.WHITE);
		isObtuseAngResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isObtuseAngResult.setBounds(198, 127, 34, 15);
		triangleOutput.add(isObtuseAngResult);
		
		JLabel lblIsAcuteAngled = new JLabel("is Acute Angled?");
		lblIsAcuteAngled.setForeground(Color.WHITE);
		lblIsAcuteAngled.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIsAcuteAngled.setBounds(273, 127, 103, 15);
		triangleOutput.add(lblIsAcuteAngled);
		
		JLabel isAcuteAngResult = new JLabel("X");
		isAcuteAngResult.setHorizontalAlignment(SwingConstants.CENTER);
		isAcuteAngResult.setForeground(Color.WHITE);
		isAcuteAngResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isAcuteAngResult.setBounds(417, 127, 34, 15);
		triangleOutput.add(isAcuteAngResult);
		
		JLabel isIsosceles = new JLabel("is Isosceles?");
		isIsosceles.setForeground(Color.WHITE);
		isIsosceles.setFont(new Font("Tahoma", Font.BOLD, 12));
		isIsosceles.setBounds(248, 72, 78, 15);
		triangleOutput.add(isIsosceles);
		
		JLabel isIsosResult = new JLabel("X");
		isIsosResult.setHorizontalAlignment(SwingConstants.CENTER);
		isIsosResult.setForeground(Color.WHITE);
		isIsosResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isIsosResult.setBounds(332, 72, 34, 15);
		triangleOutput.add(isIsosResult);
		
		JLabel lblIsScalene = new JLabel("is Scalene?");
		lblIsScalene.setForeground(Color.WHITE);
		lblIsScalene.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIsScalene.setBounds(372, 72, 71, 15);
		triangleOutput.add(lblIsScalene);
		
		JLabel isScaleneResult = new JLabel("X");
		isScaleneResult.setHorizontalAlignment(SwingConstants.CENTER);
		isScaleneResult.setForeground(Color.WHITE);
		isScaleneResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isScaleneResult.setBounds(449, 72, 34, 15);
		triangleOutput.add(isScaleneResult);
			
		JPanel inputPanel = new JPanel();
		inputPanel.setForeground(Color.WHITE);
		inputPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		inputPanel.setBackground(Color.DARK_GRAY);
		inputPanel.setBounds(36, 130, 498, 51);
		calculatePanel.add(inputPanel);
		inputPanel.setLayout(inputCards);
		
		JPanel blankInput = new JPanel();
		blankInput.setBackground(Color.DARK_GRAY);
		inputPanel.add(blankInput, "name_1122221651807700");
		inputCards.addLayoutComponent(blankInput,"blankInput");
		
		JPanel twoInput = new JPanel();
		twoInput.setBackground(Color.DARK_GRAY);
		inputPanel.add(twoInput, "name_1122059370097900");
		twoInput.setLayout(null);
		inputPanel.add(twoInput);
		inputCards.addLayoutComponent(twoInput,"twoInput");
		
		JLabel input1Label = new JLabel("Major Axis:");
		input1Label.setForeground(Color.WHITE);
		input1Label.setFont(new Font("Tahoma", Font.BOLD, 12));
		input1Label.setBounds(30, 13, 86, 22);
		twoInput.add(input1Label);
		
		JLabel input2Label = new JLabel("Minor Axis:");
		input2Label.setForeground(Color.WHITE);
		input2Label.setFont(new Font("Tahoma", Font.BOLD, 12));
		input2Label.setBounds(262, 13, 86, 22);
		twoInput.add(input2Label);
		
		doubleFormatter.setAllowsInvalid(false);
		
		JFormattedTextField input1Field = new JFormattedTextField(doubleFormatter);
		input1Field.setFont(new Font("Tahoma", Font.PLAIN, 12));
		input1Field.setBounds(146, 13, 86, 22);
		twoInput.add(input1Field);
		
		JFormattedTextField input2Field = new JFormattedTextField(doubleFormatter);
		input2Field.setFont(new Font("Tahoma", Font.PLAIN, 12));
		input2Field.setBounds(378, 13, 86, 22);
		twoInput.add(input2Field);
		
		JPanel triangleInput = new JPanel();
		triangleInput.setLayout(null);
		triangleInput.setBackground(Color.DARK_GRAY);
		inputPanel.add(triangleInput, "name_1122158849217200");
		inputPanel.add(triangleInput);
		inputCards.addLayoutComponent(triangleInput, "triangleInput");
		
		JLabel sideALabel = new JLabel("Side a:");
		sideALabel.setForeground(Color.WHITE);
		sideALabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		sideALabel.setBounds(12, 13, 51, 22);
		triangleInput.add(sideALabel);
		
		JLabel sideCLabel = new JLabel("Side c:");
		sideCLabel.setForeground(Color.WHITE);
		sideCLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		sideCLabel.setBounds(334, 13, 51, 22);
		triangleInput.add(sideCLabel);
		
		JLabel sideBLabel = new JLabel("Side b:");
		sideBLabel.setForeground(Color.WHITE);
		sideBLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		sideBLabel.setBounds(173, 13, 51, 22);
		triangleInput.add(sideBLabel);
		
		JFormattedTextField sideAField = new JFormattedTextField(doubleFormatter);
		sideAField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sideAField.setBounds(60, 13, 86, 22);
		triangleInput.add(sideAField);
		
		JFormattedTextField sideBField = new JFormattedTextField(doubleFormatter);
		sideBField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sideBField.setBounds(224, 13, 86, 22);
		triangleInput.add(sideBField);
		
		JFormattedTextField sideCField = new JFormattedTextField(doubleFormatter);
		sideCField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sideCField.setBounds(395, 13, 86, 22);
		triangleInput.add(sideCField);
		

		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selected = comboBox.getSelectedItem().toString();
				if(selected.equals("Ellipse")) {
					calcMode = 1;
					
					input1Field.setText(null);
					input2Field.setText(null);
					
					input1Label.setText("Major Axis:");
					input1Label.setBounds(30, 13, 86, 22);
					input2Label.setText("Minor Axis:");
					input2Label.setBounds(262, 13, 86, 22);
					input1Field.setBounds(146, 13, 86, 22);
					input2Field.setBounds(378, 13, 86, 22);					
					inputCards.show(inputPanel, "twoInput");
					outputCards.show(outputPanel,"blankOutput");
				}
				else if(selected.equals("Rectangle")) {
					calcMode = 2;
					
					input1Field.setText("");
					input2Field.setText("");
					
					input1Label.setText("Base:");
					input1Label.setBounds(46, 13, 38, 22);
					input2Label.setText("Height:");
					input2Label.setBounds(262, 13, 52, 22);
					input1Field.setBounds(130, 13, 86, 22);
					input2Field.setBounds(360, 13, 86, 22);
					inputCards.show(inputPanel, "twoInput");
					outputCards.show(outputPanel,"blankOutput");
				}
				else if(selected.equals("Triangle")) {
					calcMode = 3;
					
					sideAField.setText("");
					sideBField.setText("");
					sideCField.setText("");
					
					inputCards.show(inputPanel, "triangleInput");
					outputCards.show(outputPanel,"blankOutput");
				}
				else {
					calcMode = -1;
					
					input1Field.setText("");
					input2Field.setText("");
					sideAField.setText("");
					sideBField.setText("");
					sideCField.setText("");
					
					inputCards.show(inputPanel, "blankInput");
					outputCards.show(outputPanel,"blankOutput");
				}
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Ellipse", "Rectangle", "Triangle"}));
		comboBox.setBounds(240, 11, 100, 22);
		calculatePanel.add(comboBox);
		
		double[] input = new double[2];
		
		JButton calculateButton = new JButton("Calculate!");
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(calcMode == 1) {
					if(input1Field.getText().length() < 1 || input1Field.getText().equals(null)) {
						input[0] = 0;
					}
					else {
						input[0] =Math.round(Double.parseDouble(input1Field.getText())*100.0)/100.0;
					}
					if(input2Field.getText().length() < 1 || input2Field.getText().equals(null)) {
						input[1] = 0;
					}
					else {
						input[1] =Math.round(Double.parseDouble(input2Field.getText())*100.0)/100.0;
					}
					try {
						Ellipse ellip = (Ellipse) ShapeFactory.Create("Ellipse",input);
						ellipAreaLabel.setText(String.valueOf(ellip.getArea()));
						ellipCircumLabel.setText(String.valueOf(ellip.getCircumference()));
						isCircleResult.setText(String.valueOf(ellip.isCircle()).toString());
						isMajorAxisLargerLabel.setText(String.valueOf(ellip.isMajorAxisLarger()).toString());
						isMinorAxisLargerLabel.setText(String.valueOf(ellip.isMinorAxisLarger()).toString());
						outputCards.show(outputPanel, "ellipseOutput");
					}
					catch(IllegalArgumentException iae) {
						System.err.println("Invalid axis values!");
					}
					
				}
				else if(calcMode == 2) {
					if(input1Field.getText().length() < 1 || input1Field.getText().equals(null)) {
						input[0] = 0;
					}
					else {
						input[0] =Math.round(Double.parseDouble(input1Field.getText())*100.0)/100.0;
					}
					if(input2Field.getText().length() < 1 || input2Field.getText().equals(null)) {
						input[1] = 0;
					}
					else {
						input[1] =Math.round(Double.parseDouble(input2Field.getText())*100.0)/100.0;
					}
					try {
						Rectangle rect = (Rectangle) ShapeFactory.Create("Rectangle",input);
						rectAreaLabel.setText(String.valueOf(rect.getArea()));
						rectCircumLabel.setText(String.valueOf(rect.getCircumference()));
						isSquareResult.setText(String.valueOf(rect.isSquare()).toString());
						
					}
					catch(IllegalArgumentException iae) {
						//Show popup saying "invalid side values!"
						System.err.println("Invalid side values!");
					}
					
					outputCards.show(outputPanel, "rectangleOutput");
				}
				else if(calcMode == 3) {
					double[] input = new double[3];
					if(sideAField.getText().length() < 1 || sideAField.getText().equals(null)) {
						input[0] = 0;
					}
					else {
						input[0] =Math.round(Double.parseDouble(sideAField.getText())*100.0)/100.0;
					}
					if(sideBField.getText().length() < 1 || sideBField.getText().equals(null)) {
						input[1] = 0;
					}
					else {
						input[1] =Math.round(Double.parseDouble(sideBField.getText())*100.0)/100.0;
					}
					if(sideCField.getText().length() < 1 || sideCField.getText().equals(null)) {
						input[2] = 0;
					}
					else {
						input[2] =Math.round(Double.parseDouble(sideCField.getText())*100.0)/100.0;
					}
					try {
						Triangle tri = (Triangle) ShapeFactory.Create("Triangle",input);
						triArea.setText(String.valueOf(tri.getArea()));
						triCircum.setText(String.valueOf(tri.getCircumference()));
						isEquiResult.setText(String.valueOf(tri.isEquilateralTriangle()).toString());
						isScaleneResult.setText(String.valueOf(tri.isScaleneTriangle()).toString());
						isRightResult.setText(String.valueOf(tri.isRightTriangle()).toString());
						isIsosResult.setText(String.valueOf(tri.isIsoscelesTriangle()).toString());
						isAcuteAngResult.setText(String.valueOf(tri.isAcuteAngledTriangle()).toString());
						isObtuseAngResult.setText(String.valueOf(tri.isObtuseAngledTriangle()).toString());
						
					}
					catch(IllegalArgumentException iae) {
						//Show popup saying "invalid side values!"
						System.err.println("Invalid side values! ");
					}
					outputCards.show(outputPanel, "triangleOutput");
				}
				else {
					//Do nothing
				}
			}
		});
		calculateButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		calculateButton.setBounds(238, 192, 86, 23);
		calculatePanel.add(calculateButton);
		
		
		//Comparing shapes Tab
		
		JPanel comparePanel = new JPanel();
		comparePanel.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Compare", null, comparePanel, "Compare two shapes!");
		comparePanel.setLayout(null);
		
		JPanel shapeBPanel = new JPanel();
		shapeBPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		shapeBPanel.setBackground(Color.DARK_GRAY);
		shapeBPanel.setBounds(307, 11, 239, 153);
		comparePanel.add(shapeBPanel);
		shapeBPanel.setLayout(null);
		

		
		JLabel shapeALabel = new JLabel("Shape B:");
		shapeALabel.setForeground(Color.WHITE);
		shapeALabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeALabel.setBounds(27, 11, 56, 22);
		shapeBPanel.add(shapeALabel);
		
		JLabel shapeBPicture = new JLabel("");
		shapeBPicture.setBounds(82, 55, 75, 75);
		shapeBPanel.add(shapeBPicture);
		
		JPanel shapeAInputs = new JPanel();
		shapeAInputs.setForeground(Color.WHITE);
		shapeAInputs.setBackground(Color.DARK_GRAY);
		shapeAInputs.setBounds(34, 175, 240, 102);
		comparePanel.add(shapeAInputs);
		shapeAInputs.setLayout(shapeAInputCards);
		
		JPanel aBlankInput = new JPanel();
		aBlankInput.setForeground(Color.WHITE);
		aBlankInput.setBackground(Color.DARK_GRAY);
		shapeAInputs.add(aBlankInput, "name_1892516502200");
		shapeAInputCards.addLayoutComponent(aBlankInput,"aBlankInput");
		
		JPanel aTriangleInput = new JPanel();
		aTriangleInput.setForeground(Color.WHITE);
		aTriangleInput.setBackground(Color.DARK_GRAY);
		shapeAInputs.add(aTriangleInput, "name_1921499780000");
		aTriangleInput.setLayout(null);
		shapeAInputCards.addLayoutComponent(aTriangleInput,"aTriangleInput");
		
		JLabel sideALabel_1 = new JLabel("Side a:");
		sideALabel_1.setForeground(Color.WHITE);
		sideALabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		sideALabel_1.setBounds(37, 14, 41, 15);
		aTriangleInput.add(sideALabel_1);
		
		JLabel sideCLabel_1 = new JLabel("Side c:");
		sideCLabel_1.setForeground(Color.WHITE);
		sideCLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		sideCLabel_1.setBounds(38, 72, 40, 15);
		aTriangleInput.add(sideCLabel_1);
		
		JLabel sideBLabel_1 = new JLabel("Side b:");
		sideBLabel_1.setForeground(Color.WHITE);
		sideBLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		sideBLabel_1.setBounds(37, 43, 42, 15);
		aTriangleInput.add(sideBLabel_1);
		
		JFormattedTextField shapeAsideA = new JFormattedTextField(doubleFormatter);
		shapeAsideA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeAsideA.setBounds(115, 11, 86, 22);
		aTriangleInput.add(shapeAsideA);
		
		JFormattedTextField shapeAsideB = new JFormattedTextField(doubleFormatter);
		shapeAsideB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeAsideB.setBounds(116, 42, 86, 22);
		aTriangleInput.add(shapeAsideB);
		
		JFormattedTextField shapeAsideC = new JFormattedTextField(doubleFormatter);
		shapeAsideC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeAsideC.setBounds(116, 73, 86, 22);
		aTriangleInput.add(shapeAsideC);
		
		JPanel aTwoInput = new JPanel();
		aTwoInput.setBorder(new LineBorder(new Color(0, 0, 0)));
		aTwoInput.setForeground(Color.WHITE);
		aTwoInput.setBackground(Color.DARK_GRAY);
		shapeAInputs.add(aTwoInput, "name_3390662417900");
		shapeAInputCards.addLayoutComponent(aTwoInput,"aTwoInput");
		aTwoInput.setLayout(null);
		
		JLabel shapeAInput1Label = new JLabel("Side a:");
		shapeAInput1Label.setForeground(Color.WHITE);
		shapeAInput1Label.setFont(new Font("Tahoma", Font.BOLD, 12));
		shapeAInput1Label.setBounds(28, 19, 68, 22);
		aTwoInput.add(shapeAInput1Label);
		
		JFormattedTextField shapeAInput1Field = new JFormattedTextField(doubleFormatter);
		shapeAInput1Field.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeAInput1Field.setBounds(124, 19, 86, 22);
		aTwoInput.add(shapeAInput1Field);
		
		JFormattedTextField shapeAInput2Field = new JFormattedTextField(doubleFormatter);
		shapeAInput2Field.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeAInput2Field.setBounds(124, 59, 86, 22);
		aTwoInput.add(shapeAInput2Field);
		
		JLabel shapeAInput2Label = new JLabel("Side b:");
		shapeAInput2Label.setForeground(Color.WHITE);
		shapeAInput2Label.setFont(new Font("Tahoma", Font.BOLD, 12));
		shapeAInput2Label.setBounds(28, 60, 69, 22);
		aTwoInput.add(shapeAInput2Label);
		
		JPanel shapeAOutputs = new JPanel();
		shapeAOutputs.setForeground(Color.WHITE);
		shapeAOutputs.setBackground(Color.DARK_GRAY);
		shapeAOutputs.setBounds(34, 322, 239, 126);
		comparePanel.add(shapeAOutputs);
		shapeAOutputs.setLayout(null);
		
		JLabel circumLabel_3 = new JLabel("Circumference:");
		circumLabel_3.setForeground(Color.WHITE);
		circumLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		circumLabel_3.setBounds(23, 11, 98, 20);
		shapeAOutputs.add(circumLabel_3);
		
		JLabel shapeACircumLabel = new JLabel("");
		shapeACircumLabel.setForeground(Color.WHITE);
		shapeACircumLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeACircumLabel.setBounds(144, 14, 71, 14);
		shapeAOutputs.add(shapeACircumLabel);
		
		JLabel areaLabel_3 = new JLabel("Area:");
		areaLabel_3.setForeground(Color.WHITE);
		areaLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		areaLabel_3.setBounds(50, 77, 45, 20);
		shapeAOutputs.add(areaLabel_3);
		
		JLabel shapeAAreaLabel = new JLabel("");
		shapeAAreaLabel.setForeground(Color.WHITE);
		shapeAAreaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeAAreaLabel.setBounds(144, 80, 71, 14);
		shapeAOutputs.add(shapeAAreaLabel);
		
		JPanel shapeAPanel = new JPanel();
		shapeAPanel.setLayout(null);
		shapeAPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		shapeAPanel.setBackground(Color.DARK_GRAY);
		shapeAPanel.setBounds(34, 11, 240, 153);
		comparePanel.add(shapeAPanel);
		
		JComboBox shapeACombo = new JComboBox();
		shapeACombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selected = shapeACombo.getSelectedItem().toString();
				if(selected.equals("Ellipse")) {
					shapeAMode = 1;
					shapeAInput1Label.setText("Major Axis:");
					shapeAInput2Label.setText("Minor Axis:");
					shapeAInput1Field.setText(null);
					shapeAInput2Field.setText(null);
					shapeAInputCards.show(shapeAInputs,"aTwoInput");			
				}
				else if(selected.equals("Rectangle")) {
					shapeAMode = 2;
					
					shapeAInput1Field.setText(null);
					shapeAInput2Field.setText(null);
					shapeAInputCards.show(shapeAInputs,"aTwoInput");
					
				}
				else if(selected.equals("Triangle")) {
					shapeAMode = 3;
					
					shapeAInput1Field.setText(null);
					shapeAInput2Field.setText(null);
					shapeAInputCards.show(shapeAInputs,"aTriangleInput");
				}
				else {
					shapeAMode = -1;
					
					shapeAInput1Field.setText(null);
					shapeAInput2Field.setText(null);
					shapeAInputCards.show(shapeAInputs,"aBlankInput");
					shapeAAreaLabel.setText("0");
					shapeACircumLabel.setText("0");
				}
			}
		});
		shapeACombo.setModel(new DefaultComboBoxModel(new String[] {"", "Ellipse", "Rectangle", "Triangle"}));
		shapeACombo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeACombo.setBounds(110, 11, 100, 22);
		shapeAPanel.add(shapeACombo);
		
		JLabel shapeALabel_1 = new JLabel("Shape A:");
		shapeALabel_1.setForeground(Color.WHITE);
		shapeALabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeALabel_1.setBounds(27, 11, 56, 22);
		shapeAPanel.add(shapeALabel_1);
		
		JLabel shapeAPicture = new JLabel("");
		shapeAPicture.setBounds(82, 55, 75, 75);
		shapeAPanel.add(shapeAPicture);
		
		JPanel shapeBInputs = new JPanel();
		shapeBInputs.setForeground(Color.WHITE);
		shapeBInputs.setBackground(Color.DARK_GRAY);
		shapeBInputs.setBounds(307, 175, 240, 102);
		comparePanel.add(shapeBInputs);
		shapeBInputs.setLayout(shapeBInputCards);
		
		JPanel bBlankInput = new JPanel();
		bBlankInput.setForeground(Color.WHITE);
		bBlankInput.setBackground(Color.DARK_GRAY);
		shapeBInputs.add(bBlankInput, "name_1972612516700");
		shapeBInputCards.addLayoutComponent(bBlankInput,"bBlankInput");
		
		JPanel bTwoInput = new JPanel();
		bTwoInput.setForeground(Color.WHITE);
		bTwoInput.setBackground(Color.DARK_GRAY);
		shapeBInputs.add(bTwoInput, "name_1972780963000");
		bTwoInput.setLayout(null);
		shapeBInputCards.addLayoutComponent(bTwoInput,"bTwoInput");
		
		JLabel bInput1Label = new JLabel("Side a:");
		bInput1Label.setForeground(Color.WHITE);
		bInput1Label.setFont(new Font("Tahoma", Font.BOLD, 12));
		bInput1Label.setBounds(28, 19, 68, 22);
		bTwoInput.add(bInput1Label);
		
		JFormattedTextField shapeBInput1Field = new JFormattedTextField(doubleFormatter);
		shapeBInput1Field.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeBInput1Field.setBounds(124, 19, 86, 22);
		bTwoInput.add(shapeBInput1Field);
		
		JFormattedTextField shapeBInput2Field = new JFormattedTextField(doubleFormatter);
		shapeBInput2Field.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeBInput2Field.setBounds(124, 60, 86, 22);
		bTwoInput.add(shapeBInput2Field);
		
		JLabel bInput2Label = new JLabel("Side b:");
		bInput2Label.setForeground(Color.WHITE);
		bInput2Label.setFont(new Font("Tahoma", Font.BOLD, 12));
		bInput2Label.setBounds(28, 60, 68, 22);
		bTwoInput.add(bInput2Label);
		
		JPanel bTriangleInput = new JPanel();
		bTriangleInput.setForeground(Color.WHITE);
		bTriangleInput.setBackground(Color.DARK_GRAY);
		shapeBInputs.add(bTriangleInput, "name_1972936376900");
		bTriangleInput.setLayout(null);
		shapeBInputCards.addLayoutComponent(bTriangleInput,"bTriangleInput");
		
		JLabel sideALabel_1_1 = new JLabel("Side a:");
		sideALabel_1_1.setForeground(Color.WHITE);
		sideALabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		sideALabel_1_1.setBounds(34, 11, 51, 22);
		bTriangleInput.add(sideALabel_1_1);
		
		JLabel sideCLabel_1_1 = new JLabel("Side c:");
		sideCLabel_1_1.setForeground(Color.WHITE);
		sideCLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		sideCLabel_1_1.setBounds(34, 73, 51, 22);
		bTriangleInput.add(sideCLabel_1_1);
		
		JLabel sideBLabel_1_1 = new JLabel("Side b:");
		sideBLabel_1_1.setForeground(Color.WHITE);
		sideBLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		sideBLabel_1_1.setBounds(34, 42, 51, 22);
		bTriangleInput.add(sideBLabel_1_1);
		
		JFormattedTextField shapeBsideA = new JFormattedTextField(doubleFormatter);
		shapeBsideA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeBsideA.setBounds(119, 11, 86, 22);
		bTriangleInput.add(shapeBsideA);
		
		JFormattedTextField shapeBsideB = new JFormattedTextField(doubleFormatter);
		shapeBsideB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeBsideB.setBounds(119, 42, 86, 22);
		bTriangleInput.add(shapeBsideB);
		
		JFormattedTextField shapeBsideC = new JFormattedTextField(doubleFormatter);
		shapeBsideC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeBsideC.setBounds(119, 73, 86, 22);
		bTriangleInput.add(shapeBsideC);
		
		JPanel shapeBOutputs = new JPanel();
		shapeBOutputs.setForeground(Color.WHITE);
		shapeBOutputs.setBackground(Color.DARK_GRAY);
		shapeBOutputs.setBounds(307, 322, 239, 126);
		comparePanel.add(shapeBOutputs);
		shapeBOutputs.setLayout(null);
		
		JLabel circumLabel_3_1 = new JLabel("Circumference:");
		circumLabel_3_1.setForeground(Color.WHITE);
		circumLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		circumLabel_3_1.setBounds(20, 11, 98, 20);
		shapeBOutputs.add(circumLabel_3_1);
		
		JLabel shapeBCircumLabel = new JLabel("");
		shapeBCircumLabel.setForeground(Color.WHITE);
		shapeBCircumLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeBCircumLabel.setBounds(141, 14, 71, 14);
		shapeBOutputs.add(shapeBCircumLabel);
		
		JLabel shapeBAreaLabel = new JLabel("");
		shapeBAreaLabel.setForeground(Color.WHITE);
		shapeBAreaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeBAreaLabel.setBounds(141, 80, 71, 14);
		shapeBOutputs.add(shapeBAreaLabel);
		
		JLabel areaLabel_3_1 = new JLabel("Area:");
		areaLabel_3_1.setForeground(Color.WHITE);
		areaLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		areaLabel_3_1.setBounds(47, 77, 45, 20);
		shapeBOutputs.add(areaLabel_3_1);

		JComboBox shapeBCombo = new JComboBox();
		shapeBCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selected = shapeBCombo.getSelectedItem().toString();
				if(selected.equals("Ellipse")) {
					shapeBMode = 1;
					bInput1Label.setText("Major Axis:");
					bInput2Label.setText("Minor Axis:");
					shapeBInput1Field.setText(null);
					shapeBInput2Field.setText(null);
					shapeBInputCards.show(shapeBInputs,"bTwoInput");			
				}
				else if(selected.equals("Rectangle")) {
					shapeBMode = 2;
					bInput1Label.setText("Base:");
					bInput2Label.setText("Height:");
					shapeBInput1Field.setText(null);
					shapeBInput2Field.setText(null);
					shapeBInputCards.show(shapeBInputs,"bTwoInput");
					
				}
				else if(selected.equals("Triangle")) {
					shapeBMode = 3;
					
					shapeBInput1Field.setText(null);
					shapeBInput2Field.setText(null);
					shapeBInputCards.show(shapeBInputs,"bTriangleInput");
				}
				else {
					shapeBMode = -1;
					
					shapeBInput1Field.setText(null);
					shapeBInput2Field.setText(null);
					shapeBInputCards.show(shapeBInputs,"bBlankInput");
					shapeBAreaLabel.setText("0");
					shapeBCircumLabel.setText("0");
				}
			}
		});
		shapeBCombo.setModel(new DefaultComboBoxModel(new String[] {"", "Ellipse", "Rectangle", "Triangle"}));
		shapeBCombo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shapeBCombo.setBounds(110, 11, 100, 22);
		shapeBPanel.add(shapeBCombo);		
		
		JButton calculateShapeA = new JButton("Calculate!");
		calculateShapeA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(shapeAMode < 3  && shapeAMode != -1) {
					double[] input = new double[2];
					if(shapeAInput1Field.getText().length() < 1 || shapeAInput1Field.getText().equals(null)) {
						input[0] = 0;
					}
					else {
						input[0] = Math.round(Double.parseDouble(shapeAInput1Field.getText())*100.0)/100.00;
					}
					if(shapeAInput2Field.getText().length() < 1 || shapeAInput2Field.getText().equals(null)) {
						input[1] = 0;
					}
					else {
						input[1] =Math.round(Double.parseDouble(shapeAInput2Field.getText())*100.0)/100.00;
					}
					if(shapeAMode == 1) {
						
						try {
							shapeA = ShapeFactory.Create("Ellipse", input);
							shapeAAreaLabel.setText(String.valueOf((shapeA.getArea()*100.00)/100.00));
							shapeACircumLabel.setText(String.valueOf((shapeA.getCircumference()*100.0/100.00)));
						}
						catch(IllegalArgumentException iae) {
							System.err.println("Invalid axis!");
						}
					}
					else {
						try {
							shapeA = ShapeFactory.Create("Rectangle", input);
							shapeAAreaLabel.setText(String.valueOf(shapeA.getArea()*100.0/100.00));
							shapeACircumLabel.setText(String.valueOf(shapeA.getCircumference()*100.0/100.00));
						}
						catch(IllegalArgumentException iae) {
							System.err.println("Invalid side values!");
						}
					}
					
				}
				else if(shapeAMode == 3) {
					double[] input = new double[3];
					if(shapeAsideA.getText().length() < 1 || shapeAsideA.getText().equals(null)) {
						input[0] = 0;
					}
					else {
						input[0] =Math.round(Double.parseDouble(shapeAsideA.getText())*100.0)/100.00;
					}
					if(shapeAsideB.getText().length() < 1 || shapeAsideB.getText().equals(null)) {
						input[1] = 0;
					}
					else {
						input[1] =Math.round(Double.parseDouble(shapeAsideB.getText())*100.0)/100.00;
					}
					if(shapeAsideC.getText().length() < 1 || shapeAsideC.getText().equals(null)) {
						input[2] = 0;
					}
					else {
						input[2] =Math.round(Double.parseDouble(shapeAsideA.getText())*100.0)/100.00;
					}
					try {
						shapeA = ShapeFactory.Create("Triangle", input);
						shapeAAreaLabel.setText(String.valueOf(Math.round(shapeA.getArea()*100.0)/100.0));
						shapeACircumLabel.setText(String.valueOf(Math.round(shapeA.getCircumference()*100.0)/100.0));
					}
					catch(IllegalArgumentException iae) {
						System.err.println("Invalid side values!");
					}
				}
				else {
					
				}
				
			}
			
		});
		calculateShapeA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		calculateShapeA.setBounds(111, 288, 86, 23);
		comparePanel.add(calculateShapeA);
		
		JButton calculateShapeB = new JButton("Calculate!");
		calculateShapeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(shapeBMode < 3  && shapeBMode != -1) {
					double[] input = new double[2];
					if(shapeBInput1Field.getText().length() < 1 || shapeBInput1Field.getText().equals(null)) {
						input[0] = 0;
					}
					else {
						input[0] = Math.round(Double.parseDouble(shapeBInput1Field.getText())*100.0)/100.00;
					}
					if(shapeBInput2Field.getText().length() < 1 || shapeBInput2Field.getText().equals(null)) {
						input[1] = 0;
					}
					else {
						input[1] =Math.round(Double.parseDouble(shapeBInput2Field.getText())*100.0)/100.00;
					}
					if(shapeBMode == 1) {
						
						try {
							shapeB = ShapeFactory.Create("Ellipse", input);
							shapeBAreaLabel.setText(String.valueOf((shapeB.getArea()*100.00)/100.00));
							shapeBCircumLabel.setText(String.valueOf((shapeB.getCircumference()*100.0/100.00)));
						}
						catch(IllegalArgumentException iae) {
							System.err.println("Invalid axis!");
						}
					}
					else {
						try {
							shapeB = ShapeFactory.Create("Rectangle", input);
							shapeBAreaLabel.setText(String.valueOf(shapeB.getArea()*100.0/100.00));
							shapeBCircumLabel.setText(String.valueOf(shapeB.getCircumference()*100.0/100.00));
						}
						catch(IllegalArgumentException iae) {
							System.err.println("Invalid side values!");
						}
					}
					
				}
				else if(shapeBMode == 3) {
					double[] input = new double[3];
					if(shapeBsideA.getText().length() < 1 || shapeBsideA.getText().equals(null)) {
						input[0] = 0;
					}
					else {
						input[0] =Math.round(Double.parseDouble(shapeBsideA.getText())*100.0)/100.00;
					}
					if(shapeBsideB.getText().length() < 1 || shapeBsideB.getText().equals(null)) {
						input[1] = 0;
					}
					else {
						input[1] =Math.round(Double.parseDouble(shapeBsideB.getText())*100.0)/100.00;
					}
					if(shapeBsideC.getText().length() < 1 || shapeBsideC.getText().equals(null)) {
						input[2] = 0;
					}
					else {
						input[2] =Math.round(Double.parseDouble(shapeBsideA.getText())*100.0)/100.00;
					}
					try {
						shapeB = ShapeFactory.Create("Triangle", input);
						shapeBAreaLabel.setText(String.valueOf(Math.round(shapeB.getArea()*100.0)/100.0));
						shapeBCircumLabel.setText(String.valueOf(Math.round(shapeB.getCircumference()*100.0)/100.0));
					}
					catch(IllegalArgumentException iae) {
						System.err.println("Invalid side values!");
					}
				}
				
			}
		});
		calculateShapeB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		calculateShapeB.setBounds(383, 288, 86, 23);
		comparePanel.add(calculateShapeB);
	}
}
