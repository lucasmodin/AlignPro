package alignpro.Service;


import alignpro.Repository.AlignProRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")

public class AlignProServiceTest {

    @Autowired
    AlignProRepository alignProRepository;


    //*** this is different test there actually test if I get the right information from
    //*** repository and it returns expected values.
    //Together they will ensure that DashBoardDTO is working currectly.
    @Test
    void testgetProjectsForPMUser(){

        List<Integer> list = alignProRepository.pmUserProjectID(1);

        assertTrue(list.contains(1));
        assertTrue(list.contains(3));

    }

    @Test
    void testprojectNamesToSubprojectandTask(){

        List<Integer> list = alignProRepository.pmUserProjectID(1);

        Map<String,String> stuffUnderPM = new HashMap<>();

        for(int pmProjectsInt : list){
            stuffUnderPM.putAll(alignProRepository.projectNamesToSubprojectandTask(pmProjectsInt));
        }

        assertEquals(stuffUnderPM.get("RandomTask 42"),"Project Kea");
        assertEquals(stuffUnderPM.get("Scipting all the time"),"SQL Script Project");
        assertEquals(stuffUnderPM.get("Finish SQL Scripts"),"Project Kea");
    }
}
