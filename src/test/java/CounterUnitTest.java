import com.inteliment.springrest.config.AppFilter;
import com.inteliment.springrest.controller.RestCounterController;
import com.inteliment.springrest.model.Counter;
import com.inteliment.springrest.service.CounterService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CounterUnitTest {

    private MockMvc mockMvc;

    @Mock
    private CounterService counterService;

    @InjectMocks
    private RestCounterController restCounterController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(restCounterController)
                .addFilters(new AppFilter())
                .build();
    }
    
    @Test
    public void test_get_counterlist_success() throws Exception {
        
        Counter counter = CounterUnitTest.getCounter();
        
        when(counterService.getCounter()).thenReturn(counter);
        mockMvc.perform(get("/counterlist/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.counters").value(counter.getCounters()));
        verify(counterService, times(1)).getCounter();
        verifyNoMoreInteractions(counterService);
    }
    
    @Test
    public void test_get_by_topn_success() throws Exception {
    	
        Map<String,Integer> outputMap = new LinkedHashMap<>();
        outputMap.put("work",17);
        Counter counter = new Counter(outputMap);
        
        when(counterService.getTopList(1)).thenReturn(counter);

        mockMvc.perform(get("/top/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.counters").value(outputMap));
        
        verify(counterService, times(1)).getTopList(1);
        verifyNoMoreInteractions(counterService);
    }

    @Test
    public void test_get_by_topn_fail_204_not_found() throws Exception {
        when(counterService.getTopList(1)).thenReturn(null);

        mockMvc.perform(get("/top/{id}", 1))
                .andExpect(status().isNoContent());

        verify(counterService, times(1)).getTopList(1);
        verifyNoMoreInteractions(counterService);
    }
    
    public static Counter getCounter(){
    	   Map<String,Integer> mockMap = new LinkedHashMap<>();
           mockMap.put("Doniel",4);
           mockMap.put("ite",14);
           mockMap.put("test",10);
           mockMap.put("work",17);
           Counter counter = new Counter(mockMap);
           return counter;
    }
}