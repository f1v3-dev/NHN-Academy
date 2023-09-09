import com.nhnacademy.java.poker.Card;
import com.nhnacademy.java.poker.Rank;
import com.nhnacademy.java.date.Date;
import com.nhnacademy.java.date.DaysInWeek;
import com.nhnacademy.java.poker.Suit;

public class App {
    public static void main(String[] args) throws Exception {
        Date d = new Date(1999, 5, 13, DaysInWeek.THU);
        System.out.println(d);

        Card c = new Card(Suit.DIAMOND, Rank.ACE);
        System.out.println(c);
    }
}
