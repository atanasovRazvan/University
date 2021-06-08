package scs.ubb.map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import scs.ubb.map.utils.SemesterStructure;

import java.time.LocalDate;
import java.time.Month;

@Ignore
@RunWith(JUnit4.class)
public class SemesterStructureTest {
    private SemesterStructure semesterStructure;

    @Before
    public void before() {
        semesterStructure = new SemesterStructure(LocalDate.of(2019, Month.SEPTEMBER, 30),
                LocalDate.of(2020, Month.JANUARY, 17),
                LocalDate.of(2019, Month.DECEMBER, 19),
                LocalDate.of(2020, Month.JANUARY, 5));
    }

}
