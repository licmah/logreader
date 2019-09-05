import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Event implements Serializable {
    private String id;
    private long duration;
    private String type;
    private String host;
    private boolean alert;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public String getId() {
        return this.id;
    }

    @Column(name = "DURATION", nullable = false)
    public long getDuration() {
        return this.duration;
    }

    @Column(name = "TYPE", length = 64)
    public String getType() {
        return this.type;
    }

    @Column(name = "HOST", length = 64)
    public String getHost() {
        return this.host;
    }

    @Column(name = "ALERT")
    public boolean getAlert() {return this.alert; }

    protected Event() {}

    public Event( String id, long duration, String type, String host )
    {
        this.id = id;
        this.duration = duration;
        this.type = type;
        this.host = host;
        this.alert = duration > 4;
    }

    public Event( String id, String type, String host )
    {
        this(id,0, type, host);
    }

    public Event( String id, long duration )
    {
        this(id,duration,"","");
    }

    public Event( String id)
    {
        this(id,0,"","");
    }

    @Override
    public String toString() {
        return "id:" + this.id + " d:" + this.duration + " t:" + this.type + " h:" + this.host + " a:" + this.alert;
    }
}
