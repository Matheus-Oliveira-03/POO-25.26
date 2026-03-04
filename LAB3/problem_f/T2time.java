public class T2time {
    public static final int T2DAYSECONDS = 13 * 3600 + 27 * 60 + 16; // =48436 sec/day
    public static final String TIMEFORMAT = "[0-9]{2}:[0-9]{2}:[0-9]{2}";
    public static final String SECONDSFORMAT = "[0-9]+";
    private int seconds = 0;

    public T2time(int seconds) {
        this.seconds = seconds;
        checkInvariant();
    }

    public T2time(int hours, int minutes, int seconds) {
        this(toSeconds(hours, minutes, seconds));
    }

    public T2time(String str) {
        this(toSeconds(str));
    }

    public int asSeconds() {
        return this.seconds;
    }

    public T2time add(T2time arg) {
        int sum = (this.seconds + arg.asSeconds()) % T2DAYSECONDS;
        return new T2time(sum);
    }

    public String toString() {
        int hours = this.seconds / 3600;
        int minutes = (this.seconds % 3600) / 60;
        int seconds = (this.seconds % 3600) % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static boolean isTime(String str) {
        return checkBounds(toSeconds(str));
    }

    private static int toSeconds(String str) {
        int sec;
        if (str == null) {
            sec = -1;
        } else if (str.matches(TIMEFORMAT)) {
            String[] split = str.split(":");
            int h = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);
            int s = Integer.parseInt(split[2]);

            sec = toSeconds(h, m, s);
        } else if (str.matches(SECONDSFORMAT)) {
            int s = Integer.parseInt(str);
            sec = checkBounds(s) ? s : -1;
        } else {
            sec = -1;
        }

        return sec;
    }

    private static int toSeconds(int hours, int minutes, int seconds) {
        int sum = hours * 3600 + minutes * 60 + seconds;
        return checkBounds(sum) ? sum : -1;
    }

    private static boolean checkBounds(int seconds) {
        if (0 <= seconds && seconds < T2DAYSECONDS)
            return true;
        else
            return false;
    }

    private void checkInvariant() {
        if (checkBounds(this.seconds))
            return;

        throw new IllegalArgumentException("0 <= seconds < " + T2DAYSECONDS);
    }
}