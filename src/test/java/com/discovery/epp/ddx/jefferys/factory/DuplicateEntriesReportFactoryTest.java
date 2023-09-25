package com.discovery.epp.ddx.jefferys.factory;

import com.discovery.epp.ddx.jefferys.domain.DuplicateEntry;
import com.discovery.epp.ddx.jefferys.domain.YamlFile;
import com.discovery.epp.ddx.jefferys.function.PropertiesToMap;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class DuplicateEntriesReportFactoryTest {

    @Test
    void testDuplicateEntriesReportFactory() {

        var values1 = new PropertiesToMap().apply("example1.properties");
        var values2 = new PropertiesToMap().apply("example2.properties");
        var yamlFile1 = new YamlFile("example1.properties", mock(), values1);
        var yamlFile2 = new YamlFile("example2.properties", mock(), values2);
        var underTest = new DuplicateEntriesReportFactory();
        var report = underTest.create(List.of(yamlFile1, yamlFile2));
        Map<String, DuplicateEntry> duplicates = report.duplicates();
        assertThat(duplicates.keySet()).isEqualTo(
                Sets.set("refreshment.breakfast.type", "refreshment.midmorning.type",
                        "refreshment.breakfast.sugar.teaspoons", "refreshment.midmorning.sugar.teaspoons",
                        "refreshment.midmorning.beverage", "refreshment.breakfast.beverage")
        );
        var nonDupes = new HashMap<String,String>();
        nonDupes.put("foo","1");
        nonDupes.put("foo.bar","2");
        assertThat(report.nonDuplicates()).isEqualTo(nonDupes);

        var filenames = Lists.newArrayList("example1.properties","example2.properties");
        assertThat(report.duplicates().get("refreshment.breakfast.type")).isEqualTo(
                new DuplicateEntry("refreshment.breakfast.type", filenames, Lists.newArrayList("green","black"))
        );
        assertThat(report.duplicates().get("refreshment.midmorning.type")).isEqualTo(
                new DuplicateEntry("refreshment.midmorning.type", filenames, Lists.newArrayList("caramel","caramel"))
        );
        assertThat(report.duplicates().get("refreshment.breakfast.sugar.teaspoons")).isEqualTo(
                new DuplicateEntry("refreshment.breakfast.sugar.teaspoons", filenames, Lists.newArrayList("0","2"))
        );
        assertThat(report.duplicates().get("refreshment.midmorning.sugar.teaspoons")).isEqualTo(
                new DuplicateEntry("refreshment.midmorning.sugar.teaspoons", filenames, Lists.newArrayList("1","0"))
        );
        assertThat(report.duplicates().get("refreshment.breakfast.beverage")).isEqualTo(
                new DuplicateEntry("refreshment.breakfast.beverage", filenames, Lists.newArrayList("tea","coffee"))
        );
    }

}
