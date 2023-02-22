package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapTest {
    Map m1;
    Map m2;

    @BeforeEach
    void setup() {
        m1 = new OceanMap(12);
        m2 = new RadarMap(10);
    }




}
