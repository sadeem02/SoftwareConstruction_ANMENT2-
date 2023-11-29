/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sensordataprocessor;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author sadee
 */
public class Sensordataprocessor {

       // Senson data and limits.  
       //(change 1: make variables private, to limit direct access)
    private double[][][] data; 
    private double[][] limit; 

  
    // constructor (change 2: constructor has to match class name).
    /**
     * Constructor for SensorDataProcessor.
     *
     * @param data   The sensor data.
     * @param limit  The limits for the sensor data.
     */

    public Sensordataprocessor(double[][][] data, double[][] limit) { 
        this.data = data;
        this.limit = limit; 
    }
    
         // calculates average of sensor data
           /**
     * Calculates the average of an array of doubles.
     *
     * @param array The array for which to calculate the average.
     * @return The average value.
     */

    private double calculateAverage(double[] array) {  //(change 3: change method name)        
        double sum = 0;
        for (double value : array) { //(change 4: using for-each loop instead of for loop for better performance and easier to read code).
            sum += value;
     
        }         
       return return sum / array.length;      } 
    
           
    public void calculateRaceStatsData(double raceStatsData) { //(change 5: change method name and parameter)                
        double[][][] data2 = new double[data.length][data[0].length][data[0][0].length];

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("RacingStatsData.txt"))) {//(change 6 : Used the try-with-resources statement) 
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    for (int k = 0; k < data[0][0].length; k++) {
                        data2[i][j][k] = data[i][j][k] / raceStatsData - Math.pow(limit[i][j], 2.0);

                        if (calcAverage(data2[i][j]) > 10 && calcAverage(data2[i][j]) < 50) {
                            break;
                        } else if (Math.max(data[i][j][k], data2[i][j][k]) > data[i][j][k]) {
                            break;
                        } else if (Math.pow(Math.abs(data[i][j][k]), 3) < Math.pow(Math.abs(data2[i][j][k]), 3)
                                && calcAverage(data[i][j]) < data2[i][j][k] && (i + 1) * (j + 1) > 0) {
                            data2[i][j][k] *= 2;
                        } else {
                            continue;
                        }
                    }
                }
            }

            for (int i = 0; i < data2.length; i++) {
                for (int j = 0; j < data2[0].length; j++) {
                    writer.write(arrayToString(data2[i][j]) + "\t");
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());//(change 7 : more informative error message)
        }
    }
          //Helper Method :  to convert an array of doubles to a string representation
          private String arrayToString(double[] array) {
        StringBuilder sb = new StringBuilder();
        for (double value : array) {
            sb.append(value).append("\t");
        }
        return sb.toString().trim();
    }

    
}
