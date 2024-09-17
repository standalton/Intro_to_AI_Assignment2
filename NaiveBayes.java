import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NaiveBayes {
    private Map<String, Integer> partyCounts = new HashMap<>();
    private Map<String, Map<String, Integer>> answerCounts = new HashMap<>();
    private int totalResponses = 0;

    public void train(List<String[]> responses) {
        for (String[] response : responses) {
            if (response.length < 5) {
                continue;
            }
            String party = response[response.length - 1];
            partyCounts.put(party, partyCounts.getOrDefault(party, 0) + 1);
            totalResponses++;

            for (int i = 0; i < response.length - 1; i++) {
                String answer = response[i];
                answerCounts.putIfAbsent(answer, new HashMap<>());
                Map<String, Integer> partyMap = answerCounts.get(answer);
                partyMap.put(party, partyMap.getOrDefault(party, 0) + 1);
            }
        }
    }

    public String predict(String[] userAnswers) {
        double maxProbability = -1.0;
        String predictedParty = null;

        for (String party : partyCounts.keySet()) {
            double probability = calculatePartyProbability(party, userAnswers);
            if (probability > maxProbability) {
                maxProbability = probability;
                predictedParty = party;
            }
        }

        return predictedParty;
    }

    private double calculatePartyProbability(String party, String[] userAnswers) {
        double probability = (double) partyCounts.get(party) / totalResponses;

        for (String answer : userAnswers) {
            Map<String, Integer> partyMap = answerCounts.get(answer);
            if (partyMap != null && partyMap.containsKey(party)) {
                int countForParty = partyMap.get(party);
                probability *= (double) countForParty / partyCounts.get(party);
            } else {
                probability *= 0.01;
            }
        }

        return probability;
    }
}
