package gg.xp.xivsupport.events.triggers.marks.adv;

import gg.xp.xivdata.data.*;
import gg.xp.xivsupport.events.triggers.marks.AutoMarkLanguage;
import gg.xp.xivsupport.gui.util.HasFriendlyName;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Locale;

public enum MarkerSign implements HasFriendlyName, HasOptionalIconURL {
	/*
		Per-language commands:

		English:
			attack1-5
			bind1-3
			ignore1-2
			circle
			cross
			square
			triangle
			off/clear

		Japanese:
			ignore -> stop

		German:
			ignore -> ignor

		French:
			no difference from English

		Korean: ?

		Chinese: ?
	 */

	ATTACK_NEXT("Next Attack", "attack"),
	ATTACK1("Attack 1", "attack1", ATTACK_NEXT, 61201),
	ATTACK2("Attack 2", "attack2", ATTACK_NEXT, 61202),
	ATTACK3("Attack 3", "attack3", ATTACK_NEXT, 61203),
	ATTACK4("Attack 4", "attack4", ATTACK_NEXT, 61204),
	ATTACK5("Attack 5", "attack5", ATTACK_NEXT, 61205),
	ATTACK6("Attack 6", "attack6", ATTACK_NEXT, 61206),
	ATTACK7("Attack 7", "attack7", ATTACK_NEXT, 61207),
	ATTACK8("Attack 8", "attack8", ATTACK_NEXT, 61208),

	BIND_NEXT("Next Bind", "bind"),
	BIND1("Bind 1", "bind1", BIND_NEXT, 61211),
	BIND2("Bind 2", "bind2", BIND_NEXT, 61212),
	BIND3("Bind 3", "bind3", BIND_NEXT, 61213),

	IGNORE_NEXT("Next Ignore", "ignore") {
		@Override
		public String getKoreanCommand() {
			return "stop";
		}

		@Override
		public String getGermanCommand() {
			return "ignor";
		}
	},
	IGNORE1("Ignore 1", "ignore1", IGNORE_NEXT, 61221) {
		@Override
		public String getKoreanCommand() {
			return "stop1";
		}

		@Override
		public String getGermanCommand() {
			return "ignor1";
		}
	},
	IGNORE2("Ignore 2", "ignore2", IGNORE_NEXT, 61222) {
		@Override
		public String getKoreanCommand() {
			return "stop2";
		}

		@Override
		public String getGermanCommand() {
			return "ignor2";
		}
	},

	CIRCLE("Circle", "circle", 61232),
	CROSS("Cross", "cross", 61233),
	SQUARE("Square", "square", 61231),
	TRIANGLE("Triangle", "triangle", 61234),

	CLEAR("Clear Marker", "clear");

	private final String desc;
	private final String command;
	private final MarkerSign base;
	private final @Nullable HasIconURL iconUrl;

	MarkerSign(String desc, String command) {
		this.desc = desc;
		this.command = command;
		this.base = this;
		iconUrl = null;
	}

	MarkerSign(String desc, String command, int icon) {
		this.desc = desc;
		this.command = command;
		this.base = this;
		iconUrl = makeIcon(icon);
	}

	MarkerSign(String desc, String command, MarkerSign base, int icon) {
		this.desc = desc;
		this.command = command;
		this.base = base;
		iconUrl = makeIcon(icon);
	}

	private static @Nullable HasIconURL makeIcon(@Nullable Integer icon) {
		if (icon == null) {
			return null;
		}
		return IconUtils.makeIcon(icon);
	}

	public static MarkerSign of(String s) {
		MarkerSign result = ofNullable(s);
		if (result == null) {
			throw new IllegalArgumentException("Not a valid marker: " + s);
		}
		return result;
	}

	public static MarkerSign ofNullable(String s) {
		try {
			return valueOf(s.trim().toUpperCase(Locale.ROOT));
		}
		catch (IllegalArgumentException e) {
			return Arrays.stream(values())
					.filter(value -> value.getCommand().equalsIgnoreCase(s))
					.findAny()
					.orElse(null);
		}
	}

	public static MarkerSign fromId(int id) {
		return switch (id) {
			case 0 -> ATTACK1;
			case 1 -> ATTACK2;
			case 2 -> ATTACK3;
			case 3 -> ATTACK4;
			case 4 -> ATTACK5;
			case 5 -> BIND1;
			case 6 -> BIND2;
			case 7 -> BIND3;
			case 8 -> IGNORE1;
			case 9 -> IGNORE2;
			case 10 -> SQUARE;
			case 11 -> CIRCLE;
			case 12 -> CROSS;
			case 13 -> TRIANGLE;
			default -> throw new IllegalArgumentException("Unrecognized marker sign: " + id);
		};
	}

	public String getCommand() {
		return command;
	}

	public String getKoreanCommand() {
		return command;
	}

	public String getGermanCommand() {
		return command;
	}

	public String getCommand(AutoMarkLanguage language) {
		return switch (language) {
			// Automatic shouldn't make it to this point, whatever
			case EN, Automatic -> getCommand();
			case JP -> getKoreanCommand();
			case DE -> getGermanCommand();
		};
	}

	public MarkerSign getBase() {
		return base;
	}

	@Override
	public @Nullable HasIconURL getIconUrl() {
		return iconUrl;
	}

	@Override
	public String getFriendlyName() {
		return desc;
	}
}
