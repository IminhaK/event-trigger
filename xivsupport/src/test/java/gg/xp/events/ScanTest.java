package gg.xp.events;

import gg.xp.scan.AutoHandlerScan;
import gg.xp.speech.TtsCall;
import org.testng.annotations.Test;

public class ScanTest {
	@Test
	public void testAutoScan() {
		EventDistributor<Event> dist = AutoHandlerScan.create();
		TestEventCollector collector = new TestEventCollector();
		dist.registerHandler(collector);
		// Send events
		dist.acceptEvent(new ACTLogLineEvent("21|2021-09-30T19:43:43.1650000-07:00|40016AA1|Titan|2B6C|Rock Throw|106D41EA|Some Player|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|42489|50128|9900|10000|0|1000|86.77625|95.90898|-4.091016E-13|1.591002|2477238|4476950|0|10000|0|1000|113.7886|86.21142|-1.378858E-12|-0.7854581|00009CA2|0|cd69a51d5f584b836fa20c4a5b356612"));
//		Assert.assertEquals(collector.getEvents().size(), 2);
		dist.acceptEvent(new ACTLogLineEvent("21|2021-09-30T19:43:43.1650000-07:00|40016AA1|Titan|2B6C|Rock Throw|106D41EA|Other Player|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|42489|50128|9900|10000|0|1000|86.77625|95.90898|-4.091016E-13|1.591002|2477238|4476950|0|10000|0|1000|113.7886|86.21142|-1.378858E-12|-0.7854581|00009CA2|0|cd69a51d5f584b836fa20c4a5b356612"));
//		Assert.assertEquals(collector.getEvents().size(), 4);
		dist.acceptEvent(new ACTLogLineEvent("21|2021-09-30T19:43:43.1650000-07:00|40016AA1|Titan|2B6C|Rock Throw|106D41EA|Third Player|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|42489|50128|9900|10000|0|1000|86.77625|95.90898|-4.091016E-13|1.591002|2477238|4476950|0|10000|0|1000|113.7886|86.21142|-1.378858E-12|-0.7854581|00009CA2|0|cd69a51d5f584b836fa20c4a5b356612"));

		collector.getEvents();
	}

	@Test
	public void testTTS() {
		EventDistributor<Event> dist = AutoHandlerScan.create();
		TestEventCollector collector = new TestEventCollector();
		dist.registerHandler(collector);
		dist.acceptEvent(new TtsCall("Foo Bar"));

		collector.getEvents();
	}
}
