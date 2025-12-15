/**
 * Problem 4: Machine Learning Data Format Compatibility
 * Solution: Adapter Pattern
 * 
 * This demo shows how Adapter bridges the gap between NumPy arrays
 * (from preprocessing) and Pandas DataFrames (expected by ML model).
 *
 * Roles:
 *  - Target: DataFrame interface expected by MLModel
 *  - Adaptee: NumPyArray with a different API
 *  - Adapter: NumPyToDataFrameAdapter converts calls to the adaptee
 *  - Client: MLModel trains using only the target interface
 */

// Target interface - what the ML model expects
interface DataFrame {
    int getRowCount();
    int getColumnCount();
    double getValue(int row, int col);
    void display();
}

// Adaptee - existing preprocessing output (NumPy array)
class NumPyArray {
    private double[][] data;
    
    public NumPyArray(double[][] data) {
        this.data = data;
    }
    
    public int getShape(int dimension) {
        if (dimension == 0) return data.length;
        if (dimension == 1) return data.length > 0 ? data[0].length : 0;
        return 0;
    }
    
    public double getElement(int row, int col) {
        return data[row][col];
    }
    
    public double[][] getData() {
        return data;
    }
}

// Adapter - converts NumPy array to DataFrame interface
class NumPyToDataFrameAdapter implements DataFrame {
    private NumPyArray numpyArray;
    
    public NumPyToDataFrameAdapter(NumPyArray numpyArray) {
        this.numpyArray = numpyArray;
    }
    
    public int getRowCount() {
        return numpyArray.getShape(0);
    }
    
    public int getColumnCount() {
        return numpyArray.getShape(1);
    }
    
    public double getValue(int row, int col) {
        return numpyArray.getElement(row, col);
    }
    
    public void display() {
        System.out.println("DataFrame (adapted from NumPy):");
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                System.out.print(getValue(i, j) + "\t");
            }
            System.out.println();
        }
    }
}

// Client - ML model that expects DataFrame interface
class MLModel {
    public void train(DataFrame trainingData) {
        System.out.println("Training ML model with DataFrame...");
        System.out.println("  Rows: " + trainingData.getRowCount());
        System.out.println("  Columns: " + trainingData.getColumnCount());
        System.out.println("  Sample value: " + trainingData.getValue(0, 0));
        trainingData.display();
        System.out.println("✓ Model trained successfully!");
    }
}

// Preprocessing module (produces NumPy arrays)
class PreprocessingModule {
    public NumPyArray preprocess(double[][] rawData) {
        System.out.println("Preprocessing data...");
        // Simulate preprocessing operations
        return new NumPyArray(rawData);
    }
}

// Demo
public class Problem4Demo {
    public static void main(String[] args) {
        System.out.println("=== Problem 4: ML Data Format Compatibility ===\n");
        
        // Preprocessing produces NumPy array
        PreprocessingModule preprocessor = new PreprocessingModule();
        double[][] rawData = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        };
        NumPyArray numpyData = preprocessor.preprocess(rawData);
        
        // ML model expects DataFrame
        MLModel model = new MLModel();
        
        // Adapter bridges the gap
        System.out.println("\n--- Using Adapter ---");
        DataFrame adaptedData = new NumPyToDataFrameAdapter(numpyData);
        model.train(adaptedData);
        
        System.out.println("\n✓ NumPy array successfully adapted to DataFrame!");
    }
}

