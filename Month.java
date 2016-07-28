enum Month {
    January("January", 0, 31),
    February("February", 3, 28),
    March("March", 3, 31),
    April("April", 6, 30),
    May("May", 1, 31),
    June("June", 4, 30),
    July("July", 6, 31),
    August("August", 2, 31),
    September("September", 5, 30),
    October("October", 0, 31),
    November("November", 3, 30),
    December("December", 5, 31);

    int relativeStartDate;
    int numDays;
    final String name;

    Month(String name, int start, int numDays) {
        this.relativeStartDate = start;
        this.name = name;
        this.numDays = numDays;
    }

    public static Month getValue(String month) {
        for(Month m : values()) {
            if(m.name.equalsIgnoreCase(month)) {
                return m;
            }
        }

        return null;
    }

    public int getAbsoluteStartDate(int yearStart) {
        return relativeStartDate + yearStart;
    }
}