package planningEntryAPIs;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import planningEntry.FlightEntry;
import planningEntry.PlanningEntry;
import planningEntryCollection.FlightCollection;
import resource.Flight;
import resource.Resource;

public class FindPreEntryPerResourceTest {

	/**
	 * 测试findPreEntryPerResource方法
	 * 测试策略：
	 * 无前序计划，有前序计划
	 * @throws IOException
	 */
	@Test
	public void testFindPreEntryPerResource() throws IOException {
		PreEntryPerResourceAPI pra = new FindPreEntryPerResource(); 
		FlightCollection fc = new FlightCollection("test/txt/test_1.txt");
		List<FlightEntry> lf = fc.getFlightEntry();
		PlanningEntry pe1 = null;
		PlanningEntry pe2 = null;
		List<PlanningEntry> lp = new ArrayList<PlanningEntry>();
		for(FlightEntry fe:lf) {
			if(fe.getEntryName().equals("AA72")&fe.getStartAndEndTime().getStartTime().equals("2020-05-01 03:15"))
				pe1 = fe;
			if(fe.getEntryName().equals("SC30")&fe.getStartAndEndTime().getStartTime().equals("2020-05-01 13:25"))
				pe2 = fe;
			lp.add(fe);
		}
		Set<Flight> lr = fc.getResources();
		Resource re = null;
		for(Flight flight :lr) {
			if(flight.getNumbering().equals("B6863"))
				re = flight;
		}
		assertEquals(null,pra.findPreEntryPerResource(re,pe1,lp));
		assertEquals(pe1,pra.findPreEntryPerResource(re,pe2,lp));
	}

}
