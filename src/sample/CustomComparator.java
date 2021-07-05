package sample;

import java.util.Comparator;

public class CustomComparator implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub t1, FootballClub t2) {

        if (t1.getCurrentNumberOfPoints() > t2.getCurrentNumberOfPoints()) {
            return -1;
        }else{
            if(t1.getCurrentNumberOfPoints() < t2.getCurrentNumberOfPoints()){
                return 1;
            }else{
                int goalDif1 = t1.getNumberOfScoredGoals()-t1.getNumberOfReceivedGoals();
                int goalDif2 = t2.getNumberOfScoredGoals()-t2.getNumberOfReceivedGoals();

                if(goalDif1 > goalDif2){
                    return -1;
                }else{
                    if(goalDif1 < goalDif2){
                        return 1;
                    }else{
                        return 0;
                    }
                }
            }
        }
    }
}
