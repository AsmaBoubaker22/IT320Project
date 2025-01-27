/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.it320projet;

/**
 *
 * @author Asma
 */

import java.util.List;

public interface PredictiveModeling extends MachineLearning {
    void linearRegression(List<List<Object>> data, List<Integer> featureColumns, int targetColumnIndex); // For linear regression
}