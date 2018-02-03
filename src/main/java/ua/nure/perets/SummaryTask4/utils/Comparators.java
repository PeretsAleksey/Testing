package ua.nure.perets.SummaryTask4.utils;

import ua.nure.perets.SummaryTask4.bean.Test;
import ua.nure.perets.SummaryTask4.bean.UserTest;

import java.io.Serializable;
import java.util.Comparator;

public class Comparators {

    /**
     * Comparator that compares objects by name value
     */

    public static class CompareByName implements Comparator<Test>, Serializable {

        private static final long serialVersionUID = -2382790006724690579L;

        @Override
        public int compare(Test t1, Test t2) {
            return t1.getName().compareTo(t2.getName());
        }

    }

    /**
     * Comparator that compares objects by test id value
     */
    public static class CompareByUsersTestsId implements Comparator<UserTest>, Serializable {

        private static final long serialVersionUID = 8952104900287006111L;

        @Override
        public int compare(UserTest ut1, UserTest ut2) {

            return ut2.getId() - ut1.getId();
        }

    }

    /**
     * Comparator that compares objects by difficulty value
     */
    public static class CompareByDifficulty implements Comparator<Test>, Serializable {

        private static final long serialVersionUID = -2073593354243486823L;

        @Override
        public int compare(Test t1, Test t2) {

            String diff1 = t1.getDifficulty();
            String diff2 = t2.getDifficulty();

            int temp1 = 0;
            int temp2 = 0;

            if (diff1.equals("elementary")) {
                temp1 = 1;
            } else if (diff1.equals("advanced")) {
                temp1 = 2;
            } else if (diff1.equals("proficient")) {
                temp1 = 3;
            }

            if (diff2.equals("elementary")) {
                temp2 = 1;
            } else if (diff2.equals("advanced")) {
                temp2 = 2;
            } else if (diff2.equals("proficient")) {
                temp2 = 3;
            }

            return temp1 - temp2;
        }

    }

    /**
     * Comparator that compares object by question count value
     */

    public static class CompareByQuestionsCount implements Comparator<Test>, Serializable {

        private static final long serialVersionUID = 376967487844228899L;

        @Override
        public int compare(Test t1, Test t2) {
            return t1.getQuestionsCount() - t2.getQuestionsCount();
        }

    }

}
