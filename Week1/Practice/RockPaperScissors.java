package Week1.Practice;

public class RockPaperScissors {

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    public static void main(String[] args) {
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};
        String[] computerMoves = {"Scissors", "Paper", "Rock", "Paper", "Scissors"};

        int wins = 0, losses = 0, draws = 0;
        int n = playerMoves.length;

        System.out.println("Round | Player Move | Computer Move | Result");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < n; i++) {
            String result = playRound(playerMoves[i], computerMoves[i]);
            System.out.printf("Round %d | %-11s | %-13s | %s\n", (i + 1), playerMoves[i], computerMoves[i], result);
            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        double winPercentage = ((double) wins / n) * 100;
        System.out.printf("\nFinal Summary (after %d rounds): Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%\n",
                n, wins, losses, draws, winPercentage);
    }
}
