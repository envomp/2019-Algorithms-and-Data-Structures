package ee.ttu.algoritmid.scoreboard;

import java.util.*;

public class ScoreBoard {

	private TreeSet<Participant> scoreBoard = new TreeSet<>((o1, o2) -> {
		if (o1.getTime() != o2.getTime()) {
			return o1.getTime() > o2.getTime() ? 1 : -1;
		}
		if ((o1.getId() != o2.getId())) {
			return o1.getId() > o2.getId() ? 1 : -1;
		}
		return 0;
	});

	/**
	 * Adds a participant's time to the checkpoint scoreboard
	 */
	public void add(Participant participant) {
		scoreBoard.add(participant);
	}

	/**
	 * Returns top n number of participants in the checkpoint to be displayed on the scoreboard
	 * This method will be queried by the tests every time a new participant is added
	 */
	public List<Participant> get(int n) {
		ArrayList<Participant> answer = new ArrayList<>();
		Iterator<Participant> iterator = scoreBoard.iterator();
		for (int i = 0; i < n; i++) {
			if (!iterator.hasNext()) {
				break;
			}
			answer.add(iterator.next());
		}
		return answer;
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		ScoreBoard scoreBoard = new ScoreBoard();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			scoreBoard.add(new Participant(i, "", random.nextInt(100)));
		}
		System.out.println(scoreBoard.get(100));
		System.out.println((double) (System.currentTimeMillis() - start) / 1000);
	}
}

