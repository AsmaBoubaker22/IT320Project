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

public interface DataCleaning {
    void removeMissingValues(List<List<String>> data);
    void removeDuplicates(List<List<String>> data);
    void removeOutliers(List<List<String>> data);
    void fixYear(List<List<String>> data);
}