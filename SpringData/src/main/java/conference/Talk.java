package conference;

import javax.persistence.*;

@Entity
@Table(name = "TALK")
public class Talk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long talkId;

    private String when;
    private String title;

    /*@ManyToOne
    private Speaker speakerId;*/

    public Talk() {
    }

    public Talk(String title, String when) {
        this.when = when;
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getWhen() {
        return when;
    }
}
