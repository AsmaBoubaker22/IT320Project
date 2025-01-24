package com.mycompany.oop;

import java.util.List;

public interface DataCleaning {
    void removeMissingValues(List<List<Object>> data);
    void removeDuplicates(List<List<Object>> data);
    void removeOutliers(List<List<Object>> data);
    void fixYear(List<List<Object>> data);
}
