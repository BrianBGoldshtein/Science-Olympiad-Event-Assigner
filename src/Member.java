import java.util.ArrayList;

public class Member {
    private String name;
    private ArrayList<Event> requestedEvents;

    private Assigner owner;

    public Member(String name, String[] stringRequests, Assigner owner) {
        this.name = name;

        requestedEvents = new ArrayList<>();
        requests: for(String stringEvent: stringRequests) {
            for(Event event: owner.getEventList()) {
                if(event.getName().equalsIgnoreCase(stringEvent)) {
                    requestedEvents.add(event);
                    continue requests;
                }

            }
            while(true) {
                System.out.println(getName());
                System.out.println(stringEvent);
            }
        }
    }

    public String toString() {
        return this.name;
    }

    public void setOwner(Assigner owner) {
        this.owner = owner;
    }

    public Event[] getPickedEvents() {
        ArrayList<Event> events = new ArrayList<>();
        for(Event e: owner.getEventList()) {
            if(e.hasCompetitor(this)) events.add(e);
        }

        return (Event[]) events.toArray(new Event[events.size()]);
    }

    public Event getNextRequestedEvent(int loc) {

        if(loc >= 8) return null;
        return this.requestedEvents.get(loc);
    }
    public String getName() {
        return name;
    }



    public ArrayList<Event> getRequests() {
        return requestedEvents;
    }
}
