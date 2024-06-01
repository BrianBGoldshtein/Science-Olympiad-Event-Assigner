import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Assigner {
    private Member[] members;

    public int score;

    private static Event[] eventList = Event.getEventList().clone();
    public Assigner() {
        for(Event e: eventList) {
            e.setOwner(this);
        }
    }

    public void setMembers(Member[] members) {
        this.members = members;
        for(Member member: members) {
            member.setOwner(this);
        }


    }

    public Member[] shuffle(Member[] array) {
        List<Member> list = Arrays.asList(array);
        Collections.shuffle(list);
        list.toArray(array);
        return array;
    }

    public Event[] generateAssignment() {
        int sum = 0;
        members = shuffle(members);

        assignment: while(Event.availableEventsExist(this)) {
            boolean changeOccured = false;

            for(Member member: members) {
                System.out.println("newMember");
                Event request;
                int loc = 0;

                boolean continueSearch;
                loop: do {
                    System.out.println("that");

                    request = member.getNextRequestedEvent(loc);
                    System.out.println(loc);
                    sum+= loc;
                    if(request == null) break loop;
                    System.out.println(member.getName() + ", " + request.getName());

                    System.out.println(request.getName() + ": " + Arrays.deepToString(request.getCompetitors()));
                    loc++;
                    continueSearch = !request.addMember(member);
                    if(!continueSearch) changeOccured = true;
                } while (continueSearch);

                if(!changeOccured) break assignment;
            }


        }

        events: for(int i = 0; ;i++) {
            for(Event e: eventList) {
                if(!e.hasCapacity()) continue;
                for(Member member: members) {
                    if(member.getPickedEvents().length == i) {
                        e.addMember(member);
                        sum += 20;
                    }
                }
            }

            if(!Event.availableEventsExist(this)) break events;
        }

        score = sum;
        return eventList;
    }

    public Event[] getEventList() {
        return eventList;
    }
}
