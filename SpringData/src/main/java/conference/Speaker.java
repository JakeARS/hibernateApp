package conference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name = "SPEAKER")
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long speakerId;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "speakerId")
    private Set<Talk> talks;

    public Speaker() {

    }

    public Speaker(String name) {
        this.name = name;
    }

    public void addTalk(Talk talk) {
        if (talks == null) {
            talks = new HashSet<Talk>();
        }
        talks.add(talk);
    }

    public String getName() {
        return name;
    }

    public Set<Talk> getTalks() {
        return talks;
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "name='" + name + '\'' +
                '}';
    }
}
