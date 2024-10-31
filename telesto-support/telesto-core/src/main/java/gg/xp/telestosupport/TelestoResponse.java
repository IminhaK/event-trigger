package gg.xp.telestosupport;

import gg.xp.reevent.events.SystemEvent;
import org.jetbrains.annotations.Nullable;

import java.io.Serial;
import java.util.Collections;
import java.util.Map;

@SystemEvent
public class TelestoResponse extends BaseTelestoResponse {
	@Serial
	private static final long serialVersionUID = 4158223908340512712L;
	private final Map<String, Object> data;
	private final long id;

	public TelestoResponse(@Nullable Map<String, Object> data) {
		if (data == null) {
			this.data = Collections.emptyMap();
			this.id = -1;
		}
		else {
			this.data = data;
			id = Long.parseLong(data.get("id").toString());
		}
	}

	public Map<String, Object> getData() {
		return Collections.unmodifiableMap(data);
	}

	public long getId() {
		return id;
	}

	public Object getResponse() {
		return data.get("response");
	}
}
