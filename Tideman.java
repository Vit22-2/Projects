import java.util.Scanner;


public class Tideman {
    private static final Scanner scanner = new Scanner(System.in);

    // Max number of candidate
    private static final int MAX = 3;

    // preferences[i][j] is number of voters who prefer i over j
    private static final int[][] preferences = new int[MAX][MAX];

    // locked[i][j] means i is locked in over j
    private static final boolean[][] locked = new boolean[MAX][MAX];

    // Each pair has a winner and loser
    static class pair {
        int winner;
        int loser;
    }
    // Array of candidates
    private static pair[] pairs = new pair[MAX * (MAX - 1) / 2];
    private static final String[] candidates = new String[MAX];

    private static int pair_count = 0;
    private static int candidate_count = 0;

    public static void main(String[] args){
        do {
            System.out.print("\n----------Tideman Voting System----------\n1. Add Candidates\n2. Show Candidate\n3. Vote\n4. Result\n5. Exit\n");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1: add_candidate(); break;
                case 2: show_candidate(); break;
                case 3: voting(); break;
                case 4: print_winner(); break;
                case 5: System.out.print("Exiting program..."); return;
                default: System.out.println("Invalid choice. Please Try Again!!\n");
            }
        } while (true);
    }

    // Add candidate
    public static void add_candidate(){
        System.out.println("You can enter multiple candidate name at once!" +
                "\nExample \"Enter candidate name: Sean Sakphea Vith\"");
        System.out.print("Enter candidate name: ");
        String name = scanner.nextLine();

        if(candidate_count < MAX){
            String[] new_candidates = name.split(" ");
            if(candidate_count + new_candidates.length <= MAX){
                for(int i = 0; i < new_candidates.length; i++){
                    candidates[candidate_count] = new_candidates[i];
                    candidate_count++;
                }
            }
            else{
                System.out.printf("Error: Adding these candidates would exceed the maximum limit of %d\n", MAX);
            }
        }
        else{
            System.out.print("Error: Candidate list is full");
            System.out.printf("Maximum number of candidates is %d\n", MAX);
        }
    }

    // Display all candidate
    public static void show_candidate(){
        System.out.println("------------List of Candidates------------");
        for(int i = 0; i < candidate_count; i++){
            System.out.printf("%d. %s\n",i+1,candidates[i]);
        }
    }

    // Prompt users to vote
    public static void voting(){
        System.out.println("------------Vote your preference------------");
        System.out.println("Candidates: ");

        // ranks[i] is voter's ith preference
        int[] ranks = new int[candidate_count];

        // Show list of candidates
        for(int i = 0; i < candidate_count; i++)
        {
            System.out.printf("%s | ", candidates[i]);
        }

        System.out.println();

        // Vote your preferences
            for (int i = 0; i < candidate_count; i++) {
                System.out.printf("Rank %d: ", i + 1);
                String name = scanner.nextLine();

                if (!vote(i, name, ranks)) {
                    System.out.println("Invalid Vote!");
                    return;
                }
            }
        record_preference(ranks);
        add_pair();
        sort_pair();
        locked_pair();
    }

    // Update ranks given a new vote
    public static boolean vote(int rank, String name, int[] ranks){

        // Check all candidates
        for(int i = 0; i < candidate_count; i++){
            if(name.equalsIgnoreCase(candidates[i])){

                // Store candidate index in ranks at rank position
                ranks[rank]= i;
                return true;
            }
        }
        return false;
    }

    // Update preferences given one voter's ranks
    public static void record_preference(int[] ranks){

        // Check all candidate
        for(int i = 0; i < candidate_count - 1; i++){
            for(int j = 0; j < candidate_count; j++){
                if(i<j){
                    // Increment the preferences in which a candidate is preferred over the other
                    preferences[ranks[i]][ranks[j]]++;
                }
            }
        }
    }

    // Record pairs of candidates where one is preferred over the other
    public static void add_pair(){
        // Check all candidates
        for(int i = 0; i < candidate_count; i++){
            for(int j = 0; j < candidate_count; j++){

                // Check if a candidate i is preferred over j
                if(preferences[i][j] > preferences[j][i]){
                    pairs[pair_count] = new pair();
                    // Record winners/losers of the pairs
                    pairs[pair_count].winner = i;
                    pairs[pair_count].loser = j;
                    pair_count++;
                }
            }
        }
    }

    // Sort pairs in decreasing order by strength of victory
    public static void sort_pair(){
        // Check all candidates
        for(int i = 0; i < pair_count; i++){
            for (int j = i + 1; j < pair_count; j++){
                int strength_i = preferences[pairs[i].winner][pairs[i].loser];
                int strength_j = preferences[pairs[j].winner][pairs[j].loser];

                // Compare the strength of Victory
                if(strength_i < strength_j){
                    pair temp = pairs[i];
                    pairs[i] = pairs[j];
                    pairs[j] = temp;
                }
            }
        }
    }

    // Lock pairs into the candidate graph in order, without creating cycles
    public static void locked_pair(){
        for(int i = 0; i < pair_count; i++){
            for(int j = 0; j < pair_count; j++){
                if(!check_cycle()){
                    locked[i][j] = true;
                }
            }
        }
    }

    // Check if cycle is created
    public static boolean check_cycle(){
        return false;
    }

    // Print the winner of the election
    public static void print_winner(){
        // Check all candidates
        for(int i = 0; i < candidate_count; i++){
            boolean is_source = true;
            // Check all edge
            for(int j = 0; j < candidate_count; j++){
                if(locked[j][i]){
                    is_source = false;
                    break;
                }
            }
            if(is_source){
                // Printing the winner
                System.out.printf("Winner is %s", candidates[i]);
                System.out.println();
                return;
            }
        }
    }
}
