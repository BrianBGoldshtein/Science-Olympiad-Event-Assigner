public class Event {
    private String name;
    private Member[] competitors;

    private Assigner owner;

    public static Event nullEvent = new Event("nullEvent", 0);

    private static Event[] eventList = generateEventList();
    public Event(String name, int capacity) {
        this.name = name.toLowerCase();
        competitors = new Member[capacity];
    }

    public void setOwner(Assigner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        String out = getName() + " (";
        for(Member m: competitors) {
            if(m == null) continue;
            out += m.getName() + ", ";
        }
        out += ")\n";
        return out;
    }

    public Member[] getCompetitors(){
        return competitors;
    }

    public boolean hasCompetitor(Member member) {
        for (Member competitor: competitors) {
            if(competitor == null) continue;
            if(member.getName().equals(competitor.getName())) return true;
        }
        return false;
    }

    public boolean addMember(Member member) {

        if(this.equals(nullEvent)) return true;

        for(Event e: member.getPickedEvents()) {
            if(e.getName().equalsIgnoreCase((this.getName()))) return false;
        }

        //return true if successful, return false if not possible
        for(int slot = 0; slot < competitors.length; slot++) {
            if(competitors[slot] != null) {
                continue;
            }
            competitors[slot] = member;
            return true;
        }
        return false;
    }

    public static Event[] getEventList() {
        return eventList;
    }
    private static Event[] generateEventList() {
        Event[] events = new Event[23];
        events[0] = new Event("AIR TRAJECTORY", 2);
        events[1] = new Event("ANATOMY AND PHYSIOLOGY", 2);
        events[2] = new Event("can't judge a powder", 2);
        events[3] = new Event("CODEBUSTERS", 3);
        events[4] = new Event("CRIME BUSTERS", 2);
        events[5] = new Event("DISEASE DETECTIVES", 2);
        events[6] = new Event("DYNAMIC PLANET", 2);
        events[7] = new Event("ECOLOGY", 2);
        events[8] = new Event("EXPERIMENTAL DESIGN", 3);
        events[9] = new Event("FAST FACTS", 2);
        events[10] = new Event("FLIGHT", 2);
        events[11] = new Event("FORESTRY", 2);
        events[12] = new Event("FOSSILS", 2);
        events[13] = new Event("METEOROLOGY", 2);
        events[14] = new Event("MICROBE MISSION", 2);
        events[15] = new Event("OPTICS", 2);
        events[16] = new Event("REACH FOR THE STARS", 2);
        events[17] = new Event("ROAD SCHOLAR", 2);
        events[18] = new Event("ROLLER COASTER", 2);
        events[19] = new Event("TOWER", 2);
        events[20] = new Event("WHEELED VEHICLE", 2);
        events[21] = new Event("WIND POWER", 2);
        events[22] = new Event("WRITE IT DO IT", 2);

        for(Event e: events) {
            e.name = e.getName().toLowerCase();
        }
        return events;
    }

    public static boolean availableEventsExist(Assigner owner) {
        for(Event e: owner.getEventList()) {

            if(e.hasCapacity()) {
                System.out.println(e);
                return true;
            }
        }
        return false;
    }

    public boolean hasCapacity() {
        for(Member slot: competitors) {
            if(slot == null) return true;
        }
        return false;
    }
}
