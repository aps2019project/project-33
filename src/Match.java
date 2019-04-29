


public class Match {

    private Account winner;
    private Account loser;
    private Double time;

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Account getWinner() {
        return winner;
    }

    public void setWinner(Account winner) {
        this.winner = winner;
    }

    public Account getLoser() {
        return loser;
    }

    public void setLoser(Account loser) {
        this.loser = loser;
    }
}