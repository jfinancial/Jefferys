package com.discovery.epp.ddx.jefferys.function;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class PropertiesToMapTest {

    @Test
    void testPropertiesToMap(){
        var result = new PropertiesToMap().apply("example1.properties");
        var expected = new HashMap<String,String>();
        expected.put("refreshment.breakfast.type","green");
        expected.put("refreshment.breakfast.beverage","tea");
        expected.put("refreshment.breakfast.sugar.teaspoons","0");
        expected.put("refreshment.midmorning.type","caramel");
        expected.put("refreshment.midmorning.beverage","latte");
        expected.put("refreshment.midmorning.sugar.teaspoons","1");
        assertThat(result).isEqualTo(expected);
    }
}
