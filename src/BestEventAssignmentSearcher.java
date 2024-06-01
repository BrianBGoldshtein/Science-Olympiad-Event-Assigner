import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BestEventAssignmentSearcher {
    public static void main(String[] args) throws FileNotFoundException {
        BestEventAssignmentSearcher main = new BestEventAssignmentSearcher(100000);

    }


    public BestEventAssignmentSearcher(int interationNumber) throws FileNotFoundException {
        Assigner bestAssigner = null;
        int lowestScore = Integer.MAX_VALUE;
        for(int i = 0; i < interationNumber; i++) {
            Assigner n = createAssigner("DATA.csv");
            n.generateAssignment();

            if(n.score < lowestScore) bestAssigner = n;
        }

        System.out.println(Arrays.deepToString(bestAssigner.getEventList()));
    }

    public static Assigner createAssigner(String fileName) throws FileNotFoundException {
        ArrayList<Member> members = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));
        Assigner assigner = new Assigner();
        while(scanner.hasNextLine()) {
            String[] personData = scanner.nextLine().split(",");

            for(int i = 0; i < personData.length; i++) {
                personData[i] = personData[i].toLowerCase();
            }

            members.add(new Member(personData[3], new String[] {
                    personData[6], personData[7], personData[8], personData[9], personData[10], personData[11], personData[12], personData[13]
            }, assigner));
        }

        assigner.setMembers((Member[]) members.toArray(new Member[members.size()]));
        return assigner;
    }
}
