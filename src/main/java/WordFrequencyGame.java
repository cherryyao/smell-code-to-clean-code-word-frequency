import java.util.*;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String getResult(String inputStr) {

        if (inputStr.split("\\s+").length == 1) {
            return inputStr + " 1";
        } else {

            //split the input string with 1 to n pieces of spaces
            String[] arr = inputStr.split("\\s+");

            List<Input> inputList = getInputs(arr);

            //get the map for the next step of sizing the same word
            List<Word> ModelList = sizing_the_same_word(inputList);
            //inputList = list;

            ModelList.sort((w1, w2) -> w2.getCount() - w1.getCount());

            StringJoiner joiner = new StringJoiner("\n");
            for (Word w : ModelList) {
                String s = w.getValue() + " " + w.getCount();
                joiner.add(s);
            }
            return joiner.toString();

        }
    }

    private List<Word> sizing_the_same_word(List<Input> inputList) {
        Map<String, List<Input>> map = getListMap(inputList);

        List<Word> ModelList = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
            Word input = new Word(entry.getKey(), entry.getValue().size());
            ModelList.add(input);
        }
        return ModelList;
    }

    private List<Input> getInputs(String[] arr) {
        List<Input> inputList = new ArrayList<>();
        for (String s : arr) {
            Input input = new Input(s);
            inputList.add(input);
        }
        return inputList;
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
