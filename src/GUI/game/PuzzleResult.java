package GUI.game;


import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PuzzleResult {

    private PuzzlePositionHelper puzzlesHelper = new PuzzlePositionHelper();

    //get fully correct result of puzzles
    public ArrayList<Integer> getResult(ArrayList<BufferedImage> currentPuzzleImages) {
        ArrayList<Integer> result;
        ArrayList<Integer> leftSidePuzzles;
        ArrayList<Integer> rightSidePuzzles;
        ArrayList<Integer> middleVerticalSide;

        ArrayList<Integer> partOfResult;
        ArrayList<Integer> puzzles;
        leftSidePuzzles = puzzlesHelper.getLeftSidePuzzles(currentPuzzleImages);
        rightSidePuzzles = puzzlesHelper.getRightSidePuzzles(currentPuzzleImages);
        middleVerticalSide = puzzlesHelper.getMiddleVerticalPuzzles(currentPuzzleImages);

        puzzles = puzzlesHelper.getTopSidePuzzles(currentPuzzleImages);
        result = getCorrectPuzzles(puzzles, leftSidePuzzles, rightSidePuzzles, middleVerticalSide);
        if (result.size() == 3) {
            puzzles = puzzlesHelper.getMiddleHorizontalPuzzles(currentPuzzleImages);
            partOfResult = getCorrectPuzzles(puzzles, leftSidePuzzles, rightSidePuzzles, middleVerticalSide);
            result.addAll(partOfResult);
            if (result.size() >= 5) {
                puzzles = puzzlesHelper.getBottomSidePuzzles(currentPuzzleImages);
                partOfResult = getCorrectPuzzles(puzzles, leftSidePuzzles, rightSidePuzzles, middleVerticalSide);

                result.addAll(partOfResult);
            }
        }
        return result;
    }

    //get correct positions of puzzles by 3 different parts of arrays(top/bottom/middle)
    public ArrayList<Integer> getCorrectPuzzles(ArrayList<Integer> puzzlesIndexes, ArrayList<Integer> leftSidePuzzles,
                                                ArrayList<Integer> rightSidePuzzles, ArrayList<Integer> middleVerticalSide) {
        ArrayList<Integer> correctPuzzlesList = new ArrayList<>();
        int leftSideIdx = 0;
        int middleSideIdx = 0;
        int rightSideIdx = 0;
        for (int i = 0; i < puzzlesIndexes.size(); i++) {
            for (int j = 0; j < leftSidePuzzles.size(); j++) {
                if (puzzlesIndexes.get(i).equals(leftSidePuzzles.get(j))) {
                    leftSideIdx = puzzlesIndexes.get(i);

                }
            }
            for (int j = 0; j < middleVerticalSide.size(); j++) {
                if (puzzlesIndexes.get(i).equals(middleVerticalSide.get(j))) {
                    middleSideIdx = puzzlesIndexes.get(i);
                }
            }
            if (puzzlesIndexes.size() != 2) {
                for (int j = 0; j < rightSidePuzzles.size(); j++) {
                    if (puzzlesIndexes.get(i).equals(rightSidePuzzles.get(j))) {
                        rightSideIdx = puzzlesIndexes.get(i);

                    }
                }
            } else {
                rightSideIdx = 404;
            }
        }
        correctPuzzlesList.add(leftSideIdx);
        correctPuzzlesList.add(middleSideIdx);
        if (rightSideIdx != 404) {
            correctPuzzlesList.add(rightSideIdx);
        }
        return correctPuzzlesList;
    }


}
